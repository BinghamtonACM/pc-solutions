##### [Back to competition page](../README.md)

# Pythagorean Crop Formation
#### Problem written by Matthew Cole

--------
## Solutions

#### - [Python](./solution.py/)

--------

One day Pythagoras decided to play a trick upon Aristotle's student, Dicaearchus. Overnight, Pythagoras would make a pattern upon Dicaearchus' grain field. In the morning, Dicaearchus would awaken and discover the pattern, thinking it was the trickster Hermes riding in his chariot. Making crop formations in circles was no special challenge: no, Pythagoras would need something magnificient - the right triangle!

Using _n_-many chains, each with many links of a given unit size, Pythagoras would mark out several triangles using stakes. Then, he'd trample out the grain underneath each of the chains. The only question is this: given a single cyclic chain of a certain length _l_, what integral-length sides _a_, _b_, and _c_ should be constructed in order to form a right triangle?

### Constraints

| _n_ | _l_ | sides _a_, _b_, _c_ |
|-----|-----|---------------------|
| 1 < n < 10 | < 2 x 10^7 | a < b < c with a, b, c in integers > 0 |

### Input Format

On line 0, the number of chains (_n_). On lines 1 to _n_, the length of each chain (_l_).

### Output Format

On line 0 to _n-1_, the length of each side of the right triangle in increasing order of length separated by a space.


### Sample Input

```
2
12
60
```

### Sample Output

```
3 4 5
10 24 26
```

### Explanation

In the first test case, a (3,4,5) right triangle is constructed. The sum of the sides is the perimeter of the chain, or 12. In the second test case, a (10, 24, 26) right triangle is constructed. The sum of the sides of the perimeter of the chain, or 60. 

Not all right triangles constructed will have sides which form a _primitive right triangle_ (that is, there may be a right triangle which is geometrically _similar_ to this triangle), as in the second case. In this case, the primitive triple is (5,12,13), and the constructed triangle has a similar ratio of 2.

As a note, because the maximum perimeter of any particular triangle is quite large, you may wish to find an efficient method to generate only right triangles rather than a naive iterative search of all triangles having a given perimeter!
