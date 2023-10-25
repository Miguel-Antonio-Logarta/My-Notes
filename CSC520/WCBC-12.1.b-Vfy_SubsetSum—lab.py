# On my honor as an SFSU student, I, Miguel Logarta,
# have neither given or received inappropriate help with 
# this homework assignment.

DEV = True
VERBOSE = True

def Vfy_SubsetSum(I,S,H):
    # I -- a problem instance consisting of a white space delimited list of
    # weights, followed by a semi-colon (';') and the target weight
    # S -- the solution. Either a white-space delimited list of weights, or
    #      'no'. If the former, I is verified as a positive instance if
    #       item in S is also in I (allowing duplicates), and S sums to the
    #      target weight
    # H – Hint ignored,

    if len(S) > 100: # LAB: CHANGE False to set reasonable S & H (DONE)
                         # lenth restriction
        if  VERBOSE: print(f'Solution size too long: {len(S)}')
        return 'unsure'  

    if S == 'no':
        if VERBOSE: print(f'VERBOSE: solution is "no"')
        return 'unsure' 

    ####### PARSE PROBLEM INSTANCE
    #'2 4 8; 12' is an example of a correctly formatted I, with one semicolon
    try:
        (weights,target_weight) = I.split(';')
    except:
        if VERBOSE: print(f'VERBOSE: "{I}" has a semicolon problem')
        return 'unsure' 

    
    try:
        target_weight = int(target_weight)   #21 # LAB: CHANGE to set target_weight to correct (DONE)
                           # variable int value
    except:
        if VERBOSE: print(f'VERBOSE: "{target_weight}" is not an integer')
        return 'unsure'

    weights = weights.split() # Convert weights to list of strings
    
    #Convert weights from a list of strings to a list of integers
    try:
        # LAB: CHANGE to assign weigths the correct non-constant list of ints (DONE)
        weights = [int(x) for x in weights]
    except:
        if VERBOSE: print(f'VERBOSE: "{weights}" is not a white-space delimited list of ints')
        return 'unsure'
    
    if DEV:
        print(f'DEV: weights={weights}; target weight = {target_weight}') 

    ####### PARSE PROBLEM SOLUTION    
    S = S.split() # split on white space
    # Convert S from a list of strings to a list of integers
    try:

        # LAB: CHANGE to correct non-constant list of ints (DONE)
        S = [int(x) for x in S]
    except:
        if VERBOSE: print(f'VERBOSE: ("{S}" is not a white-space delimited list of ints')
        return 'unsure' # LAB: CHANGE to correct return value (DONE)

    # make copy for verifying that every item in S is also in weights
    # weights is a list of weights from the input
    weights2 = weights[:]  
    
    for s in S: # I changed this line from "weights" to "S"
        try:
            weights2.remove(s)
        except:
            if VERBOSE: print(f'VERBOSE: "{s}" not in "{weights}"')
            return 'unsure' # LAB: CHANGE to correct return value (DONE)
    
    S_weight = sum(S)   # LAB: CHANGE to the actual sum of the weights in the int list S (DONE)

    if DEV:
        print( f'Solution weight = {S_weight}')
        
    if S_weight == target_weight: 
          return 'correct' # LAB: CHANGE to correct return value (DONE)

    if VERBOSE:
        print(f'VERBOSE: solution weight is {S_weight}, target weight is {target_weight}')                      
    return 'unsure' # LAB: CHANGE to correct return value (DONE)


if __name__ == '__main__':
    def test_case(F,I,S,H,expected,num,comment=''):
        # Evaluate test case to see whether or not it meets expectations.
        #
        err = '** '   # Error flag is on by default
        result = F(I,S,H) # Call the verifier and store the result.

        # Get the function name as a string
        func_name = str(F).split()[1]
        call = f'''{func_name}("{I}","{S}","{H}")'''
        if result == expected:
            err = ''  # turn off error flag when results meet expectations

        e = expected
        print (f'{err}test #{num} {call}: expected "{e}", received "{result}"')
        print (f'test #{num} Explanation: {comment}\n')
        return num + 1


    f = Vfy_SubsetSum
    H = ""
    test_num = 1
    explanation = 'missing semicolon'
    
    test_num = test_case(f,'12 4 6 24 4 16 32','24 4 4',H,'unsure',test_num,explanation)
    print()

    explanation = 'problem instance not a white space delimited list of ints'    
    test_num = test_case(f,'12 4 6 24 4x 16;32','24 4 4',H,'unsure',test_num, explanation)
    print()

    explanation = 'target weight is not an integer'    
    test_num = test_case(f,'12 4 6 24 4 16;abc','24 16',H,'unsure', test_num, explanation)
    print()

    explanation = 'solution not a white space delimited list of ints'        
    test_num = test_case(f,'12 4 6 24 4 16;32','24 16a',H,'unsure', test_num, explanation)
    print()

    explanation  = 'solution is "no"'
    test_num = test_case(f,'12 4 6 24 4 16;32','no',H,'unsure', test_num,  explanation)
    print()

    explanation = '"1" is a weight in the solution but not in the problem instance'
    test_num = test_case(f,'12 4 6 24 4 16;32','24 4 1 3',H,'unsure', test_num, explanation)
    print()

    explanation = 'target weight is 32, solution weight is 40'
    test_num = test_case(f,'12 4 6 24 4 16;32','24 16',H,'unsure', test_num, explanation)
    print()

    explanation = 'target weight is 32, solution weight is 32'    
    test_num = test_case(f,'12 4 6 24 4 16;32','24 4 4',H,'correct', test_num, explanation)

    '''
    1 1 2 3 5 8;14
    Is this is a positive of subsetum, and if so, what's a solution? 1 5 8 is a solution

    2 4 6;7
    
    '''
