package repositories;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import jpa.DatabaseExecutionContext;
import play.db.jpa.JPAApi;

/**
 * Provide JPA operations running inside of a thread pool sized to the connection pool
 */
public class JPARepository<T> {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;
    Class<T> tClass;

    @Inject
    public JPARepository(JPAApi jpaApi, DatabaseExecutionContext executionContext, Class<T> tClass) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
        this.tClass = tClass;
    }

    public CompletionStage<T> add(T t) {
        return supplyAsync(() -> wrap(em -> insert(em, t)), executionContext);
    }

    private <X> X wrap(Function<EntityManager, X> function) {
        return jpaApi.withTransaction(function);
    }

    private T insert(EntityManager em, T t) {
        em.persist(t);
        return t;
    }

    public EntityManager em() {
    	return jpaApi.em();
    }
    
    public List<T> list(Integer id) {
    	CriteriaBuilder criteriaBuilder = em().getCriteriaBuilder();
    	CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
    	Root<T> root = criteriaQuery.from(tClass);
    	
    	if(id != null) {
    		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
    	}
    	
    	CriteriaQuery<T> query = criteriaQuery.select(root);
    	return em().createQuery(query).getResultList();
    }
    
}
