import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

class Driver {
  static int rand(int low, int high) {
    Random gen = new Random();
    return gen.nextInt(high - low) + low;
  }

  static void testPoppingHeap(Heap heap) {
    System.out.println("\nWe will now pop elements from the heap until it is empty");
    System.out.println("We should expect to see that it is still a valid heap " +
        "and that we pop the minimum element each time.");
    while (heap.size() > 0) {
      System.out.println("\tPopped: " + heap.pop() + "\t" + heap);
    }
  }

  static void testIntegerHeap(int iterations) {
    System.out.println("\n=================== Testing an integer heap\n");
    MinHeap<Integer> heap = new MinHeap<>();
    System.out.printf("Create empty integer heap: %s%n%n", heap);

    int min = 0, max = 100;
    System.out.printf("Pushing on %d random integers from %d to %d%n",
        iterations, min, max);
    System.out.println("We should expect to see that it is a valid heap each iteration.");
    for (int i = 0; i < iterations; i++) {
      heap.push(rand(min, max));
      System.out.println("\t" + heap);
    }

    testPoppingHeap(heap);
    System.out.println("\n===================\n");
  }

  static void testStringHeap() {
    System.out.println("\n=================== Testing an string heap\n");
    MinHeap<String> heap = new MinHeap<>();
    System.out.printf("Create empty string heap: %s%n%n", heap);

    System.out.println("Pushing on some strings");
    System.out.println("We should expect to see that it is a valid heap each iteration.");
    ArrayList<String> strings = new ArrayList<>(Arrays.asList(
      "alice", "bob", "carol", "david", "eve", "faythe", "grace"));
    Collections.shuffle(strings);
    for (String string : strings) {
      heap.push(string);
      System.out.println("\t" + heap);
    }

    testPoppingHeap(heap);
    System.out.println("\n===================\n");
  }

  public static void main(String[] args) {
    testIntegerHeap(7);
    testStringHeap();
  }
}
