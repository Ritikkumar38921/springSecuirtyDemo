package com.springSecurity.securityDemo;

import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	private int func(int n, int count_a, int count_consecutive_l, int[][][] dp , String str) {
		if (n < 0) {
			System.out.println("str : " + str);
			return 1;
		}

		if (dp[n][count_a][count_consecutive_l] != -1) {
			return dp[n][count_a][count_consecutive_l];
		}

		int ans1 = func(n - 1, count_a, 0, dp , str + "P");
		int ans2 = 0;
		if (count_a == 0) {
			ans2 = func(n - 1, count_a + 1, count_consecutive_l, dp,str + "A");
		}
		int ans3 = 0;
		if (count_consecutive_l <= 1) {
			ans3 = func(n - 1, count_a, count_consecutive_l + 1, dp,str + "L");
		}

		dp[n][count_a][count_consecutive_l] = ans1 + ans2 + ans3;
		return dp[n][count_a][count_consecutive_l];
	}

	public void answer(int n) {
		int[][][] dp = new int[n][2][3];
		for (int[][] rows : dp) {
			for (int[] count_a : rows) {
				Arrays.fill(count_a, -1);
			}
		}
		int ans = func(n - 1, 0, 0, dp , "");
		System.out.println("ans : " + ans);
	}

}
