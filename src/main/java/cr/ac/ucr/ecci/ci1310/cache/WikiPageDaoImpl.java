package cr.ac.ucr.ecci.ci1310.cache;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by pjmq2 on 15/07/2017.
 */
public class WikiPageDaoImpl implements WikiPageDao {
    private String sqlConnectionString;
    private String classForName;
    private String user;
    private String password;

    public WikiPageDaoImpl() {
        classForName = "com.mysql.jdbc.Driver";
        sqlConnectionString = "jdbc:mysql://localhost:3306/wiki";
        user = "root";
        password = "hola";
    }

    /*
1 - page_id int(10) UN AI PK
2 - page_namespace int(11)
3 - page_title varchar(255)
4 - NO SE TRATARA page_restrictions tinyblob
5 - page_counter bigint(20) UN
6 - page_is_redirect tinyint(3) UN
7 - page_is_new tinyint(3) UN
8 - page_random double UN
9 - NO SE TRATARA page_touched binary(14)
10 - page_latest int(10) UN
11 - page_len int(10) UN
* */

    public WikiPage searchId(String id) throws SQLException, ClassNotFoundException {
        WikiPage wiki = null;
        Connection sqlConnection = conecting();
        String query = "Select * from page where page_id =" + id;
        if(sqlConnection != null){
            Statement sqlStatement = sqlConnection.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery(query);
            int sqlId = sqlResult.getInt("page_id");
            int namespace = sqlResult.getInt("page_namespace");
            String title = sqlResult.getString("page_title");
            int counter = sqlResult.getInt("page_counter");
            int is_redirect = sqlResult.getInt("page_is_redirect");
            int is_new = sqlResult.getInt("page_is_new");
            int random = sqlResult.getInt("page_random");
            int latest = sqlResult.getInt("page_latest");
            int len = sqlResult.getInt("page_len");
            wiki = new WikiPage(sqlId, namespace, title,counter, is_redirect,is_new,random,latest,len );
        }
        return wiki;
    }

    public ArrayList<WikiPage> searchName(String name) throws SQLException, ClassNotFoundException {
        ArrayList<WikiPage> wikipedia = new ArrayList<WikiPage>();
        Connection sqlConnection = conecting();
        String query = "Select * from page where page_title like '%"+ name +"%'";
        if(sqlConnection != null){
            Statement sqlStatement = sqlConnection.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery("");
            while(sqlResult.next()){
                int sqlId = sqlResult.getInt("page_id");
                int namespace = sqlResult.getInt("page_namespace");
                String title = sqlResult.getString("page_title");
                int counter = sqlResult.getInt("page_counter");
                int is_redirect = sqlResult.getInt("page_is_redirect");
                int is_new = sqlResult.getInt("page_is_new");
                int random = sqlResult.getInt("page_random");
                int latest = sqlResult.getInt("page_latest");
                int len = sqlResult.getInt("page_len");
                WikiPage wiki = new WikiPage(sqlId, namespace, title,counter, is_redirect,is_new,random,latest,len );
                wikipedia.add(wiki);
            }
        }
        return wikipedia;
    }
     private Connection conecting() throws SQLException, ClassNotFoundException {
         Class.forName(classForName);
         Connection sqlConnection = DriverManager.getConnection( sqlConnectionString, user, password);
         return sqlConnection;
     }
}
