package cr.ac.ucr.ecci.ci1310.cache;

import java.math.BigDecimal;
import java.sql.*;
import java.util.BitSet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void  main(String[] parametros) throws SQLException, ClassNotFoundException {
        conectividad();
    }

    public static void conectividad() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection sqlConnection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/wiki","root", "hola");
        WikiPage wiki = null;
        String query = "Select * from page where page_id = " + "20";
        System.out.println(query);
        if(sqlConnection != null){
            System.out.println("Se conecto bien");
            Statement sqlStatement = sqlConnection.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery(query);
            int sqlId = sqlResult.getInt(1);
            int namespace = 0;//sqlResult.getInt("page_namespace");
            String title = "p";//sqlResult.getString("page_title");
            int counter = 0;//sqlResult.getBigDecimal("page_counter");
            int is_redirect = 0;//sqlResult.getInt("page_is_redirect");
            int is_new = 0;//sqlResult.getInt("page_is_new");
            int random = 0;//sqlResult.getInt("page_random");
            int latest =0;// sqlResult.getInt("page_latest");
            int len = 0;//sqlResult.getInt("page_len");
            wiki = new WikiPage(sqlId, namespace, title,counter, is_redirect,is_new,random,latest,len );
        }
        wiki.print();
        if(sqlConnection != null){
            sqlConnection.close();
        }
    }
}
