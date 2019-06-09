package singleton.monostate;

import lombok.ToString;

//use static fields
//can make many instances, but any change will be reflected to all
//not safe
//confusing
@ToString
public class ChiveExecutiveOfficer {

    private static String name;
    private static int age;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        ChiveExecutiveOfficer.name = name;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        ChiveExecutiveOfficer.age = age;
    }
}

class Demo{
    public static void main(String[] args) {
        ChiveExecutiveOfficer ceo = new ChiveExecutiveOfficer();
        ceo.setAge(21);
        ceo.setName("Adam");

        ChiveExecutiveOfficer ceo2 = new ChiveExecutiveOfficer();
        System.out.println(ceo2);
    }
}
