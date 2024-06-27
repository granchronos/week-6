package pe.edu.idat.week_6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Stack;

@SpringBootApplication
public class Week6Application {

	public static void main(String[] args) {
		SpringApplication.run(Week6Application.class, args);
	}

	static boolean isValid(String s) {
		Stack<Character> strings = new Stack<>();
		for (char c : s.toCharArray()) {
			if (c == '(')
				strings.push(')');
			else if (c == '[')
				strings.push(']');
			else if (c == '{')
				strings.push('}');
			else if (strings.isEmpty() || strings.pop() != c)
				return false;
		}
		return strings.isEmpty();
	}

}
