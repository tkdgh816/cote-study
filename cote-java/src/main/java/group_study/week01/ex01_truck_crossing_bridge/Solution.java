package group_study.week01.ex01_truck_crossing_bridge;

// [프로그래머스] 다리를 지나는 트럭
// https://school.programmers.co.kr/learn/courses/30/lessons/42583

// 트럭 한 대가 다리 위에 있는 시간: bridge_length
// truck_weights는 순차적으로 다리에 진입 -> 그리디 문제 아님
// FIFO 구조 -> 큐 사용
// 매 초마다 다리 위의 무게를 계산하고
// 트럭이 빠지고 새로운 트럭을 넣을 때 무게 때문에 넣지 못하면
// 무게가 0인 트럭을 넣는다고 생각함

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // 현재 다리 위 트럭 무게
        int currentWeight = 0;
        Queue<Integer> queue = new ArrayDeque<>();

        // 큐(다리)를 무게가 0인 트럭으로 모두 채움
        // 시간은 0
        for (int i = 0; i < bridge_length; i++) {
            queue.offer(0);
        }
        int nextTruckIndex = 0;
        int elapsedTime = 0;

        while (!queue.isEmpty()) {
            // out: 다리를 지난 트럭의 무게
            int out = queue.poll();
            currentWeight -= out;

            // 건너갈 트럭이 있으면 큐에 넣을 수 있고
            // 건너갈 트럭이 없으면 더이상 큐에 넣지 않고
            // 큐가 빌 때까지(마지막 트럭이 건너갈 때까지) 반복문 순회
            if (nextTruckIndex < truck_weights.length) {
                // in: 다리에 진입할 트럭의 무게
                int in = truck_weights[nextTruckIndex];

                // 진입하여 다리 위 트럭 무게가 제한 무게 이하일 때에만
                // 진입 허용(큐에 넣음)
                // 진입 불가 시 무게 0인 가상의 트럭을 큐에 넣음
                if (currentWeight + in <= weight) {
                    queue.offer(in);
                    currentWeight += in;
                    nextTruckIndex++;
                } else {
                    queue.offer(0);
                }
            }

            // 한 번의 순회 당 1초의 시간을 더함
            elapsedTime++;
        }

        return elapsedTime;
    }
}