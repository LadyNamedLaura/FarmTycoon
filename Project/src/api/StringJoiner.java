package api;

public class StringJoiner {
	private String glue;
	StringBuilder builder;

	public StringJoiner(String glue, Object...coll){
		this(glue);
		for(Object o : coll)
			add(o);
	}
	public StringJoiner(String glue){
		super();
		this.glue = glue;
	}
	
	public void add(Object o) {
		if (builder == null)
			builder = new StringBuilder(o.toString());
		else
			builder.append(glue).append(o.toString());
	}
	
	public String toString(){
		return builder.toString();
	}
}