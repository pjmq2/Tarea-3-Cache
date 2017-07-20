package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static java.lang.Thread.sleep;

/**
 * Created by benja on 20/7/2017.
 */
public class FIFOCache<K,V> extends Cache<K,V> implements Runnable {

    public Queue<K> cola;
    Object monitorCache = new Object(); //Se utiliza para el manejo de la concurrencia con el chequeo de limpiar la cola y LifeTime cor

     /*Constructores*/

    public FIFOCache(String nam, int siz) {

        super(nam, siz);
        this.cola = new LinkedList<K>();

        Thread Thread1 = new Thread(this::run); //Crea el thread adicional
        Thread1.start();


    }

    public FIFOCache(int lifeTim, String nam, int siz) throws InterruptedException {
        super(lifeTim, nam, siz);
        this.cola = new LinkedList<K>();

        Thread Thread1 = new Thread(this::run); //Crea el thread adicional
        Thread1.start();

    }

    public FIFOCache(int lifeTim, String nam, int siz, int tiempoClearLaps) {
        super(lifeTim, nam, siz, tiempoClearLaps);
        this.cola = new LinkedList<K>();

        Thread Thread1 = new Thread(this::run); //Crea el thread adicional
        Thread1.start();

    }

    public FIFOCache(String nam, int siz, int tiempoClearLaps) {
        super(nam, siz, tiempoClearLaps);
        this.cola = new LinkedList<K>();

        Thread Thread1 = new Thread(this::run); //Crea el thread adicional
        Thread1.start();
    }

        /*Metodos*/

    public void put(K var1, V var2) {
        K elegido;
        synchronized (monitorCache) {
            if (super.lleno()) //La entrada más antigua se debe eliminar
            {
                elegido = cola.poll();
                evict(elegido);
            }
            super.putInterno(var1, var2);
            cola.add(var1);
        }
    } //Pregunta si esta lleno, si es así se debe eliminar la pagina del cache segun la disciplina e insertar la nueva pagina

    public void lifeTimeMethod() throws InterruptedException //Elimina a aquellos que ha sobrepasado el tiempo de vida
    { Stack<K> elegidos;

        System.out.println("Entre al Life Metod");

        elegidos = super.lifeTimeMembers();

        while (elegidos.size() != 0) {

            cola.remove(elegidos.pop());
        }

    }

    public void clear() throws InterruptedException {
        boolean hayQueLimpiar; //Controla si hay que limpiar la cola o no
        System.out.println("Entre a Clear");

        hayQueLimpiar = super.tiempoClearCacheInterno();
        if (hayQueLimpiar) {
            System.out.println("Se limpio el cache");
            cola.clear();
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
                try {
                    clear();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    lifeTimeMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    } //Thread que corre desde que se inicia el cache, verifica tanto el tiempo de vida como la fecha donde se debe vacear el cachce

} //Hereda de la clase cache, y posee el run para ejecutar otro hilo simultaneamente.