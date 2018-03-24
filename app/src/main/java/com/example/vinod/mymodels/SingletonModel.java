package com.example.vinod.mymodels;

import java.io.Serializable;

/**
 * Created by Vinod on 3/24/2018.
 */

public class SingletonModel implements Serializable{

    private static volatile SingletonModel singletonModel;

    private SingletonModel(){
        if (singletonModel !=null)
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
    }

    public static SingletonModel getInstance(){
        if (singletonModel==null){
            synchronized (SingletonModel.class){
                if (singletonModel==null) singletonModel=new SingletonModel();
            }
        }
        return singletonModel;
    }

    //Make singleton from serialize and deserialize operation.
    protected SingletonModel readResolve() {
        return getInstance();
    }
}
