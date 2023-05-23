package solution;

import java.util.Arrays;
import java.util.Comparator;

public class Solution_테이블해시함수 {
	
	public int solution(int[][] data, int col, int begin, int end) {
		int answer = 0;
		
		Arrays.sort(data, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// col-1 번째 데이터 오름차순 -> 같으면 0번째 데이터 내림차순
				if(o1[col-1] == o2[col-1]) return o2[0] - o1[0];
				return o1[col-1] - o2[col-1];
			}
		});
		int[] s = new int[end-begin+1]; // S_i 데이터 배열
		int idx = 0;
		for(int i = begin-1; i <= end-1; i++) {
			int x = 0;
			for(int j = 0; j < data[i].length; j++) {
				x += data[i][j] % (i+1);
			}
			s[idx++] = x;
		}
		
		for(int i = 0; i < s.length; i++) {
			answer = answer ^ s[i];
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		int[][] arr = {{2,2,6},{1,5,10},{4,2,9},{3,8,3}};
		int result = new Solution_테이블해시함수().solution(arr,2,2,4);
		System.out.println(result);
	}
}
