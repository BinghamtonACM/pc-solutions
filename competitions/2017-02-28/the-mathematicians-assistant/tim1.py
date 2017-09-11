'''
    Author: Tim Hung

        This solution uses some python eval() magic
    I will upload a solution that does not use eval for reference

    My main approach to the problem was in two parts:
        1. Convert the input into a valid expression string
        2. Evaluate that expression (with or without eval)

        With dictionaries, you can go through the raw string in O(n)
    And not have to worry about any edge cases EXCEPT for numbers between
    20 and 99 that aren't multiples of ten. i.e. twenty two, thirty three etc.
    There are a few approaches to this. I chose to keep a separate dictionary
    of numbers in the one's place. The aforementioned edge case only happens
    when we have a multiple of ten, followed by a single digit number.

        Whenever we encounter a single digit, we check to see if the last thing
    added ends in a '0', i.e. a multiple of 10. If so, we can just remove that 0
    and replace it with the one's digit: forty five -> 40 five -> 4five -> 45.
    This allows us to easily parse through the input.
'''

# All things to look up except for single digits (0 doesn't matter)
symbols = {
    'plus': '+', 'minus': '-', 'times': '*', 'divided': '/',
    'zero': '0', 'ten': '10', 'eleven': '11', 'twelve': '12', 'thirteen': '13', 'fourteen': '14', 
    'fifteen': '15', 'sixteen': '16', 'seventeen': '17', 'eighteen': '18', 'nineteen': '19', 
    'twenty': '20', 'thirty': '30', 'forty': '40', 'fifty': '50', 
    'sixty': '60', 'seventy': '70', 'eighty': '80', 'ninety': '90'
}
# Single digit numbers
ones = {
    'one': '1', 'two': '2', 'three': '3', 'four': '4', 
    'five': '5', 'six': '6', 'seven': '7', 'eight': '8', 'nine': '9'
}

math = ''
for word in input().split():
    if word in symbols:
        # Append translated symbol
        math += symbols[word]
    elif word in ones:
        # Verify non empty expression, and check if last char is 0
        if len(math) > 0 and math[-1] == '0':
            # Remove last element
            math = math[0:-1]
        # Append translated symbol
        math += ones[word]

# Evaluate as code, round it, and print it
print(round(eval(math)))
