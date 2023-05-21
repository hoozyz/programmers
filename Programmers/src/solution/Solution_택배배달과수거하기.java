package solution;

import java.util.Stack;

public class Solution_택배배달과수거하기 {
	
	public long solution(int cap, int n, int[] del, int[] pic) {
		long answer = 0;
		// 배달 스택
		Stack<Integer> dStack = new Stack<>();
        Stack<Integer> pStack = new Stack<>();

        // 가까운 순으로 집마다(거리로 계산) 하나씩 스택 쌓고 cap만큼 반복하면서 배달, 수거
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < del[i]; j++) {
                dStack.push(i + 1);
            }
            for (int j = 0; j < pic[i]; j++) {
                pStack.push(i + 1);
            }
        }

        // 스택에서 제일 마지막 인덱스+1을 저장하고, cap만큼 빼기
        // 배달 또는 수거를 완료했을 때 빠져나와서 완료못한 배달 또는 수거를 아래에서 완료
        while (!dStack.isEmpty() && !pStack.isEmpty()) {
            int d = dStack.peek();
            int p = pStack.peek();

            for (int i = 0; i < cap; i++) {
                if (!dStack.isEmpty()) dStack.pop();
                if (!pStack.isEmpty()) pStack.pop();
            }

            answer += Math.max(d, p) * 2L;
        }
        
        // 남은거 다시 돌기
        while (!dStack.isEmpty()) {
            int d = dStack.peek();

            for (int i = 0; i < cap; i++) {
                if (!dStack.isEmpty()) dStack.pop();
            }

            answer += d * 2L;
        }

        while (!pStack.isEmpty()) {
            int p = pStack.peek();

            for (int i = 0; i < cap; i++) {
                if (!pStack.isEmpty()) pStack.pop();
            }

            answer += p * 2L;
        }
		
		return answer;
	}
	
	public static void main(String[] args) {
		int[] arr1 = {1, 0, 2, 0, 1, 0, 2};
		int[] arr2 = {0, 2, 0, 1, 0, 2, 0};
		long result = new Solution_택배배달과수거하기().solution(2, 7, arr1, arr2);
		System.out.println(result);
	}
}
