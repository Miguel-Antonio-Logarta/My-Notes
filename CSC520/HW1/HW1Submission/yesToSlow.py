# On my honor as an SFSU student, I, Miguel Logarta,
# have neither given or received inappropriate help with
# this homework assignment.

from utils import rf
import utils
from slowerThanInputLength import slowerThanInputLength 

# slowerThanInputLength is undecidable because in this function,
# If slowerThanInputLength returns yes, we will subtract the length
# of that input string by 1
# For example, if P has 6 statements, and I has a length of 6 characters,
# slowerThanInputLength would return 'yes' since 6 >= 6.
# However, we return a length of 7, which would make the statement 6 >= 7 
# which is false. 
# This causes a contradiction since we can now plug in a program P
# and an inputString I that causes alterYesToSlow to say 'yes' when 
# it should say 'no' instead.
# Therefore, slowerThanInputLength is undecidable by turing reduction.
def alterYesToSlow(inString):
    # HW1: Add function body
    (progString, newString) = utils.DSS(inString)
    if 'yes':
        return len(newString) + 'a'
    else:
        return len(newString)

# yesOnString is reducible to slowerThanInputLength:
# if slowerThanInputLength were decidable, then
# yesOnString would be decidable too.
def yesViaSlow(progString,inString):
    # HW1: add needed statement(s)
    singleString = utils.ESS(progString, inString)
    # HW1: Add another argument IF needed
    return slowerThanInputLength(rf('alterYesToSlow.py'), singleString)


# NOTES FOR MYSELF PLS IGNORE -------------------------------------------------
# slowerThanInputLength(P) will return 'yes' if
# I(P) executes more statements than I itself.

# Reduction on YesOnString means that the function slowerThanInputLength(P)
# will return a 'yes' or 'no' answer

# We have to prove that slowerThanInputLength(P) is undecidable (uncomputable)
# Meaning that we have to prove that we can't reliably predict whether
# it will return 'yes' or 'no'

# This function has 6 statements
def f(inString):
    result = 'z'
    for ch in 'abc':
        result += 'ch'
    return 'done'

# This says no because the length of abcabcabc has a length of 9
# While the number of statements in f is 6. 6 < 9 so it will return no
slowerThanInputLength(rf('f.py'), 'abcabcabc') == 'no'

# This will return yes, since abc is shorter than 6.
slowerThanInputLength(rf('f.py'), 'abc') == 'yes'
