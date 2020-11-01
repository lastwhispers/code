package main

import (
	"fmt"
)

/**
 * 题目地址：https://leetcode-cn.com/problems/reverse-vowels-of-a-string/
 * 编号：345
 * -------------------------------------------------------------------
 * 思考：
 * -------------------------------------------------------------------
 * 思路：对撞指针条件交换元素
 *  元音字母：a,e,i,o,u 大小写
 * -------------------------------------------------------------------
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
func reverseVowels(s string) string {
	l, r := 0, len(s)-1
	chars := []uint8(s)
	for l < r {
		for l < r && !valid(chars[l]) {
			l++
		}
		for l < r && !valid(chars[r]) {
			r--
		}
		if l < r { // 需要判断
			c := chars[l]
			chars[l] = chars[r]
			chars[r] = c
			l++
			r--
		}
	}
	return string(chars)
}

func valid(c uint8) bool {
	return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' ||
		c == 'E' || c == 'I' || c == 'O' || c == 'U'
}

func main() {
	s := "hello"
	fmt.Println(s)
	fmt.Println(reverseVowels(s))
}
