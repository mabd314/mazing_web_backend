public interface Command {
  public void setGame(Game game);
  public Game getGame();
  public void applyEffect();
  default public void printResponse(){
    System.out.println(getResponse());
  };
  public Response getResponse();
  public void executeNext();
  public void setResponse(Response response);

  default public void execute(){
    applyEffect();
    printResponse();
    executeNext();
  }
}
