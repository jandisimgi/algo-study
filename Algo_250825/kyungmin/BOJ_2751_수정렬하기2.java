import java.io.*;

public class Main {
    static int[] tmp;

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }

        // 남은 원소 처리
        while (i <= mid) tmp[k++] = arr[i++];
        while (j <= right) tmp[k++] = arr[j++];

        // 원래 배열에 복사
        for (int t = left; t <= right; t++) {
            arr[t] = tmp[t];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        tmp = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(arr, 0, N - 1);

        for (int num : arr) {
            bw.write(num + "\n");
        }
        bw.flush();
    }
}
