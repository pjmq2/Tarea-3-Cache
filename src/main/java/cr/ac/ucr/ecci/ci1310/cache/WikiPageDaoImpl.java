package cr.ac.ucr.ecci.ci1310.cache;

import java.sql.*;

/**
 * Created by pjmq2 on 15/07/2017.
 */
public class WikiPageDaoImpl implements WikiPageDao {
    private String sqlConnectionString;
    private String classForName;
    private String user;
    private String password;

    public WikiPageDaoImpl(SQLData sql) {
        classForName = "com.mysql.jdbc.Driver";
        sqlConnectionString = "jdbc:mysql://localhost:3306/wiki";
        user = "root";
        password = "hola";
    }

    public WikiPage searchId(String id){
        Class.forName(classForName);
        Connection sqlConnection = DriverManager.getConnection( sqlConnectionString, user, password);
        Statement sqlStatement = sqlConnection.createStatement();
        ResultSet sqlResult = sqlStatement.executeQuery();
    }

    public WikiPage searchName(String name) {

    }
}
