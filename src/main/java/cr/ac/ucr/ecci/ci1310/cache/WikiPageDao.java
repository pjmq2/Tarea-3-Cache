package cr.ac.ucr.ecci.ci1310.cache;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by pjmq2 on 15/07/2017.
 */
public interface WikiPageDao {
    public abstract WikiPage searchId(String id) throws SQLException, ClassNotFoundException;
    public abstract ArrayList<WikiPage> searchName(String name) throws SQLException, ClassNotFoundException;
}
