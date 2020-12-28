public class ForwardCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(getGame().getThrough(getGame().getDirection()));
  }

}
