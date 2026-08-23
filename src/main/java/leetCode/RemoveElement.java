package leetCode;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        int val = 3;
        int k = removeElement(nums, val);
        System.out.println("New length: " + k);
        System.out.print("Modified array: ");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static int removeElement(int[] nums, int val) {
        // 我的解法：使用ArrayUtils.remove會導致陣列長度不變，無法正確回傳新長度 錯誤使用
//        int[] uniqueElements = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            System.out.println("Current element: " + nums[i] + ", Target value: " + val);
//            if (nums[i]==(val)) {
//                ArrayUtils.remove(nums, i);
//                System.out.println("Removing element: " + nums[i]);
//            }
//        }
//        System.out.println(
//                "Array after removal: " + java.util.Arrays.toString(nums)
//        );
//        return uniqueElements.length;
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        // slow 即為新長度 k
        return slow;
    }
}
