package solution;

import java.util.Arrays;

public class Solution_연속된부분수열의합 {
	
	public int[] solution(int[] sequence, int k) {
        
        int n = sequence.length;
        int left = 0, right = n; // 합이 k일 때 인덱스 범위
        int sum = 0;
        for(int L = 0, R = 0; L < n; L++) {
        	// L ~ R 구간까지 sum이 k 이상까지만 더하기
        	// sum을 넘으면 L 원소값만 빼고 다시 다음 원소
        	// 반복 후 sum이 k와 같으면 left, right에 저장
            while(R < n && sum < k) {
                sum += sequence[R++];
            }
            
            if(sum == k) {
                int range = R - L - 1; 
                if((right - left) > range) { // 인덱스 사이 범위가 작을 때
                    left = L;
                    right = R - 1;
                }
            }
            
            sum -= sequence[L];
        }
        
        return new int[] {left, right};
    }
	
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5};
		int a = 7;
		int[] result = new Solution_연속된부분수열의합().solution(arr, a);
		System.out.println(Arrays.toString(result));
	}
}
