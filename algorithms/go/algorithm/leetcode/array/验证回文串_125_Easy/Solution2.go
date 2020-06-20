package main

import (
	"fmt"
)

/**
 * 题目地址：https://leetcode-cn.com/problems/valid-palindrome/
 * 编号：125
 * -------------------------------------------------------------------
 * 思考：
 * -------------------------------------------------------------------
 * 思路：
 *  对撞指针遍历比较
 *  统一转成大写：ch & 0b11011111 简写：ch & 0xDF
 *  统一转成小写：ch | 0b00100000 简写：ch | 0x20
 * -------------------------------------------------------------------
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
func isPalindrome(s string) bool {
	l, r := 0, len(s)-1
	for l < r { // 这里不能等于
		for l < r && !valid(s[l]) {
			l++
		}
		for l < r && !valid(s[r]) {
			r--
		}
		if (s[l] & 0xDF) != (s[r] & 0xDF) {
			return false
		}
		l++
		r--
	}
	return true
}

func valid(c uint8) bool {
	return (c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122)
}

func main() {
	fmt.Println(isPalindrome("A man, a plan, a canal: Panama"))
}
