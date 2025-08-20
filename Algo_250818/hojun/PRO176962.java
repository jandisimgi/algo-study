package pro.practice;

import java.util.*;

class PRO176962 {

    class Plan implements Comparable<Plan> {
        String name;
        int start;
        int playtime;

        Plan(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }

        @Override
        public int compareTo(Plan o) {
            return this.start - o.start;
        }
    }

    public String[] solution(String[][] plans) {

        List<Plan> list = new ArrayList<>();
        for (String[] plan : plans) {
            list.add(new Plan(
                plan[0],
                Integer.parseInt(plan[1].substring(0, 2)) * 60 +
                Integer.parseInt(plan[1].substring(3, 5)),
                Integer.parseInt(plan[2])
            ));
        }

        Collections.sort(list);

        Stack<Plan> stack = new Stack<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < list.size() - 1; i++) {
            Plan current = list.get(i);
            Plan next = list.get(i + 1);

            int diff = next.start - current.start;
            if (current.playtime <= diff) {
                result.add(current.name);
                diff -= current.playtime;

                while (!stack.isEmpty() && diff > 0) {
                    Plan prev = stack.pop();
                    if (prev.playtime <= diff) {
                        diff -= prev.playtime;
                        result.add(prev.name);
                    } else {
                        prev.playtime -= diff;
                        stack.push(prev);
                        break;
                    }
                }
            } else {
                current.playtime -= diff;
                stack.push(current);
            }
        }

        result.add(list.get(list.size() - 1).name);

        while (!stack.isEmpty()) {
            result.add(stack.pop().name);
        }
        String[] answer = result.toArray(new String[0]);

        return answer;
    }
}
