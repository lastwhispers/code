package main

import "fmt"

/**
 * 题目地址：https://leetcode-cn.com/problems/merge-sorted-array/
 * 编号：88
 * -------------------------------------------------------------------
 * 思考：
 *  数据特征：数组有序
 * -------------------------------------------------------------------
 * 思路：
 *  从后向前复制
 * -------------------------------------------------------------------
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
func merge(nums1 []int, m int, nums2 []int, n int) {
	// 从后到前找 num1 或 num2 最大的复制到 num1 末尾
	p1, p2, end := m-1, n-1, m+n-1
	for p1 >= 0 && p2 >= 0 {
		if nums1[p1] > nums2[p2] {
			nums1[end] = nums1[p1]
			p1--
		} else {
			nums1[end] = nums2[p2]
			p2--
		}
		end--
	}
	// 如果 num1 还没复制完，不用管，因为num1自身就是有序的
	// 将 num2 中剩余的数据 copy 到 temp
	for p2 >= 0 {
		nums1[end] = nums2[p2]
		end--
		p2--
	}
}

func main() {
	nums1 := []int{1, 2, 3, 0, 0, 0}
	nums2 := []int{2, 5, 6}
	m := 3
	n := 3
	merge(nums1, m, nums2, n)
	fmt.Println(nums1)
}
