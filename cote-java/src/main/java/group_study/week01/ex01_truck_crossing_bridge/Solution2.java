package group_study.week01.ex01_truck_crossing_bridge;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution2 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // 현재 다리 위 트럭 무게
        int currentWeight = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        int nextTruckIndex = 0;
        int elapsedTime = 0;
        int leftTime = 0;
        while (!queue.isEmpty()) {
            // out: 다리를 지난 트럭의 무게
            int out = queue.poll();
            currentWeight -= out;
            if (nextTruckIndex < truck_weights.length) {
                // in: 다리에 진입할 트럭의 무게
                int in = truck_weights[nextTruckIndex];
            }
        }
        return 0;
    }
}
