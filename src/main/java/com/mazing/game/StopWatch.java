package com.mazing.game;

import java.util.Timer;
import java.util.TimerTask;

public class StopWatch extends TimerTask {

  private final long secondsNeeded;
  private long start;
  private boolean isTimerOn;

  public void startCounting() {
    Timer timer = new Timer();
    timer.schedule(this,secondsNeeded*1000);
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

  public String getRemainingSecondsString(){
    return getTimeString(getRemainingSeconds());
  }

  private String getTimeString(long seconds) {
    return seconds / 60 + ":" + seconds % 60;
  }

  private long getElapsedSeconds() {
    long now = System.currentTimeMillis();
    return (now - start) / 1000;
  }

  private long getRemainingSeconds() {
    return secondsNeeded - getElapsedSeconds();
  }

  public void stopTimer() {
    this.cancel();
    isTimerOn = false;
  }

  public String checkTime() {
    if (isTimerOn) {
      return "Elapsed: " + getElapsedSecondsString() + ", Remaining: " + getRemainingSecondsString();
    }
    return "You've won the game, the timer decided to take a break.";
  }

  @Override
  public void run() {
    MessagePrinter.printTimeElapsedMessage();
    System.exit(0);
  }
}
