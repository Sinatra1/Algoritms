package vlad.shumilov;

public class Main {

    public static void main(String[] args) {
        System.out.print("Stack:\n");

	    Stack<String> stack = new Stack<>(2);

	    stack.push("1");
	    stack.push("2");

        System.out.print(stack.pop() + "\n");
        System.out.print(stack.pop() + "\n");

        System.out.print("Queue:\n");

        Queue<String> queue = new Queue<>(2);

        queue.enqueue("1");
        queue.enqueue("2");
        System.out.print(queue.dequeue() + "\n");
        queue.enqueue("3");

        System.out.print(queue.dequeue() + "\n");
        System.out.print(queue.dequeue() + "\n");

        System.out.print("List:\n");

        List<String> list = new List<>();
        list.insert("1");
        list.insert("2");
        list.insert("3");

        list.remove("2");

        list.out();
    }
}
