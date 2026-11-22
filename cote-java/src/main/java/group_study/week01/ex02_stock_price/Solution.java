package group_study.week01.ex02_stock_price;

// [프로그래머스] 주식가격
// https://school.programmers.co.kr/learn/courses/30/lessons/42584

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] solution(int[] prices) {
        int[] result = new int[prices.length];

        // 더 작은 가격이 나타나지 않아
        // 가격이 결정되지 않은 인덱스들 모음
        // 감소하지 않는 함수 -> Stack에서 꺼낼수록 index와 해당하는 price 모두 작아짐
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty()) {
                int undetermined = stack.pop();

                // 이전 미결정 가격이 현재 가격보다 클 경우
                // 미결정 가격을 결정할 수 있음
                if (prices[undetermined] <= prices[i]) {
                    stack.push(undetermined);
                    break;
                }

                result[undetermined] = i - undetermined;
            }

            stack.push(i);
        }

        // 미결정 가격으로 남은 인덱스들의 가격을 확정함
        while (!stack.isEmpty()) {
            int index = stack.pop();
            result[index] = prices.length - index - 1;
        }

        return result;
    }
}