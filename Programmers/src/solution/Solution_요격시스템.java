package solution;

import java.util.Arrays;
import java.util.Comparator;

public class Solution_요격시스템 {
	
	public int solution(int[][] targets) {
		int answer = 0;
		
		// 끝 좌표 기준 오름차순
		Arrays.sort(targets, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) return o1[0] - o2[0];
				return o1[1] - o2[1];
			}
		});
		
		int end = targets[0][1];
		answer++; // 제일 처음(왼쪽) 요격시스템
		
		for(int[] target : targets) {
			// 이전 요격시스템보다 뒤 좌표일 때 새로운 요격시스템 생성
			if(end <= target[0]) {
				answer++;
				end = target[1];
			}
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		int[][] arr = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};
		int result = new Solution_요격시스템().solution(arr);
		System.out.println(result);
	}
}
