package solution;

public class Solution_두원사이의정수쌍 {
	
	public long solution(int r1, int r2) {
		long answer = 0;
		
		// x축 기준으로 1부터 r2까지 r2원과 r1원 사이 점 개수 구하기
		for(int i = 1; i <= r2; i++) {
			// x=i 기준 내부 원에서 올림 -> 내부 원 바로 바깥 점
			long min = (long) Math.ceil(Math.sqrt(1.0*r1*r1 - 1.0*i*i));
			
			// x=i 기준 외부 원에서 내림 -> 외부 원 바로 아래 점
			long max = (long) Math.floor(Math.sqrt(1.0*r2*r2 - 1.0*i*i));
			
			// x=i 기준 외부 원 바로 아래점 - 내부 원 바로 바깥 점 +1 하면 외부 원과 내부 원 사이 점 개수
			answer += (max - min + 1);
		}
		
		return answer * 4;
	}
	
	public static void main(String[] args) {
		int r1 = 2;
		int r2 = 3;
		long result = new Solution_두원사이의정수쌍().solution(r1, r2);
		System.out.println(result);
	}
}
