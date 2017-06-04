package Delivery.sequence;

/**
 * Created by LevelNone on 17.05.2017.
 */
public class SequenceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errCode;

    //get, set...
    public SequenceException(String errMsg) {
        String errMsg1 = errMsg;
    }

}