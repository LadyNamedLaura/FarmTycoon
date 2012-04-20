package domain;

import java.sql.SQLException;
import java.util.Set;

public abstract class Savable {
	protected int id = -1;

	public int getId() {
		if (id == -1)
			id = persistence.MapperList.valueOf(
					this.getClass().getSimpleName().toUpperCase()).getNextID();
		return id;
	}

	public void save() throws SQLException {
		persistence.MapperList.valueOf(
				this.getClass().getSimpleName().toUpperCase()).save(this);
	}

	public static Savable load(Class<? extends Savable> type, int id)
			throws SQLException {
		return persistence.MapperList.valueOf(
				type.getSimpleName().toUpperCase()).loadById(id);
	}

	public static Set<Savable> loadAll(Class<? extends Savable> type)
			throws SQLException {
		return persistence.MapperList.valueOf(
				type.getSimpleName().toUpperCase()).loadAll();
	}
}
