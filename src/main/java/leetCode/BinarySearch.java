package leetCode;

//二元搜尋樹
public class BinarySearch {

	public static int binary_search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1; // array 長度 -1
		while (left <= right) {
			int mid = (left + right) / 2; // 用 int 的性質做無條件捨去
			if (nums[mid] > target) {
				right = mid - 1;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				return mid; // 剛好找到 target
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, 4, 7, 8, 10 };
		System.out.println(binary_search(nums, 1));

	}

}
