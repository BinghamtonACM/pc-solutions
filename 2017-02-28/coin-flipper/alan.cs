using System;
using System.Collections;
using System.Collections.Specialized;
using System.Linq;
using System.IO;

class Solution {
    static void Main(String[] args) {
        // Read in number of cases to complete
        int numCases = Int32.Parse(System.Console.ReadLine());

        // Set up our map with initial values
        OrderedDictionary map = new OrderedDictionary();
        String[] possibilities = { "TTT", "TTH", "THT", "THH", "HTT", "HTH", "HHT", "HHH" }; 
        foreach (String seq in possibilities) map.Add(seq, 0);

        // Complete each case
        for (int i = 0; i < numCases; i++) {
            // Read in label and row of coin flips
            String label = System.Console.ReadLine();
            String row = System.Console.ReadLine();

            // Extract all 3-character sequence combinations and track frequency
            for (int j = 0; j <= 37; j++) {
                String seq = string.Concat(string.Concat(row[j], row[j + 1]), row[j + 2]);
                map[seq] = (int)map[seq] + 1;
            }

            // Print case result and reset counts
            System.Console.Write(label);
            for (int j = 0; j < 8; j++) {
                System.Console.Write(" " + map[j]);
                map[j] = 0;
            }
            System.Console.Write("\n");
        }
    }
}
