package com.mazing.game;

public class StopWatch extends Thread {

  private final long secondsNeeded;
  private long start;
  private boolean isTimerOn;

  public void startCounting() {
    start = System.currentTimeMillis();
    isTimerOn = true;
  }

  public StopWatch(long secondsNeeded) {
    isTimerOn = false;
    this.secondsNeeded = secondsNeeded;
  }

  public String getElapsedSecondsString(){
    return getTimeString(getElapsedSeconds());
  }

  public String getTimeString(long seconds) {
    return seconds / 60 + ":" + seconds % 60;
  }

  public long getElapsedSeconds() {
    long now = System.currentTimeMillis();
    return (now - start) / 1000;
  }

  public long getRemainingSeconds() {
    return secondsNeeded - getElapsedSeconds();
  }

  public void stopTimer() {
    isTimerOn = false;
  }

  public String checkTime() {
    if (isTimerOn) {
      return "Elapsed: " + getTimeString(getElapsedSeconds()) + ", Remaining: " + getTimeString(
          getRemainingSeconds());
    }
    return "You've won the game, the timer decided to take a break.";
  }

  @Override
  public void run() {
    startCounting();
    while (getRemainingSeconds() >= 0 && isTimerOn) {
    }
    if (isTimerOn) {
      MessagePrinter.printTimeElapsedMessage();
      System.exit(0);
    }
  }
}
