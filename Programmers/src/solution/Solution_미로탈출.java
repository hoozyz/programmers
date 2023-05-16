package solution;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_미로탈출 {

	public int n, m; // n*m
	public Node start; // 시작 지점
	public char[][] map;
	public boolean[][][] visit;
	public int[] dx = { -1, 1, 0, 0 };
	public int[] dy = { 0, 0, -1, 1 };

	public int solution(String[] maps) {

		n = maps.length;
		m = maps[0].length();
		map = new char[n][m];
		visit = new boolean[n][m][2];

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < m; j++) {
				map[i][j] = maps[i].charAt(j);

				if (map[i][j] == 'S') {
					start = new Node(i, j, 0, 0);
				}
			}
		}

		return bfs();
	}

	public int bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(start);
		visit[start.x][start.y][0] = true; // 레버를 안당겼으니까 0 넣기

		while (!q.isEmpty()) {
			Node now = q.poll();

			// E 이면서 레버를 당겼을 때
			if (map[now.x][now.y] == 'E' && visit[now.x][now.y][1])
				return now.count;

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m)
					continue;

				if (map[nx][ny] == 'X' || visit[nx][ny][now.key])
					continue;

				if (map[nx][ny] == 'L') {
					visit[nx][ny][1] = true;
					q.offer(new Node(nx, ny, now.count + 1, 1));
				} else {
					visit[nx][ny][now.key] = true; // 키 유무 그대로 다음 좌표
					q.offer(new Node(nx, ny, now.count + 1, now.key));
				}

			}
		}

		return -1; // E를 못갈 때
	}

	public class Node {
		int x, y, count, key;

		public Node(int x, int y, int count, int key) {
			this.x = x;
			this.y = y;
			this.count = count; // 이동 거리
			this.key = key; // 레버 당긴 유무
		}
	}

	public static void main(String[] args) {
		String[] arr = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
		int result = new Solution_미로탈출().solution(arr);
		System.out.println(result);
	}
}
