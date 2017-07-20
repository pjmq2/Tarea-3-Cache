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

    public static void  main(String[] parametros) throws Throwable {
        menu();
    }

    public static void menu() throws Throwable {
        MenuController menu = new MenuController();
    }

    public void prueba1() throws SQLException, ClassNotFoundException {
        WikiPageServiceImpl wikipedia = new WikiPageServiceImpl(false);
        ArrayList<WikiPage> lista = wikipedia.searchName("Kansas");
    }
}
