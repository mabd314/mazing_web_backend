public class BuyCommand extends TradingCommand {
  String itemName;
  public BuyCommand(String itemName) {
    this.itemName=itemName;
  }


  @Override
  public void applyEffect() {
    Item item = Item.getItemFromList(itemName,getGame().getFacingWallTradingList());
    if (item == NoItem.getInstance()) {
      setResponse(new Response(ResponseType.INVALID,
          "The seller does not have what you desire"));
    }
    else if (getGame().getGold().payForItem(item)) {
      getGame().getCharacter().addItem(item);
      setResponse(new Response(ResponseType.SUCCESS,
          "You bought an item: " + item));
    }
    else{
      setResponse(new Response(ResponseType.FAILURE,
          "You do not have enough gold to buy this item"));
    }
  }
}
