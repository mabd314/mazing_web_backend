package com.mazing.logic.command;

import com.mazing.logic.game.MessagePrinter;

public class TradingHelpCommand extends TradingCommand {

  @Override
  public void applyEffect() {}

  @Override
  public void printResponse() {
    MessagePrinter.printTradingHelp();
  }
}
