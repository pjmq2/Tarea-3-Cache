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
    private static  MenuController menu;

    public static void  main(String[] parametros) throws Throwable {
       menu = new MenuController();
       pruebas();
    }

    public static void pruebas() throws SQLException, ClassNotFoundException {
        menu.pruebas();
    }

    public static void iniciar() throws Throwable {
        menu.iniciar();
    }
}
