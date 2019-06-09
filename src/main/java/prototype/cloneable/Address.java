package prototype.cloneable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//shallow copy - when you copy references
//deep copy


// don't use clone
//the behavior is not determined
//and does a shallow copy
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Address implements Cloneable{
    private String streetName;
    private int houseNumber;

    //Deep copying
    @Override
    public Object clone(){
        return new Address(streetName, houseNumber);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
class Person implements Cloneable{
    private String [] names;
    private Address address;

    @Override
    public Object clone(){
//        return new Person(names, address); // incorrect - both names and address are references
        return new Person(
                names.clone(),
                (Address) address.clone()); // incorrect - both names and address are references
    }
}

class Demo{
    public static void main(String[] args) {
        Person john = new Person(new String[]{"John", "Smith"},
                new Address("London Road", 123));

//        //wrong - reference copying
//        Person jane = john;
//        jane.getNames()[0] = "Jane";
//        jane.getAddress().setHouseNumber(124);
//
//        System.out.println(john);
//        System.out.println(jane);


        Person jane = (Person) john.clone();
        jane.getNames()[0] = "Jane";
        jane.getAddress().setHouseNumber(124);

        System.out.println(john);
        System.out.println(jane);
    }
}
