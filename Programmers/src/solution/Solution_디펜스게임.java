package solution;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_디펜스게임 {
	
	public int solution(int n, int k, int[] enemy) {
		int answer = 0;
		
		// 최대 힙 -> 내림차순 저장
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		for(int e : enemy) {
			maxHeap.offer(e); // 현재 숫자 넣기
			n -= e; // 일단 빼기
			if(n < 0) { // 만약 음수면 -> 못막으면
				// 무적권 없으면 게임 끝
				if(k == 0) {
					break;
				}
				// 지금까지 막은 적중 제일 많은 적이 현재 적보다 적으면 무적권으로 현재 적 막기
				if(maxHeap.peek() < e) { 
					n += e;
				} 
				// 지나온 적이 더 많으면 지나온 제일 큰 적한테 무적권 쓰고 제일 큰 적만큼 다시 아군 추가
				else {
					n += maxHeap.poll();
				}
				k--; // 무적권 사용한 거 뺴기
				// 지나온 가장 큰 적을 무적권으로 방어해도 현재 적을 못방어하면 게임 끝
				if(n < 0) {
					break;
				}
			} 
			answer++;
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		int[] arr = {4,2,4,5,3,3,1};
		int result = new Solution_디펜스게임().solution(2,4,arr);
		System.out.println(result);
	}
}
