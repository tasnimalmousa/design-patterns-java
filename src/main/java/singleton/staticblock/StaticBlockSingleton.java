package singleton.staticblock;

import lombok.Getter;

import java.io.File;
import java.io.IOException;

//If the constructor might throw an exception use static block
public class StaticBlockSingleton {

    @Getter
    private static StaticBlockSingleton instance;

    StaticBlockSingleton() throws IOException {
        System.out.println("Singleton is initializing");
        File.createTempFile(".", ".");
    }

    static {
        try {
            instance = new StaticBlockSingleton();
        }catch (Exception e){
            System.out.println("Failed initializing singleton");
        }
    }
}
