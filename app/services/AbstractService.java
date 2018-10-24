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

	public T add(T t) throws Exception {
		return repository.add(t);
	}

}
