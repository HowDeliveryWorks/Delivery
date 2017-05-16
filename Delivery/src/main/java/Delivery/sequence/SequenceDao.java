package Delivery.sequence;

/**
 * Created by LevelNone on 17.05.2017.
 */
public interface SequenceDao {
    long getNextSequenceId(String key) throws SequenceException;
}