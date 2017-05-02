# I'll Give You the Next Word

One technique in analyzing texts for authorship and uniqueness is to observe which words follow a given word. Certain words appear near each other frequently. These word pairs can then be assembled as a graph of edges between the pairs, then the graph can be compared to other graphs known to be from a particular author.

In this problem, you will perform the first two steps in this analysis: reading a text, then finding all words that follow a given word within that text.

It is important that punctuation and whether the word is capitalized or not does not affect our analysis. Therefore you will eliminate all leading or following punctuation and casefold all words (e.g. `'To-morrow is Saint Crispian:'` becomes `to-morrow is saint crispian`). Additionally, the text may appear on one or more lines, with some lines being quite long and some lines having no text at all.

## Constraints

Let the number of lines be _n_, and the number of words on that line be _w_.

|    n   |    w    |
|--------|---------|
| 5 - 50 | 0 - 200 |

All text will be from the following subset of the ASCII characters consisting of lowercase, uppercase, digits and punctuation.

```
abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!"#$%&'()*+,-./:;<=>?@[\]^_`{|}
```

## Input Format

On line 0, the number of lines, $$n$$.
On line 1 to $$n$$, text to be processed, consisting of up to $$w$$ words.

## Output Format

On line 0, the number of unique words in the text.
On line 1, the first word (sorted lexicographically) from the text, then all words (sorted lexicographically) that followed this word in the text.
On line n, the nth word (sorted lexicographically) from the text, then all words (sorted lexicographically) that followed this word in the text.

## Sample Input

```
5
I DO NOT LIKE THEM IN A HOUSE.
I DO NOT LIKE THEM WITH A MOUSE.
I DO NOT LIKE THEM HERE OR THERE.
I DO NOT LIKE THEM ANYWHERE.
I DO NOT LIKE GREEN EGGS AND HAM.
```

## Sample Output

```
17
a house mouse
and ham
anywhere i
do not
eggs and
green eggs
here or
house i
i do
in a
like green them
mouse i
not like
or there
them anywhere here in with
there i
with a
```
