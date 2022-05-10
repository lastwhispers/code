package cn.lastwhisper.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeCasTest {
    public static void main(String[] args) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        User user = new User("jsbintask");
        long nameOffset = unsafe.objectFieldOffset(User.class.getDeclaredField("name"));
        unsafe.compareAndSwapObject(user, nameOffset, "jsbintask1", "jsbintask2");
        System.out.println("第一次更新后的值：" + user.getName());
        unsafe.compareAndSwapObject(user, nameOffset, "jsbintask", "jsbintask2");
        System.out.println("第二次更新后的值：" + user.getName());
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}