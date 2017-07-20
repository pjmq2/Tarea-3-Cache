package cr.ac.ucr.ecci.ci1310.cache;

import java.util.ArrayList;

/**
 * Created by pjmq2 on 19/07/2017.
 */
public class CacheTemp {
    WikiPage wiki;
    ArrayList<WikiPage> wikipedia;
    public CacheTemp() {
        wiki = new WikiPage(1,1,"1",1,1,1,1,1,1);
        wikipedia = new ArrayList<WikiPage>();
    }
    public boolean exist(String entrada){
        return true;
    }

    public WikiPage getcontent(String uno){
        return wiki;
    }

    public ArrayList<WikiPage> getContent(String uno){
        return wikipedia;
    }

    public void put (String llave, WikiPage valor){

    }

    public void put (String llave, ArrayList<WikiPage> valor){

    }
}
