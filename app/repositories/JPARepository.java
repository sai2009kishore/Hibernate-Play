package repositories;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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

    public CompletionStage<Stream<T>> list() {
        return supplyAsync(() -> wrap(em -> list(em)), executionContext);
    }

    private <X> X wrap(Function<EntityManager, X> function) {
        return jpaApi.withTransaction(function);
    }

    private T insert(EntityManager em, T t) {
        em.persist(t);
        return t;
    }

    private Stream<T> list(EntityManager em) {
        List<T> list = em.createQuery("select p from " + tClass.getName() + " p", tClass).getResultList();
        return list.stream();
    }
}
