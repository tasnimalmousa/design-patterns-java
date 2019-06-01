package principles.isp;

//ISP: Interface segregation principle
//breaking the interface
public class Document{

}
interface Machine {
    void print(Document document);
    void scan(Document document) throws Exception;
    void fax(Document document);
}

//sol: segregate - break
interface Printer {
    void print(Document document);
}

interface Scanner {
    void scan(Document document);
}

interface Fax {
    void fax(Document document);
}

//YANGI - You Ain't Going TO Need It

class MultiFunctionPrinter implements Machine{

    @Override
    public void print(Document document) {
        //some implementation
    }

    @Override
    public void scan(Document document) {
        //some implementation
    }

    @Override
    public void fax(Document document) {
        //some implementation
    }
}

class OldFashionedPrinter implements Machine{

    @Override
    public void print(Document document) {
        //some implementation
    }

    @Override
    public void scan(Document document) throws Exception {//will have to add this to Machine interface but you might not own the source code of it
        //Leave empty or throw exception not implemented
        throw new Exception();
    }

    @Override
    public void fax(Document document) {
        //Leave empty or throw exception not implemented
    }
}

class PhotCopier implements Printer, Scanner{

    @Override
    public void print(Document document) {
        //some implementation
    }

    @Override
    public void scan(Document document) {
        //some implementation
    }
}

//sol 2:
interface MultiFunctionMachine extends Printer, Scanner{

}

class MultifunctionDevice implements MultiFunctionMachine{
    @Override
    public void print(Document document) {
        //some implementation
    }

    @Override
    public void scan(Document document) {
        //some implementation
    }
}

//sol 3 : Decorator Design Pattern
interface DecoratorMultiFunctionMachine extends Printer, Scanner{

}

class DecoratorMultifunctionDevice implements DecoratorMultiFunctionMachine{

    private Printer printer;
    private Scanner scanner;

    public DecoratorMultifunctionDevice(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document document) {
       printer.print(document);
    }

    @Override
    public void scan(Document document) {
        scanner.scan(document);
    }
}