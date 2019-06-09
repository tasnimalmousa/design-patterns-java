package singleton.innerstaticsingleton;


//To avoid synchronization and it is thread safe
public class InnerStaticSingleton {

    public InnerStaticSingleton(){

    }

    private static class Imp{
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    private InnerStaticSingleton getInstance(){
        return Imp.INSTANCE;
    }
}
