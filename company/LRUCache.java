package com.company;

import java.time.LocalDateTime;

import static java.lang.Thread.sleep;

/**
 * Created by benja on 20/7/2017.
 */
public class LRUCache<K,V> extends Cache<K,V> implements Runnable {
    Object monitorCache = new Object(); //Se utiliza para el manejo de la concurrencia con el chequeo de limpiar la cola y LifeTime cor

    /*Constructores*/

    public LRUCache(String nam, int siz) {
        super(nam, siz);
        Thread Thread1 = new Thread(this::run); //Crea el thread adicional
        Thread1.start();
    }

    public LRUCache(int lifeTim, String nam, int siz) {
        super(lifeTim, nam, siz);
        Thread Thread1 = new Thread(this::run); //Crea el thread adicional
        Thread1.start();
    }

    public LRUCache(int lifeTim, String nam, int siz, int tiempoClearLaps) {
        super(lifeTim, nam, siz, tiempoClearLaps);
        Thread Thread1 = new Thread(this::run); //Crea el thread adicional
        Thread1.start();
    }

    public LRUCache(String nam, int siz, int tiempoClearLaps) {
        super(nam, siz, tiempoClearLaps);
        Thread Thread1 = new Thread(this::run); //Crea el thread adicional
        Thread1.start();
    }

    /*Metodos*/

    public void put(K var1, V var2) {
        IDLastAccess<K> elegido; //Si se elimina
        synchronized (monitorCache) {

            if (super.lleno()) //Se debe eliminar el ultimo acceeso
            {
                IDLastAccess nuevo = new IDLastAccess(var1, LocalDateTime.now()); //El que va a entrar
                elegido = super.first();
                evict(elegido.id);
            }
            super.putInterno(var1, var2);
        }
    } //Pregunta si esta lleno, si es así se debe eliminar la pagina del cache segun la disciplina e insertar la nueva pagina

    public void lifeTimeMethod() //Elimina a aquellos que ha sobrepasado el tiempo de vida.
    {
        super.lifeTimeMembers();
    }

    public void clear() {

        super.tiempoClearCacheInterno();

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