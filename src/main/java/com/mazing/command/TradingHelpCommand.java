package com.mazing.command;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public class TradingHelpCommand extends TradingCommand {

  @Override
  public void applyEffect() {

  }

  @Override
  public void printResponse() {
    MessagePrinter.printTradingHelp();
  }
}
