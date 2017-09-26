confusing = {"B":"8", "G":"C", "I":"1","O":"0","Q":"0","S":"5","U":"V","Y":"V","Z":"2" }
alphabet = "0123456789ACDEFHJKLMNPRTVWX"
check_digit_constant = [2,4,5,7,8,10,11,13]

cases = int(input())
for c in range(cases):
    num, data = input().split()
    
    #this for loop computes check digit
    check_digit = 0
    for i in range(len(data)-1):
        #get the ith char in input string
        #if it is a confusing letter, get the right value from dictionary
        ch = data[i] if data[i] not in confusing else confusing[data[i]]
        
        # check digit += value_of_char * constant
        check_digit += (alphabet.find(ch) * check_digit_constant[i])
        
    #mod 27
    check_digit %= 27
    
    #compare computed check digit to the last digit
    
    #if not equal, print invalid
    if alphabet[check_digit] != data[-1]:
        print(num, "Invalid")
    
    else:
        # do base 27 conversion
        res = 0
        base = 7
        for ch in data:
            res += (alphabet.find(ch) * 27**base)
            base-=1
        print(num, int(res))
        
    