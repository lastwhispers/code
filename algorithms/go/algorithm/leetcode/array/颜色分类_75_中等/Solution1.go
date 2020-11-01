package main

import "fmt"

/**
 * 题目地址：https://leetcode-cn.com/problems/sort-colors/
 * 编号：75
 * -------------------------------------------------------------------
 * 思考：
 *  数据特征：只有0,1,2
 * -------------------------------------------------------------------
 * 思路：
 *   计数排序，统计每一个数出现的频率
 * -------------------------------------------------------------------
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
func sortColors(nums []int) {
	freq := [3]int{}
	for i := 0; i < len(nums); i++ {
		freq[nums[i]]++
	}
	idx := 0
	for i := 0; i < len(freq); i++ {
		for freq[i] > 0 {
			nums[idx] = i
			idx++
			freq[i]--
		}
	}
}

func main() {
	var arr = []int{2, 0, 2, 1, 1, 0}
	sortColors(arr)
	fmt.Println(arr)
}
