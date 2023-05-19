package solution;

import java.util.ArrayList;
import java.util.List;

public class Solution_광물캐기 {
	
	public int min = Integer.MAX_VALUE; // 최소
	public List<Integer> picks = new ArrayList<>();; // 곡괭이 하나씩 순서대로 풀기
	public boolean[] visit; // 곡괭이 방문 체크
	public int last; // dfs가 돌아야할 횟수 -> 곡괭이를 다 쓰거나, 광물을 다 캐거나
	
	public int solution(int[] pick, String[] minerals) {
		visit = new boolean[15];
		
		for(int i = 0; i < pick.length; i++) {
			// 곡괭이 종류마다 개수만큼 배열에 넣기
			for(int j = 0; j < pick[i]; j++) {
				picks.add(i);
			}
		}
		
		// 광물 총 길이의 5로 나눈 값의 올림만큼 광물 5개씩
		last = Math.min((int) Math.ceil((double) minerals.length), picks.size());
		
		dfs(0, minerals, 0);
		
		return min;
	}
	
	public void dfs(int depth, String[] minerals, int tired) {

		if(tired >= min) return;
		
		if(depth == last) {
			min = Math.min(min, tired);
			return;
		}
		
		for(int i = 0; i < picks.size(); i++) {
			if(!visit[i]) {
				int temp = tired(picks.get(i), depth, minerals);
				visit[i] = true;
				tired += temp;
				dfs(depth+1, minerals, tired);
				visit[i] = false;
				tired -= temp;
			}
		}
	}
	
	public int tired(int pick, int index, String[] minerals) {
		int result = 0;
		
		index *= 5; // 인덱스의 5배
		// 만약 남은 광물이 5개 미만일 때를 대비해서
		int range = Math.min(index+5, minerals.length);
		for(int i = index; i < range; i++) {
			switch(minerals[i]) {
			case "diamond":
				result += pick == 0 ? 1 : pick == 1 ? 5 : 25;
				break;
			case "iron":
				result += pick == 0 || pick == 1 ? 1 : 5;
				break;
			case "stone":
				result += 1;
				break;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		// 곡괭이 보다 등급이 한단계 높으면 5, 두단계 높으면 25(5^2), 같으면 1 -> 피로도 생성
		int[] picks = {0,1,1}; // 다이아, 철, 돌 곡괭이 개수 -> 곡괭이마다 광물 5개 가능
		String[] minerals = {"diamond", "diamond", "diamond", "diamond", 
				"diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
		int result = new Solution_광물캐기().solution(picks, minerals);
		System.out.println(result);
	}
}
