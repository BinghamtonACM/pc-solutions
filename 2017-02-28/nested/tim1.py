''' 
    Author: Tim Hung

    My solution here is very cheaty and probably a bit unfair to those
    not familiar with python or other languages with eval, but a tool
    is a tool! That's why it's important to know your standard libraries
    and learn a handful of different languages.

    First I convert the input string to valid parseable python list
    notation. Then I evaluate it as python code as a python list.
    Finally I can just retrieve elements from my list normally.
'''

# For using regex search and replace
import re

# Parse input
raw = input()
# Read up on python list comprehensions if this is confusing
indeces = [int(x) for x in input().split(' ')]

# This is a good demonstration of why it's always important to read the input format.
# "A char can be [0-9A-Za-z!@#$%^&*()>"
# So we have to worry about chars being '<' and '>': We can't just do a blind replace
# First store the char angle brackets as a safe character: Here I'm using braces.
raw = re.sub("'<'", "'{'", raw)
raw = re.sub("'>'", "'}'", raw)

# Reformat the list to be in python list format, 
# i.e. replacing angle brackets with squares brackets
raw = re.sub("<", "[", raw)
raw = re.sub(">", "]", raw)

# Restore the braces to angle brackets
raw = re.sub("{", "<", raw)
raw = re.sub("}", ">", raw)

# The magic happens here! Thanks Guido
# eval() parses a string as if it were actual python code
list = eval(raw)

# Loop through the indeces and retrieve my element
for index in indeces:
    # Set as found sublist
    list = list[index]

print(list)
