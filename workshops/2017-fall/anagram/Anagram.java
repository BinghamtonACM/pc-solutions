import java.util.Scanner;
import java.util.Arrays;

// Implementations of algorithms to check if two words are anagrams
public class Anagram {

  // Alphabetically sorts a word
  public static String sortWord(String word) {
    char[] chars = word.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }


  // Suboptimal O(n*log(n)) solution
  public static boolean naiveAreAnagrams(String word1, String word2) {
    // Check if sorted words are equal to eachother
    return sortWord(word1).equals(sortWord(word2));
  }


  // A better O(n) solution
  public static boolean areAnagrams(String word1, String word2) {
    // Allocate an array for each ASCII value
    int[] histogram = new int[256];

    // Increment the frequency of each char when you find one in word1
    for (char c : word1.toCharArray()) histogram[c]++;
    // Decrement the frequency of each char when you find one in word2
    for (char c : word2.toCharArray()) histogram[c]--;

    // If the words are anagrams, they should have the same frequency
    // => The frequencies should all be zero since they canceled each other out.
    for (int frequency : histogram) {
        if (frequency != 0) return false;
    }
    return true;
  }


  public static void main(String[] args) {
    // Prompt and handle input
    Scanner in = new Scanner(System.in);
    System.out.print("\nPlease enter two words\n>> ");
    String word1 = in.nextLine();
    System.out.print(">> ");
    String word2 = in.nextLine();

    // Test naive algorithm
    System.out.println(
      "\n\tAccording to the naive sorting implementation, are the words anagrams?\n"
      + "\t" + (naiveAreAnagrams(word1, word2) ? "Yes!" : "No!")
    );

    // Test optimal algorithm
    System.out.println(
      "\n\tAccording to the optimal implementation, are the words anagrams?\n"
      + "\t" + (areAnagrams(word1, word2) ? "Yes!" : "No!")
    );

    System.out.println();
  }
}
