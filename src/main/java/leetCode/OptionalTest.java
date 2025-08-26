package leetCode;

import java.util.Optional;

public class OptionalTest {

	public static void main(String[] args) {
		
		Optional<String> optOrNull = Optional.ofNullable(null);
		System.out.println(optOrNull); 
		
		Optional<String> empty = Optional.empty();
		System.out.println(empty); 
		
		Optional<String> opt = Optional.of(null);
		System.out.println(opt); 

	}

}
