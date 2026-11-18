package group_study.week01.ex02_stock_price;

// [프로그래머스] 주식가격
// https://school.programmers.co.kr/learn/courses/30/lessons/42584

import java.sql.Time;
import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        PriorityQueue<Time> pq = new PriorityQueue<>(
                Comparator.comparingInt(Time::price)
                        .thenComparingInt(Time::index)
        );
        for (int i = 0; i < prices.length - 1; i++) {
            pq.offer(new Time(i, prices[i]));
        }

        int[] answer = new int[prices.length];;
        boolean[] polled = new boolean[prices.length];

        while (!pq.isEmpty()) {
            Time current = pq.poll();
            polled[current.index()] = true;

            for (int i = 0; i <= current.index(); i++) {
                if (polled[i]) {
                    answer[i]++;
                }
            }
        }

        return answer;
    }

    private record Time(int index, int price) {

    }
}

class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        var r1 = sol.solution(new int[]{1, 2, 3, 2, 3});
        System.out.println(Arrays.toString(r1));

        var r2 = sol.solution(new int[]{5, 4, 3, 2, 1});
        System.out.println(Arrays.toString(r2));

        var r3 = sol.solution(new int[]{5, 1, 5, 1, 5});
        System.out.println(Arrays.toString(r3));
    }
}
