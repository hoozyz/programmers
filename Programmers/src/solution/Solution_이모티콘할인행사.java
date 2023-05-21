package solution;

import java.util.Arrays;

public class Solution_이모티콘할인행사 {
	
	public int[] per = {10, 20, 30, 40};
	public int plus, price = 0; // 가입자, 판매액
	
	public int[] solution(int[][] users, int[] emoticons) {
		int[] pers = new int[emoticons.length]; // 이모티콘마다 퍼센트
		
		dfs(users, emoticons, 0, pers);
		
		return new int[] {plus, price};
	}
	
	public void dfs(int[][] users, int[] emoticons, int depth, int[] pers) {
		if(depth == emoticons.length) {
			calc(users, emoticons, pers);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			pers[depth] = per[i];
			dfs(users, emoticons, depth+1, pers);
		}
	}
	
	public void calc(int[][] users, int[] emoticons, int[] pers) {
		
		int ePlus = 0; // 이모티콘 플러스 가입자
		int total = 0; // 전체 금액
		
		for(int[] user : users) {
			int userTotal = 0; // 이번 유저 금액합
			int userPer = user[0]; // 유저 퍼센트
			int userPrice = user[1]; // 유저 금액
			
			for(int i = 0; i < pers.length; i++) {
				// 원하는 할인율 이상
				if(userPer <= pers[i]) {
					userTotal += emoticons[i]*(100-pers[i])/100;
				}
				// 금액 넘으면 이모티콘 플러스
				if(userTotal >= userPrice) {
					ePlus++;
					userTotal = 0;
					break;
				}
			}
			total += userTotal;
		}
		// 이번 할인 배열이 가입자가 더 많으면
		if(ePlus > plus) {
			plus = ePlus;
			price = total;
		} 
		// 가입자가 같을 때 금액 더 큰거로 교체
		else if (ePlus == plus) {
			price = Math.max(price, total);
		}
	}
	
	// 할인율 10 ~ 40까지 dfs 조합짜고, 조합마다 계산해서 제일 큰거 결과
	public static void main(String[] args) {
		int[][] arr1 = {{40, 10000}, {25, 10000}};
		int[] arr2 = {7000, 9000};
		int[] result = new Solution_이모티콘할인행사().solution(arr1, arr2);
		System.out.println(Arrays.toString(result));
	}
}