package Delivery.DAO;

import Delivery.services.SequenceException;

public interface SequenceDAO {
    long getNextSequenceId(String key) throws SequenceException;
}