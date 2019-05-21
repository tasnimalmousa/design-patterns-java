package ocp;

import java.util.List;
import java.util.stream.Stream;

//OCP: Open-closed Principle
//Open for extension, closed for modification
//Specification Design Pattern
enum Color{
    RED, GREEN, BLUE;
}

enum Size{
    LARGE, MEDIUM, SMALL, YUGE;
}
class Product {
    String name;
    Color color;
    Size size;

    Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}
class ProductFilter{

    Stream<Product> filterByColor(List<Product> products, Color color){
        return  products.stream().filter(p -> p.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size){
        return  products.stream().filter(p -> p.size == size);
    }

    public Stream<Product> filterBySizeAndColor(List<Product> products, Size size, Color color){
        return  products.stream().filter(p -> p.size == size && p.color == color);
    }
}

//Specification Design Pattern------------------------------------------
interface Specification<T>{
   boolean isSatisfied(T item);
}
interface Filter<T>{
    Stream<T> filter(List<T> items, Specification<T> specification);
}

class ColorSpecification implements Specification<Product>{
    private Color color;

    ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product product) {
        return product.color == color;
    }
}

class SizeSpecification implements Specification<Product>{
    private Size size;

    SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product product) {
        return product.size == size;
    }
}

class AndSpecification<T> implements Specification<T>{
    Specification<T> first, second;

    AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}
class BetterFilter implements Filter<Product>{

    @Override
    public Stream<Product> filter(List<Product> products, Specification<Product> specification) {
        return products.stream().filter(specification::isSatisfied);
    }
}
//Specification Design Pattern------------------------------------------
class Demo{
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = List.of(apple, tree, house);

        System.out.println("Old Imp: ");
        ProductFilter filter = new ProductFilter();
        filter.filterByColor(products, Color.GREEN).forEach(p -> System.out.println("- " + p.name + " color is " + p.color));

        System.out.println("New Imp: ");
        BetterFilter betterFilter = new BetterFilter();
        betterFilter.filter(products, new ColorSpecification(Color.GREEN)).forEach(p -> System.out.println("- " + p.name + " color is " + p.color));

        System.out.println("New Imp large blue: ");
        betterFilter.filter(products,
                new AndSpecification<>(
                        new ColorSpecification(Color.BLUE),
                        new SizeSpecification(Size.LARGE)
                ))
                .forEach(p -> System.out.println("- " + p.name + " color is " + p.color + " and size is " + p.size));
    }
}
