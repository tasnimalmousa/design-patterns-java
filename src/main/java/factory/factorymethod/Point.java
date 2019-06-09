package factory.factorymethod;

enum CoordinateSystem{
    CARTESIAN,
    POLAR
}
public class Point {

    private double x,y;

  //CASE1 - doesn't work
/*    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(double rho, double theta) {
        this.x = rho * Math.cos(theta);
        this.y = rho * Math.sin(theta);
    }*/


//CASE 2 - pass params is annoying

//    /**
//     *
//     * @param a is x when cartesian and rho when polar
//     * @param b
//     * @param coordinateSystem
//     */
    /*public Point(double a, double b, CoordinateSystem coordinateSystem) {
        switch (coordinateSystem){
            case POLAR:
                this.x = a;
                this.y = b;
            case CARTESIAN:
                this.x = a * Math.cos(a);
                this.y = b * Math.sin(b);
        }
    }*/



    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point newCartesianPoint(double x, double y){
        return new Point(x, y);
    }

    public static Point newPolarPoint(double rho, double theta){
        return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
    }
}

class Demo{
    public static void main(String[] args) {
        Point point1 = Point.newCartesianPoint(1, 2);
        Point point2 = Point.newPolarPoint(1, 2);
    }
}
