package cn.lastwhisper.linkedlist;

/**
 * 约瑟夫环
 * @author lastwhisper
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.createCircle(5);
        circleSingleLinkedList.showBoy();
        System.out.println();
        circleSingleLinkedList.calc(1, 2, 5);

    }
}

/**
 * 单向环形链表
 */
class CircleSingleLinkedList {
    private Boy frist;

    /**
     * 创建单向环形链表
     *
     * @param num
     * @return void
     */
    public void createCircle(int num) {
        if (num < 1) {
            System.out.println("错误的num");
            return;
        }
        // 定位最后一个boy的位置，方便添加结点
        Boy currBoy = null;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                // 只有一个boy
                frist = boy;
                frist.setNext(frist);
                currBoy = boy;
            } else {
                // 在已有环链的基础上添加
                currBoy.setNext(boy);
                boy.setNext(frist);
                currBoy = boy;
            }
        }
    }

    /**
     * 打印当前环链的结点信息
     */
    public void showBoy() {
        if (frist == null) {
            System.out.println("没有小孩");
            return;
        }
        Boy currBoy = frist;
        while (true) {
            System.out.printf("%d ", currBoy.getNo());
            currBoy = currBoy.getNext();
            if (currBoy == frist) {
                break;
            }
        }

    }

    /**
     * 有n个人的环，约定编号为k（1<=k<=n）的人从1开始报数，数到m 的那个人出列，写出出队序列.
     *
     * @param k 从第几个小孩开始
     * @param m 数几次
     * @param n 总人数
     * @return void
     */
    public void calc(int k, int m, int n) {
        // 1、数据校验
        // 2、pre指针，让pre指针移动到frist指针前一个位置
        Boy preNode = frist;
        while (preNode.getNext() != frist) {
            preNode = preNode.getNext();
        }
        // 3、报数前，让frist、pre指针同时移动k-1次，进入起始位置
        for (int i = 0; i < k - 1; i++) {
            frist = frist.getNext();
            preNode = preNode.getNext();
        }
        // 4、报数时，让frist、pre指针同时移动m-1次，frist指针进入待移除位置，输出待移除位置的编号
        //    frist再向前移一位，pre.next=frist，完成小孩出队。
        // 5、重复第四步，直至还剩一个小孩
        while (true) {
            if (frist.getNo() == preNode.getNo()) {
                System.out.printf("最后留在圈中小孩编号：%d\n", frist.getNo());
                break;
            }
            for (int i = 0; i < m - 1; i++) {
                frist = frist.getNext();
                preNode = preNode.getNext();
            }
            System.out.printf("小孩出圈编号为：%d\n", frist.getNo());
            frist = frist.getNext();
            preNode.setNext(frist);
        }
    }

}

class Boy {
    private int no; //编号
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}