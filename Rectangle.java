/**
 * Represent rectangle coordinates
 *
 * Created by todd on 2/1/16.
 */
public class Rectangle {

    private Coordinate bottomLeft, topRight;

    public Rectangle(Coordinate bottomLeft, Coordinate topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public Rectangle(int blX, int blY, int trX, int trY) {
        this.bottomLeft = new Coordinate(blX, blY);
        this.topRight = new Coordinate(trX, trY);
    }

    public boolean containsCoordinate(Coordinate coordinate) {
        return coordinate.getX() >= bottomLeft.getX()
                && coordinate.getX() <= topRight.getX()
                && coordinate.getY() >= bottomLeft.getY()
                && coordinate.getY() <= topRight.getY();
    }

    public Coordinate getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(Coordinate bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public Coordinate getTopRight() {
        return topRight;
    }

    public void setTopRight(Coordinate topRight) {
        this.topRight = topRight;
    }

}
