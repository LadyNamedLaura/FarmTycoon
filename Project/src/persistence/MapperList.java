package persistence;

//import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import domain.Savable;

import exceptions.DBConnectException;

@SuppressWarnings("serial")
public enum MapperList {
	Tile(new HashMap<String,String>(){{
		put("x","INT");
		put("y","INT");
		put("state","TEXT");
		put("stateid","INT");}},
		new Mapper(){
			public domain.Tile load(Map<String,Object> data) throws SQLException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
				domain.TileState state;
				if (((String) data.get("state")).equals("none")) {
					state = null;
				} else if ((Integer) data.get("stateid") == -1) {
					state = (domain.TileState) Class.forName((String) data.get("state"))
							.getConstructor(Integer.class, Integer.class)
							.newInstance((Integer) data.get("x"),(Integer) data.get("y"));
				} else {
					state = (domain.TileState)
						MapperList.valueOf((String) data.get("state")).loadById((Integer) data.get("stateid"));}
				return new domain.Tile(
						(Integer) data.get("x"),
						(Integer) data.get("y"),
						state);}
			public Map<String,Object> save(domain.Savable obj) throws SQLException{
				domain.Tile tile = (domain.Tile) obj;
				Map<String,Object> ret = new HashMap<String,Object>();
				ret.put("x", tile.getCoords()[0]);
				ret.put("y", tile.getCoords()[1]);
				if(tile.getState()==null) {
					ret.put("state", "'none'");
					ret.put("stateid", -1);
				} else {
					ret.put("state", "'"+ tile.getState().getClass().getSimpleName()+"'");
					if(tile.getState() instanceof domain.Savable) {
						domain.Savable state = (domain.Savable) tile.getState();
						ret.put("stateid", state.getId());
						state.save();
					} else {
						ret.put("stateid", -1);}
				}
				return ret;}}),

	Crop(new HashMap<String,String>(){{
		put("type","TEXT");
		put("planted","BIGINT");}},
		new Mapper(){
			public domain.Crop load(Map<String,Object> data) throws SQLException {
				return new domain.Crop( (String) data.get("type"),
										(Long) data.get("planted"));}
			public Map<String,Object> save(domain.Savable obj){
				domain.Crop crop = (domain.Crop) obj;
				Map<String,Object> ret = new HashMap<String,Object>();
				ret.put("type", "'"+crop.getType()+"'");
				ret.put("planted", crop.getPlanted().getTime());
				return ret;}}),

	Clock((Map<String,String>) new HashMap<String,String>(){{
		put("Offset","BIGINT");
		put("Multi","DOUBLE");}},
		new Mapper(){
			public domain.Clock load(Map<String,Object> data){
				data.get("Multi");
				data.get("Offset");
				return new domain.Clock((Double) data.get("Multi"), (Long) data.get("Offset"));}
			public Map<String, Object> save(Savable obj) throws SQLException {
				Map<String,Object> ret = new HashMap<String,Object>();
				domain.Clock clock = (domain.Clock) obj;
				ret.put("Offset", clock.getOffset());
				ret.put("Multi", clock.getMultiplier());
				return ret;}}),

	Farm((Map<String,String>) new HashMap<String,String>(){{
		put("cash","INT");
		put("marketX","INT");
		put("marketY","INT");}},
		new Mapper(){
			public domain.Farm load(Map<String,Object> data) throws SQLException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
				return new domain.Farm(
						(Integer) data.get("cash"),
						new domain.Market((Integer) data.get("marketX"), (Integer) data.get("marketY")),
						true);}
			public Map<String,Object> save(domain.Savable obj){
				Map<String,Object> ret = new HashMap<String,Object>();
				domain.Farm farm = (domain.Farm) obj;
				ret.put("cash", farm.getCash());
				ret.put("marketX", farm.getMarket().getCoords()[0]);
				ret.put("marketY", farm.getMarket().getCoords()[1]);
				return ret;}});
	
	private static abstract class Mapper {
		public abstract domain.Savable load(Map<String,Object> data) throws SQLException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException;
		public abstract Map<String,Object> save(domain.Savable obj) throws SQLException;}
	
	private DB db;
	private String tablename;
	private Mapper mapper;
	private Map<String,String> fields;
	private int nextid=0;
	
	private MapperList(Map<String,String> fields, Mapper mapper){
		this("",fields,mapper);
		this.tablename = name();
	}
	private MapperList(String name, Map<String,String> fields, Mapper mapper){
		try {
			this.db = PersistenceController.getInstance().getDB();
		} catch (DBConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.mapper = mapper;
		this.tablename = name;
		this.fields = fields;}

	/**
	 * @param obj
	 * @return
	 * @throws SQLException 
	 * @see persistence.Mapper#save(domain.Savable)
	 */
	public void save(domain.Savable obj) throws SQLException {
		this.initIfNeed();
		java.sql.Statement st = db.getConnection().createStatement();
		ResultSet rs;
		String update = "";
		System.err.println("saving "+ this.tablename + " with id = "+ obj.getId());
		Map<String,Object> values;
		rs = st.executeQuery(String.format(
				"SELECT COUNT(*) AS count FROM %s WHERE id = %d",
				this.tablename, obj.getId()));
		values = mapper.save(obj);
		if(rs.getInt("count")>0) {
			//ugly format black magic
			update = String.format("UPDATE %s SET %s WHERE id = %d",
					this.tablename, "%s%s%s%4$s", obj.getId());
			for (Map.Entry<String, Object> e : values.entrySet())
				update = String.format(update, e.getKey()," = ", e.getValue().toString(), "%5$s %s%s%s%4$s", ",");
			update = String.format(update, "","","","","");
		} else {
			update = String.format("INSERT INTO %s (%s%%s%%s%%s) VALUES(%s%%s%%s%%s)",
					this.tablename, "id", obj.getId());
			for (Map.Entry<String, Object> e : values.entrySet())
				update = String.format(update,
						", ", e.getKey(),"%s%s%s",
						",", e.getValue().toString(), "%s%s%s");
			update = String.format(update, "","","","","","");
		}
		st.executeUpdate(update);
	}

	public void init() throws SQLException {
		java.sql.Statement st = db.getConnection().createStatement();
		st.executeUpdate(String.format(
				"DROP RABLE IF EXISTS %s",this.tablename));
		this.initIfNeed();
	}
	public void initIfNeed() throws SQLException {
		java.sql.Statement st = db.getConnection().createStatement();
		String update = "id INTEGER PRIMARY KEY";
		System.err.println(this.name());
		System.err.println(fields);
		for (Map.Entry<String, String> e : this.fields.entrySet())
			update += ", " + e.getKey() + " " + e.getValue();
		st.executeUpdate(String.format(
				"CREATE TABLE IF NOT EXISTS %s (%s)",this.tablename,update));
		
	}
	
	public domain.Savable loadById(int id) throws SQLException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException{
		java.sql.Statement st = db.getConnection().createStatement();
		java.sql.ResultSet rs = st.executeQuery(String.format(
				"SELECT * FROM %s WHERE id = %d",
				this.tablename, id));
		java.sql.ResultSetMetaData meta = rs.getMetaData(); 

		Map<String, Object> data = new HashMap<String, Object>();
		int count = meta.getColumnCount();
		for(int i = 1; i <= count ;i++) {
			System.err.println(meta.getColumnName(i)+ " : "+rs.getObject(i));
			data.put(meta.getColumnName(i), rs.getObject(i));
		}
		return mapper.load(data);
	}
	public int getNextID() {
		return nextid++;
	}
}