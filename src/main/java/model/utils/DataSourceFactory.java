package model.utils;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DataSourceFactory {

    private final static String DB_HOST = "localhost";
    private final static String DB_PORT = "5432";
    private final static String DB_NAME = "projectdb";
    private final static String DB_USERNAME = "postgres";
    private final static String DB_PASSSWORD = "postgres";

    private static final DataSource dataSource = new DataSource();

    static {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME);
        p.setDriverClassName("org.postgresql.Driver");
        p.setUsername(DB_USERNAME);
        p.setPassword(DB_PASSSWORD);
        dataSource.setPoolProperties(p);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
