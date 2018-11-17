package services;

import java.util.List;

import repositories.AbstractRepository;

public abstract class AbstractService<T> {

	private final AbstractRepository<T> repository;

	public AbstractService(AbstractRepository<T> repository) {
		this.repository = repository;
	}

	public int delete(Integer id) {
		return repository.delete(id);
	}

	public List<T> list(Integer id) {
		return repository.list(id);
	}

	public T update(Integer id) {
		return repository.update(id);
	}

	public T add(T t) throws Exception {
		return repository.add(t);
	}

	public Object executeNamedQuery(String name) {
		return repository.executeNamedQuery(name);
	}

}
