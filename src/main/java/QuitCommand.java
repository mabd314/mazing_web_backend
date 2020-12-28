public class QuitCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.LOST,
        "You quit the game. YOU LOSE."));
  }

  public void executeNext(){
    System.exit(1);
  }

}
