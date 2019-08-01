package cn.lastwhisper.hashtab;

/**
 *
 * @author lastwhisper
 */
public class HashTabDemo {
    public static void main(String[] args) {
        // EmpLinkedList测试
        //Emp emp1 = new Emp(1, "tom");
        //Emp emp2 = new Emp(2, "condy");
        //EmpLinkedList empLinkedList = new EmpLinkedList();
        //empLinkedList.add(emp1);
        //empLinkedList.add(emp2);
        //empLinkedList.list();
        //Emp empById = empLinkedList.findEmpById(2);
        //System.out.printf("id:%d-->name:%s\t", empById.id, empById.name);

        Emp emp1 = new Emp(1, "tom");
        Emp emp2 = new Emp(2, "condy");
        HashTable hashTable = new HashTable(7);
        hashTable.add(emp1);
        hashTable.add(emp2);
        Emp emp9 = new Emp(9, "kangkang");
        hashTable.add(emp9);
        hashTable.list();

        System.out.println("===============根据id查找==============");
        System.out.println(hashTable.findEmpById(9));
        System.out.println("===============根据id更新==============");
        hashTable.update(new Emp(9, "jackma"));
        System.out.println(hashTable.findEmpById(9));
        System.out.println("===============根据id删除==============");
        hashTable.delete(9);
        hashTable.list();
    }
}

// 哈希表
class HashTable {
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTable(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    // 添加
    public void add(Emp emp) {
        EmpLinkedList empLinkedList = empLinkedLists[hashFun(emp.id)];
        empLinkedList.add(emp);
    }

    // 根据id查找
    public Emp findEmpById(int id) {
        EmpLinkedList empLinkedList = empLinkedLists[hashFun(id)];
        Emp emp = empLinkedList.findEmpById(id);
        return emp;
    }

    // 修改
    public void update(Emp emp) {
        EmpLinkedList empLinkedList = empLinkedLists[hashFun(emp.id)];
        empLinkedList.update(emp);
    }

    // 删除
    public void delete(int id) {
        EmpLinkedList empLinkedList = empLinkedLists[hashFun(id)];
        empLinkedList.delete(id);
    }

    // 展示节点
    public void list() {
        for (int i = 0; i < empLinkedLists.length; i++) {
            System.out.printf("第%d个链表：", i + 1);
            empLinkedLists[i].list();
        }
    }

    // hash函数
    public int hashFun(int id) {
        return id % size;
    }

}

// 链表
class EmpLinkedList {
    private Emp head;

    // 添加
    public void add(Emp emp) {
        // 第一个元素
        if (head == null) {
            head = emp;
            return;
        }
        // 将链表最后一个节点.next=待添加节点添加
        Emp currEmp = head;
        while (currEmp.next != null) {
            currEmp = currEmp.next;
        }
        currEmp.next = emp;
    }

    // 根据id查找
    public Emp findEmpById(int empId) {
        Emp currEmp = head;
        if (currEmp == null) {
            System.out.println("链表元素为空");
            return null;
        }
        while (currEmp != null) {
            if (currEmp.id == empId) {
                return currEmp;
            }
            currEmp = currEmp.next;
        }
        return null;
    }

    // 修改
    public void update(Emp emp) {
        Emp currEmp = head;
        if (currEmp == null) {
            System.out.println("链表元素为空");
            return;
        }
        while (currEmp != null) {
            if (currEmp.id == emp.id) {
                currEmp.name = emp.name;
                break;
            }
            currEmp = currEmp.next;
        }
    }

    // 删除
    public void delete(int id) {
        Emp currEmp = head;
        if (currEmp == null) {
            System.out.println("链表元素为空");
            return;
        }

        while (currEmp != null) {
            if (currEmp.next.id == id) {
                currEmp.next = currEmp.next.next;
                break;
            }
            currEmp = currEmp.next;
        }
    }

    // 展示节点
    public void list() {
        Emp currEmp = head;
        if (currEmp == null) {
            System.out.println("链表元素为空");
            return;
        }
        while (currEmp != null) {
            System.out.printf("id:%d-->name:%s\t", currEmp.id, currEmp.name);
            currEmp = currEmp.next;
        }
        System.out.println();
    }
}

// 员工节点
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}