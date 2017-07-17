package cr.ac.ucr.ecci.ci1310.cache;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by pjmq2 on 16/07/2017.
 */
public class WikiPageServiceImpl implements WikiPageService {
    private WikiPageDaoImpl dao;
    private boolean useCache;

    public WikiPageServiceImpl(boolean use) {
        useCache = use;
        dao = new WikiPageDaoImpl();
    }

    public WikiPage searchId(String id) throws SQLException, ClassNotFoundException {
        WikiPage wiki = null;
        if(useCache){

        }
        else{
            wiki = dao.searchId(id);
        }
        return wiki;
    }

    public ArrayList<WikiPage> searchName(String name) throws SQLException, ClassNotFoundException {
        ArrayList<WikiPage> list = new ArrayList<WikiPage>();
        if(useCache){

        }else {
            list = dao.searchName(name);
        }
        return list;
    }
}
