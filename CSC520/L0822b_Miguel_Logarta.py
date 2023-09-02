'''
Affirm an individual or group honor code by replacing <name> or <names>.
`
For individual submissions: I, Miguel, have neither given or recived inappropriate help with this homework assignment.

For group submissions: We, <names>, have neither given or recived inappropriate help with this homework assignment. All group members participated in this work, and all concur with this submission.
'''


def L0822b(inString: str) -> str:
    # For lab L0822b, add code that returns the sum of odd positive # ints.
    # The test cases indicate that we are receiving a string of space delimited integers
    numbers = [int(n) for n in inString.split()]
    sum: int = 0
    for num in numbers:
        if num % 2 == 1 and num > 0:
            sum += num
    return str(sum)


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

    F = L0822b
    s = '1 5 9 -2'
    exp = '#(%3 = 0) = #(%3 = 1) = #(%3 = 2) = 1, -2 ignored' 
    num = test_case(F,s,'15',num,exp)
    
    s = ' -2'
    exp = '#(%3 = 0) = #(%3 = 1) = #(%3 = 2) = 0, -2 ignored' 
    num = test_case(F,s,'0',num,exp)    

    s = '  3 -5 0 1 '
    exp = '#(%3 = 0) = 1, but #(%3 = 1) = #(%3 = 2) = 1, -5 ignored' 
    num = test_case(F,s,'4',num,exp)    

    s = '1 6 1 4 1 1 -2'
    exp = '#(%3 = 0) = #(%3 = 1) = 1, but #(%3 = 2) = 2' 
    num = test_case(F,s,'4',num,exp)

    
