package cr.ac.ucr.ecci.ci1310.cache;

import java.sql.*;

/**
 * Created by pjmq2 on 15/07/2017.
 */
public class WikiPageDaoImpl implements WikiPageDao {
    private SQLData sql;

    public WikiPageDaoImpl(SQLData sql) {
        this.sql = sql;
    }

    public void searchId(String id){

    }

    public void searchName(String name) {

    }

    public static Connection getConnection(){
        Connection connection=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wiki","root","hola");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
