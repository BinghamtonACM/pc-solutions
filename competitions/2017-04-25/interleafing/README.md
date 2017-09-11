##### [Back to competition page](../README.md)

# InterLEAFing

--------

## Solutions

#### - [Python](./solution.py/)

--------

# Problem Statement

After a nice, long programming contest with lots of sheet pizza, there's nothing the Binghamton University ACM Chapter likes more than a big, leafy green salad. Each of the contest organizers would like a salad consisting of one or more of the following twenty leafy vegetables, $$\left{ V \right}$$:

`arugula basil bokchoi broccoli cabbage cauliflower celery cilantro clover collards endive fennel kale lettuce parsnip radish spinach turnip wasabi watercress`

Your job is to decide which organizer receives which leafy vegetables for their salad. Each test case will have a different number of organizers ($$n$$), and each test case will have a different subset ($$v \subseteq V$$) of the leafy vegetables available. The vegetables will be distributed to each organizer by de-interleaving the list of available vegetables in their order of appearance and availability. 

In other words, the first name listed receives the first available vegetable, the second name listed receives the second available vegetable, ..., the _n_th person receives the _n_th available vegetable, the first name listed receives the _n+1_th vegetable, etc. Once the vegetables have been distributed, sort the outputs as discussed in **Output Format**

## Constraints

| $$\left| n \right|$$ | $$\left| v \right|$$ |
|----------------------|----------------------|
| 1 <= n <= 10         |  n < v < 20          | 

In other words, every organizer will receive at least one leafy vegetable. There is no guarantee that each organizer will receive the same number of leafy vegetables.

## Input Format
On the first line, the number of organizers, $$n$$.
On the second line, the names of each of the organizers, separated by a single space.
On the third line, the list of vegetables, with each item in the list followed by a `1` if it's available or a `0` if it is not available. The list of vegetables will always be 40 items long - twenty vegetables and twenty availabilities.

The 1st and 2nd lines may or may not be sorted.

## Output Format
On each line, the name of an organizer followed by the vegetables they receive. Print the names of the organizers by line in lexicographic order. Print the vegetables they each receive by lexicographic order separated by spaces following the organizer's name.

## Sample Input

First input:

```
4
Alice Bob Eve Trudy
arugula 1 basil 0 bokchoi 0 broccoli 1 cabbage 1 cauliflower 0 celery 1 cilantro 0 clover 0 collards 0 endive 0 fennel 0 kale 1 lettuce 1 parsnip 0 radish 1 spinach 1 turnip 0 wasabi 0 watercress 0
```

Second input:

```
3
Alice Mallory Bob
lettuce 1 parsnip 1 celery 1 cilantro 1 clover 1 radish 1 spinach 1 turnip 1 wasabi 0 watercress 0 arugula 0 basil 0 bokchoi 0 broccoli 0 cabbage 0 cauliflower 0 collards 0 endive 0 fennel 0 kale 0
```

## Sample Output

First output:

```
Alice arugula kale 
Bob broccoli lettuce
Eve cabbage radish
Trudy celery spinach
```

Second output:

```
Alice cilantro lettuce spinach
Bob celery radish
Mallory clover parsnip turnip
```
