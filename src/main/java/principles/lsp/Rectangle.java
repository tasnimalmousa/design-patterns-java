package principles.lsp;

//Liskov Substitution principle
//you should be able to substitute a sub class for a base class
public class Rectangle {
    protected int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea(){
        return width*height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    //sol:1
    public boolean isSquare(){
        return width == height;
    }
}

//sol2: use factory design pattern
class RectangleFactory{
    Rectangle newRectangle(int width, int height){
        return newRectangle(width, height);
    }

    Rectangle newSquare(int side){
        return new Rectangle(side, side);
    }
}

class Square extends Rectangle{
    public Square() {
    }

    public Square(int size) {
       width =height=size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}

class Demo{
    static void useIr(Rectangle rectangle){
        int width = rectangle.getWidth();
        rectangle.setHeight(10);
        //area = width *10
        System.out.println(
                "Ecpected area of " + 10*width
                +", got " + rectangle.getArea()
        );
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(2, 3);
        useIr(rectangle);

        Square square = new Square();
        square.setWidth(5);
        useIr(square);
    }
}