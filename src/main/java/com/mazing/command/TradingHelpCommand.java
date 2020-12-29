package com.mazing.command;

import com.mazing.game.MessagePrinter;

public class TradingHelpCommand extends TradingCommand {

  @Override
  public void applyEffect() {}

  @Override
  public void printResponse() {
    MessagePrinter.printTradingHelp();
  }
}
