package main

import (
	"fmt"
)

/**
 * 题目地址：https://leetcode-cn.com/problems/reverse-string/
 * 编号：344
 * -------------------------------------------------------------------
 * 思考：
 * -------------------------------------------------------------------
 * 思路：对撞指针交换元素
 * -------------------------------------------------------------------
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
func reverseString(s []byte) {
	l, r := 0, len(s)-1
	for l < r {
		c := s[l]
		s[l] = s[r]
		s[r] = c
		l++
		r--
	}
}

func main() {
	s := []byte{'h', 'e', 'l', 'l', 'o'}
	fmt.Println(s)
	reverseString(s)
	fmt.Println(s)
}
