package persistence.hibernate.helper;

import java.io.Serializable;
import java.util.Collection;

public interface IDatabaseHelper<T> {

	Serializable create(final T obj);

	T read(final Class<T> requestedEntityType, final Serializable primaryKeyValue);

	Collection<T> readAll(final Class<T> requestedEntityType);

	void update(final T obj);

	void delete(final T obj);

}
