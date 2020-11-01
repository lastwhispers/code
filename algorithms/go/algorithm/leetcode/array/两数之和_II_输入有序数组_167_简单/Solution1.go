package main

import (
	"fmt"
)

/**
 * 题目地址：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 * 编号：167
 * -------------------------------------------------------------------
 * 思考：
 * -------------------------------------------------------------------
 * 思路：暴力搜索，找出所有组合进行对比
 * -------------------------------------------------------------------
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
func twoSum(numbers []int, target int) []int {
	for i := 0; i < len(numbers); i++ {
		for j := i + 1; j < len(numbers); j++ { // i + 1 是一个优化点
			if i != j && numbers[i]+numbers[j] == target {
				return []int{i + 1, j + 1}
			}
		}
	}
	return nil
}

func main() {
	fmt.Println(twoSum([]int{2, 3, 4}, 6))
}
