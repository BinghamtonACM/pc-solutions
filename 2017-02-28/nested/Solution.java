import java.io.*;
import java.util.*;

/**
 * @author Tim Hung
 *  
 * We will define a recursive function to parse in our nested
 * lists. Remember to develop for the base case first:
 * In this scenario, the base case is a single element container:
 * i.e. <'x'>, we know this because we READ THE QUESTION. :^)
 *  
 * We know that we have to construct a sublist or finish constructing
 * a sublist when we run into an open or close angle bracket respectively.
 * So we handle those cases, and we handle the case of adding a character.
 * We keep appending these sublists and characters onto the current
 * list that we are building.
 *
 * This solution is an example of where you might want to create
 * your own data structure to solve a problem.
 */
public class Solution {

    private static class NestedList {
        Character value;
        ArrayList<NestedList> items;
        int strLength;

        // Default Constructor
        public NestedList() {
            value = null;
            items = new ArrayList<NestedList>();
            strLength = 0;
        }
        // Constructor for a single character "NestedList"
        public NestedList(Character c) {
            this.value = c;
            items = null;
            strLength = 3;
        }
        // Constructor given a string recipe
        public NestedList(String raw) {
            NestedList parsed = parseText(raw);
            this.value = parsed.value;
            this.items = parsed.items;
            this.strLength = parsed.strLength;
        }

        /**
         * Appends a NestedList to this NestedList
         * @param sublist The NestedList to be pushed
         */
        public void append(NestedList sublist) {
            this.items.add(sublist);
        }

        /**
         * Returns a NestedList constructed from a given string
         * @param raw The string recipe for the NestedList
         * @return The constructed NestedList   
         */
        public static NestedList parseText(String raw) {
            NestedList stack = new NestedList();

            for(int i = 1; i < raw.length(); i++) {
                // We must account for char values i.e. 'x'
                if(raw.charAt(i) == '\'') {
                    // Begins with a single quote
                    // Push on the char
                    stack.append(
                            new NestedList(
                                new Character(raw.charAt(i + 1))
                                )
                            );
                    // Push the index past the closing single quote
                    i += 2;
                } else if (raw.charAt(i) == '<') {
                    // Start of a sublist: We need a recursive call
                    NestedList sublist = parseText(raw.substring(i));
                    // Push the index past the size of the sublist
                    i += sublist.strLength;
                    // Append the packaged sublist onto our main stack
                    stack.append(sublist);
                } else if(raw.charAt(i) == '>'){
                    // End of a list: Just return
                    stack.strLength = i;
                    return stack;
                }
            }
            return new NestedList();
        }

        // Only needed for testing purposes
        public String toString() {
            if(this.items == null) return "'" + this.value + "'";
            String retVal = "<";
            for(NestedList elem : this.items) {
                retVal += elem.toString() + ",";
            }
            return retVal.substring(0, retVal.length() - 1) + ">";
        }
    }
    public static void main(String[] args) {
        // Parse input
        Scanner in = new Scanner(System.in);
        String raw = in.nextLine();
        String[] rawIndices = in.nextLine().split(" ");
        int[] indices = new int[rawIndices.length]; 
        for(int i = 0; i < rawIndices.length; i++) {
            indices[i] = Integer.parseInt(rawIndices[i]);
        }

        // Construct a NestedList by passing the input string
        NestedList test = new NestedList(raw);

        // Loop through the specified indices to traverse to the target element
        for(int i : indices) {
            test = test.items.get(i);
        }
        System.out.println(test.value);
    }
}
