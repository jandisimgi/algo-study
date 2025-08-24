import java.io.*;
import java.util.*;

public class StampSolutionTest {
    // 스탬프 정보 저장
    private Map<Integer, StampInfo> stamps;
    
    // 실제 찍힌 스탬프들의 좌표와 값 저장 (sparse matrix)
    private Map<Long, Integer> grid;
    
    // 스탬프 정보 클래스
    static class StampInfo {
        int[][] pattern;
        int size;
        
        StampInfo(int size, int[][] pattern) {
            this.size = size;
            this.pattern = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    this.pattern[i][j] = pattern[i][j];
                }
            }
        }
    }
    
    // 회전된 패턴 캐시
    private Map<String, int[][]> rotationCache;
    
    public void init(int N) {
        stamps.clear();
        grid.clear();
        rotationCache.clear();
        System.out.println("init(" + N + ") 완료");
    }
    
    public void makestamp(int mid, int msize, int mcnt, int[][] mpixel) {
        stamps.put(mid, new StampInfo(msize, mpixel));
        System.out.println("makestamp(" + mid + ", " + msize + "x" + msize + ") 완료");
        
        // 스탬프 패턴 출력
        System.out.println("스탬프 " + mid + " 패턴:");
        for (int i = 0; i < msize; i++) {
            for (int j = 0; j < msize; j++) {
                System.out.print(mpixel[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public void stamp(int mid, int row, int col, int dir) {
        StampInfo stampInfo = stamps.get(mid);
        if (stampInfo == null) {
            System.out.println("스탬프 " + mid + " 없음!");
            return;
        }
        
        // 회전된 패턴 가져오기
        int[][] rotatedPattern = getCachedRotation(mid, dir);
        
        System.out.println("stamp(" + mid + ", " + row + ", " + col + ", " + dir + ") - " + 
                          getDirectionName(dir) + " 회전");
        
        // 회전된 패턴 출력
        System.out.println("회전된 패턴:");
        for (int i = 0; i < stampInfo.size; i++) {
            for (int j = 0; j < stampInfo.size; j++) {
                System.out.print(rotatedPattern[i][j] + " ");
            }
            System.out.println();
        }
        
        // 스탬프 적용
        int appliedCount = 0;
        for (int i = 0; i < stampInfo.size; i++) {
            for (int j = 0; j < stampInfo.size; j++) {
                if (rotatedPattern[i][j] != 0) {
                    long key = getKey(row + i, col + j);
                    grid.put(key, rotatedPattern[i][j]);
                    appliedCount++;
                    System.out.println("  -> (" + (row + i) + ", " + (col + j) + ") = " + rotatedPattern[i][j]);
                }
            }
        }
        System.out.println("적용된 픽셀 수: " + appliedCount);
    }
    
    public int check(int row, int col) {
        int count = 0;
        System.out.println("check(" + row + ", " + col + ") - 25x25 영역 검사");
        
        // 디버깅을 위해 실제 값들 출력
        List<String> foundPixels = new ArrayList<>();
        
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                long key = getKey(row + i, col + j);
                if (grid.containsKey(key)) {
                    count++;
                    foundPixels.add("(" + (row + i) + ", " + (col + j) + ")=" + grid.get(key));
                }
            }
        }
        
        System.out.println("발견된 픽셀들: " + foundPixels);
        System.out.println("총 개수: " + count);
        return count;
    }
    
    // 좌표를 long으로 압축
    private long getKey(int row, int col) {
        return ((long)row << 32) | (col & 0xFFFFFFFFL);
    }
    
    // 회전된 패턴을 캐시에서 가져오기
    private int[][] getCachedRotation(int mid, int dir) {
        String cacheKey = mid + "_" + dir;
        
        return rotationCache.computeIfAbsent(cacheKey, k -> {
            StampInfo stampInfo = stamps.get(mid);
            return rotatePattern(stampInfo.pattern, stampInfo.size, dir);
        });
    }
    
    // 패턴 회전
    private int[][] rotatePattern(int[][] pattern, int size, int dir) {
        if (dir == 0) return pattern;
        
        int[][] result = new int[size][size];
        
        switch (dir) {
            case 1: // 90도 시계방향
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        result[j][size - 1 - i] = pattern[i][j];
                    }
                }
                break;
            case 2: // 180도
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        result[size - 1 - i][size - 1 - j] = pattern[i][j];
                    }
                }
                break;
            case 3: // 270도 시계방향
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        result[size - 1 - j][i] = pattern[i][j];
                    }
                }
                break;
        }
        
        return result;
    }
    
    }
}
