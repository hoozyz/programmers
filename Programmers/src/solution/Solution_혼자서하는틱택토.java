package solution;

public class Solution_혼자서하는틱택토 {
	
	public int x, o = 0;
	public boolean check = false; // false면 안되는 경우
	public char[][] result = new char[3][3];
	
	public int solution(String[] board) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				char c = board[i].charAt(j);
				if(c == 'O') o++; 
				if(c == 'X') x++; 
				
				result[i][j] = c;
			}
		}
		
		if(x > o || x+1 < o) return 0;
		
		check();
		
		if(!check) return 0;
		
		return 1;
	}
	
	// o가 2번 빙고, x가 0번 빙고면 가능
	// o가 1번, x가 1번은 불가능
	public void check() {
		int oWin = 0; // O 빙고 횟수
		int xWin = 0; // X 빙고 횟수
		
		int ox = 1; // 1,1인 중심에서 비교
		int oy = 1;
		
		// 서로 반대편끼리 바로 다음 인덱스에 넣기
		int[] dx = {-1, 1, -1, 1, -1, 1, 0, 0};
		int[] dy = {-1, 1, 0, 0, 1, -1, -1, 1};
		
		for(int i = 0; i < 8; i+=2) {
			// 서로 반대편이 같을 때 -> 가운데에 따라 빙고 유무
			int nx1 = ox + dx[i];
			int ny1 = oy + dy[i];
			int nx2 = ox + dx[i+1];
			int ny2 = ox + dy[i+1];
			
			if(result[nx1][ny1] == result[nx2][ny2]) {
				if(result[nx1][ny1] == result[ox][oy]) {
					if(result[ox][oy] == 'O') oWin++;
					else if (result[ox][oy] == 'X') xWin++;
				}
			}
		}
		
		// 중심제외 가로 세로 빙고 체크
		for(int i = 0; i <= 2; i+=2) {
			if(result[i][0] == result[i][1] && result[i][1] == result[i][2]) {
				if(result[i][0] == 'O') oWin++;
				else if (result[i][0] == 'X') xWin++;
			}
			if(result[0][i] == result[1][i] && result[1][i] == result[2][i]) {
				if(result[0][i] == 'O') oWin++;
				else if (result[0][i] == 'X') xWin++;
			}
		}
		
		if(xWin == 0 && oWin == 2) {
			if(x == 4 && o == 5) {
				check = true;
			}
		} else if(oWin == 1) {
			if(xWin == 0 && x < o) {
				check = true;
			}
		} else if(oWin == 0) {
			if(xWin == 0 && x <= o) {
				check = true;
			}
			if(xWin == 1 && x == o) {
				check = true;
			}
		} 
	}
	// 중앙에서 8방향 가고 빙고 개수만큼 O의 개수에서 뺀 개수가 X의 개수와 같으면 가능한 경우
	public static void main(String[] args) {
		String[] arr = {"...", "...", "..."};
		int result = new Solution_혼자서하는틱택토().solution(arr);
		System.out.println(result);
	}
}
