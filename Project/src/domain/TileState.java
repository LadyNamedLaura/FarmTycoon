package domain;

public interface TileState {
	public StateList getStateType();
	
	public int getId();
	public enum StateList {
		Crop,
		Market,
	}
}
