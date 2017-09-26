'''
Conversion Example: 1234

Octal       1234 = 1*8^3 + 2*8^2 + 3*8^1 + 4*8^0
Decimal     1234 = 1*10^3 + 2*10^2 + 3*10^1 + 4*10^0
Hex         1234 = 1*16^3 + 2*16^2 + 3*16^1 + 4*16^0     

'''

def asOctal(data):
    if '8' in data or '9' in data:
        return 0
    return sum([int(data[i]) * 8**(len(data)-1-i) for i in range(len(data))])

def asDec(data):
    return int(data)

def asHex(data):
    return sum([int(data[i]) * 16**(len(data)-1-i) for i in range(len(data))])


cases = int(raw_input())
for c in range(cases):
    num, data = raw_input().split()
    print num, asOctal(data), asDec(data), asHex(data)