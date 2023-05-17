package solution;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class Solution_과제진행하기 {
	
	public String[] solution(String[][] plans) {
		String[] answer = new String[plans.length];
		int answerIndex = 0; // 결과 배열 인덱스
		
		Stack<Plan> stop = new Stack<>();
		PriorityQueue<Plan> pq = new PriorityQueue<>(
				// o1, o2 int[] 를 비교해서 start 오름차순
				(o1, o2) -> (o1.start - o2.start));
		
		for(int i = 0; i < plans.length; i++) {
			int h = Integer.parseInt(plans[i][1].split(":")[0]);
			int m = Integer.parseInt(plans[i][1].split(":")[1]);
			int start = h*60 + m;
			int time = Integer.parseInt(plans[i][2]);
			
			pq.offer(new Plan(plans[i][0], start, time));
		}
		
		while(!pq.isEmpty()) {
			Plan now = pq.poll();
			String name = now.name;
			int start = now.start;
			int time = now.time;
			
			int currentTime = start; // 현재 시간 (중단되거나 끝난 시간)
			
			// 진행할 과제가 더 남았을 때
			if(!pq.isEmpty()) {
				Plan next = pq.peek();
				
				// 다음 과제가 시작하기 전에 일 끝났을 때 -> 다음 과제 사이에 중단된 과제 있으면 실행
				if(currentTime + time < next.start) {
					answer[answerIndex++] = name;
					currentTime += time; // 과제 끝나서 과제 시간 추가
					
					while(!stop.isEmpty()) {
						Plan stopPlan = stop.pop();
						System.out.println(stopPlan.time);
						// 중단된 과제가 다음 과제보다 빨리 끝날 경우
						if(currentTime + stopPlan.time <= next.start) {
							answer[answerIndex++] = stopPlan.name;
							currentTime += stopPlan.time;
							continue;
						}
						// 다음 과제 시작 전에 중단된 과제를 못끝낼 경우
						else {
							int x = stopPlan.time - (next.start - currentTime);
							stop.push(new Plan(stopPlan.name, x));
							break; // 중단된 과제는 더이상 못하니까 다음 과제 시작
						}
					}
					
				}
				// 이번 과제 끝난 후 바로 다음 과제 시작할 경우 -> 바로 answer에 넣기
				else if (currentTime + time == next.start) {
					answer[answerIndex++] = name;
					continue;
				}
				// 이번 과제 끝내기 전에 다음 과제가 시작할 경우
				else {
					// 현재시간과 다음 과제 시작시간의 차이만큼 중단된 과제의 시간에서 빼고
					// 중단된 과제 목록에 추가
					int x = time - (next.start-currentTime);
					stop.push(new Plan(name, x));
				}
			}
			// 진행할 과제가 없을 때 -> 중단된 과제 순서대로 다 실행하기 
			else {
				answer[answerIndex++] = name; // 현재 과제 넣기
				currentTime += time; // 시간 변경
				
				while(!stop.isEmpty()) {
					Plan stopPlan = stop.pop();
					answer[answerIndex++] = stopPlan.name;
				}
			}
		}
		
		return answer;
	}
	
	public class Plan {
		String name;
		int start;
		int time;
		
		// 과제 목록
		public Plan(String name, int start, int time) {
			this.name = name;
			this.start = start;
			this.time = time;
		}
		
		// 중단된 과제
		public Plan(String name, int time) {
			this.name = name;
			this.time = time;
		}
	}
	
	public static void main(String[] args) {
		String[][] arr = {{"1", "00:00", "30"}, {"2", "00:10", "10"}, 
				{"3", "00:30", "10"}, {"4", "00:50", "10"}};
		String[] result = new Solution_과제진행하기().solution(arr);
		System.out.println(Arrays.toString(result));
	}
}
