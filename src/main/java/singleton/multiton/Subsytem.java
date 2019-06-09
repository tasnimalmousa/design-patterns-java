package singleton.multiton;

import java.util.HashMap;

//you can have a finite set of instances

//we can have only three instances
public enum Subsytem{
    PRIMARY,
    AUXULIARY,
    FALLBACK

}

class Printer {
    private static HashMap<Subsytem, Printer> instances = new HashMap<>();

    private static int instancesCount = 0;

    private Printer(){
        instancesCount++;
        System.out.println("A total number of " + instancesCount + " created so far.");
    }

    public static Printer get(Subsytem subsytem){

        //lazy loading
        if(!instances.containsKey(subsytem)){
            instances.put(subsytem, new Printer());
        }

        return instances.get(subsytem);
    }
}

class Demo{
    public static void main(String[] args) {
        Printer main = Printer.get(Subsytem.PRIMARY);
        Printer secondary = Printer.get(Subsytem.AUXULIARY);
        Printer secondary2 = Printer.get(Subsytem.AUXULIARY);
    }
}