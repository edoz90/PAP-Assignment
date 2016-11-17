package pap.ass03.shape;

/**
 * @author Edoardo Rosa
 */
public class Line implements Shape {

    private P2d a;
    private P2d b;

    public Line(P2d a, P2d b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void move(V2d v) {
        this.a = this.a.sum(v);
        this.b = this.b.sum(v);
    }

    @Override
    public double getPerim() {
        return P2d.distance(this.a, this.b);
    }

    @Override
    public boolean isInside(BBox box) {
        P2d upperLeft = box.getUpperLeft();
        P2d bottomLeft = box.getBottomRight();
        return (this.a.getX() >= upperLeft.getX() && this.a.getY() >= upperLeft.getY()
                && this.b.getX() >= upperLeft.getX() && this.b.getY() >= upperLeft.getY()
                && this.a.getX() <= bottomLeft.getX() && this.a.getY() <= bottomLeft.getY()
                && this.b.getX() <= bottomLeft.getX() && this.b.getY() <= bottomLeft.getY());
    }

    @Override
    public boolean contains(P2d p) {
        return (P2d.distance(a, b) == (P2d.distance(a, p) + P2d.distance(b, p)));
    }

    @Override
    public BBox getBBox() {
        return new BBox(
                new P2d(Math.min(this.a.getX(), this.b.getX()),
                        Math.min(this.a.getY(), this.b.getY())),
                new P2d(Math.max((this.a.getX()), this.b.getX()),
                        Math.max((this.a.getY()), this.b.getY())));
    }

    @Override
    public String printShape() {
        return "Line [(" + this.a.getX() + "," + this.a.getY() + "), (" + this.b.getX() + "," + this.b.getY() + ")]";
    }

    public P2d getA() {
        return this.a;
    }

    public P2d getB() {
        return this.b;
    }

}
