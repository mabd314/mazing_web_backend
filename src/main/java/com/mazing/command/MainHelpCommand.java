package com.mazing.command;
import com.mazing.game.*;

public class MainHelpCommand extends MainCommand {

  @Override
  public void applyEffect() {
  }

  @Override
  public void printResponse() {
    MessagePrinter.printMainHelp();
  }
}