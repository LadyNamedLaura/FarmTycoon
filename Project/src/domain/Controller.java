package domain;

import java.sql.SQLException;

import exceptions.DBConnectException;

public class Controller {
	private static Controller instance;
	private Game game;
	
	public static Controller getInstance(){
		if(instance==null)
			instance=new Controller();
		return instance;
	}
	
	public boolean saveExists(){
		try {
			return persistence.PersistenceController.getInstance().saveExists();
		} catch (DBConnectException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void loadGame(){
		System.out.println("loading game...");
		setGame(new Game(true));
		System.out.println();
	}
	public void newGame(){
		System.out.println("initializing new game");
		setGame(new Game(false));
		try {
			game.save();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
