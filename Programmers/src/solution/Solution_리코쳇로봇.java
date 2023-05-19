package solution;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_리코쳇로봇 {
	
	public int min = Integer.MAX_VALUE;
	public int n, m;
	public char[][] board;
	public boolean[][] visit;
	public int[] dx = {-1, 1, 0, 0};
	public int[] dy = {0, 0, -1, 1};
	
	public int solution(String[] bbb) {
		n = bbb.length;
		m = bbb[0].length();
		board = new char[n][m];
		visit = new boolean[n][m];
		int sx = 0, sy = 0;
		
		for(int i = 0; i < n; i++) {
			
			for(int j = 0; j < m; j++) {
				board[i][j] = bbb[i].charAt(j);
				if(board[i][j] == 'R') {
					sx = i;
					sy = j;
				}
			}
		}

		return bfs(sx, sy);
	}
	
	// 끝점만 visit 체크, 중간점은 방문 체크x, 다음이 D이거나 벽인데 현재가 R일때 거리 리턴
	public int bfs(int sx, int sy) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(sx, sy, 0));
		int min = -1;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			int x = now.x;
			int y = now.y;
			
			if(board[x][y] == 'G') {
				if(min == -1) {
					min = now.count;
				} else {
					min = Math.min(min, now.count);
				}
				continue;
			}

			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 | ny < 0 || nx >= n || ny >= m) {
					continue;
				}
				
				if(board[nx][ny] == 'D') {
					visit[x][y] = true;
					continue;
				}
				
				// 벽이나 D를 만날 때까지 반복 이동
				while(true) {
					nx = nx + dx[i];
					ny = ny + dy[i];
					
					if(nx < 0 || ny < 0 || nx >= n || ny >= m || board[nx][ny] == 'D') {
						break;
					} 
				}
				// 벽 만나기 전으로 되돌리기
				nx = nx - dx[i];
				ny = ny - dy[i];

				if(visit[nx][ny]) { // 만약 벽만나고 온곳이 왔던곳이면
					continue;
				}
				visit[nx][ny] = true; // 이동완료하고 벽을 만나면 방문 체크
				q.offer(new Node(nx, ny, now.count+1));
				
			}
		}
		
		return min;
	}
	
	public class Node {
		int x, y, count;

		public Node(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
	
	public static void main(String[] args) {
		String[] board = {"..R", "...", ".G.", "...", "..."};
		int result = new Solution_리코쳇로봇().solution(board);
		System.out.println(result);
	}
}
