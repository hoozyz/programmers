package solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_호텔대실 {
    public Map<Integer, Integer> map = new HashMap<>();
	public List<Time> time = new ArrayList<>();
	
	public int solution(String[][] timeArr) {

		for(int i = 0; i < timeArr.length; i++) {
			int start = Integer.parseInt(timeArr[i][0].replace(":", ""));
			int end;
			if(timeArr[i][1].charAt(3) == '5') {
				end = Integer.parseInt(timeArr[i][1].replace(":", ""))+50;
			} else {
				end = Integer.parseInt(timeArr[i][1].replace(":", ""))+10;
			}
			time.add(new Time(start, end));
		}
		
		Collections.sort(time); // 시작시간 오름차순
		map.put(0, time.get(0).end); // 0번방에 마지막시간 넣기
		
		// 
		for(int i = 1; i < time.size(); i++) {
			int start = time.get(i).start;
			int end = time.get(i).end;
			timePlus(i, start, end);
		}
		
		return map.size();
	}
	
	// 시작시간을 가지고 i 전방까지 반복하며 마감시간보다 빠르면 새로운방, 느리거나 같으면 방 뒤에 넣고 map값 바꾸기
	public void timePlus(int index, int start, int end) {
		int i = 0;
		while(i < index) {
			if(map.getOrDefault(i, 0) != 0) {
				if(map.get(i) <= start) { // 시작시간이 방의 마감시간보다 느릴 때
					map.replace(i, end); // 현재방에 시간 넣고, 마지막 시간 바꾸기 
					return;
				}
			}
			
			i++;
		}
		// 시작시간 보다 빠른 마감시간 방이 없을 때 -> 새로 방 생성
		map.put(map.size(), end); // 새로운 방에 지금 시간 넣기
	}
	
	public class Time implements Comparable<Time>{
		int start, end;
		
		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		// 오름차순
		@Override
		public int compareTo(Time other) {
			return this.start - other.start;
		}
	}
	
	public static void main(String[] args) {
		String[][] arr = {{"15:00", "17:00"}, {"16:40", "18:20"}, 
		                  {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
		int result = new Solution_호텔대실().solution(arr);
		System.out.println(result);
	}
}
