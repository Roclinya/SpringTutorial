package leetCode;

public class BestTimetoBuyAndSellStock_121 {

	public static void main(String[] args) {

		int[] array = { 1, 9, 3, 7, 5, 6 };
		int maxDifference = findMaxDifference(array);
		if(maxDifference<=0) {
			maxDifference=0;
		}

		System.out.println("最大差異值為：" + maxDifference);

	}

	static int findMaxDifference(int[] array) {
		int minElement = array[0];
		int maxDifference = Integer.MIN_VALUE;

		for (int i = 1; i < array.length; i++) {
			int currentDifference = array[i] - minElement;
			//目前差異與最大差異比,比較大的就是最新的最大差異值
			maxDifference = Math.max(maxDifference, currentDifference);
			minElement = Math.min(minElement, array[i]);
		}

		return maxDifference;
	}

}
