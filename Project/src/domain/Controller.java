package domain;

public class Controller {
	private static Controller instance;
	private Game game;
	
	public static Controller getInstance(){
		if(instance==null)
			instance=new Controller();
		return instance;
	}
	
	public boolean saveExists(){
		return true;
	}
	public void loadGame(){
		System.out.println("loading game (nah, jk)");
		setGame(new Game());
	}
	public void newGame(){
		System.out.println("initializing new game");
		setGame(new Game());
	}
	public Game getGame(){
		return this.game;
	}
	public Clock getClock(){
		return getGame().getClock();
	}
	public void setGame(Game newgame) {
		this.game = newgame;
	}
}
