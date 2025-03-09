import java.util.Arrays;
import java.util.Stack;

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
//        int[] nums = {1, 2, 3, 4, 5, 6};
//       System.out.println(moveAllZeros(nums));
//        for (int c: rotateJuggling(nums, 2)) {
//            System.out.println(c);
//        }

        String s = "eabbae";
        System.out.println(isPalindrome(s));

        System.out.println(reverseString("Anoop"));
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

    // Interview Question 08 -> Second-largest number in an array.

    public static int secondLargest(int[] arr) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }

    public static int thirdLargest(int[] arr) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE, third = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > first) {
                third = second;
                second = first;
                first = arr[i];
            } else if (arr[i] > second) {
                third = second;
                second = arr[i];
            } else if (arr[i] > third) {
                third = arr[i];
            }
        }
        return third;
    }

    public static int maxProductTriplet(int[] arr) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE, third = Integer.MIN_VALUE;
        int firstMin = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        int product = 0;
        for (int i =0; i < arr.length; i++) {
            if (arr[i] > first) {
                third = second;
                second = first;
                first = arr[i];
            } else if (arr[i] > second) {
                third = second;
                second = arr[i];
            } else if (arr[i] > third) {
                third = arr[i];
            }

            if (arr[i] < firstMin) {
                secondMin = firstMin;
                firstMin = arr[i];
            } else if (arr[i] < secondMin) {
                secondMin = arr[i];
            }
        }
        product = Math.max(first * second * third, firstMin * secondMin * first);
        return product;
    }

    public static int[] moveAllZeros(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                int temp = arr[i];
                arr[i] = arr[count];
                arr[count] = temp;
                count++;
            }
        }
        return arr;
    }

    public static int[] reverseGroup(int[] arr, int k) {
        int n = arr.length;
        for (int i = 0; i < n; i += k) {
            int left = i;
            int right = Math.min(i + k - 1, n - 1);

            while (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        return arr;
    }

    public static int[] rotateLeft(int[] arr, int k) {
         int n = arr.length;
         k = k % n;
         reverse(arr, 0, n-1);
         reverse(arr, 0, n - k - 1);
         reverse(arr, n - k, n - 1);
         return arr;
    }

    //using  juggling algorithm
    public static int[] rotateJuggling(int[] arr, int d) {
         int n = arr.length;
         d %= n;

         int cycles = gcd(n, d);
         for (int i=0; i < cycles; i++) {
             int startEle = arr[i];
             int currentIdx = i, nextIdx;

             while (true)  {
                 nextIdx = (currentIdx + d) % n;

                 if (nextIdx == i)
                     break;

                 arr[currentIdx] = arr[nextIdx];
                 currentIdx = nextIdx;
             }
             arr[currentIdx] = startEle;
         }
         return arr;
    }

    public static int gcd(int b, int a) {
        if (b == 0)
            return a;
        return gcd(b % a, a);
    }

    public static boolean isPalindrome(String s) {
        int len = s.length();
        for (int i = 0; i < len /2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) return false;
        }
        return true;
    }
    public static String reverseString(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length();i++) {
            st.push(s.charAt(i));
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            res.append(st.pop());
        }
        return res.toString();
    }
}