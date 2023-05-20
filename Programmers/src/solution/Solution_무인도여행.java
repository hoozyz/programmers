package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution_무인도여행 {
	
	public int[][] map;
	public boolean[][] visit;
	public int n, m;
	public int[] dx = {-1, 1, 0, 0};
	public int[] dy = {0, 0, -1, 1};
	
	public int[] solution(String[] maps) {
		n = maps.length;
		m = maps[0].length();
		map = new int[n][m];
		visit = new boolean[n][m];
		List<Integer> result = new ArrayList<>();
		int sx = -1; // 시작 -> 처음 숫자
		int sy = -1;
		
		for(int i = 0; i < n; i++) {
			
			for(int j = 0; j < m ;j ++) {
				char c = maps[i].charAt(j);
				if(c == 'X') {
					map[i][j] = 0;
				} else {
					map[i][j] = (int) (c - '0');
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			
			for(int j = 0; j < m ; j++) {
				// 숫자가 있고, 방문하지 않았을 때
				if(map[i][j] != 0 && !visit[i][j]) { 
					sx = i;
					sy = j;
					result.add(bfs(sx, sy));
				}
			}
		}
		// 숫자가 하나도 없을 때
		if(sx == -1 && sy == -1) return new int[] {-1};
		
		Collections.sort(result);
		int[] answer = new int[result.size()];
		for(int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i);
		}
		
		return answer;
	}
	
	public int bfs(int sx, int sy) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {sx, sy});
		visit[sx][sy] = true;
		int allCount = 0; // sx, sy에서 시작한 섬의 총 식량

		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];

			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == 0 || visit[nx][ny]) {
					continue;
				}
				visit[nx][ny] = true;
				// 방문 체크 하면서 현재 좌표에 연결된 모든 숫자좌표 숫자 더함 -> 방문 다 하면 큐가 비어서 끝
				allCount += map[nx][ny];
				q.offer(new int[] {nx, ny});
			}
		}
		
		return allCount + map[sx][sy]; // 현재 시작한 좌표의 값 더하기
	}
	
	public static void main(String[] args) {
		String[] arr = {"X591X","X1X5X","X231X", "1XXX1"};
		int[] result = new Solution_무인도여행().solution(arr);
		System.out.println(Arrays.toString(result));
	}
}
