package cn.lastwhisper.feature5.equals;

class BaseClass {
    private int x;

    public BaseClass(int i) {
        x = i;
    }

    // equals()的对等性被打破
    //    public boolean equals(Object rhs) {
//        if (!(rhs instanceof BaseClass))
//            return false;
//
//        return x == ((BaseClass) rhs).x;
//    }

    public boolean equals(Object rhs) {
        // Class保证equlas的对等性
        if (rhs == null || getClass() != rhs.getClass())
            return false;

        return x == ((BaseClass) rhs).x;
    }
}

class DerivedClass extends BaseClass {
    private int y;

    public DerivedClass(int i, int j) {
        super(i);
        y = j;
    }

    public boolean equals(Object rhs) {
        if (!(rhs instanceof DerivedClass))
            return false;

        return super.equals(rhs) &&
                y == ((DerivedClass) rhs).y;
    }
}

public class EqualsWithExtends {
    public static void main(String[] args) {
        BaseClass a = new BaseClass(5);
        DerivedClass b = new DerivedClass(5, 8);
        DerivedClass c = new DerivedClass(5, 8);

        System.out.println("b.equals(c): " + b.equals(c));
        // equals()的对等性被打破
        System.out.println("a.equals(b): " + a.equals(b));
        System.out.println("b.equals(a): " + b.equals(a));
    }
}