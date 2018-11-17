package repositories;

import javax.inject.Named;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import models.Accessory;
import play.db.jpa.JPAApi;

@Named
@Singleton
public class AccessoryRepository extends AbstractRepository<Accessory> {

	@Inject
	public AccessoryRepository(JPAApi jpaApi) {
		super(jpaApi, Accessory.class);
	}
}
