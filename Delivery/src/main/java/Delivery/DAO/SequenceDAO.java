package Delivery.DAO;

import Delivery.sequence.SequenceException;

/**
 * Created by LevelNone on 17.05.2017.
 */
public interface SequenceDAO {
    long getNextSequenceId(String key) throws SequenceException;
}