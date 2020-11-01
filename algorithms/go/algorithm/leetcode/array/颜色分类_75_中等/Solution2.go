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
 *   三路快速排序的思想，对整个数组只遍历了一遍
 *  （1）双指针 left=-1，right=n
 *  （2）下标 i 遍历数组，num[i]==1，i++
 *  （3）num[i]==0，将 0 移动到数组最前位置，swap(num,++left,i++)
 *      i++，即使 num[++left]==0，这次的交换和位移也是正确的
 *  （4）num[i]==2，将 2 移动到数组最后位置，swap(num,--right,i)
 *      i不变，因为有可能num[--right]==2
 * -------------------------------------------------------------------
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
func sortColors(nums []int) {
	left := -1
	right := len(nums)
	for i := 0; i < right; {
		if nums[i] == 1 {
			i++
		} else if nums[i] == 0 {
			left++
			swap(nums, left, i)
			i++
		} else if nums[i] == 2 {
			right--
			swap(nums, right, i)
		}
	}
}

func swap(nums []int, i, j int) {
	temp := nums[i]
	nums[i] = nums[j]
	nums[j] = temp
}

func main() {
	var arr = []int{2, 0, 2, 1, 1, 0}
	sortColors(arr)
	fmt.Println(arr)
}
