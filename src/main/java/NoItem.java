public class NoItem extends Item {

  private static NoItem instance;

  private NoItem() {
  }

  public static synchronized NoItem getInstance() {
    if (instance == null) {
      instance = new NoItem();
    }
    return instance;
  }

  @Override
  public ItemType getType() {
    return ItemType.NOITEM;
  }
}
