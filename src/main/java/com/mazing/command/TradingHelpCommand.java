package com.mazing.command;

import com.mazing.game.*;

public class TradingHelpCommand extends TradingCommand {

  @Override
  public void applyEffect() {
  }

  @Override
  public void printResponse() {
    MessagePrinter.printTradingHelp();
  }
}
