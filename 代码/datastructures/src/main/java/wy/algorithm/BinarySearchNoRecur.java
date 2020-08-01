package wy.algorithm;

public class BinarySearchNoRecur {

    public int binarySearch(int arr[], int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {    //向左查找
                right = mid - 1;
            } else if (arr[mid] < target) {     //向右查找
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
