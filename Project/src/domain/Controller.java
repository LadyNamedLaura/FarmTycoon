package domain;

import java.sql.SQLException;

import exceptions.*;

public class Controller {
	private static Controller instance;
	private Game game;

	public static Controller getInstance() {
		if (instance == null)
			instance = new Controller();
		return instance;
	}

	public boolean saveExists() {
		try {
			return persistence.PersistenceController.getInstance().saveExists();
		} catch (DBConnectException e) {
			e.printStackTrace();
		} catch (SystemDBException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void loadGame() {
		setGame(new Game(true));
	}

	public void newGame() {
		setGame(new Game(false));
		try {
			game.save();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Game getGame() {
		return this.game;
	}

	public Clock getClock() {
		try{
			return getGame().getClock();
		} catch (NullPointerException e){
			throw new NoSuchGameException();
		}
	}

	public void setGame(Game newgame) {
		this.game = newgame;
	}
	public int[] getFarmSize(){
		int [] ret = new int[2];
		ret[0] = Farm.width;
		ret[1] = Farm.height;
		return ret;
	}
	public int getCash() throws NoSuchGameException{
		try{
			return this.game.getFarm().getCash();
		} catch (NullPointerException e){
			throw new NoSuchGameException();
		}
	}
}
