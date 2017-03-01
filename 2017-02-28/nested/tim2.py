''' 
    Author: Tim Hung

    This is the more standard approach to this problem.
    If you want to see the "cheaty" way using eval(), check my 
    other solution.

    Whenever you see problems that involve parsing list-like
    structures with open and closing delimiters,
    e.g. matching parentheses, parsing nested arrays, etc.
    your mind should immediately think "I SHOULD USE A STACK"

    We will define a recursive function to parse in our nested
    lists. Remember to develop for the base case first:
    In this scenario, the base case is a single element container:
    i.e. <'x'>, we know this because we READ THE QUESTION. :^)

    We know that we have to construct a sublist or finish constructing
    a sublist when we run into an open or close angle bracket respectively.
    So we handle those cases, and we handle the case of adding a character.
    We keep appending these sublists and characters onto the current
    list that we are building.
'''

# Parse input
raw = input()
# Read up on python list comprehensions if this is confusing
indices = [int(x) for x in input().split(' ')]

def parse_list(raw):
    # Honestly not really a stack because we never use
    # pop, but the sentiment still stands.
    stack = []

    # This is probably python heresy, but I want precise control
    # of my loop indices. Can probably use iter() and iter.next
    # to do this too, but I'm not too familiar with python.
    i = 1
    while i < len(raw):
        # We must account for char values i.e. 'x'
        if raw[i] == "'":
            # Begins with a single quote
            # Push on the char
            stack.append(raw[i + 1])
            # Push the index past the closing single quote
            i += 2
        elif raw[i] == '<':
            # Start of a sublist: We need a recursive call
            sublist, subLen = parse_list(raw[i:])
            # Push the index past the size of the sublist
            i += subLen
            # Append the packaged sublist onto our main stack
            stack.append(sublist)
        elif raw[i] == '>':
            # End of a list: Just return
            return stack, i
        i += 1
    # Bad practice, but since we know the inputs are always
    # valid, we don't have to ever return anything here.
    # The raw[i] == '>' case will always happen eventually.
    return "whatever yo because this ain't ever gon happen"

final_list, size = parse_list(raw)

# Loop through the indices and retrieve my element
for index in indices:
    # Set as found sublist
    final_list = final_list[index]

print(final_list)
