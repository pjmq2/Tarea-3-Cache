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
        sqlConnectionString = "jdbc:mysql://localhost:3306/wiki?useSSL=false";
        user = "root";
        password = "hola";
    } //Constructor de la clase

    public WikiPage searchId(String id) throws SQLException, ClassNotFoundException {
        WikiPage wiki = null;
        Connection sqlConnection = conecting();
        String query = "Select * from page where page_id =" + id;
        if(sqlConnection != null){
            Statement sqlStatement = sqlConnection.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery(query);
            sqlResult.next();
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
            sqlConnection.close();
        }
        return wiki;
    } //Permite buscar una pagina por Id desde la base de datos

    public ArrayList<WikiPage> searchName(String name) throws SQLException, ClassNotFoundException {
        ArrayList<WikiPage> wikipedia = new ArrayList<WikiPage>();
        Connection sqlConnection = conecting();
        String query = "Select * from page where page_title like '%"+ name +"%'";
        if(sqlConnection != null){
            Statement sqlStatement = sqlConnection.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery(query);
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
            sqlConnection.close();
        }
        return wikipedia;
    } //Permite buscar un arreglo de paginas por titulo desde la base de datos

    public int numberSearchName(String name) throws SQLException, ClassNotFoundException {
        int number=0;
        Connection sqlConnection = conecting();
        String query = "Select count(*) from page where page_title like '%"+ name +"%'";
        if(sqlConnection != null) {
            Statement sqlStatement = sqlConnection.createStatement();
            ResultSet sqlResult = sqlStatement.executeQuery(query);
            sqlResult.next();
            number = sqlResult.getInt(1);
            sqlConnection.close();
        }
        return number;
    } //Devuelve la cantidad de paginas que se copiaron con la busqueda por titulo

    private Connection conecting() throws SQLException, ClassNotFoundException {
         Class.forName(classForName);
         Connection sqlConnection = DriverManager.getConnection( sqlConnectionString, user, password);
         return sqlConnection;
     } //Crea una conexion segura a la base de datos
}
