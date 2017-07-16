package cr.ac.ucr.ecci.ci1310.cache;

/**
 * Created by Pablo José Madrigal on 15/07/2017.
 */
public class WikiPageServiceImpl implements WikiPageService {
    private boolean useCache;
    public WikiPageServiceImpl(boolean cache){
        useCache = cache;
    }
    public WikiPageServiceImpl(){
        useCache = true;
    }
    public void searchId(String id){
        if(useCache){

        }
    }
    public void serachName(String name){
        if(useCache){

        }
    }
}
