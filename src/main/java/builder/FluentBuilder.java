package builder;

public class FluentBuilder {
}

class Person{
    String name, position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

////Return type is an issue
//class PersonBuilder{
//    protected Person person = new Person();
//
//    public PersonBuilder withName(String name){
//        person.name = name;
//        return this;
//    }
//
//    public Person build(){
//        return this.person;
//    }
//}
//
////Return type is an issue
//class EmployeeBuilder extends PersonBuilder{
//    public EmployeeBuilder worksAt(String position){
//        person.position = position;
//        return this;
//    }
//}

class PersonBuilder<T extends PersonBuilder<T>>{
    protected Person person = new Person();

    public T withName(String name){
        person.name = name;
        return self();
    }

    public Person build(){
        return this.person;
    }

    public T self(){
        return (T) this;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder>{
    public EmployeeBuilder worksAt(String position){
        person.position = position;
        return this;
    }

    @Override
    public EmployeeBuilder self(){
        return this;
    }
}

class Demo{
    public static void main(String[] args) {
//        PersonBuilder personBuilder = new PersonBuilder();
//        personBuilder.withName("Tasnim").build();

        EmployeeBuilder employeeBuilder = new EmployeeBuilder();
        Person person = employeeBuilder
                .withName("Tasnim")
                .worksAt("Developer")
                .build();

        System.out.println(person);
    }
}
