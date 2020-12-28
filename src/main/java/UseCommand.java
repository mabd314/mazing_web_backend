public class UseCommand extends MainCommand {
  String itemName;
  public UseCommand(String itemName) {
    this.itemName=itemName;
  }

  @Override
  public void applyEffect() {
    Item item = Item.getItemFromList(itemName, getGame().getCharacterItems());
    if (item == NoItem.getInstance()) {
      setResponse(new Response(ResponseType.INVALID,
          "How come you want to use an item that you do not have?"));
    }
    else{
      setResponse(item.use(getGame()));
    }
  }
}
