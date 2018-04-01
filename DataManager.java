package example;

import java.util.List;

public abstract class DataManager {
	//author harsha
    public abstract List<User> getData();

    public static DataReader1 getDataManager(){
        return new DataReader1();
    }

}




