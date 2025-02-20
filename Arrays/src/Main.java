import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        System.out.println(removeElement(arr, 1));

//        String[] strarray = {"apple", "banana", "kiwi", "pear"};
//        System.out.println(findLongestString(strarray));
//        for (int c: FindMinMax(arr)) {
//            System.out.println(c);
//        }

//        int[] smp = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        System.out.println(removeDuplicates(smp));

//        int[] prices = {7, 1, 5, 3, 6, 4};
//        System.out.println(maxProfit(prices));
        int[] nums = {1,2,3,4,5};
        System.out.println(maxSubarray(nums));
    }

    // Interview question 01 -> Remove element.
    public static int removeElement(int[] arr, int val) {
        int newLength = 0;
        for (int c: arr) {
            if ( c != val) {
                newLength++;
            }
        }
        return newLength;
    }

    // Interview Question 02 -> Find Max Min from array.
    public static int[] FindMinMax(int[] arr) {
        Arrays.sort(arr);
        return new int[]{arr[arr.length - 1], arr[0]};
    }

    //Interview Question 03 -> Find Longest string in array.
    public static String findLongestString(String[] stringList) {
        String str = "";
        for (String c: stringList) {
            if (c.length() > str.length()) {
                str = c;
            }
        }
        return str;
    }

    // Interview Question 04 -> Remove duplicates.
    public static int removeDuplicates(int[] nums){
        if (nums.length == 0) return 0;
        int newLength = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                newLength++;
            }
        }
        return newLength;
    }

    // Interview Question 05 -> Max profit.{7, 1, 5, 3, 6, 4}
    public static int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int stockPurchased = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < stockPurchased) {
                stockPurchased = prices[i];
            }else {
                profit  = Math.max(profit, prices[i] - stockPurchased);
            }
        }
        return profit;
    }

    // Interview Question 06 -> Rotate.
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public static void reverse(int[] nums,int start, int end) {
        while (start < end) {
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            start++;
            end--;
        }
    }

    // Interview Question 07 -> Max Sub Array.

    public static int maxSubarray(int[] nums) {
        int maxSum = nums[0];
        int localSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            localSum = Math.max(localSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, localSum);
        }
        return maxSum;
    }

}