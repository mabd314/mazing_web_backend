public class TradeCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.SUCCESS,
        "You entered the trading mode"));
  }

  public void executeNext(){
    getGame().executeTradeCommand();
  }
}
