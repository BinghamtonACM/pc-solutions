import sys
sys.setrecursionlimit(1500)
'''
- Above is a python hack to set the recursion depth limit of your program
    - you can try this if your recursion is correct, but blows up the stack
    - max for a python program is 1000 by default
    - but if you go over the system limit, the program shall crash

- Another call - resource.setrlimit(resource, limits) may allow you to change the system limit,
    - but it involves a system call, so there may be an error
    - you can look into it more if interested

- sys.getrecursionlimit() - returns the current recursion depth for your python program

'''

#solve(num) return a tuple (p, q)
#where p/q is the value in the num-th node of tree
def solve(num):
    #base case
    if num == 1:
        return 1, 1
    #get (p, q) of parent node - parent(n) = n/2 (integer divide)
    p, q = solve(num/2)

    #caluclate (p, q) for num-th node based on formula given
    if num & 1:
        return p+q, q
    else:
        return p, p+q
    
cases = int(raw_input())
for c in range(cases):
    num, data = raw_input().split()
    a, b = solve(int(data))
    print "%s %d/%d"% (num, a, b)