package cn.lastwhisper.interview.dp.整数的分解;

public class Solution
{
	public static void main(String[] args)
	{
		int[] nums = {3,4,5,6,7};
		for (int num : nums) {
			System.out.println(num + "\t(" + decompose(num)+ ") 种");
		}
	}
	
	// 求整数的分数情况
	public static int decompose(int num) {
		if (num < 3) return 0;
		
		// 从3开始，构建二维数组
		int[][] results = new int[num+1][];
		for (int i = 3; i <= num; i++) {
			//  分解后最小数可能的种数
			int cols = 	(i-1)/2;
			results[i] = new int[cols+1];
			results[i][0] = 0;
			for (int j = 1; j <= cols; j++) {
				// 对于最小为j的情况，至少有一个分解
				int count = 1;
				int biger = i - j;
				if (biger > 2*j) {
					// 若大数为偶数，可以分解为biger/2 + biger/2
					if (biger % 2 == 0) count++;
					// 其余的分解可用biger的分解中最小数j的情况确定
					for (int k = j; k < results[biger].length; k++)
						count += results[biger][k];
				}
				results[i][j] = count;
			}
		}
		
		int total = 0;
		// 第num行所有的和为分解的所有情况
		for (int i = 0; i < results[num].length; i++)
			total += results[num][i];
		return total;
	}
}