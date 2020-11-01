package main

import (
	"fmt"
	"strings"
)

/**
 * 题目地址：https://leetcode-cn.com/problems/valid-palindrome/
 * 编号：125
 * -------------------------------------------------------------------
 * 思考：
 * -------------------------------------------------------------------
 * 思路：
 *  对撞指针遍历比较
 * -------------------------------------------------------------------
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
func isPalindrome(s string) bool {
	upperS := strings.ToUpper(s)
	l, r := 0, len(s)-1
	for l < r {
		for l < r && !valid(upperS[l]) {
			l++
		}
		for l < r && !valid(upperS[r]) {
			r--
		}
		if upperS[l] != upperS[r] {
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
