package main

import (
	"fmt"
	"math"
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
func minSubArrayLen(s int, nums []int) int {
	// 滑动窗口[l,r]
	l, r := 0, -1
	// 窗口和
	sum, result := 0, math.MaxInt64
	// 窗口左边界未越界，说明窗口还可以继续扩容或者缩小
	for l < len(nums) {
		// sum<s 窗口右边界扩容，同时右边界不能未越界
		if r+1 < len(nums) && sum < s {
			r++
			sum += nums[r]
		} else {
			sum -= nums[l]
			l++
		}
		if sum >= s {
			result = min(result, r-l+1)
		}
	}
	if result == math.MaxInt64 {
		return 0
	}
	return result
}

func min(x int, y int) int {
	if x > y {
		return y
	}
	return x
}

func main() {
	s := 7
	nums := []int{2, 3, 1, 2, 4, 3}
	fmt.Println(minSubArrayLen(s, nums))
}
