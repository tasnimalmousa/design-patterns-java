package srp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//SRP Single Responsibility Principle
//every class should have a single reason to change
//we don't want god objects
//make code manageable and easier to understand
public class Journal {

    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntry(String text){
        entries.add("" + (++count) + ": " + text);
    }

    public void removeEntry(int index){
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }


//    public void save(String filename) throws FileNotFoundException {
//        try(PrintStream out = new PrintStream(filename)) {
//            out.println(toString());
//        }
//    }
//
//    public void load(String filename){}
//    public void load(URL url){}
}

class Persistence {
    public void saveToFile(Journal journal, String filename, boolean overwrite) throws FileNotFoundException {
        if(overwrite || new File(filename).exists()){
            //try-with-resources(auto closing for resources)
            try(PrintStream out = new PrintStream(filename)) {
                out.println(journal);
            }
        }
    }
    public void load(String filename){}
    public void load(URL url){}
}

class Demo{
    public static void main(String[] args) throws IOException {
        Journal j = new Journal();

        j.addEntry("I cried today");
        j.addEntry("I ate a bug");

        System.out.println(j);

        Persistence p = new Persistence();

        String filename = "srp_example.txt";
        p.saveToFile(j, filename, true);
        Runtime.getRuntime().exec("notepad.exe " + filename);
    }
}
