package cn.lastwhisper.aop.proxyfactory;

/**
 * @author cunchang
 * @date 2022/5/23 11:42 PM
 */
public class BookShopService {


    public void deleteBook() {
        System.out.println(this.getClass() + ": deleteBook");
    }

    public void queryBook() {
        System.out.println(this.getClass() + ": queryBook");
    }

}
