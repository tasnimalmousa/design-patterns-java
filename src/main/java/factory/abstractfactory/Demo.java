package factory.abstractfactory;

import org.javatuples.Pair;
import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Demo {
    public static void main(String[] args) throws Exception {
        HotDrinkMachine machine = new HotDrinkMachine();

        HotDrink drink = machine.makeDrink();
        drink.consume();
    }

}

interface HotDrink{
    void consume();
}

class Tea implements HotDrink{

    @Override
    public void consume() {
        System.out.println("Hot tea is delicious");
    }
}

class Coffee implements HotDrink{

    @Override
    public void consume() {
        System.out.println("Hot coffee is delicious");
    }
}

interface HotDrinkFactory{
    HotDrink prepare(int amount);
}

class TeaFactory implements HotDrinkFactory{

    @Override
    public Tea prepare(int amount) {
        System.out.println("Prepare " + amount + "ml of tea");
        return new Tea();
    }
}

class CoffeeFactory implements HotDrinkFactory{

    @Override
    public Coffee prepare(int amount) {
        System.out.println("Prepare " + amount + "ml of coffee");
        return new Coffee();
    }
}

class HotDrinkMachine{
    private List<Pair<String, HotDrinkFactory>> namedFactories = new ArrayList<>();

    public HotDrinkMachine() throws Exception {
        Set<Class<? extends HotDrinkFactory>> types =
                new Reflections("").getSubTypesOf(HotDrinkFactory.class);

        for (Class<? extends HotDrinkFactory> type:types){
            namedFactories.add(new Pair<>(
                    type.getSimpleName().replace("Factory", ""),
                    type.getDeclaredConstructor().newInstance()
            ));
        }
    }

    public HotDrink makeDrink() throws IOException {
        System.out.println("Available Drinks: ");

        for (int i=0; i< namedFactories.size(); i++){
            Pair<String, HotDrinkFactory> item = namedFactories.get(i);
            System.out.println("" + i + ": " + item.getValue0());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            String s;
            int i, amount;

            if((s = reader.readLine()) != null
            && (i= Integer.parseInt(s)) >= 0
            && i<namedFactories.size()){
                System.out.println("Specify amount: ");
                s = reader.readLine();
                if(s != null
                && (amount = Integer.parseInt(s)) > 0){
                    return namedFactories.get(i).getValue1().prepare(amount);
                }
            }
            System.out.println("Incorrect, try again");
        }
    }
}
