package solution;

import java.util.Arrays;
import java.util.Stack;

public class Solution_뒤에있는큰수찾기 {
	
	public int len;
	public int[] answer;
	
	public int[] solution(int[] numbers) {
		len = numbers.length;
		answer = new int[len];
		
		dfs(numbers);
		
		return answer;
	}

	// 원래 인덱스, 값
	public void dfs(int[] numbers) {
		Stack<int[]> stack = new Stack<>();
		
		stack.add(new int[] {0, numbers[0]});
		stack.add(new int[] {1, numbers[1]});

		while(true) {
			int[] now = stack.peek();
			int size = stack.size(); // 스택에서의 인덱스
			int index = now[0];
			int val = now[1];
			int next = 0;
			if(index != len-1) {
				next = numbers[index+1];
			}
			// 스택 아래 있는 값들이 작으면 다 바꾸기
			if(size > 1 && stack.elementAt(size-2)[1] < val) { 
				int[] before = stack.elementAt(size-2);
				answer[before[0]] = val;
				stack.remove(size-2);
				continue; // 반복해서 아래 스택과 비교 후 제거
				
			} 
			// 다음 숫자가 클 때
			if(val < next) {
				answer[index] = next;
				stack.pop(); // 스택에서 제거 -> 다음에 필요없음
				
			} 
			if(index == len-1) {
				break;
			}
			// 다음 숫자 넣기
			stack.add(new int[] {index+1, next});
		}
		
		while(!stack.isEmpty()) {
			int index = stack.pop()[0];
			
			answer[index] = -1;
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {2, 3, 3, 5};
		int[] result = new Solution_뒤에있는큰수찾기().solution(arr);
		System.out.println(Arrays.toString(result));
	}
}
