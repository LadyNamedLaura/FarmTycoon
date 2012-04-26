package api;

public class TileInfo {
	private final String field;
	private final String subtype;
	private final String state;

	public TileInfo(String field, String subtype, String state) {
		this.field = field;
		this.subtype = subtype;
		this.state = state;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @return the subtype
	 */
	public String getSubtype() {
		return subtype;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	public String toString() {
		String str= this.field;
		if(this.subtype!=null && this.subtype.length()>0)			
			str += "_" + this.subtype;
		if(this.state!=null && this.state.length()>0)			
			str += "_" + this.state;
		return str;
	}
}
