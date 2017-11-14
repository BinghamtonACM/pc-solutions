import java.util.Scanner;

class Node {
  String val;
  Node prev;
  Node next;

  public Node(String s) {
    this.val = s;
    prev = null;
    next = null;
  }
}

public class Stack {

  Node head;
  public Stack() {
    head = null;
    tail = null;
  }

  public void push(String val) {
    if(!head) {
      head = new Node(val);
      tail = head;
    } else {
      tail.next = 
    }
  }

  public static void main(String[] args) {
    // Prompt and handle input
    Scanner in = new Scanner(System.in);
    String input = in.nextLine();

    System.out.println("Your input: " + input);
  }
}
