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
 * 思路：二分搜索
 *  （1）遍历数组，对每一个值 nums[i]，在 nums 数组中进行 [i,n] 范围的二分搜索
 * -------------------------------------------------------------------
 * 时间复杂度：O(n*logn)
 * 空间复杂度：O(1)
 */
func twoSum(numbers []int, target int) []int {
	for i := 0; i < len(numbers); i++ {
		j := bs(numbers, i, len(numbers)-1, target-numbers[i])
		if j != -1 {
			return []int{i + 1, j + 1}
		}
	}
	return nil
}

func bs(nums []int, l int, r int, target int) int {
	// 这里是“<=”因为 [l,r] 内所有值都是可能的目标
	for l <= r {
		mid := (l-r)/2 + r
		if nums[mid] == target {
			return mid
		} else if nums[mid] > target {
			r = mid - 1
		} else {
			l = mid + 1
		}
	}
	return -1
}

func main() {
	fmt.Println(twoSum([]int{2, 3, 4}, 6))
}
