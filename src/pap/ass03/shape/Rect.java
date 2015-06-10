package pap.ass03.shape;

/**
 *
 * @author Edoardo Rosa
 */
public class Rect implements Shape {

    private P2d a;
    private P2d b;

    public Rect(P2d a, P2d b) {
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
        return 2 * (P2d.distance(a, new P2d(this.a.getX(), this.b.getY())) + P2d.distance(a, new P2d(this.b.getX(), this.a.getY())));
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
        return (p.getX() >= this.a.getX() && p.getY() >= this.a.getY()
                && p.getX() <= this.b.getY() && p.getY() <= this.b.getY());
    }

    @Override
    public BBox getBBox() {
        return new BBox(new P2d(this.a.getX(), this.a.getY()), new P2d(this.b.getX(), this.b.getY()));
    }

    @Override
    public String printShape() {
        return "Rect [(" + this.a.getX() + "," + this.a.getY() + "), (" + this.b.getX() + "," + this.b.getY() + ")]";
    }

    public P2d getA() {
        return this.a;
    }

    public P2d getB() {
        return this.b;
    }
}
