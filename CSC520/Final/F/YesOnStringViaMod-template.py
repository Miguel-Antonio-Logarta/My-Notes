# I, Miguel Antonio Logarta, have neither given or received inappropriate help with this 
# final exam. 

# "Oracle" function -- assume exists to show contradiction
#
import Mod2InMod3Out 
import utils
from utils import rf

from universal import universal


# Complete for the final exam by filling in the ellipses
def alterYesToMod(inString):
    progString = "mod2InMod3Out.py"
    newInString = ""

    # The following needs to be true
    # len(inString) % 2 + 1 == len(newInString) % 3
    firstCondition = len(inString) % 2 + 1 
    if firstCondition == 1:
        newInString =  "x"
    elif firstCondition == 2:
        newInString =  "xxx"

    result = universal(progString,newInString)
    return result
        

# Complete for the final exam by filling in the ellipses. For the
# call to mod2InMod3Out, supply a 2nd argument if needed.
def yesViaMod(progString,inString):
    alteredString = alterYesToMod(inString)
    result = mod2InMod3Out(rf('alterYesToMod.py'), alteredString)
    return result



'''
Mod2InMod3Out is undecidable because ...

    Assume that Mod2InMod3Out is decidable, that means that every positive instance of 
    Mod2InMod3Out takes in a string I and outputs string I' where the length
    len(I') % 3 == len(I) % 2 + 1

    When we try to test for a positive instance of mod2InMod3Out, we should expect the condition
    above to hold true, however, it only returns the correct answer half of the time.
    When the length of I is 0, it will return I' with the correct condition
    but when the length of I % 2 == 1, it will return I' where I' % 3 == 0. It doesn't match up
'''
