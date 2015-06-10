package pap.ass03.shape;

/**
 *
 * @author Edoardo Rosa
 */
public class Circle implements Shape {

    private P2d c;
    private final int r;

    public Circle(P2d c, int r) {
        this.c = c;
        this.r = r;
    }

    @Override
    public void move(V2d v) {
        this.c = this.c.sum(v);
    }

    @Override
    public double getPerim() {
        return 2 * Math.PI * r;
    }

    @Override
    public boolean isInside(BBox box) {
        P2d upperLeft = box.getUpperLeft();
        P2d bottomRight = box.getBottomRight();
        return ((this.c.getX() - r) >= upperLeft.getX() && (this.c.getY() - r) >= upperLeft.getY()
                && (this.c.getX() - r) <= bottomRight.getX() && (this.c.getY() - r) <= bottomRight.getY());
    }

    @Override
    public boolean contains(P2d p) {
        return (P2d.distance(p, c) <= r);
    }

    @Override
    public BBox getBBox() {
        return new BBox(new P2d(this.c.getX() - r, this.c.getY() - r),
                new P2d(this.c.getX() + r, this.c.getY() + r));
    }

    @Override
    public String printShape() {
        return "Circle [(" + this.c.getX() + "," + this.c.getY() + "), " + r + "]";
    }

    public P2d getC() {
        return this.c;
    }

    public int getR() {
        return this.r;
    }
}
