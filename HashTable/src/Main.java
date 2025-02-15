import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        hashTable.set("vishnu", 1000);
        hashTable.set("kumar", 100);
        hashTable.set("raju", 200);
        hashTable.set("muthu", 300);
        hashTable.set("unni", 400);
        hashTable.set("gokul", 500);
        hashTable.set("praveen", 650);
        System.out.println(hashTable.get("kumar"));
        hashTable.printTable();
        System.out.println(hashTable.keys());

        int[] array1 = {1,2,3};
        int[] array2 = {4,9,6};
        System.out.println(itemInCommon(array1, array2));

        int[] sample = {2,1,4,4,4};
        System.out.println(findDuplicates(sample));

        System.out.println(firstNonRepeatingChar("hello"));
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(input));

        int[] nums = {2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSums(nums, 9)));

        int[] tes = {1,2,3,4,5};

        System.out.println(Arrays.toString(subarraySum(tes, 6)));
        Integer[] dupli = {1, 2, 3, 4, 1, 2, 5, 6, 7, 3, 4, 8, 9, 5};
        List<Integer> dup = new ArrayList<>(List.of(dupli));
        System.out.println(removeDuplicates(dup));

        System.out.println(hasUniqueChars("hello"));

        int[] a = {1, 2, 3, 4, 5};
        int[] b = {2, 4, 6, 8, 10};
        for (int[] arr: findPairs(a,b,7)) {
            System.out.println(Arrays.toString(arr));
        }

        int[] bb = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutiveSequence(bb));

    }

    // Interview Question: 1 -> Check whether common item is there in two array list.

    public static boolean itemInCommon(int[] array1, int[] array2) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int i : array1) {
            map.put(i, true);
        }
        for (int j : array2) {
            if (map.containsKey(j)) return true;
        }
        return false;
    }

    // Interview Question 2-> Find Duplicates.

    public static List<Integer> findDuplicates(int[] array) {
        HashMap<Integer, Integer> lookup = new HashMap<>();
        for (int i : array) {
            lookup.put(i, lookup.getOrDefault(i, 0) + 1);
        }
        List<Integer> duplicates = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : lookup.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.add(entry.getKey());
            }
        }
        return duplicates;
    }

    // Interview Question: 3 -> First Non-Repeating Character.
    public static Character firstNonRepeatingChar(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] strArray = str.toCharArray();
        for(char c: strArray) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return null;
    }

    // Interview Question: 4 -> Group Anagrams.
    public static List<List<String>> groupAnagrams(String[] strings) {
        Map<String, List<String>> anagramsGroup = new HashMap<>();
        for (String str: strings) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String cano = new String(chars);
            if (anagramsGroup.containsKey(cano)) {
                List<String> anagramList = anagramsGroup.get(cano);
                anagramList.add(str);
            } else {
                List<String> newGroup = new ArrayList<>(List.of(str));
                anagramsGroup.put(cano, newGroup);
            }
        }
        List<List<String>> result = new ArrayList<>(anagramsGroup.values());
        return result;
    }

    // Interview Question: 5 -> Two sums
    public static int[] twoSums(int[] nums, int target) {
        Map<Integer, Integer> lookup = new HashMap<>();
        for (int i = 0 ; i < nums.length; i++ ) {
            int compliment = target - nums[i];
            if (lookup.containsKey(compliment)) {
                int compl = lookup.get(compliment);
                return new int[]{compl,i};
            } else {
                lookup.put(nums[i], i);
            }
        }
        return new int[0];
    }

    // Interview Question: 6 -> Subarray Sum
    public static int[] subarraySum(int[] nums, int target) {
        Map<Integer, Integer> lookup = new HashMap<>();
        lookup.put(0, -1);
        int sum =0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (lookup.containsKey(sum - target)) {
                return new int[]{lookup.get(sum - target) + 1, i};
            }
            lookup.put(sum, i);
        }
        return new int[0];
    }

    // Set Questions
    // Interview Question 1: Remove Duplicates from a list.

    public static List<Integer> removeDuplicates(List<Integer> myList) {
        Set<Integer> set = new HashSet<>(myList);
        return new ArrayList<Integer>(set);
    }

    //Interview Question 2: Has Unique Chars.
    //Alternate and easy way, Use contains to check already in Set;
    public static boolean hasUniqueChars(String string) {
        char[] charArray = string.toCharArray();
        Set<Character> set = new HashSet<>();
        int ogLength = charArray.length;
        for (char c : charArray) {
            set.add(c);
        }
        int afterLength = set.size();
        if (ogLength == afterLength) {
            return true;
        }
        return false;
    }

    // Interview Question: 3 -> Find Pairs
    public static List<int[]> findPairs(int[] arr1, int[] arr2, int target) {
        Set<Integer> set1 = new HashSet<>();
        List<int[]> result = new ArrayList<>();
        for (int c: arr1) {
            set1.add(c);
        }
        for (int el: arr2) {
            int complement = target - el;
            if (set1.contains(complement)) {
                int[] val = new int[]{complement, el};
                result.add(val);
            }
        }
        return result;
    }

    public static int longestConsecutiveSequence(int[] nums) {
        Set<Integer> set1 = new HashSet<>();
        int longest = 0;
        for (int c: nums) {
            set1.add(c);
        }
        for (int c: nums) {
            if (!set1.contains(c - 1)) {
                int currentStreak = 1;
                int currentNum = c;

                while(set1.contains(currentNum + 1)) {
                    currentStreak++;
                    currentNum++;
                }
                longest = Math.max(longest, currentStreak);
            }
        }
        return longest;
    }
}