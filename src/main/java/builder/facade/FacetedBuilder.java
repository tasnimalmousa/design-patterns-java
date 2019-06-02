package builder.facade;

class Person{
    //address
    String StreetAddress, postcode, city;

    //employement
    String companyName, position;
    int annualIncome;

    @Override
    public String toString() {
        return "Person{" +
                "StreetAddress='" + StreetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

class PersonBuilder{
    protected Person person = new Person();

    public AddressBuilder lives(){
        return new AddressBuilder(person);
    }

    public JobBuilder works(){
        return new JobBuilder(person);
    }
    public Person build(){
        return person;
    }
}

class AddressBuilder extends PersonBuilder{

    public  AddressBuilder(Person person){
        this.person = person;
    }

    public AddressBuilder withStreetAddress(String streetAddress){
        person.StreetAddress = streetAddress;
        return  this;
    }

    public AddressBuilder withPostcode(String postcode){
        person.postcode = postcode;
        return  this;
    }

    public AddressBuilder withCity(String city){
        person.city = city;
        return  this;
    }
}

class JobBuilder extends PersonBuilder{

    public  JobBuilder(Person person){
        this.person = person;
    }

    public JobBuilder withCompanyName(String companyName){
        person.companyName = companyName;
        return this;
    }

    public JobBuilder withPosition(String position){
        person.position = position;
        return this;
    }

    public JobBuilder withAnnualIncome(int annualIncome){
        person.annualIncome = annualIncome;
        return this;
    }
}
public class FacetedBuilder {

    public static void main(String[] args) {
        PersonBuilder personBuilder = new PersonBuilder();

        Person person = personBuilder
                .lives()
                .withStreetAddress("st.")
                .withPostcode("11171")
                .withCity("Amman")
                .works()
                .withPosition("Developer")
                .withCompanyName("Fetchr")
                .withAnnualIncome(0)
                .build();

        System.out.println(person);
    }
}



