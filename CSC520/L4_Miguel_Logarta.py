'''
Affirm an individual or group honor code by replacing <name> or <names>.
`
For individual submissions: I, <name>, have neither given or recived inappropriate help with this homework assignment.

For group submissions: We, <names>, have neither given or recived inappropriate help with this homework assignment. All group members participated in this work, and all concur with this submission.

I, Miguel Antonio Logarta, have neither given or recieved inappropriate help with this homework assignment 

'''


def L4(inString: str) -> str:
    # For lab #4, add code that  computes the counts of non-negative
    # ints equal to 0, 1 and 2 mod 3, and returns 'yes' if the 3
    # counts are the same; otherwise, L4 returns 'no'.
    
    # inString is a string of space delimited integers
    # Parse the string and read it as numbers
    numbers = [int(n) for n in inString.split()]
    
    # We are going to count the values of the remainders after dividing by 3
    # After doing (number)%3, we have three possible results: 0, 1, and 2
    zeroesCount = 0
    onesCount = 0
    twosCount = 0

    for num in numbers:
        if num >= 0:   # Ignore negative numbers
            remainder = num % 3
            if remainder == 0:
                zeroesCount += 1
            elif remainder == 1:
                onesCount += 1
            elif remainder == 2:
                twosCount += 1
   
    # If the counts are all the same, return 'yes' else  'no'
    if zeroesCount == onesCount == twosCount :
        return 'yes'
    else:
        return 'no'


if __name__ == '__main__':

    def test_case(F,string,expected,num,comment=''):
        err = '** '
        result = F(string)
        func_name = str(F).split()[1]
        func_call = f'''{func_name}("{string}")'''
        if result == expected: err = ''
        e = expected
        print (f'{err}test #{num} {func_call}: expected "{e}", received "{result}"')
        print (f'test #{num} Explanation: {comment}\n')
        return num + 1

    num = 1

    s = '1 5 9 -2'
    exp = '#(%3 = 0) = #(%3 = 1) = #(%3 = 2) = 1, -2 ignored' 
    num = test_case(L4,s,'yes',num,exp)
    
    s = ' -2'
    exp = '#(%3 = 0) = #(%3 = 1) = #(%3 = 2) = 0, -2 ignored' 
    num = test_case(L4,s,'yes',num,exp)    

    s = '  -5 0 '
    exp = '#(%3 = 0) = 1, but #(%3 = 1) = #(%3 = 2) = 1, -5 ignored' 
    num = test_case(L4,s,'no',num,exp)    

    s = '1 5 9 11 -2'
    exp = '#(%3 = 0) = #(%3 = 1) = 1, but #(%3 = 2) = 2' 
    num = test_case(L4,s,'no',num,exp)

    
