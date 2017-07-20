package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import static java.lang.Thread.sleep;

/**
 * Created by benja on 20/7/2017.
 */
public class RANDOMCache<K,V> extends Cache<K,V> implements Runnable
{
    public List<K> lista;
    Object monitorCache = new Object(); //Se utiliza para el manejo de la concurrencia con el chequeo de limpiar la cola y LifeTime cor

    /*Constructores*/

    public RANDOMCache (String nam, int siz)
    {
        super(nam,siz);
        lista=new LinkedList<K>();
        Thread Thread1 = new Thread(this::run); //Crea el thread adicional
        Thread1.start();

    }
    public RANDOMCache (int lifeTim, String nam, int siz)
    {
        super(lifeTim,nam,siz);
        lista=new LinkedList<K>();
        Thread Thread1 = new Thread(this::run); //Crea el thread adicional
        Thread1.start();

    }
    public RANDOMCache(int lifeTim, String nam, int siz, int tiempoClearLaps)
    {
        super(lifeTim,nam,siz,tiempoClearLaps);
        lista=new LinkedList<K>();
        Thread Thread1 = new Thread(this::run); //Crea el thread adicional
        Thread1.start();

    }
    public RANDOMCache (String nam, int siz, int tiempoClearLaps)
    {
        super(nam,siz,tiempoClearLaps);
        lista=new LinkedList<K>();
        Thread Thread1 = new Thread(this::run); //Crea el thread adicional
        Thread1.start();

    }

    /*Metodos*/

    public void put (K var1,V var2) {
        K elegido;
        Random rnd;
        int pos;

        synchronized (monitorCache) {

            if (super.lleno()) //La al azar se debe eliminar
            {
                rnd = new Random();
                pos = (int) (rnd.nextDouble() * getContador());
                elegido = lista.remove(pos);
                evict(elegido);
            }
            lista.add(var1);
            super.putInterno(var1, var2);
        }
    } //Pregunta si esta lleno, si es así se debe eliminar la pagina del cache segun la disciplina

    public void lifeTimeMethod() //Elimina a aquellos que ha sobrepasado el tiempo de vida
    {
        Stack<K> elegidos;

        elegidos = super.lifeTimeMembers();

        while (elegidos.size() != 0) {
            lista.remove(elegidos.pop());
        }

    }

    public void clear() {

        boolean hayQueLimpiar; //Controla si hay que limpiar la cola o no
        hayQueLimpiar = super.tiempoClearCacheInterno();
        if (hayQueLimpiar) {
            lista.clear();
        }

    } //Vacia la cola si ha pasado la fecha de vaceo y actualiza la fecha para un nuevo vaceo

    public boolean exist(K id) //Devuelve true si la pagina K existe en el cache
    {  synchronized (monitorCache) {
        return super.existInterno(id);
    }
    }
    public V getContent(K var1) //Devuelve el contenido de la pagina y se actualiza su ultimo acceso
    {  synchronized (monitorCache) {
        return super.getContentInterno(var1);
    }
    }

    public void run () {
        while (true) {
            synchronized (monitorCache) {

                clear();


                lifeTimeMethod();

            }
            try {
                sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }//Thread que corre desde que se inicia el cache, verifica tanto el tiempo de vida como la fecha donde se debe vacear el cachce

} //Hereda de la clase cache, y posee el run para ejecutar otro hilo simultaneamente.