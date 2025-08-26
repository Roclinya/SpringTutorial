package leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Two_sum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		int[] nums = { 2, 7, 11, 15 };
//		int target = 9;
		int[] nums = { 3, 2, 4 };
		int target = 6;

		int[] result = Solution.twoSum(nums, target);
		System.out.println("結果" + Arrays.toString(result));

	}

	static class Solution {
		public static int[] twoSum(int[] nums, int target) {
			Map<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i < nums.length; i++) {
				int complement = target - nums[i];
				if (map.containsKey(complement)) {
					return new int[] { map.get(complement), i };
				}
				map.put(nums[i], i);
				System.out.println("目前遍歷過的內容 => " + map);
			}
			throw new IllegalArgumentException("No Answer");
		}
	}

}
