package persistence.hibernate.helper;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DatabaseHelper<T> implements IDatabaseHelper<T> {

	private static final String CONFIGURATION_FILE_NAME = "hibernate.cfg.xml";

	private SessionFactory sessionFactory = new Configuration()
			.configure(CONFIGURATION_FILE_NAME)
			.buildSessionFactory();

	@Override
	public Serializable create(final T obj) {
		Transaction trans = null;

		try (Session session = getSession()) {
		
			trans = session.beginTransaction();

			Serializable generatedValue = session.save(obj);

			trans.commit();

			return generatedValue;
		
		} catch (Exception e) {
			
			rollback(trans);			
			
			throw e;
		}
	}

	@Override
	public T read(final Class<T> requestedEntityType, final Serializable primaryKeyValue) {
		try (Session session = getSession()) {
			
			T obj = (T) session.get(requestedEntityType, primaryKeyValue);

			return obj;
		}
	}

	@Override
	public Collection<T> readAll(final Class<T> requestedEntityType) {
		try (Session session = getSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<T> criteria = builder.createQuery(requestedEntityType);

			Root<T> root = criteria.from(requestedEntityType);

			criteria.select(root);

			Query<T> query = session.createQuery(criteria);

			Collection<T> allObjects = query.getResultList();
		
			return allObjects;
		}
	}

	@Override
	public void update(final T obj) {
		Transaction trans = null;

		try (Session session = getSession()) {
		
			trans = session.beginTransaction();

			session.update(obj);

			trans.commit();
		
		} catch (Exception e) {
			
			rollback(trans);			
			
			throw e;
		}
	}

	@Override
	public void delete(final T obj) {
		Transaction trans = null;

		try (Session session = getSession()) {

			trans = session.beginTransaction();

			session.delete(obj);

			trans.commit();

		} catch (Exception e) {

			rollback(trans);
			
			throw e;
		}
	}
	
	private Session getSession() {
		return sessionFactory.openSession();
	}
	
	private void rollback(Transaction transaction) {
		if (transaction != null)
			transaction.rollback();
	}
	
}
