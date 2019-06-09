package singleton.basic;

import lombok.*;

import java.io.*;

@ToString
public class BasicSingleton implements Serializable{

    @Getter
    @Setter
    private int value = 0;

    private BasicSingleton(){

    }

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    public static BasicSingleton getINSTANCE() {
        return INSTANCE;
    }

    public Object readResolve(){
        return INSTANCE;
    }
}

class Demo{

    public static void writeToFile(BasicSingleton basicSingleton, String filename) throws Exception{
        try(
                FileOutputStream fileOutputStream = new FileOutputStream(filename);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                ) {
            objectOutputStream.writeObject(basicSingleton);
        }
    }

    public static BasicSingleton readFromFile(String filename) throws Exception{
        try(
                FileInputStream fileInputStream = new FileInputStream(filename);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ) {
            return (BasicSingleton) objectInputStream.readObject();
        }
    }
    public static void main(String[] args) throws Exception {
//        BasicSingleton basicSingleton = BasicSingleton.getINSTANCE();
//        basicSingleton.setValue(1);
//
//        System.out.println(basicSingleton);

        //issues
        //1. reflections will allow people to make new instances .newInstance() even if const is private
        //2. serialization - like read/write to file will create new instance - solved by adding readResolve()


        BasicSingleton basicSingleton1 = BasicSingleton.getINSTANCE();
        writeToFile(basicSingleton1, "singelton.bin");

        basicSingleton1.setValue(11);

        BasicSingleton basicSingleton2 = readFromFile("singelton.bin");

        System.out.println(basicSingleton1 == basicSingleton2);

        System.out.println(basicSingleton1.getValue());
        System.out.println(basicSingleton2.getValue());
    }
}
