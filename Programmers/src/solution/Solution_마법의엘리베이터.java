package solution;

public class Solution_마법의엘리베이터 {
	
	public int solution(int storey) {
		int answer = 0;
		
		while(storey != 0) {
			int x = storey % 10; // 맨뒤부터 나머지
			if(x > 5) {
				storey += 10 - x;
				answer += 10 - x;
			} 
			// 다음 10의 자리수가 5이상고 현재 5면 5층을 내려가는거 보다 
			// 5층을 올라가서 다음 자리수를 10에 가깝게 만드는게 빠름
			else if (x == 5 && (storey / 10) % 10 >= 5){
				storey += 10 - x;
				answer += 10 - x;
			}
			
			else {
				answer += x;
			}
			storey = storey / 10; // 나머지 없애기
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		int result = new Solution_마법의엘리베이터().solution(123646789);
		System.out.println(result);
	}
}
