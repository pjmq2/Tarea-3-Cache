package com.company;
import java.time.LocalDateTime;

public class Data<K,V> { //Almacena K+V+ultimoAcces, así como sus metodos

    private LocalDateTime lastAccesTime; //Ultimo acceso a la Wikipage
    private V content;
    private K id;

    public Data (V valor, K nombre)
    {this.content=valor;
        this.id=nombre;}

    public V getContent() //Obtener el contenido del wikipage
    {return this.content;}

    public K getId() //Obtener el K
    {return this.id;}

    public LocalDateTime getLastAccesTime() //Obtener el ultimo acceso
    {return this.lastAccesTime;}

    public void setLastAccesTime() //Actualiza el ultimo acceso
    {lastAccesTime=LocalDateTime.now();}

    public boolean equals(Object o){

        Data data2 = (Data)o;
        if (this.getId()==data2.getId())
        {return true;}

        return false;
    } //Implementacion para comparar este objeto, se hace por K


}