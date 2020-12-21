package cn.lastwhisper.feature5.reflect;

import java.util.Objects;

/**
 * @author lastwhisper
 */
public class ReflectPoint {
    private int x;
    private int y;

    public ReflectPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // 重载
    public boolean equals(ReflectPoint o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReflectPoint that = (ReflectPoint) o;
        return x == that.x &&
                y == that.y;
    }

    // 重写
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReflectPoint that = (ReflectPoint) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
