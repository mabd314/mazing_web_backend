import java.util.List;

public abstract class Item {

  private int price;

  public Item() {
    this.price = 0;
  }

  public static Item getItemFromList(String itemName, List<Item> items) {
    for (Item item : items) {
        if (item.toString().equals(itemName.toUpperCase())) {
            return item;
        }
    }
    return NoItem.getInstance();
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Response use(Game game) {
    return new Response(ResponseType.INVALID,
        "This item is unusable");
  }

  public abstract ItemType getType();

  @Override
  public String toString() {
    return getType().toString();
  }
}
