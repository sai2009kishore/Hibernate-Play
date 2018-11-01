package services;

import static constants.MessageConstants.CREATED_DATA_SUCCESSFULLY;
import static constants.MessageConstants.DROPPED_AND_CREATED_DATA_SUCCESSFULLY;
import static constants.MessageConstants.ERROR_CREATING_TABLES;
import static constants.MessageConstants.RESOURCE_NOT_FOUND;
import static constants.MessageConstants.TABLE_DOES_NOT_EXIST;
import static constants.MiscellaneousConstants.INITIALIZE_SCRIPT;
import static constants.MiscellaneousConstants.NEW_LINE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.inject.Inject;

import enums.Tables;
import play.Logger;
import play.db.jpa.JPAApi;

public class DBInitializerService {

	private final JPAApi jpaApi;

	@Inject
	public DBInitializerService(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}

	public String initializeDB(Boolean drop) {
		if (drop) {
			try {
				dropTables();
			} catch (Exception e) {
				Logger.info(TABLE_DOES_NOT_EXIST);
			}
		}
		StringBuffer fileData = new StringBuffer();
		File file = new File(INITIALIZE_SCRIPT);
		if (!file.exists()) {
			return RESOURCE_NOT_FOUND;
		}
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String data = null;
			while ((data = reader.readLine()) != null) {
				fileData.append(data);
				fileData.append(NEW_LINE);
			}
		} catch (IOException e) {
			Logger.error(ERROR_CREATING_TABLES);
		}
		try {
			for (String query : fileData.toString().replaceAll("\n", "").split(";")) {
				executeQuery(query);
			}
		} catch (Exception e) {
			return ERROR_CREATING_TABLES;
		}
		return drop ? DROPPED_AND_CREATED_DATA_SUCCESSFULLY : CREATED_DATA_SUCCESSFULLY;
	}

	private void dropTables() throws Exception {
		try {
			for (Tables table : Tables.values()) {
				executeQuery("DROP TABLE " + table.getName() + ";");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private final void executeQuery(String query) {
		jpaApi.em().createNativeQuery(query).executeUpdate();
	}
}
