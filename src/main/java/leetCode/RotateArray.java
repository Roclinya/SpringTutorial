package leetCode;

public class RotateArray {

	public static void main(String[] args) {

		int k = 3;
		int[] nums = { 1, 2, 3, 4, 5, 6, 7 };

		for (int i = 0; i < nums.length; i++) {
			if (i + k >= nums.length) {
				nums[i + k - nums.length] = nums[i];
			} else {
				nums[i + k] = nums[i];
			}

		}

		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");

		}

	}

}
