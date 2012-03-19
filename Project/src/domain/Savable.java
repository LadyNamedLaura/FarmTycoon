package domain;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public abstract class Savable {
	protected int id = -1;

	public int getId() {
		if (id == -1)
			id = persistence.MapperList
					.valueOf(this.getClass().getSimpleName()).getNextID();
		return id;
	}

	public void save() throws SQLException {
		persistence.MapperList.valueOf(this.getClass().getSimpleName()).save(
				this);
	}

	public static Savable load(Class<? extends Savable> type, int id)
			throws IllegalArgumentException, SecurityException, SQLException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, ClassNotFoundException,
			InvocationTargetException {
		return persistence.MapperList.valueOf(type.getSimpleName())
				.loadById(id);
	}
}
