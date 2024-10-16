import java.util.Scanner;
public class QuickSort {
    static int partition(int arr[], int low, int high) {
        int i = low;
        int j = high;
        while (i < j) {
            while (arr[i] <= arr[low] && i <= high - 1) {
                i++;
            }
            while (arr[j] > arr[low] && j >= 1) {
                j--;
            }
            if (i < j) {
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap arr[low] and arr[j]
        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;
        return j;
    }
    static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int j = partition(arr, low, high);
            quickSort(arr, low, j-1);
            quickSort(arr, j+1, high);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        quickSort(arr, 0, n-1);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}