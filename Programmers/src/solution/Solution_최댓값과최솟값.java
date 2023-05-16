package solution;

public class Solution_최댓값과최솟값 {
	
	public String solution(String s) {
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        String[] str = s.split(" ");
        for(int i = 0; i < str.length; i++) {
            int x = Integer.parseInt(str[i]);
            System.out.println(x);
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
    
        return min + " " + max;
    }
	
	public static void main(String[] args) {
		String s = "-1 -2 -3 -4";
		String result = new Solution_최댓값과최솟값().solution(s);
		System.out.println(result);
	}
}
