(Matthew's note: if you're interested in the real-world theory behind this question, check out [this declassified report from the Cold War](http://www.dtic.mil/dtic/tr/fulltext/u2/a017852.pdf)

The year is 2018. You have survived the nuclear apocalypse caused by the last U.S. president in part because the Watson School for Engineering was built by Vault-Tec (_"Revolutionizing safety for an uncertain future!"_™). Not only was this bleak concrete structure made to demoralize Harpur College undergraduates but also to survive a direct hit from a nuclear warhead. You were studying for your midterms in G7 when the bombs fell.

While wandering the ruin that once was the C4 Dining Hall looking for Sugar Bombs, Fancy Lad Snack Cakes, Blamco Brand Mac and Cheese or even a disgusting tin of expired Cram, you pick up a distress signal broadcast on your Pip-Boy purportedly from WHRW asking for immediate help! You recall that the Vault Dweller's Survival Guide contains a chapter on decoding radio broadcasts and includes rules to ensure that the radio broadcast is authentic (not sent by ham-fisted Super Mutants). The VDSG tells you that each transmitted token must be __Plain Letter Spell Out__ (PLSO) according to the following information:

---

Tokens which are not PLSO are garbles (represented by four question marks): 

`????`

The following are PLSO tokens:

`ALFA BRAVO CHARLIE DELTA ECHO FOXTROT GOLF HOTEL INDIA JULIETT KILO LIMA MIKE NOVEMBER OSCAR PAPA QUEBEC ROMEO SIERRA TANGO UNIFORM VICTOR WHISKEY XRAY YANKEE ZULU`

Additionally, a token with no more than one error from a PLSO token is interpreted as PLSO and not a garble:

| Error         | Example | Interpret As | Discussion |
|---------------|---------|--------------|------------|
| Insertion     | ALPHHA  | ALPHA        | An extra 'H' was inserted at index 4. | 
|               | ALP HA  | ALPHA        | An extra space was inserted between index 2 and 3 |
|               | AALPHXA | ????         | Two extra characters were inserted. This is a garble. |
| Deletion      | BAVO    | BRAVO        | The 'R' was deleted at index 1. |
|               | BRAVOBRAVO | BRAVO BRAVO | The space was deleted between two otherwise correct letters. |
|               | BVO     | ????         | Two characters were deleted. This is a garble. |
| Transposition | CHRALIE   | CHARLIE    | Two characters at index 2 and 3 were transposed. |
|               | CHRLAIE   | ????       | Two characters at index 2 and 3, then 3 and 4 were transposed. This is a garble. |

---

You must decode each radio broadcast to determine if it's safe to respond. Take a Mentat and good luck!

### Constraints

| Length        |
|---------------|
| 1 - 32 tokens |

### Input Format

A message consisting of up to 32 tokens all on one line. All characters in all tokens will be from the capitalized ASCII letters set. 


### Output Format

The Plain Language Spell Out translation of the input message.

### Sample Input

```
DELTAECHO WHISKEY HOTXEL ROMOE WHISKY BRV UNFRM
```

### Sample Output

```
DELTA ECHO WHISKEY HOTEL ROMEO WHISKEY ???? ????
```

### Discussion

Hint: A word is PLSO after an insertion or deletion if and only if it has a [Levenshtein distance](https://en.wikipedia.org/wiki/Levenshtein_distance) of 0 or 1.
