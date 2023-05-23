package solution;

public class Solution_점찍기 {
	
	public long solution(int k, int d) {
		long answer = 0;
		
		for(int i = 0; i <= d; i+=k) {
			// y가 가장 큰 숫자
			long max = (long) Math.floor(Math.sqrt(1.0*d*d-1.0*i*i));
			
			answer += (max / k) + 1;
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		long result = new Solution_점찍기().solution(2, 4);
		System.out.println(result);
	}
}
