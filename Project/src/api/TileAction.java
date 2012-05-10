package api;

public interface TileAction {
	public enum Defaults implements TileAction {
		EXPIRE,
		CANCEL;
		public int getCost(){
			return 0;
		}
		public int getTime(){
			return 0;
		}
	}
	public String name();
	
	public int getCost();
	public int getTime();
}
