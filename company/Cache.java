package com.company;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.time.LocalDateTime;
import java.util.*;
import java.lang.*;

public class Cache<K,V> {
    private String name; //Nombre del cache
    private int size; //Tamaño máximo del cache
    private int lifeTime; //Segundos que puede permanecer una pagina sin ser accesada
    private int tiempoClearLapso; //Segundos que hay para limpiar el cache
    private int contador; //Cuenta la cantidad de paginas que hay en el cache
    private LocalDateTime tiempoClear; //Almacena la fecha del procximo vaceo del cache
    private Map<K,Data<K,V>> mapa; //Mapeo del contenido con su respectivo K
    private PriorityQueue<IDLastAccess<K>> colaUltimoAcceso ; //Almacena el K junto a su ultimo acceso, para eliminar aquellas en donde se les vencio el tiempo en Cache

    /*Se crea los distintos constructores del cache*/

    public Cache (String nam, int siz)
    {
        contador=0;
        this.name=nam;
        this.size=siz;
        this.lifeTime=3600;
        this.tiempoClearLapso=Integer.MAX_VALUE;
        this.tiempoClear=LocalDateTime.now().plusSeconds(tiempoClearLapso); //la hora de borrado sera infinito

        mapa= new HashMap<K,Data<K,V>>();
        Comparator comparator= new LastAccessComparator();
        colaUltimoAcceso = new PriorityQueue<IDLastAccess<K>>(comparator);
    }

    public Cache (int lifeTim, String nam, int siz)
    {   contador=0;
        this.lifeTime=lifeTim;
        this.name=nam;
        this.size=siz;
        this.tiempoClearLapso=Integer.MAX_VALUE;
        this.tiempoClear=LocalDateTime.now().plusSeconds(tiempoClearLapso); //la hora de borrado sera infinito

        mapa= new HashMap <K,Data<K,V>> ();
        Comparator comparator= new LastAccessComparator();
        colaUltimoAcceso = new PriorityQueue<IDLastAccess<K>>(size, comparator);
    }
    public Cache (int lifeTim, String nam, int siz, int tiempoClearLaps)
    {   contador=0;
        this.lifeTime=lifeTim;
        this.name=nam;
        this.size=siz;
        this.tiempoClearLapso=tiempoClearLaps;
        this.tiempoClear=LocalDateTime.now().plusSeconds(tiempoClearLaps); //Le suma a la hora actual los segundos

        mapa= new HashMap <K,Data<K,V>> ();
        Comparator comparator= new LastAccessComparator();
        colaUltimoAcceso = new PriorityQueue<IDLastAccess<K>>(size, comparator);
    }
    public Cache (String nam, int siz, int tiempoClearLaps)
    {   contador=0;
        this.name=nam;
        this.size=siz;
        this.lifeTime=3600; // Default una hora
        this.tiempoClearLapso=tiempoClearLaps;
        this.tiempoClear=LocalDateTime.now().plusSeconds(tiempoClearLaps); //Le suma a la hora actual los segundos

        mapa= new HashMap <K,Data<K,V>> ();
        Comparator comparator= new LastAccessComparator();
        colaUltimoAcceso = new PriorityQueue<IDLastAccess<K>>(size, comparator);
    }

    /*Metodos*/

    public String getName() //Retorna el nombre del Cache
    {return name;}

    public V getContentInterno(K var1) //Devuelve el contenido de una pagina y actualiza el ultimo acceso de la pagina consultada
    {IDLastAccess Elegido= new IDLastAccess(var1,LocalDateTime.now()); //El que se va a eliminar
        IDLastAccess newElement= new IDLastAccess(var1,LocalDateTime.now()); //El nuevo que va a entrar en la cola

        mapa.get(var1).setLastAccesTime(); //Se actualiza el dato en el mapa
        colaUltimoAcceso.remove(Elegido); //Elimina de la cola de ultimo acceso
        colaUltimoAcceso.add(newElement); //Añade el nuevo (Actualiza el ultimo acceso)

        return mapa.get(var1).getContent();
    }

    public int getContador() //Devuelve la cantidad de paginas existentes en el cache
    {return contador;}

    public void evict(K var1) //Elimina la pagina del mapa y el identificador cola
    {
        mapa.remove(var1);
        IDLastAccess Elegido=new IDLastAccess(var1,LocalDateTime.now());
        colaUltimoAcceso.remove(Elegido);
        --contador;}

    public boolean tiempoClearCacheInterno() {
        if (LocalDateTime.now().isAfter(tiempoClear)) { //Si se paso del tiempo de limpieza
            System.out.println("Se acabo el tiempo de vida del cache");
            colaUltimoAcceso.clear();
            mapa.clear();
            contador=0;
            tiempoClear=LocalDateTime.now().plusSeconds(tiempoClearLapso);
            return true; //Si se limpio el cache
        }
        return false; //No se limpio el cache
    } //Chequea si debe vacearse el cache, limpia el mapa y la cola y devuelve un true si esto sucede

    public Stack<K> lifeTimeMembers() //Elimina las paginas que sobrepasaron el tiempo de vida y devuelve una lista con sus respecticos K
    { IDLastAccess<K> elegido;
        Stack<K> sobrepasaron=new Stack<K>();
        while ((colaUltimoAcceso.size()!=0)&&(!(LocalDateTime.now().isBefore(colaUltimoAcceso.peek().LastAccess.plusSeconds(lifeTime))))) //Si no esta vacia y la hora actual es mayor o igual que la hora del ultimo acceso más el tiempo de permite sin consultas
        {
            sobrepasaron.push(colaUltimoAcceso.peek().id);
            evict(colaUltimoAcceso.poll().id); //Saquelo de la pila y eliminelo
        }
        return sobrepasaron;  //Devuelva la lista de los que hay que eliminar
    }

    public void putInterno(K var1, V var2) //Mete la pagina el mapacy el identificador en la cola
    { Data newData = new Data (var2,var1);
        newData.setLastAccesTime(); //Se encapsula V+k a Dato

        IDLastAccess newIDLastAcces = new IDLastAccess (var1,LocalDateTime.now());  //Se encapsula V+Ultimo Acceso a Dato

        mapa.put(var1,newData);
        colaUltimoAcceso.add(newIDLastAcces);
        ++contador;}

    public boolean lleno() //Devuelve un bool si el cache esta lleno
    {if (size==contador)
    {return true;}
    else
    {return false;}
    }

    public IDLastAccess first() //Devuelve el primer elemento en cola
    {return colaUltimoAcceso.peek();}

    public boolean existInterno(K id) //Devuelve true si dicha pagina existe en el cache
    {
        return mapa.containsKey(id);
    }
} //Principal, en el cual todas las implementaciones del cache heredan