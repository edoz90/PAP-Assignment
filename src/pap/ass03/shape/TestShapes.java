package pap.ass03.shape;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Edoardo Rosa
 */
public class TestShapes {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CLOSE = "\u001B[0m";

    public static void main(String[] args) {
        Line l = new Line(new P2d(2, 2), new P2d(8, 8));
        Circle c = new Circle(new P2d(10, 10), 5);
        Rect r = new Rect(new P2d(4, 4), new P2d(6, 7));
        
        List<Shape> testList = Arrays.asList(l, c, r);
        Combo comboList = new Combo(testList);
        
        TestShapes.testListExec(testList);        
    }

    private static void testListExec(List<Shape> list) {
        V2d moveVect = new V2d(3, 3);
        BBox box = new BBox(new P2d(4, 4), new P2d(12, 12));
        P2d contained = new P2d(5, 6);

        System.out.println(ANSI_BLUE + "Bounding Box: (" + box.getUpperLeft().getX() + ", " + box.getUpperLeft().getY() + "), (" + box.getBottomRight().getX() + ", " + box.getBottomRight().getY() + ")" + ANSI_CLOSE);
        Utils.logAll(Utils.inBBox(list, box));
        System.out.print(System.getProperty("line.separator"));

        System.out.println(ANSI_BLUE + "Max Perim:" + ANSI_CLOSE);
        System.out.println(Utils.maxPerim(list));
        System.out.print(System.getProperty("line.separator"));

        System.out.println(ANSI_BLUE + "Shape with maximum perimeter:" + ANSI_CLOSE);
        (Utils.shapeWithMaxPerim(list)).printShape();
        System.out.print(System.getProperty("line.separator"));

        System.out.println(ANSI_BLUE + "SortShapeByX:" + ANSI_CLOSE);
        Utils.logAll(Utils.sortShapeByX(list));
        System.out.print(System.getProperty("line.separator"));

        System.out.println(ANSI_BLUE + "Is P2d(" + contained.getX() + ", " + contained.getY() + ") contained in all shapes?" + ANSI_CLOSE);
        System.out.println(Utils.contains(list, contained));
        System.out.print(System.getProperty("line.separator"));

        System.out.println(ANSI_BLUE + "Which shape contains: P2d(" + contained.getX() + ", " + contained.getY() + ")?" + ANSI_CLOSE);
        Utils.logAll(Utils.getContaining(list, contained));
        System.out.print(System.getProperty("line.separator"));

        System.out.println(ANSI_BLUE + "Moves all shapes: V2d(" + moveVect.getX() + ", " + moveVect.getY() + ")" + ANSI_CLOSE);
        Utils.logAll(Utils.moveShapes(list, moveVect));
    }
}
