package cr.ac.ucr.ecci.ci1310.cache;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static  MenuController menu;

    public static void  main(String[] parametros) throws Throwable {
       menu = new MenuController();
       menu.menuPruebas();
    }
}
