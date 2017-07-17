package cr.ac.ucr.ecci.ci1310.cache;

import java.math.BigDecimal;

/**
 * Created by pjmq2 on 15/07/2017.
 */
public class WikiPage {
    private int id;
    private int namespace;
    private String title;
    private int counter;
    private int is_redirect;
    private int is_new;
    private double random;
    private int latest;
    private int len;

    public WikiPage(int id, int namespace, String title, int counter, int is_redirect, int is_new, double random, int latest, int len) {
        this.id = id;
        this.namespace = namespace;
        this.title = title;
        this.counter = counter;
        this.is_redirect = is_redirect;
        this.is_new = is_new;
        this.random = random;
        this.latest = latest;
        this.len = len;
    }

    public int getId() {
        return id;
    }

    public int getNamespace() {
        return namespace;
    }

    public String getTitle() {
        return title;
    }

    public int getCounter() {
        return counter;
    }

    public int getIs_redirect() {
        return is_redirect;
    }

    public int getIs_new() {
        return is_new;
    }

    public double getRandom() {
        return random;
    }

    public int getLatest() {
        return latest;
    }

    public int getLen() {
        return len;
    }

    public void print(){
        String mensaje = "Wikipage: \n";
        mensaje += "id: " + id + "\n";
        mensaje += "Namespace: " + namespace + "\n";
        mensaje += "Title: " + title + "\n";
        mensaje += "Counter: " + counter + "\n";
        mensaje += "Is_redirect: " + is_redirect + "\n";
        mensaje += "Is_new: " + is_new + "\n";
        mensaje += "Random: " + random + "\n";
        mensaje += "Latest: " + latest + "\n";
        mensaje += "len: " + len + "\n";
        System.out.print(mensaje);
    }
}

/*
1 - page_id int(10) UN AI PK
2 - page_namespace int(11)
3 - page_title varchar(255)
4 - NO SE TRATARA page_restrictions tinyblob
5 - page_counter bigint(20) UN
6 - page_is_redirect tinyint(3) UN
7 - page_is_new tinyint(3) UN
8 - page_random double UN
9 - NO SE TRATARA page_touched binary(14)
10 - page_latest int(10) UN
11 - page_len int(10) UN
* */