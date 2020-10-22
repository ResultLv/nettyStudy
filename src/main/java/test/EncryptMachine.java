package test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Result Lv
 * @date 2020/9/1 10:52 下午
 */
public class EncryptMachine {

    private String name;

    private EncryptMachine(){};

    private enum Singleton{
        INSTANCE;
        private final EncryptMachine encryptMachine;
        Singleton(){
            encryptMachine = new EncryptMachine();
        }
    }

    public static EncryptMachine getInstance(){
        return Singleton.INSTANCE.encryptMachine;
    }

}
