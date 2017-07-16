package cr.ac.ucr.ecci.ci1310.cache;

/**
 * Created by pjmq2 on 15/07/2017.
 */
public interface WikiPageDao {
    public abstract WikiPage searchId(String id);
    public abstract WikiPage searchName(String name);
}
