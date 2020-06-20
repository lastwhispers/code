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
 *   归并排序，归的过程。
 *  （1）从前到后比较(找小)复制只用 nums1，需要移动 nums1 中的元素，不如创建中间数据
 *  （2）从后到前比较(找大)复制只用 nums1，无需移动 nums1 中的元素
 * -------------------------------------------------------------------
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
func merge(nums1 []int, m int, nums2 []int, n int) {
	temp := make([]int, m+n)
	// p1 是 num1 已拷贝数据的指针，[0,p1]
	p1, p2, t := 0, 0, 0
	// 从前到后找 num1 或 num2 最小的复制到 temp
	for p1 < m && p2 < n {
		if nums1[p1] > nums2[p2] {
			temp[t] = nums2[p2]
			p2++
		} else {
			temp[t] = nums1[p1]
			p1++
		}
		t++
	}
	// 将 num1 中剩余的数据 copy 到 temp
	for p1 < m {
		temp[t] = nums1[p1]
		t++
		p1++
	}
	// 将 num2 中剩余的数据 copy 到 temp
	for p2 < n {
		temp[t] = nums2[p2]
		t++
		p2++
	}
	// 将 temp 中所有数据 copy 到 num1
	for i := 0; i < len(temp); i++ {
		nums1[i] = temp[i]
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
