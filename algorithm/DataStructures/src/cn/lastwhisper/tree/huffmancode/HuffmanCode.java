package cn.lastwhisper.tree.huffmancode;

import java.io.*;
import java.util.*;

/**
 * 哈夫曼编码
 * @author lastwhisper
 */
public class HuffmanCode {

    // 构建哈夫曼编码表的值，左边为0，右边为1，即a:110的110
    static StringBuffer sb = new StringBuffer();
    // 哈夫曼编码表，key为字符，value为编码的值。如：a:110，key=a，value=110
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    public static void main(String[] args) {

        /*--------------------------------压缩与解压的实际应用------------------------------------*/

        //测试压缩文件
        String srcFile = "d://src.bmp";
        String dstFile = "d://src.bmp.zip";
        zipFile(srcFile, dstFile);
        System.out.println("压缩文件ok~~");


        //测试解压文件
        //String zipFile = "d://src.bmp.zip";
        //String dstFile = "d://src2.bmp";
        //unZipFile(zipFile, dstFile);
        //System.out.println("解压成功!");

        /*--------------------------------压缩与解压的实现步骤------------------------------------*/

        //// String在jdk11时byte value[]，jdk11以下char value[]
        //String content = "i like like like java do you like a java";//压缩前 40个char
        //byte[] compressBytes = huffmanZip(content); //压缩后 17个byte
        // 哈夫曼编码压缩后的字节数组：[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
        //System.out.println("哈夫曼编码压缩后的字节数组：" + Arrays.toString(compressBytes));

        //String bitString = byteToBitString(true, (byte) 1);
        //System.out.println("夫曼编码压缩后的字节转比特："+bitString);

        //byte[] unZipBytes = unzip(huffmanCodes, compressBytes);
        // 哈夫曼编码解压缩后的字节数组：i like like like java do you like a java
        //System.out.println("哈夫曼编码解压缩后的字节数组：" + new String(unZipBytes));

        /*--------------------------------压缩的实现步骤------------------------------------*/

        //System.out.println("==========测试：待压缩字符串——》二叉树节点========");
        //// Node{data=32, weight=9}, Node{data=97, weight=5}, Node{data=100, weight=1}, Node{data=101, weight=4}, Node{data=117, weight=1}, Node{data=118, weight=2},
        //// Node{data=105, weight=5}, Node{data=121, weight=1}, Node{data=106, weight=2}, Node{data=107, weight=4}, Node{data=108, weight=4}, Node{data=111, weight=2}
        //List<Node> listNode = getListNode(content);
        ////System.out.println(listNode);
        //System.out.println("==========测试：二叉树节点——》哈夫曼树========");
        //Node root = createHuffmanTree(listNode);
        //// 40,17,8,4,4...
        ////preOrder(root);
        //System.out.println("==========测试：哈夫曼树——》哈夫曼编码表========");
        //// a:100 k: 1110
        //Map<Byte, String> huffmanCodes = getCodes(root);
        //// 32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011
        ////System.out.println("哈夫曼编码表：" + huffmanCodes);
        //byte[] huffmanCodeBytes = zip(content, huffmanCodes);
        //System.out.println("==========测试：哈夫曼编码表——》哈夫曼编码========");
        //// [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
        //System.out.println("哈夫曼编码：" + Arrays.toString(huffmanCodeBytes));

    }

    /**
     * 对压缩文件的解压
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {

        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和  is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组  huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = unzip(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                if (os != null) os.close();
                if (ois != null) ois.close();
                if (is != null) is.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }

        }
    }

    //编写方法，将一个文件进行压缩

    /**
     *
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {

        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes); //我们是把
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (is != null) is.close();
                if (oos != null) oos.close();
                if (os != null) os.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     *
     *
     * @param huffmanCodes 哈夫曼编码表
     * @param huffmanZipBytes 哈夫曼编码压缩后的字节数组
     * @return byte[]
     */
    public static byte[] unzip(Map<Byte, String> huffmanCodes, byte[] huffmanZipBytes) {
        // 1）、将哈夫曼编码压缩后的字节数组还原为哈夫曼编码
        StringBuilder sbHuffmanCodeBytes = new StringBuilder();
        for (int i = 0; i < huffmanZipBytes.length; i++) {
            // 是否补位、截取；除了最后一个字节都为false
            boolean flag = (huffmanZipBytes.length - 1) == i;
            sbHuffmanCodeBytes.append(byteToBitString(!flag, huffmanZipBytes[i]));
        }
        // 霍夫曼编码压缩后的字节数组对应的霍夫曼编码：1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
        //System.out.println("被压缩的哈夫曼编码=" + sbHuffmanCodeBytes.toString());
        // 2）、将哈夫曼编码表进行反转调换，调转后：100:97
        Map<String, Byte> reverseMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }
        // 原map：{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
        // 反转map：{000=108, 01=32, 100=97, 101=105, 11010=121, 0011=111, 1111=107, 11001=117, 1110=101, 11000=100, 11011=118, 0010=106}
        //System.out.println(reverseMap);
        // 3）、遍历哈夫曼编码sbHuffmanCodeBytes，遍历到的每一个字符串作为key；
        // 将key放到反转哈夫曼编码表中查询取出对应有效的value，添加到还原的字节组数中，
        // 同时下次遍历哈夫曼编码sbHuffmanCodeBytes时从上次遍历到的主串之后开始遍历。
        // 示例：如子串key=000，在主串10101000中匹配到了000，说明key对应的value=108，ascii 108对应字符'L'，将108添加到还原的字节数组中，
        // 同时下次遍历哈夫曼编码sbHuffmanCodeBytes时下标从10101000之后开始遍历。
        // 还原的字节数组，这里使用list代替
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < sbHuffmanCodeBytes.length(); ) {
            // i与index配合遍历一个字符串
            int index = 1;
            // 找到原字节
            boolean flag = true;
            // 原字节
            Byte b = null;
            // (i + index) <= sbHuffmanCodeBytes.length()防止截取字符串时越界
            while (flag && (i + index) <= sbHuffmanCodeBytes.length()) {
                // 第一次for循环i=0，截取字符串[0,1]，[0,2]...[0,length-1]
                // 第二次for循环i=1，截取字符串[1,2]，[1,3]...[1,length-1]
                // 寻找编码对应的原字节
                String key = sbHuffmanCodeBytes.substring(i, i + index);
                b = reverseMap.get(key);
                if (b == null) {
                    index++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            // 在(i, i + index)匹配到编码后，不用i++匹配(i+1,i+index)的主串，
            // 因为哈夫曼编码是最优前缀编码，所以在(i+1,i+index)的编码不会有重复，直接匹配(i+index,length-1)的主串即可。
            i += index;
        }
        //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     * 将byte转为bit
     *  情况一：负数，-1
     *      Integer.toBinaryString(-1)返回的二进制补码：11111111111111111111111111111111
     *      负数的补码，太长需要截取
     *      正确：1111 1111
     *  情况二：正数：1
     *      Integer.toBinaryString(-1)返回的二进制补码：1
     *      正数的补码就是自己，补码太短，需要补高位之后再截取
     *      正确：0000 0001
     *  情况三：最后一个字节不需要补位，28
     *      因为前面将哈夫曼编码转为byte[]时(zip方法)最后不足八位的字符串直接当做一个字节数组了，
     *      所以再将byte转为bit时，最后一个字节不需要补位！！！
     *      正确：11100
     *
     * @param flag 是否需要补位，true表示需要补位，false表示不需要补位
     * @param b 需要转换的byte
     * @return java.lang.String byte转换后的bitString（补码）
     */
    public static String byteToBitString(boolean flag, byte b) {
        // 将byte转为int
        int temp = b;
        // 如果是正数需要补高位
        if (flag) {
            temp |= 256; // 按位与：256的二进制1 0000 0000 | 1 = 1 0000 0001
        }
        String bitString = Integer.toBinaryString(temp); // 返回的是二进制补码
        if (flag) {
            // int负数的补码长度为64，截取[1,8]，其中8为byte的长度
            // 正数补高位后长度为9，截取[1,8]，其中8为byte的长度
            return bitString.substring(bitString.length() - 8);
        } else {
            return bitString;
        }
    }


    /**
     * 核心方法，以哈夫曼编码的方式压缩字节数组
     *
     * @param content 原字符串
     * @return byte[] 压缩后的字节数组
     */
    public static byte[] huffmanZip(String content) {
        byte[] bytes = content.getBytes();
        return huffmanZip(bytes);
    }

    /**
     * 核心方法，以哈夫曼编码的方式压缩字节数组
     *
     * @param bytes 原字节数组
     * @return byte[] 压缩后的字节数组
     */
    public static byte[] huffmanZip(byte[] bytes) {
        // 1）、将byte[]转为二叉树的节点
        List<Node> listNode = getListNode(bytes);
        // 2）、将二叉树的节点的节点转为哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(listNode);
        // 3）、根据哈夫曼树，找出哈夫曼编码表
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        // 4）、将哈夫曼编码表转为哈夫曼编码字符串
        // 再根据哈夫曼编码字符串，按每8位一个字节转为字节数组，该字节数组就是压缩后的数据。
        return zip(bytes, huffmanCodes);
    }

    /**
     *
     *
     * @param content 待压缩字符串
     * @param huffmanCodes 哈夫曼编码表
     * @return byte[] 压缩后的字节数组
     */
    public static byte[] zip(String content, Map<Byte, String> huffmanCodes) {
        byte[] bytes = content.getBytes();
        return zip(bytes, huffmanCodes);
    }

    /**
     *  // 待压缩字符串——》 "i like like like java do you like a java"
     *  // 待压缩字节数组——》 'i',' ','l',......
     *  哈夫曼编码表——》 {32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
     *  哈夫曼编码字符串（二进制字符串）——》1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
     *  哈夫曼编码byte[](8位一组对应一个byte)huffmanCodeBytes
     *
     *  Integer.parseInt("10101000", 2)是按补码来看待这个字符串的
     *  huffmanCodeBytes[0]=10101000(补码)——》10101000-1=10100111(反码)——》11011000(原码)=-88
     *
     * @param bytes 待压缩字符串
     * @param huffmanCodes 哈夫曼编码表
     * @return byte[] 压缩后的字节数组
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 数据校验
        if (bytes == null || huffmanCodes == null) {
            return null;
        }
        // 1）、将哈夫曼编码表转为哈夫曼编码字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(huffmanCodes.get(b));
        }
        // 2）、将哈夫曼编码字符串转为哈夫曼编码byte[]
        // 先求出哈夫曼编码byte[]的长度
        int len;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }
        // 创建哈夫曼编码byte[]
        byte[] huffmanCodeBytes = new byte[len];
        // huffmanCodeBytes的下标
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String huffmanCodeStr;
            if (i + 8 > sb.length()) {
                // 哈夫曼编码字符串剩余不足8位，直接当做一个字节
                huffmanCodeStr = sb.substring(i);
            } else {
                huffmanCodeStr = sb.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(huffmanCodeStr, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * 根据哈夫曼树建立哈弗曼编码表
     *
     * @param root 根结点
     * @return java.util.Map<java.lang.Byte, java.lang.String>
     */
    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", sb, huffmanCodes);
        getCodes(root.right, "1", sb, huffmanCodes);
        return huffmanCodes;
    }

    /**
     * 根据哈夫曼树建立哈弗曼编码表（递归）
     * @param node 以node结点为root开始建立哈夫曼编码表
     * @param code 左边为0，右边为1
     * @param stringBuffer 拼接编码的值
     * @param huffmanCodes 存储哈夫曼编码表
     */
    public static void getCodes(Node node, String code, StringBuffer stringBuffer, Map<Byte, String> huffmanCodes) {
        // 拼接编码的值，如 a:1——a:11——a:110
        StringBuffer sb = new StringBuffer(stringBuffer);
        sb.append(code);
        // 非叶子结点，哈夫曼树只有叶子结点有data
        if (node.data == null) {
            // 向左递归
            getCodes(node.left, "0", sb, huffmanCodes);
            // 向右递归
            getCodes(node.right, "1", sb, huffmanCodes);
        } else {
            // 叶子结点，
            huffmanCodes.put(node.data, sb.toString());
        }
    }

    // 先序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树！");
        }
    }

    /**
     * 创建哈夫曼树，并返回root结点
     *
     * @param nodes 二叉树结点
     * @return Node 哈夫曼树根结点
     */
    public static Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            // 1）、将序列升序排序，每一个结点都可以看做是一颗最简单的二叉树
            Collections.sort(nodes);
            // 2）、找到权值最小的二叉树（结点）
            Node leftNode = nodes.get(0);
            // 2）、找到权值次小的二叉树（结点）
            Node rightNode = nodes.get(1);
            // 3）、将两个权值最小的二叉树构成一颗新的二叉树，新二叉树的权值为前面两个二叉树权值之和。
            Node node = new Node(leftNode.weight + rightNode.weight);
            node.left = leftNode;
            node.right = rightNode;
            // 4）、将新二叉树放入序列中，并移除两个权值最小的二叉树
            nodes.add(node);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
        }
        return nodes.get(0);
    }

    /**
     * 将字符串转换成哈夫曼树结点
     * @param zipStr 待压缩字符串
     * @return java.util.List<cn.lastwhisper.tree.huffmancode.Node>
     */
    public static List<Node> getListNode(String zipStr) {
        byte[] zipStrBytes = zipStr.getBytes();
        return getListNode(zipStrBytes);
    }

    /**
     * 将字符串转换成哈夫曼树结点
     * @param zipBytes 待压缩字节数组
     * @return java.util.List<cn.lastwhisper.tree.huffmancode.Node>
     */
    public static List<Node> getListNode(byte[] zipBytes) {
        // 将字节数组按字节拆分，统计每个字节出现的次数（select count(byte) weight from zipBytes order by byte）
        Map<Byte, Integer> map = new HashMap<>();
        for (int i = 0; i < zipBytes.length; i++) {
            Integer weight = map.get(zipBytes[i]);
            if (weight == null) {
                map.put(zipBytes[i], 1);
            } else {
                map.put(zipBytes[i], weight + 1);
            }
        }
        List<Node> nodes = new ArrayList<>();
        // 把每一个键值对转成一个Node对象，并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }


}

// 哈夫曼树结点
class Node implements Comparable<Node> {
    Byte data; // 字节
    int weight; // 权值，主要用于构建哈夫曼树
    Node left; // 左子树
    Node right; // 右子树

    public Node(int weight) {
        this.weight = weight;
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    // 先序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        // 升序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}