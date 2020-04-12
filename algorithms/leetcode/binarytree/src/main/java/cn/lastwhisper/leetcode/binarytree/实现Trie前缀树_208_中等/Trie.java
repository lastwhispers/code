package cn.lastwhisper.leetcode.binarytree.实现Trie前缀树_208_中等;

import cn.lastwhisper.leetcode.common.tree.TrieNode;
import org.junit.Assert;

class Trie {

    private TrieNode root;

    /**
     * 功能：
     *  找到具有同一前缀的全部键值。
     *  按词典序枚举字符串的数据集。
     *
     *  与哈希表相比，Trie 树在存储多个具有相同前缀的键时可以使用较少的空间。
     *  此时 Trie 树只需要 O(m) 的时间复杂度，其中 m 为键长。而在平衡树中查找键值需要 O(mlogn) 时间复杂度。
     *
     *  https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode/
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * 通过搜索 Trie 树来插入一个键。我们从根开始搜索它对应于第一个键字符的链接。有两种情况：
     *  链接存在。沿着链接移动到树的下一个子层。算法继续搜索下一个键字符。
     *  链接不存在。创建一个新的节点，并将它与父节点的链接相连，该链接与当前的键字符相匹配。
     * 重复以上步骤，直到到达键的最后一个字符，然后将当前节点标记为结束节点，算法完成。
     *
     * 时间复杂度：O(m)，其中 m 为键长。在算法的每次迭代中，我们要么检查要么创建一个节点，直到到达键尾。只需要 m 次操作。
     * 空间复杂度：O(m)。最坏的情况下，新插入的键和 Trie 树中已有的键没有公共前缀。此时需要添加 m 个结点，使用 O(m) 空间。
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    /**
     * 功能：查找word在是否在前缀树中
     *
     * 每个键在trie中表示为从根到内部节点或叶的路径。我们用第一个键字符从根开始，检查当前节点中与键字符对应的链接。
     *  有两种情况：
     *      1.存在链接。我们移动到该链接后面路径中的下一个节点，并继续搜索下一个键字符。
     *      2.不存在链接。若已无键字符，且当前结点标记为 isEnd，则返回 true。否则有两种可能，均返回 false :
     *          还有键字符剩余，但无法跟随 Trie 树的键路径，找不到键。
     *          没有键字符剩余，但当前结点没有标记为 isEnd。也就是说，待查找键只是Trie树中另一个键的前缀。
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    // search a prefix or whole key in trie and
    // returns the node where search ends
    /**
     *
     * 时间复杂度 : O(m)
     * 空间复杂度 : O(1)
     */
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter);
            } else {
                return null;
            }
        }
        return node;
    }

    /**
     * 功能：查找prefix在是否在前缀树中
     *
     * 时间复杂度 : O(m)
     * 空间复杂度 : O(1)
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        Assert.assertTrue(trie.search("apple"));   // 返回 true
        Assert.assertFalse(trie.search("app"));     // 返回 false
        Assert.assertTrue(trie.startsWith("app")); // 返回 true
        trie.insert("app");
        Assert.assertTrue(trie.search("app"));     // 返回 true
    }
}