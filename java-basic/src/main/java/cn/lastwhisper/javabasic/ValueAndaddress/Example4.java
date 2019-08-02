package cn.lastwhisper.javabasic.ValueAndaddress;

/**
 * @author gaojun
 * @desc 形参为对象
 * @email 15037584397@163.com
 */
public class Example4 {
    public static void main(String[] args) {

        User hollis = new User();
        hollis.setName("gaojun");
        hollis.setGender("Male");
        pass(hollis);
        System.out.println("print in main , user is " + hollis);
    }

    public static void pass(User user) {
        user = new User();
        user.setName("gaojun-update");
        user.setGender("Male");
        System.out.println("print in pass , user is " + user);
    }

    private static class User {
        private String name;
        private String gender;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", gender='" + gender + '\'' +
                    '}';
        }
    }
}
