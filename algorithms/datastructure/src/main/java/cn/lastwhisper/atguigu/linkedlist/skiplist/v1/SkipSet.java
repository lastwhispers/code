package cn.lastwhisper.atguigu.linkedlist.skiplist.v1;

/**
 *  https://www.iteye.com/blog/imtinx-1291165
 *  跳表节点数据存储结构
 */
class SkipNode<E extends Comparable<? super E>> {
    public final E value; //节点存储的数据  
    public final SkipNode<E>[] forward; //节点的指针数组  

    /**
     * 根据节点的层级构造一个节点 
     * @param level 节点层级 
     * @param value 节点存储值 
     */
    @SuppressWarnings("unchecked")
    public SkipNode(int level, E value) {
        forward = new SkipNode[level + 1];//level层的元素后面带着level+1的指针数组  
        this.value = value;
    }

}

public class SkipSet<E extends Comparable<? super E>> {

    /**
     * 概率因子，实验证明p=1/e比p=0.5要好，e是个神奇的数字！ 
     */
//  public static final double P = 0.5;  
    public static final double P = 1 / Math.E;
    /**
     *  最大层级 
     */
    public static final int MAX_LEVEL = 6;

    /**
     * 开始节点，不存值，贯穿所有层 
     */
    public final SkipNode<E> header = new SkipNode<E>(MAX_LEVEL, null);
    /**
     * 当前跳表的最高层级 
     */
    public int level = 0;

    /**
     * 插入一个元素 
     * @param value 待插入值 
     */
    @SuppressWarnings("unchecked")
    public void insert(E value) {
        SkipNode<E> x = header;
        SkipNode<E>[] update = new SkipNode[MAX_LEVEL + 1];
        //查找元素的位置，这里其实做了一次contain操作，注释见contain  
        for (int i = level; i >= 0; i--) {
            while (x.forward[i] != null
                    && x.forward[i].value.compareTo(value) < 0) {
                x = x.forward[i];
            }
            //update[i]是比value小的数里面最大的，是value的前置节点  
            update[i] = x;
        }
        x = x.forward[0];

        //此处不允许插入相同元素，为一个set  
        if (x == null || !x.value.equals(value)) {//跳表中不包含所要插的元素  
            //随机产生插入的层级  
            int lvl = randomLevel();
            //产生的随机层级比当前跳表的最高层级大，需要添加相应的层级，并更新最高层级  
            if (lvl > level) {
                for (int i = level + 1; i <= lvl; i++) {
                    update[i] = header;
                }
                level = lvl;
            }

            //生成新节点  
            x = new SkipNode<E>(lvl, value);
            //调整节点的指针，和指向它的指针  
            for (int i = 0; i <= lvl; i++) {
                x.forward[i] = update[i].forward[i];
                update[i].forward[i] = x;
            }

        }
    }

    /**
     * 删除一个元素 
     * @param value 待删除值 
     */
    @SuppressWarnings("unchecked")
    public void delete(E value) {
        SkipNode<E> x = header;
        SkipNode<E>[] update = new SkipNode[MAX_LEVEL + 1];
        //查找元素的位置，这里其实做了一次contain操作，注释见contain  
        for (int i = level; i >= 0; i--) {
            while (x.forward[i] != null
                    && x.forward[i].value.compareTo(value) < 0) {
                x = x.forward[i];
            }
            update[i] = x;
        }
        x = x.forward[0];
        //删除元素，调整指针  
        if (x.value.equals(value)) {
            for (int i = 0; i <= level; i++) {
                if (update[i].forward[i] != x)
                    break;
                update[i].forward[i] = x.forward[i];
            }
            //如果元素为本层最后一个元素，则删除同时降低当前层级  
            while (level > 0 && header.forward[level] == null) {
                level--;
            }

        }
    }

    /**
     * 查找是否包含此元素 
     * @param searchValue 带查找值 
     * @return true：包含；false:不包含 
     */
    public boolean contains(E searchValue) {
        SkipNode<E> x = header;
        //从开始节点的最高层级开始查找  
        for (int i = level; i >= 0; i--) {
            //当到达本层级的NULL节点或者遇到比查找值大的节点时，转到下一层级查找  
            while (x.forward[i] != null
                    && x.forward[i].value.compareTo(searchValue) < 0) {
                x = x.forward[i];
            }
        }
        x = x.forward[0];
        //此时x有三种可能，1.x=null,2.x.value=searchValue,3.x.value>searchValue  
        return x != null && x.value.equals(searchValue);
    }

    /**
     * 这里是跳表的精髓所在，通过随机概率来判断节点的层级 
     * @return 节点的层级
     */
    public static int randomLevel() {
        int lvl = (int) (Math.log(1. - Math.random()) / Math.log(1. - P));
        return Math.min(lvl, MAX_LEVEL);
    }

    /**
     * 输出跳表的所有元素 
     * 遍历最底层的元素即可 
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        SkipNode<E> x = header.forward[0];
        while (x != null) {
            sb.append(x.value);
            x = x.forward[0];
            if (x != null)
                sb.append(",");
        }
        sb.append("}");
        return sb.toString();
    }
}  