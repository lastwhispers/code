package cn.lastwhisper.feature5.introspection;

/**
 * JavaBean
 * JavaBean是一种特殊的Java类，主要用于传递数据信息，这种java类中的方法主要用于访问私有的字段，且方法名符合某种命名规则。
 *
 */
public class Point {

    private Integer x;
    private Integer y;

    public Point(Integer x, Integer y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}