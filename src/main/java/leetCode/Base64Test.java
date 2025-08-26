package leetCode;

import java.util.Base64;

public class Base64Test {

    public static void main(String[] args) {

        // https://www.baeldung.com/java-base64-encode-and-decode
        String originalInput = "test input";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        System.out.println("encodedString: "+encodedString);

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);

        System.out.println("decodedString: "+decodedString);

    }


}
