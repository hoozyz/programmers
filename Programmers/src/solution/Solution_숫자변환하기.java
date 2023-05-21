package solution;

import java.util.Arrays;

public class Solution_숫자변환하기 {

	public int solution(int x, int y, int n) {
		int[] dp = new int[1000001];
		Arrays.fill(dp, -1);
		dp[x] = 0;
		
		for(int i = x; i <= y; i++) {
			if(dp[i] != -1) {
				if(i + n <= y) {
					if(dp[i + n] == -1) {
						dp[i + n] = dp[i] + 1;
					} else if (dp[i + n] != -1){
						dp[i + n] = Math.min(dp[i]+1, dp[i + n]);
					}
				}
				if(i * 2 <= y) {
					if(dp[i * 2] == -1) {
						dp[i * 2] = dp[i] + 1;
					} else if (dp[i * 2] != -1){
						dp[i * 2] = Math.min(dp[i]+1, dp[i * 2]);
					}
				}
				if(i * 3 <= y) {
					if(dp[i * 3] == -1) {
						dp[i * 3] = dp[i] + 1;
					} else if (dp[i * 3] != -1) {
						dp[i * 3] = Math.min(dp[i]+1, dp[i * 3]);
					}
				}
			}
		}

		return dp[y];
	}

	public static void main(String[] args) {
		int result = new Solution_숫자변환하기().solution(10, 40, 30);
		System.out.println(result);
	}
}
