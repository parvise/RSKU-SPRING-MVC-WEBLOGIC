package com.hp.c4.rsku.rSku.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hp.c4.rsku.rSku.constants.DBConstants;
import com.hp.c4.rsku.rSku.dbio.persistent.CdbConnectionMgr;

@Configuration
public class LoadRskuDBService {

	@Value("${c4.db.properties}")
	private String C4_DB_PROP_PATH;

	@Value("${rsku.services.env}")
	private String C4_RSKU_ENV;

	private static Properties mEmailerProps;

	private static final Logger mLogger = LogManager.getLogger(LoadRskuDBService.class);

	@Bean
	public String initializeDBServices() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			mEmailerProps = new Properties();

			FileInputStream in = null;
			try {
				mLogger.info("Loading All C4 DB properties into C4 Env = " + C4_RSKU_ENV + ":" + C4_DB_PROP_PATH);
				in = new FileInputStream(C4_DB_PROP_PATH);
				mEmailerProps.load(in);

				Properties sys = System.getProperties();
				Set<Object> keyset = mEmailerProps.keySet();
				Iterator<Object> itr = keyset.iterator();
				while (itr.hasNext()) {
					String key = (String) itr.next();
					sys.put(key, mEmailerProps.getProperty(key));
				}

				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				mLogger.error("Inside exception of Cemailer constructor ---- " + e.getMessage());
			} finally {

				try {
					if (in != null) {
						in.close();
						in = null;
					}
				} catch (Exception ignore) {
					mLogger.error(" Error in the Finally block " + ignore.getMessage());
				}
			}

			try {
				mLogger.info("Pool creation started for =" + DBConstants.C4_DBPOOL_C4PROD_ONSI);
				CdbConnectionMgr.getConnectionMgr().createPool(DBConstants.C4_DBPOOL_C4PROD_ONSI,
						mEmailerProps.getProperty(DBConstants.C4_DBPOOL_C4PROD_ONSI));

				mLogger.info("Pool creation started for =" + DBConstants.C4_DBPOOL_C4PROD_OFFI);
				CdbConnectionMgr.getConnectionMgr().createPool(DBConstants.C4_DBPOOL_C4PROD_OFFI,
						mEmailerProps.getProperty(DBConstants.C4_DBPOOL_C4PROD_OFFI));

				mLogger.info("Pool creation started for =" + DBConstants.C4_DBPOOL_GPSNAP_ONSI);
				CdbConnectionMgr.getConnectionMgr().createPool(DBConstants.C4_DBPOOL_GPSNAP_ONSI,
						mEmailerProps.getProperty(DBConstants.C4_DBPOOL_GPSNAP_ONSI));

				mLogger.info("Pool creation started for =" + DBConstants.C4_DBPOOL_INFOSHU_INFI);
				CdbConnectionMgr.getConnectionMgr().createPool(DBConstants.C4_DBPOOL_INFOSHU_INFI,
						mEmailerProps.getProperty(DBConstants.C4_DBPOOL_INFOSHU_INFI));

				mLogger.info("All C4 DB Accounts Created Done..........");

			} catch (SQLException e) {
				mLogger.error("Exception occured at DB Passwords Initilaization = " + e.getMessage());
				// System.exit(1);
			} catch (IOException e) {
				mLogger.error("Exception occured at DB Passwords Initilaization = " + e.getMessage());
				// System.exit(1);
			}

		} catch (Exception e) {
			mLogger.error("Exception occured at DB Passwords Initilaization = " + e.getMessage());
			// System.exit(1);
		}

		return "DB services Loaded";

	}

}
