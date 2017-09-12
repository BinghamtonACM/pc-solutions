import java.util.Scanner;   // Used for parsing input
import java.util.Arrays;    // Used for sorting arrays

// Implementations of algorithms to see if words are in alphabetical order.
public class Alphabetical {

  // Suboptimal O(n*log(n)) solution
  public static boolean naiveIsAlphabetical(String word) {
    // Sort the word
    char[] chars = word.toCharArray();
    Arrays.sort(chars);
    String sorted = new String(chars);

    // If the sorted word is equal to the original word,
    // then the word was in alphabetical order
    return word.equals(sorted);
  }

  // Optimal O(n) solution
  public static boolean isAlphabetical(String word) {
    // We will iterate from the first char to the
    // second to last char in the word.
    // This way we don't go past the end of the word.

    char prev, current = ' ';
    for (int i = 0; i < word.length() - 1; i++) {
      prev = word.charAt(i);
      current = word.charAt(i + 1);

      if (prev > current) return false;
    }
    return true;
  }

  public static void main(String[] args) {

    // Prompt the user and parse in their word
    Scanner in = new Scanner(System.in);
    System.out.print("\nPlease enter a word\n>> ");
    String word = in.nextLine();

    // Test naive algorithm
    System.out.println(
      "\n\tAccording to the naive sorting implementation, is the word in alphabetical order?\n"
      + "\t" + (naiveIsAlphabetical(word) ? "Yes!" : "No!")
    );

    // Test optimal algorithm
    System.out.println(
      "\n\tAccording to the optimal implementation, is the word in alphabetical order?\n"
      + "\t" + (isAlphabetical(word) ? "Yes!" : "No!")
    );

    System.out.println();
  }
}
