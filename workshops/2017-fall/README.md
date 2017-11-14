# Problems and Solutions from ACM Workshops in Fall 2017

---

## Alphabetical

September 11th, 2017

How can we check if a word is in alphabetical order? We examine two implementations:

 - We can sort the word and see if it's equal to the original word
 - We can traverse each subsequent pairs of characters and verify that the former alphabetically comes before the latter.

Which algorithm is "better"? How can we analyze these two algorithms? What are their time complexities?

#### - [Java Solution](./alphabetical/Alphabetical.java)
#### - [Python3  Solution](./alphabetical/alphabetical.py)


## Anagrams

September 11th, 2017

How can we check if two words anagrams of each other? We examine two implementations:

 - We can sort both words and compare them. If they are anagrams they will be equal!
 - We can check if the letter frequencies of the words are the same.

Which algorithm is "better"? How can we analyze these two algorithms? What are their time complexities?

#### - [Java Solution](./anagram/Anagram.java)


## Priority Queues

November 14th, 2017

What are Priority Queues? How can we implement them?

#### - [Binary Min Heap implementation in java](./heap)
