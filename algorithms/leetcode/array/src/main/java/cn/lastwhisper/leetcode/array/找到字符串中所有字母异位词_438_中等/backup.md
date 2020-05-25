第一步：
```java

int l = 0, r = 0;
        int[] visit = new int[26];
while (l < s.length()) {
            if (r < s.length() && r - l < p.length() && !anagram(s, l, r, p, visit)) {
                r++;
            } else {
                if (anagram(s, l, r, p, visit)) {
                    result.add(l);
                }
                l++;
            }
        }


public boolean anagram(String s, int l, int r, String p, int[] visit) {
        if (r - l != p.length()) {
            return false;
        }
...
}
```

第二步：

思路：双指针（滑动窗口）
（1）双指针 l、r 维护一个滑动窗口，窗口的长度不大于模式串 p 的长度
（2）判断当前 [l,r] 满足 异位词
（3）如果不满足，窗口向右扩容
（4）如果满足，记录当前 l 的下标，窗口向右缩小
（5）循环2、3、4，条件 l 未越界

```java
int l = 0, r = 0;
        int[] visit = new int[26];
if (!anagram(s, l, r, p, visit)) {
                // [l,r]不满足异位词且窗口大小不满足，扩展右边界
                if ((r - l+1) != p.length()) {
                    r++;
                } else {
                    // [l,r]不满足异位词且窗口大小满足，扩展左边界
                    l++;
                }
            } else {
                result.add(l);
                l++;
            }
```

第三步：


```java

int l = 0, r = 0;
        int[] visit = new int[26];
while (l < s.length() && r < s.length()) {
            // [l,r]不满足窗口大小，扩展右边界
            if ((r - l + 1) != p.length()) {
                r++;
            } else {
                // [l,r]满足窗口大小，检查是否是异位词
                if (anagram(s, l, r, p, visit)) {
                    result.add(l);
                }
                l++;
            }
        }

```

第四步：

```java
int l = 0, r = 0;
        int[] visit = new int[26];
while (l < s.length() && r < s.length()) {
            // [l,r]不满足模式串长度，扩展右边界
            while ((r - l + 1) != p.length()) {
                r++;
            }
            // 此时窗口肯定满足，模式串的长度了，但是有可能越界了
            if (r < s.length()) {
                // 检查是否是异位词
                if (anagram(s, l, r, p, visit)) {
                    result.add(l);
                }
                l++;
            }
        }

```
第五步：

```java

int l = 0, r = 0;
        int[] visit = new int[26];
        while (l < s.length() && r < s.length()) {
            // [l,r]不满足模式串长度，扩展右边界到窗口最大长度
            // (r - l + 1) != p.length() ==》 推导 r = p.length() + l - 1;
            r = p.length() + l - 1;
            // 此时窗口肯定满足，模式串的长度了，但是有可能越界了
            if (r < s.length()) {
                // 检查是否是异位词
                if (anagram(s, l, r, p, visit)) {
                    result.add(l);
                }
                l++;
            }
        }

```
第六步：

```java
public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        // 过滤肯定不符合规则的数据
        if (s == null || s.length() < p.length()) {
            return result;
        }
        // 直接让 [l,r] 满足模式串长度
        int l = 0, r = p.length() - 1;
        int[] visit = new int[26];

        while (r < s.length()) {
            // 检查是否是异位词
            if (anagram(s, l, r, p, visit)) {
                result.add(l);
            }
            l++;
            r++;
        }
        return result;
    }
```
