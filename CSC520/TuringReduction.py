import echoFirstCharacter 
import utils
from utils import rf

from universal import universal

def alterYesToEchoFirst(inString):
    # Calls yesViaEchoFirst and returns the inverse of the answer: so "yes" turns into "no" and vice versa
    if yesViaEchoFirst(rf(inString), inString):
        return "no"
    else:
        return "yes"


# "Oracle" function -- assume exists to show contradiction
#
import echoFirstCharacter 
import utils
from utils import rf

def yesViaEchoFirst(progString,inString):
    # Function compares if the first character of the program string matches the first character of the string passed in
    # Complete for L0905b, including adding
    # any extra needed parameter to the call to
    # echoFirstCharacter
    # ...
    # ... echoFirstCharacter(rf('alterYesToEchoFirst.py'),..)
    # ...
    if echoFirstCharacter(progString) == echoFirstCharacter(inString):
        return "yes"
    else:
        return "no"


# Using Turing Reduction, I am going to prove why echoFirstCharacter() is undecidable.
# Let’s assume that F <=_t G. F is our alterYesToEchoFirst() function and is reducible 
# to G, and G is our “oracle” function which is yesViaEchoFirst().

# If G is computable, then F is also computable, so we have a reduction from F to G.
# However, this is a contradiction since F is uncomputable.
# Therefore since our assumption that G is computable is incorrect, we also conclude that G is uncomputable.

# alterYesToEchoFirst() returns the inverse of yesViaEchoFirst. 
# If alterYesToEchoFirst() is reducable to yesViaEchoFirst(), this means that yesViaEchoFirst() must be computable
# However, if we pass yesViaEchoFirst() into itself yesViaEchoFirst(rf("yesViaEchoFirst.py"), "yesViaEchoFirst"), it would return "no" since the program
# string and the name of the function have different first characters.
# This means that yesViaEchoFirst cannot exist
# This causes a contradiction. Since yesViaEchoFirst() is not computable, then alterYesToEchoFirst() is also not computable.
# Therefore, by proof of contraction alterYesToEchoFirst() is not a decidable program

 