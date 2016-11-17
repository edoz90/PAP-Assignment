package pap.ass03.shape;

import java.util.List;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

/**
 * @author Edoardo Rosa
 */
public class Combo implements Shape {

    private List<Shape> combo;

    public Combo(List<Shape> list) {
        this.combo = list;
    }

    @Override
    public void move(V2d v) {
        Utils.moveShapes(combo, v);
    }

    @Override
    public double getPerim() {
        return this.combo.stream().map(s -> s.getPerim()).reduce((x, y) -> x + y).get();
    }

    @Override
    public boolean isInside(BBox box) {
        return combo.stream().map(s -> s.isInside(box)).reduce((x, y) -> x && y).get();
    }

    @Override
    public boolean contains(P2d p) {
        return combo.stream().map(s -> s.contains(p)).reduce((x, y) -> x && y).get();
    }

    @Override
    public BBox getBBox() {
        List<BBox> listBBox = combo.stream().map(s -> s.getBBox()).collect(toList());
        return new BBox(new P2d(listBBox.stream().map(b -> b.getUpperLeft()).min(comparingInt(x -> x.getX())).get().getX(),
                listBBox.stream().map(b -> b.getUpperLeft()).min(comparingInt(y -> y.getY())).get().getY()),
                new P2d(listBBox.stream().map(b -> b.getBottomRight()).min(comparingInt(x -> x.getX())).get().getX(),
                        listBBox.stream().map(b -> b.getBottomRight()).min(comparingInt(y -> y.getY())).get().getY()));
    }

    @Override
    public String printShape() {
        Utils.logAll(combo);
        return null;
    }
}
