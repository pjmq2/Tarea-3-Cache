package com.company;

import java.time.LocalDateTime;

public class IDLastAccess<K>{ //Asemeja un Struct que se utiliza para guardar en la cola
    public K id; //K del wiki Page
    public LocalDateTime LastAccess; //Ultimo acceso del wikipage

    IDLastAccess (K name, LocalDateTime LastAcc)
    {this.id=name;
        this.LastAccess=LastAcc;}

    public boolean equals(Object o){ //Comparador del objeto, en donde se ignora el ultimo acceso
        IDLastAccess c = (IDLastAccess)o;
        if (this.id==c.id)
        {return true;}

        return false;
    }

}