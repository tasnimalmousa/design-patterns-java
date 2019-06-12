package singleton.lazysingleton;

public class LazySingletone {

    private static LazySingletone instance;


//    //Race condition
//    public static LazySingletone getInstance(){
//        if(instance == null){
//            instance = new LazySingletone();
//        }
//
//        return instance;
//    }

//    //performance issue
//    public static synchronized LazySingletone getInstance(){
//        if(instance == null){
//            instance = new LazySingletone();
//        }
//
//        return instance;
//    }

    //double-checked locking - lazy and thread safe
    public static LazySingletone getInstance(){
        if(instance == null){
            synchronized (LazySingletone.class){
                if(instance == null){
                    instance = new LazySingletone();
                }
            }
        }

        return instance;
    }
}
