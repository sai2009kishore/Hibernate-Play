package jobs;

import javax.inject.Inject;

import com.google.inject.AbstractModule;
import com.typesafe.config.Config;

import play.Environment;
import play.Logger;

public class TableSeeder extends AbstractModule {

	private Environment environment;
	private Config config;

	@Inject
	public TableSeeder(Environment environment, Config config) {
		this.environment = environment;
		this.config = config;
	}

	@Override
	protected void configure() {
		Logger.debug("I am here");
//		EntityManager manager = jpaApi.em();
//		Query q = manager.createNativeQuery("BEGIN create table test END;");
//		q.executeUpdate();
	}

}