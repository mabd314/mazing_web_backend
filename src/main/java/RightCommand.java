public class RightCommand extends MainCommand{

  @Override
  public void applyEffect() {
    getGame().getCharacter().turnRight();
    setResponse(new Response(ResponseType.SUCCESS,
        "You turned right"));
  }

}
