package main

import (
	"fmt"
	"math"
)

/**
 * 题目地址：https://leetcode-cn.com/problems/container-with-most-water/
 * 编号：11
 * -------------------------------------------------------------------
 * 思考：
 * -------------------------------------------------------------------
 * 思路：双指针（对撞指针）
 * -------------------------------------------------------------------
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
func maxArea(height []int) int {
	l, r, max := 0, len(height)-1, float64(-1)
	for l < r {
		if height[l] < height[r] {
			max = math.Max(max, float64(height[l]*(r-l)))
			l++
		} else {
			max = math.Max(max, float64(height[r]*(r-l)))
			r--
		}
	}
	return int(max)
}

func main() {
	nums := []int{1, 8, 6, 2, 5, 4, 8, 3, 7}
	fmt.Println(maxArea(nums))
}
