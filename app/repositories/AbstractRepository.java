package repositories;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import play.db.jpa.JPAApi;

public class AbstractRepository<T> {

	private final JPAApi jpaApi;
	Class<T> tClass;

	@Inject
	public AbstractRepository(JPAApi jpaApi, Class<T> tClass) {
		this.jpaApi = jpaApi;
		this.tClass = tClass;
	}

	@Transactional
	public T add(T t) throws Exception {
		em().persist(t);
		return t;
	}

	public EntityManager em() {
		return jpaApi.em();
	}

	public List<T> list(Integer id) {
		CriteriaBuilder criteriaBuilder = em().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
		Root<T> root = criteriaQuery.from(tClass);

		if (id != null) {
			criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
		}

		CriteriaQuery<T> query = criteriaQuery.select(root);
		return em().createQuery(query).getResultList();
	}

	public T findById(Integer id) {
		return em().find(tClass, id);
	}

	public T update(Integer id) {
		T t = findById(id);
		return t;
	}

	public int delete(Integer id) {
		CriteriaBuilder criteriaBuilder = em().getCriteriaBuilder();
		CriteriaDelete<T> criteriaQuery = criteriaBuilder.createCriteriaDelete(tClass);
		Root<T> root = criteriaQuery.from(tClass);
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

		return em().createQuery(criteriaQuery).executeUpdate();
	}

	public List<T> executeNamedQuery(String name) {
		Query query = em().createNamedQuery("Person.fetchAll");
		return query.getResultList();
	}

}
