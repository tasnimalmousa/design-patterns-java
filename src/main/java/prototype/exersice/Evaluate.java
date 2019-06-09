package prototype.exersice;

import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class Evaluate
{
    @Test
    public void test()
    {
        Line line1 = new Line(
                new Point(3, 3),
                new Point(10, 10)
        );

        Line line2 = line1.deepCopy();
        line1.start.x = line1.end.x = line1.start.y = line1.end.y = 0;

        assertEquals(3, line2.start.x);
        assertEquals(3, line2.start.y);
        assertEquals(10, line2.end.x);
        assertEquals(10, line2.end.y);
    }
}


class Point
{
    public int x, y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}

class Line
{
    public Point start, end;

    public Line(Point start, Point end)
    {
        this.start = start;
        this.end = end;
    }

    public Line(Line line)
    {
        this(
                new Point(line.start.x, line.start.y ),
                new Point(line.end.x, line.end.y));
    }

    public Line deepCopy()
    {
        return new Line(this);
    }
}
