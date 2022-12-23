package com.rj.demo.groovyunittest;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;

public class MysqlJdbcConnectionTester  extends org.dbunit.JdbcDatabaseTester {
    public MysqlJdbcConnectionTester(String driverClass, String connectionUrl, String username, String password,
                                   String schema) throws ClassNotFoundException {
        super(driverClass, connectionUrl, username, password, schema);
    }

    @Override
    public IDatabaseConnection getConnection() throws Exception {
        IDatabaseConnection connection = super.getConnection();
        DatabaseConfig dbConfig = connection.getConfig();
        dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
        dbConfig.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
        return connection;
    }
}