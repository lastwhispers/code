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
 * 思路：双指针（对撞指针）
 *  这种思路的前提是数组有序
 *  （1）定义两个初始指针 l,r 分别指向数组头尾。
 *  （2）nums[l]+nums[r] > target，r--
 *  （3）nums[l]+nums[r] < target，l++
 *  （4）nums[l]+nums[r] == target，找到答案
 * -------------------------------------------------------------------
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
func twoSum(numbers []int, target int) []int {
	l, r := 0, len(numbers)-1
	// 这里不能等于，因为 index1 必须小于 index2
	for l < r {
		if numbers[l]+numbers[r] == target {
			return []int{l + 1, r + 1}
		} else if numbers[l]+numbers[r] < target {
			l++
		} else {
			r--
		}
	}
	return nil
}

func main() {
	fmt.Println(twoSum([]int{2, 3, 4}, 6))
}
