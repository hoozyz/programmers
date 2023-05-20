package solution;

import java.util.Arrays;

public class Solution_당구연습 {
	
	public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
		int[] answer = new int[balls.length];
		for(int i = 0; i < balls.length; i++) {
			int min = Integer.MAX_VALUE;
			int x = balls[i][0];
			int y = balls[i][1];
			
			if (startX != x || startY < y) {
				min = Math.min(min, (int) (Math.pow(x-startX, 2) + Math.pow(y+startY, 2)));
			} 
			if (startY != y || startX < x){
				min = Math.min(min, (int) (Math.pow(x+startX, 2) + Math.pow(y-startY, 2)));
			}
			if (startX != x || y < startY) {
				min = Math.min(min, (int) (Math.pow(x-startX, 2) + Math.pow(n-y+n-startY, 2)));
			} 
			if (startY != y || x < startX) {
				min = Math.min(min, (int) (Math.pow(n-x+n-startX, 2) + Math.pow(y-startY, 2)));
			}
			
			answer[i] = min;
		}
		
		
		return answer;
	}
	
	// 당구대를 기준으로 대칭 -> 큰 삼각형 피타고라스 하면 최소 거리나옴
	public static void main(String[] args) {
		int[][] balls = {{7,7}, {2,7}, {7,3}};
		int[] result = new Solution_당구연습().solution(10, 10, 3, 7, balls);
		System.out.println(Arrays.toString(result));
	}
}

