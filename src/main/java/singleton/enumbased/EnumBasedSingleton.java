package singleton.enumbased;

import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//Solves reflections issue
//Serializable (different) - cannot preserve the state
//cannot inherit
public enum  EnumBasedSingleton {
    INSTANCE;

    EnumBasedSingleton(){
        this.value = 1;
    }

    @Getter
    @Setter
    private int value;
}

class Demo{
    public static void writeToFile(EnumBasedSingleton enumBasedSingleton, String filename) throws Exception{
        try(
                FileOutputStream fileOutputStream = new FileOutputStream(filename);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(enumBasedSingleton);
        }
    }

    public static EnumBasedSingleton readFromFile(String filename) throws Exception{
        try(
                FileInputStream fileInputStream = new FileInputStream(filename);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ) {
            return (EnumBasedSingleton) objectInputStream.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        String filename = "myfile.bin";

        EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
        singleton.setValue(111);
        writeToFile(singleton, filename);

        EnumBasedSingleton singleton2 = readFromFile(filename);
        System.out.println(singleton2.getValue());

        //The value is not saved to the file - doesn't preserve the state of enum
        singleton.setValue(222);

        EnumBasedSingleton singleton3 = readFromFile(filename);
        System.out.println(singleton3.getValue());//will be 222 even that i didn't write that value to the file
    }
}
