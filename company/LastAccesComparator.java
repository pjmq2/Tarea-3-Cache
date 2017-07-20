package com.company;

import java.util.Comparator;

 class LastAccessComparator<K> implements Comparator<Object> //Comparador para oredenar en la cola de prioridad, se hace mediante el ultimo acceso
{
    public int compare(Object object1, Object object2) {

        IDLastAccess<K> Data1 = (IDLastAccess<K> )object1;
        IDLastAccess<K>  Data2 = (IDLastAccess<K> )object2;

        if (Data1.LastAccess.isAfter(Data2.LastAccess))//El primer tiempo es reciente
        {
            return 1;
        }
        if (Data1.LastAccess.isAfter(Data2.LastAccess)) //El segundo tiempo es reciente
        {
            return -1;
        }
        return 0; //Son iguales
    }

}