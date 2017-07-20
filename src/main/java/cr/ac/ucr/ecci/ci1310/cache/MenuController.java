package cr.ac.ucr.ecci.ci1310.cache;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by pjmq2 on 16/07/2017.
 */
public class MenuController {

    WikiPageServiceImpl wikipedia;

    public MenuController(){

    } //Constructor de la clase Menu

    public void menuPruebas() throws Throwable {
        System.out.println("Desea realizar las pruebas de eficiencia para la base de datos con cache?\nFavor escribir \"S\" o escriba \"n\" si no lo quiere realizar");
        String answer = "";
        Scanner into = new Scanner(System.in);
        answer = into.nextLine();
        boolean respuestaIncorrecta = true;
        while(respuestaIncorrecta){
            if(answer.equalsIgnoreCase("s")) {
                this.pruebas();
                this.iniciarBusqueda();
                respuestaIncorrecta=false;
            }
            else if(answer.equalsIgnoreCase("N")){
                this.iniciarBusqueda();
            }
            else{
                System.out.println("Favor escribir una letra correcta");
            }
        }
    }

    public void iniciarBusqueda() throws Throwable {
        WikiPageServiceImpl wikipediaTemp;
        boolean parametros = false;
        System.out.println("Bienvenido al buscador de paginas de Wikipedia \n");
        System.out.println("Desea utilizar Cache en sus busquedas? de ser asi favor escribir \"S\" o escriba \"n\" si no lo va a utilizar");
        String answer = "";
        Scanner into = new Scanner(System.in);
        answer = into.nextLine();
        if(answer.equalsIgnoreCase("s")){
            String nombreCache = "";
            int tamanoMaximo = 10;
            int vidaCache = 999999;
            int vidaDatos = 3600;
            System.out.println("Favor ingresar el nombre para el cache");
            nombreCache = into.nextLine();
            System.out.println("Desea ingresar un tamaño para el cache, favor digitar \"s\", cualquier otra letra se tomara como un No");
            answer = into.nextLine();
            if(answer.equalsIgnoreCase("S")){
                System.out.println("Favor ingresar el tamaño para el cache");
                boolean falloValor = true;
                while(falloValor) {
                    try {
                        falloValor = false;
                        tamanoMaximo = into.nextInt();
                    } catch (Exception e) {
                        System.out.println("Favor ingresar un numero Valido");
                        falloValor = true;
                    }
                }
            }
            System.out.println("Desea ingresar un tiempo de vida para el cache, favor digitar \"s\", cualquier otra letra se tomara como un No");
            answer = into.nextLine();
            if(answer.equalsIgnoreCase("S")){
                System.out.println("Favor ingresar el tiempo de vida para el cache");
                boolean falloValor = true;
                while(falloValor) {
                    try {
                        falloValor = false;
                        vidaCache = into.nextInt();
                    } catch (Exception e) {
                        System.out.println("Favor ingresar un número Valido");
                        falloValor = true;
                    }
                }
            }
            System.out.println("Desea ingresar un tiempo de vida para los datos, favor digitar \"s\", cualquier otra letra se tomara como un No");
            answer = into.nextLine();
            if(answer.equalsIgnoreCase("S")){
                System.out.println("Favor ingresar el tiempo de vida para los datos");
                boolean falloValor = true;
                while(falloValor) {
                    try {
                        falloValor = false;
                        vidaDatos = into.nextInt();
                    } catch (Exception e) {
                        System.out.println("Favor ingresar un número Válido");
                        falloValor = true;
                    }
                }
            }

            wikipediaTemp = new WikiPageServiceImpl(vidaDatos,nombreCache,tamanoMaximo, vidaCache);
        }
        else if(answer.equalsIgnoreCase("N")){
            wikipediaTemp = new WikiPageServiceImpl();
        }
        else{
            wikipediaTemp = null;
            System.out.print("Favor digite una letra correcta\n");
            this.iniciarBusqueda();
        }
        wikipedia = wikipediaTemp;
        this.busqueda();
    } //Inicia con las configuraciones necesarias para las busquedas de las paginas

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
    } //Realiza la busqueda de una pagina

    public void finalizar() throws Throwable {
        Scanner into = new Scanner(System.in);
        System.out.println("Desea realizar otra busqueda? \nFavor digitar \"S\" si desea realizar otra busqueda con la configuracion actual\nDigitar \"N\" si desea cambiar la configuracion de cache antes de la nueva busqueda\nDigitar \"E\" si desea salir del programa ");
        String respuesta = into.nextLine();
        if(respuesta.equalsIgnoreCase("S")){
            this.busqueda();
        }
        else if(respuesta.equalsIgnoreCase("N")){
            this.iniciarBusqueda();
        }
        else if(respuesta.equalsIgnoreCase("E")){
            System.exit(0);
        }
        else{
            System.out.println("Favor digitar una letra correcta");
            finalizar();
        }
    } //Finaliza el programa

    public void pruebas() throws SQLException, ClassNotFoundException {
        System.out.print("Bienvenido a la seccion de pruebas y estadisticas. A continuacion se realizaran las siguientes pruebas:\n");
        WikiPageServiceImpl serviceCache1 = new WikiPageServiceImpl(9999,"hola",30,1800);
        WikiPageServiceImpl serviceNormal = new WikiPageServiceImpl();
        WikiPage wiki = null;
        //Primero vamos a dejar cargadas ciertas paginas en el cache
        for(int i=0; i<10;i++){
            int p = i+673;
            int q = i+935;
            serviceNormal.searchId(""+p);
            serviceNormal.searchId(""+q);
        }

        //Ahora vamos a generar 2 arreglos para guardar las diferencias de tiempo para cada caso
        long[] diferenciaNormal1 = new long[30];
        long[] diferenciaCache1 = new long[30];
        //Tambien declaramos los LocalDateTime necesarios para medir las diferencias
        LocalDateTime antesCache = LocalDateTime.now();
        LocalDateTime despuesCache = LocalDateTime.now();
        LocalDateTime antesNormal = LocalDateTime.now();
        LocalDateTime despuesNormal = LocalDateTime.now();

        //Sabemos cuales paginas estan cargadas en cache, por lo tanto vamos a hacer las 20 pruebas para paginas que ya estan cargadas
        for(int i=0; i<10;i++){
            int p = i+673;
            int q = i+935;
            //El ciclo hace los primeros 10 datos
            antesCache = LocalDateTime.now();
            serviceCache1.searchId(""+p);
            despuesCache = LocalDateTime.now();
            antesNormal = LocalDateTime.now();
            serviceNormal.searchId(""+p);
            despuesNormal = LocalDateTime.now();
            diferenciaCache1[i]=ChronoUnit.MILLIS.between(antesCache, despuesCache);
            diferenciaNormal1[i]=ChronoUnit.MILLIS.between(antesNormal, despuesNormal);
            //El ciclo hace los segundos 10 datos
            antesCache = LocalDateTime.now();
            serviceCache1.searchId(""+q);
            despuesCache = LocalDateTime.now();
            antesNormal = LocalDateTime.now();
            serviceNormal.searchId(""+q);
            despuesNormal = LocalDateTime.now();
            diferenciaCache1[10+i]=ChronoUnit.MILLIS.between(antesCache, despuesCache);
            diferenciaNormal1[10+i]=ChronoUnit.MILLIS.between(antesNormal, despuesNormal);
        }
        //Ya tenemos un arreglo con lo que se duro obteniendo cada una de las 20 paginas con y sin cache
        //Ahora vamos a ver cual es el promedio de tiempo con y sin cache
        long promCache=0;
        long promNormal=0;
        for (int i = 0; i<20; i++){
            promCache+=diferenciaCache1[i];
            promNormal+=diferenciaNormal1[i];
            System.out.println("Cache #"+i+" duro: "+diferenciaCache1[i]);
            System.out.println("Normal #"+i+" duro: "+diferenciaNormal1[i]);
        }
        promCache = promCache / 20;
        promNormal = promNormal / 20;
        System.out.println("En promedio se dura "+promNormal+" milisegundos en realizar una busqueda sin cache" );
        System.out.println("En promedio se dura "+promCache+" milisegundos en realizar una busqueda con cache" );
        System.out.println("\nComo vemos la diferencia entre usar cache y no usarlo en una sola busqueda no es tan grande\npor lo tanto vamos a tomar el tiempo que se dura realizando 5 busquedas seguidas");

        //Vamos a llenar el cache con las 50 paginas nuevas
        for(int i=0; i<5;i++){
            int a = i+980;
            int b = i+1206;
            int c = i+1009;
            int d = i+1016;
            int e = i+1026;
            int f = i+1227;
            int g = i+1151;
            int h = i+1166;
            int j = i+1192;
            int k = i+1197;
            serviceCache1.searchId(""+a);
            serviceCache1.searchId(""+b);
            serviceCache1.searchId(""+c);
            serviceCache1.searchId(""+d);
            serviceCache1.searchId(""+e);
            serviceCache1.searchId(""+f);
            serviceCache1.searchId(""+g);
            serviceCache1.searchId(""+h);
            serviceCache1.searchId(""+j);
            serviceCache1.searchId(""+k);
        }

        //Vamos a hacer las pruebas para grupos de 5 datos, al ser de rangos diferentes no se puede usar un ciclo que lo abarque a todo y se va a tener que hacer manualmente

        /*
        * Grupo 1
        */
        antesCache = LocalDateTime.now();
        for(int i=980; i<985;i++){
            serviceCache1.searchId(""+i);
        }
        despuesCache = LocalDateTime.now();
        antesNormal = LocalDateTime.now();
        for(int i=980; i<985;i++){
            serviceNormal.searchId(""+i);
        }
        despuesNormal = LocalDateTime.now();
        diferenciaCache1[20]=ChronoUnit.MILLIS.between(antesCache, despuesCache);
        diferenciaNormal1[20]=ChronoUnit.MILLIS.between(antesNormal, despuesNormal);

        /*
        * Grupo 2
        */
        antesCache = LocalDateTime.now();
        for(int i=1206; i<1211;i++){
            serviceCache1.searchId(""+i);
        }
        despuesCache = LocalDateTime.now();
        antesNormal = LocalDateTime.now();
        for(int i=1206; i<1211;i++){
            serviceNormal.searchId(""+i);
        }
        despuesNormal = LocalDateTime.now();
        diferenciaCache1[21]=ChronoUnit.MILLIS.between(antesCache, despuesCache);
        diferenciaNormal1[21]=ChronoUnit.MILLIS.between(antesNormal, despuesNormal);

        /*
        * Grupo 3
        */
        antesCache = LocalDateTime.now();
        for(int i=1009; i<1014;i++){
            serviceCache1.searchId(""+i);
        }
        despuesCache = LocalDateTime.now();
        antesNormal = LocalDateTime.now();
        for(int i=1009; i<1014;i++){
            serviceNormal.searchId(""+i);
        }
        despuesNormal = LocalDateTime.now();
        diferenciaCache1[22]=ChronoUnit.MILLIS.between(antesCache, despuesCache);
        diferenciaNormal1[22]=ChronoUnit.MILLIS.between(antesNormal, despuesNormal);

        /*
        * Grupo 4
        */
        antesCache = LocalDateTime.now();
        for(int i=1016; i<1021;i++){
            serviceCache1.searchId(""+i);
        }
        despuesCache = LocalDateTime.now();
        antesNormal = LocalDateTime.now();
        for(int i=1016; i<1021;i++){
            serviceNormal.searchId(""+i);
        }
        despuesNormal = LocalDateTime.now();
        diferenciaCache1[23]=ChronoUnit.MILLIS.between(antesCache, despuesCache);
        diferenciaNormal1[23]=ChronoUnit.MILLIS.between(antesNormal, despuesNormal);

        /*
        * Grupo 5
        */
        antesCache = LocalDateTime.now();
        for(int i=1026; i<1031;i++){
            serviceCache1.searchId(""+i);
        }
        despuesCache = LocalDateTime.now();
        antesNormal = LocalDateTime.now();
        for(int i=1026; i<1031;i++){
            serviceNormal.searchId(""+i);
        }
        despuesNormal = LocalDateTime.now();
        diferenciaCache1[24]=ChronoUnit.MILLIS.between(antesCache, despuesCache);
        diferenciaNormal1[24]=ChronoUnit.MILLIS.between(antesNormal, despuesNormal);

        /*
        * Grupo 6
        */
        antesCache = LocalDateTime.now();
        for(int i=1227; i<1232;i++){
            serviceCache1.searchId(""+i);
        }
        despuesCache = LocalDateTime.now();
        antesNormal = LocalDateTime.now();
        for(int i=1227; i<1232;i++){
            serviceNormal.searchId(""+i);
        }
        despuesNormal = LocalDateTime.now();
        diferenciaCache1[25]=ChronoUnit.MILLIS.between(antesCache, despuesCache);
        diferenciaNormal1[25]=ChronoUnit.MILLIS.between(antesNormal, despuesNormal);

        /*
        * Grupo 7
        */
        antesCache = LocalDateTime.now();
        for(int i=1151; i<1156;i++){
            serviceCache1.searchId(""+i);
        }
        despuesCache = LocalDateTime.now();
        antesNormal = LocalDateTime.now();
        for(int i=1151; i<1156;i++){
            serviceNormal.searchId(""+i);
        }
        despuesNormal = LocalDateTime.now();
        diferenciaCache1[26]=ChronoUnit.MILLIS.between(antesCache, despuesCache);
        diferenciaNormal1[26]=ChronoUnit.MILLIS.between(antesNormal, despuesNormal);

        /*
        * Grupo 8
        */
        antesCache = LocalDateTime.now();
        for(int i=1166; i<1171;i++){
            serviceCache1.searchId(""+i);
        }
        despuesCache = LocalDateTime.now();
        antesNormal = LocalDateTime.now();
        for(int i=1166; i<1171;i++){
            serviceNormal.searchId(""+i);
        }
        despuesNormal = LocalDateTime.now();
        diferenciaCache1[27]=ChronoUnit.MILLIS.between(antesCache, despuesCache);
        diferenciaNormal1[27]=ChronoUnit.MILLIS.between(antesNormal, despuesNormal);
/*
        * Grupo 9
        */
        antesCache = LocalDateTime.now();
        for(int i=1192; i<1197;i++){
            serviceCache1.searchId(""+i);
        }
        despuesCache = LocalDateTime.now();
        antesNormal = LocalDateTime.now();
        for(int i=1192; i<1197;i++){
            serviceNormal.searchId(""+i);
        }
        despuesNormal = LocalDateTime.now();
        diferenciaCache1[28]=ChronoUnit.MILLIS.between(antesCache, despuesCache);
        diferenciaNormal1[28]=ChronoUnit.MILLIS.between(antesNormal, despuesNormal);

        /*
        * Grupo 10
        */
        antesCache = LocalDateTime.now();
        for(int i=1197; i<1202;i++){
            serviceCache1.searchId(""+i);
        }
        despuesCache = LocalDateTime.now();
        antesNormal = LocalDateTime.now();
        for(int i=1197; i<1202;i++){
            serviceNormal.searchId(""+i);
        }
        despuesNormal = LocalDateTime.now();
        diferenciaCache1[29]=ChronoUnit.MILLIS.between(antesCache, despuesCache);
        diferenciaNormal1[29]=ChronoUnit.MILLIS.between(antesNormal, despuesNormal);

        //Ahora vamos a sacar cuanto duraba en promedio en leer los 5 datos
        for(int i=0; i<5; i++){
            int p=20+i;
            promCache += diferenciaCache1[p];
            promNormal += diferenciaNormal1[p];
            System.out.println("Cache #"+p+" duro: "+diferenciaCache1[p]);
            System.out.println("Normal #"+p+" duro: "+diferenciaNormal1[p]);
        }
        promCache = promCache /20;
        promNormal = promNormal /20;
        System.out.println("En promedio leyendo 5 paginas sin cache duro: "+promNormal+" milisegundos");
        System.out.println("En promedio leyendo 5 paginas con cache duro: "+promCache+" milisegundos");
    } //Realiza las pruebas y experimentos necesarios

}

