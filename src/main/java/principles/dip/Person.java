package principles.dip;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//principles.dip: dependency inversion principle

//High-level modules should not depend on law-level modules
//both should depend on abstraction

//Abstraction should not depend on details
//Details should depend on abstraction
enum Relationship{
    PARENT,
    CHILD,
    SIBLING
}

public class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }
}

//sol:
interface RelationshipsBrowser{
    List<Person> findAllChildrenOf(String name);
}

class Relationships implements RelationshipsBrowser{ // low-level (related to data storage)

    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child){
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    //sol:
    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
                .filter(x -> x.getValue0().name.equals(name) && x.getValue1() == Relationship.PARENT)
                .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}

//we have direct access to data storage here, 1. data can be manipulated or 2. data type might change (will have to change imp in many places)
class Research{ // high level (perform operations on law level)
    public Research(Relationships relationships){
        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();

        relations.stream().filter(x -> x.getValue0().name.equals("John")
         && x.getValue1() == Relationship.PARENT)
        .forEach(ch -> System.out.println("John has a child called " + ch.getValue2().name));
    }

    //sol:
    public Research(RelationshipsBrowser browser){
        browser.findAllChildrenOf("john")
                .forEach(ch -> System.out.println("John has a child called " + ch.name));
    }
}

class Demo{
    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1 = new Person("Adam");
        Person child2 = new Person("Chris");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        Research research1 = new Research(relationships);

        //sol:
        Research research2 = new Research(relationships);
    }
}
