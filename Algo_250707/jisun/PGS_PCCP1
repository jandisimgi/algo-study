public class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLength = parseTime(video_len);
        int currentPos = parseTime(pos);
        int opStart = parseTime(op_start);
        int opEnd = parseTime(op_end);

     
        if (currentPos >= opStart && currentPos <= opEnd) {
            currentPos = opEnd;
        }

        // 명령어 실행
        for (String command : commands) {
            if (command.equals("prev")) {
                currentPos = Math.max(0, currentPos - 10);
            } else if (command.equals("next")) {
                currentPos = Math.min(videoLength, currentPos + 10);
            }

            // 이동 후 오프닝 스킵 확인
            if (currentPos >= opStart && currentPos <= opEnd) {
                currentPos = opEnd;
            }
        }

        return formatTime(currentPos); 
    }

    // "mm:ss" → 초 변환
    private int parseTime(String time) {
        String[] parts = time.split(":");
        int min = Integer.parseInt(parts[0]);
        int sec = Integer.parseInt(parts[1]);
        return min * 60 + sec;
    }

    // 초 → "mm:ss" 문자열 변환
    private String formatTime(int seconds) {
        int m = seconds / 60;
        int s = seconds % 60;
        return String.format("%02d:%02d", m, s);
    }
}



