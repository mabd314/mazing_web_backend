public class NoKey extends Key {

  private static NoKey instance;

  private NoKey() {
    super(-1);
  }

  public static synchronized NoKey getInstance() {
    if (instance == null) {
      instance = new NoKey();
    }
    return instance;
  }
}