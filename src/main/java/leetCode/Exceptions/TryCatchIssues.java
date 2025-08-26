package leetCode.Exceptions;

public class TryCatchIssues {
    public static void main(String[] args) {
        System.out.println(1/0);
        try {
            System.out.println("execute");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("finally");
        }

    }
}
