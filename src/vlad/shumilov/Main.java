package vlad.shumilov;

public class Main {

    public static void main(String[] args) {
	    Stack<String> stack = new Stack<>(2);

	    stack.push("1");
	    stack.push("2");

        System.out.print(stack.pop());
        System.out.print(stack.pop());
    }
}
