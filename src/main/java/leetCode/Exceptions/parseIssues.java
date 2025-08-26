package leetCode.Exceptions;

public class parseIssues {
    public static void main(String[] args) {
        parseExecute("invalid");
    }

    private static void parseExecute(String str) {

        double d = 0;
        try {
            d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            d = 0;
//            throw new RuntimeException(e);
        } finally {
            System.out.println(d);
        }
    }
}
