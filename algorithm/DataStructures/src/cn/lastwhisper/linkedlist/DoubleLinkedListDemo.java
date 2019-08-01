package cn.lastwhisper.linkedlist;

/**
 * 双向链表
 * @author lastwhisper
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        System.out.println("===============查看链表所有结点================");
        doubleLinkedList.list();
        System.out.println("===============删除链表某结点==================");
        doubleLinkedList.delete(2);
        System.out.println("===============查看链表所有结点================");
        doubleLinkedList.list();
        System.out.println("================更新链表结点,4=================");
        doubleLinkedList.update(new HeroNode2(4, "林冲", "豹子头~~~"));
        System.out.println("===============查看链表所有结点================");
        doubleLinkedList.list();
        System.out.println("===============按序插入结点,8================");
        doubleLinkedList.addByNoOrder(new HeroNode2(8, "武松", "行者"));
        System.out.println("===============查看链表所有结点================");
        doubleLinkedList.list();
        System.out.println("===============按序插入结点,6================");
        doubleLinkedList.addByNoOrder(new HeroNode2(6, "入云龙", "公孙胜"));
        System.out.println("===============查看链表所有结点================");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    HeroNode2 head = new HeroNode2(0, "", "");

    /**
     * 添加
     */
    public void add(HeroNode2 newNode) {
        HeroNode2 tempNode = head;
        while (true) {
            if (tempNode.next == null) {
                break;
            }
            tempNode = tempNode.next;
        }
        // lastNode.next指向newNode
        tempNode.next = newNode;
        // newNode.pre指向lastNode
        newNode.pre = tempNode;
    }

    /**
     * 删除
     */
    public void delete(int no) {
        HeroNode2 tempNode = head;
        while (true) {
            if (tempNode.next == null) {
                System.out.printf("未找到no==%d 结点\n", no);
                break;
            }
            tempNode = tempNode.next;
            if (tempNode.no == no) {
                tempNode.pre.next = tempNode.next;
                System.out.printf("已删除no==%d 结点\n", no);
                break;
            }
        }
    }

    // 修改
    public void update(HeroNode2 updateNode) {
        HeroNode2 tempNode = head;
        while (true) {
            if (tempNode.next == null) {
                System.out.printf("未找到no==%d 结点\n", updateNode.no);
                break;
            }
            tempNode = tempNode.next;
            // 找到待更新结点
            if (tempNode.no == updateNode.no) {
                tempNode.name = updateNode.name;
                tempNode.nickName = updateNode.nickName;
                System.out.printf("已更新no==%d 结点\n", updateNode.no);
                break;
            }
        }
    }

    /**
     * 展示所有结点，沿着头结点遍历
     *
     * @param
     * @return void
     */
    public void list() {
        HeroNode2 currNode = head.next;
        while (currNode != null) {
            System.out.printf("%d\t%s\t%s\t\n", currNode.no, currNode.name, currNode.nickName);
            currNode = currNode.next;
        }
    }

    /**
     * 根据排名插入到指定位置
     *  如果已存在该排名添加失败
     *
     * @param heroNode
     * @return void
     */
    public void addByNoOrder(HeroNode2 heroNode) {
        HeroNode2 tempNode = head;
        //HeroNode2 preNode;
        boolean flag = true;
        while (true) {
            if (tempNode.next == null) {
                break;
            }
            //preNode = tempNode;
            tempNode = tempNode.next;
            // tempNode是待插入结点的前一个结点
            if (heroNode.no == tempNode.no) {
                flag = false;
                System.out.println("已存在排名，添加失败" + heroNode);
                break;
            }

            if (heroNode.no < tempNode.no) {
                // 1,3,4 heroNode.no=2 tempNode.next.no=3 2<3 可以添加
                // heroNode指向no=3结点，即2,3
                heroNode.next = tempNode;
                // tempNode.pre为no=1的结点 即1,2
                // preNode.next = heroNode;
                tempNode.pre.next = heroNode;
                flag = false;
                break;
            }
        }
        if (flag) {
            // 待插入结点no最大
            tempNode.next = heroNode;
            heroNode.pre = tempNode;
        }
    }
}

/**
 * 双向链表结点
 *
 */
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next; // 当前结点的后一个结点
    public HeroNode2 pre; //当前结点的前一个结点

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}