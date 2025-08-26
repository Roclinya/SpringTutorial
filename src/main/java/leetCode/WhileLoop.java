package leetCode;

public class WhileLoop {

	public static void main(String[] args) {
		  int count = 0;

		  int maxDifference = findMaxDifference(count);
		  
		

}
    static int findMaxDifference(int count) {
        
        while (true) {
            // Some condition to check for exit
            if (count == 5) {
                break; // Exit the while loop when count reaches 5
            }

            System.out.println("Count: " + count);
            count++;
        }
        System.out.println("Outside the while loop");
        return count;
    }
   
}
