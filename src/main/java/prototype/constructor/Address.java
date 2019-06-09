package prototype.constructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Data
public class Address {
    private String streetAddress, city, country;

    public Address(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    public Address(Address other) {
        this(other.streetAddress,
                other.city,
                other.country);
    }
}

@ToString
@Data
class Employee{
    private String name;
    private Address address;

    public Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee other) {
        this(other.name, new Address(other.address));
    }
}

class Demo {
    public static void main(String[] args) {
        Employee john = new Employee("John",
                new Address("123 London Road",
                        "London",
                        "UK"));

        Employee chris = new Employee(john);
        chris.setName("Chris");

        System.out.println(john);
        System.out.println(chris);
    }
}
