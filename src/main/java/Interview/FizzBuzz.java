package Interview;

public class FizzBuzz {

	public static void main(String[] args) {
		int i = 1;
		
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
