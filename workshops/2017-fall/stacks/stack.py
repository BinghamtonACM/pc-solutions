def is_balanced(string):
    """
    string will consist of only open and close parentheses
    """
    if len(string) % 2 != 0:
        return False

    depth = 0
    for char in string:
        if char == '(':
            depth += 1
        elif char == ')':
            depth -= 1
        if depth < 0:
            return False

    return depth == 0


def is_balanced_stack(string):
    stack = []
    for char in string:
        if char == '(':
            stack.append(char)
        elif char == ')':
            if stack:
                stack.pop()
            else:
                return False
    return not stack


print(is_balanced_stack('()'))
print(is_balanced_stack('(())'))
print(is_balanced_stack('()))'))
print(is_balanced_stack('((()'))
