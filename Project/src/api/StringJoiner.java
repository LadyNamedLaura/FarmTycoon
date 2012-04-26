package api;

import java.util.Collection;

public class StringJoiner {
	private String glue;
	StringBuilder builder;

	public StringJoiner(String glue, Object...coll){
		this(glue);
		for(Object o : coll)
			add(o);
	}
	public StringJoiner(String glue, Collection< ? extends Object > coll){
		this(glue,coll.toArray(new Object[coll.size()]));
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