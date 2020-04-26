package Interview;

import org.testng.annotations.Test;

public class FizzBuzzTNG {
	
	@Test
	public void fizzBuzz() {

		int i = 1;
		
		//Using the modulus operator, if the remainder is zero, i is therefore divisible
		//by 5, or 7, or 5 & 7
		while (i < 101) {
			if (((i % 7) == 0) && ((i % 5) == 0))
				System.out.println(i + ": " + "Fizz Buzz");
			else if ((i % 5) == 0)
				System.out.println(i + ": " + "Fizz");
			else if ((i % 7) == 0)
				System.out.println(i + ": " + "Buzz");
			else
				System.out.println(i + ": " + "Nussing to say");
			
			i++;
		}
	  
	}
}
