package domain;

public class Controller {
	private Game game;
	public boolean saveExists(){
		return true;
	}
	public void loadGame(){
		System.out.println("loading game (nah, jk)");
		game = new Game();
	}
	public void newGame(){
		System.out.println("initializing new game");
		game = new Game();
	}
	public Game getGame(){
		return game;
	}
}
