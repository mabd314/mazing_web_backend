public enum ItemType {
  KEY,
  GOLD,
  FLASHLIGHT,
  NOITEM;

  public static ItemType getItemType(String itemName) {
    for (ItemType type : ItemType.values()) {
      if (itemName.toUpperCase().equals(type.toString())) {
        return type;
      }
    }
    return ItemType.NOITEM;
  }

}
