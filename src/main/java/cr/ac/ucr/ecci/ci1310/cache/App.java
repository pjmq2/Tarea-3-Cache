package cr.ac.ucr.ecci.ci1310.cache;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void  main(String[] parametros) throws SQLException {
        Connection connection = null;
        try{
            connection = WikiPageDaoImpl.getConnection();
            if(connection != null){
                System.out.println("Buena Conexion");
            }
            else{
                System.out.println("Mala Conexion");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (connection != null){
                connection.close();
            }
        }
    }
}
