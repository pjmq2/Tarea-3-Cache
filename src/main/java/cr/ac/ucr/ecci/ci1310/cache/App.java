package cr.ac.ucr.ecci.ci1310.cache;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void  main(String[] parametros) throws SQLException, ClassNotFoundException {
        WikiPageDaoImpl busqueda = new WikiPageDaoImpl();
        ArrayList<WikiPage> lista = busqueda.searchName("kansas");

        for(WikiPage wiki:lista){
            wiki.print();
        }
    }
}
