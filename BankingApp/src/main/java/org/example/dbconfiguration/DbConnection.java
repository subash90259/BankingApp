package org.example.dbconfiguration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;


public final class DbConnection {

    private DbConnection() { }

        private static final HikariDataSource datasource;

        static {
            try {
                Properties props = new Properties();

                InputStream input = Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("liquibase.properties");

                if (input == null) {
                    throw new RuntimeException("liquibase.properties file not found");
                }

                props.load(input);

                Class.forName(props.getProperty("driver"));

                HikariConfig config = new HikariConfig();

                config.setJdbcUrl(props.getProperty("url"));
                config.setUsername(props.getProperty("username"));
                config.setPassword(props.getProperty("password"));

                config.setMaximumPoolSize(
                        Integer.parseInt(props.getProperty("Hikari.maximumPool")));

                config.setMinimumIdle(
                        Integer.parseInt(props.getProperty("Hikari.minimumIdle")));

                datasource = new HikariDataSource(config);

            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize DB Connection", e);
            }
        }

        public static Connection getConnection() throws Exception {
            return datasource.getConnection();
        }
    }

