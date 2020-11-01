package main

import (
	"fmt"
)

/**
 * 题目地址：https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * 编号：209
 * -------------------------------------------------------------------
 * 思考：边界值一定要考虑取还是不取，为什么？
 * -------------------------------------------------------------------
 * 思路：滑动窗口
 *  （1）双指针 l、r 维护一个滑动窗口
 *  （2）窗口的左边界在数组范围内,则循环继续
 *  （3）r未到数组尾且 sum<s，需要动态扩展窗口右边界
 *  （4）sum>s 需要动态缩小窗口左边界
 *  （5）循环内每次找 sum >= s 时的最小长度
 * -------------------------------------------------------------------
 * 时间复杂度：O(n) // l 和 r 指针同时遍历了一遍数组 O(2n)
 * 空间复杂度：O(1)
 */
func lengthOfLongestSubstring(s string) int {
	freq := make([]uint8, 256)
	// 窗口 [l,r] 内无重复元素
	l, r, res := 0, 0, 0
	for l < len(s) {
		if r < len(s) && freq[s[r]] == 0 {
			freq[s[r]]++
			r++
		} else {
			freq[s[l]]--
			l++
		}
		// 这里 r - l 不加一，因为 l、r 初始化值都是0，并且是先用在加的
		res = max(res, r-l)
	}
	return res
}

func max(x int, y int) int {
	if x > y {
		return x
	}
	return y
}

func main() {
	s := "abcabcbb"
	fmt.Println(lengthOfLongestSubstring(s))
}
