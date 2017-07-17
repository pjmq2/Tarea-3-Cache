package cr.ac.ucr.ecci.ci1310.cache;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by pjmq2 on 16/07/2017.
 */
public class MenuController {
    WikiPageServiceImpl wikipedia;
    public MenuController() throws Throwable {
        iniciar();
    }

    public void iniciar() throws Throwable {
        WikiPageServiceImpl wikipediaTemp;
        System.out.println("Bienvenido al buscador de paginas de Wikipedia \nDesea utilizar Cache en sus busquedas? de ser asi favor escribir \"S\" \n");
        String useCache = "";
        Scanner into = new Scanner(System.in);
        useCache = into.nextLine();
        if(useCache.equalsIgnoreCase("s")){
            wikipediaTemp = new WikiPageServiceImpl(true);
        }
        else{
            wikipediaTemp = new WikiPageServiceImpl(false);
        }
        wikipedia = wikipediaTemp;
        this.busqueda();
    }

    public void busqueda() throws Throwable {
        System.out.println("Estamos listos, si quiere realizar busqueda por titulo de la pagina favor digitar \"T\" o \"I\" para buscar por ID");
        String searchTipe = "";
        Scanner into = new Scanner(System.in);
        searchTipe = into.nextLine();
        if(searchTipe.equalsIgnoreCase("T")){
            System.out.println("Favor digitar el titulo que desea buscar");
            String title = into.nextLine();
            int number = wikipedia.numberSearchName(title);
            System.out.print("Se han encontrado "+number+" paginas que calzan con ese nombre. ");
            if(number == 1) {
                ArrayList<WikiPage> lista = wikipedia.searchName(title);
                WikiPage wiki = lista.get(0);
                System.out.println("Su pagina es la siguiente: ");
                wiki.print();
            }
            else if(number > 1){
                System.out.println("Favor digitar el numero correspondiente a la pagina que usted desea: ");
                ArrayList<WikiPage> lista = wikipedia.searchName(title);
                int contador = 1;
                for(WikiPage wiki:lista){
                    System.out.print(contador+" - ");
                    wiki.printIdTitle();
                    contador++;
                }
                System.out.println("Favor digitar el número de la página que desea recuperar");
                int result = into.nextInt();
                result--;
                WikiPage wiki = lista.get(result);
                System.out.println("Su pagina es la siguiente: ");
                wiki.print();
            }
        }
        else if(searchTipe.equalsIgnoreCase("I")){
            System.out.println("Favor digitar el ID que desea buscar");
            String id = into.nextLine();
            WikiPage wiki = wikipedia.searchId(id);
            System.out.println("Su pagina es la siguiente: ");
            wiki.print();
        }
        else{
            System.out.println("Favor digitar una letra correcta");
            this.busqueda();
        }
        finalizar();
    }
    public void finalizar() throws Throwable {
        Scanner into = new Scanner(System.in);
        System.out.println("Desea realizar otra busqueda? \nFavor digitar \"S\" si desea realizar otra busqueda con la configuracion actual\nDigitar \"N\" si desea cambiar la configuracion de cache antes de la nueva busqueda\nDigitar \"E\" si desea salir del programa ");
        String respuesta = into.nextLine();
        if(respuesta.equalsIgnoreCase("S")){
            this.busqueda();
        }
        else if(respuesta.equalsIgnoreCase("N")){
            this.iniciar();
        }
        else if(respuesta.equalsIgnoreCase("E")){
            System.exit(0);
        }
        else{
            System.out.println("Favor digitar una letra correcta");
            finalizar();
        }
    }
}

