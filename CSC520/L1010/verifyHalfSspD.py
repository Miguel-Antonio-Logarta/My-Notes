# SISO program verifyTspD.py

# Verifies a solution to the computational problem TspD.

# The parameters I, S, H are the instance, proposed solution, and hint
# for a verifier as described in Chapter 12.

# The return value is as described in Chapter 12 for verifiers:
# 'correct' if S was successfully verified, and 'unsure' otherwise.
import utils; from utils import rf
from graph import Graph, Path

VERBOSE = False
def verifyHalfSspD(I, S, H):
    if len(S) > 3 or len(H) > len(I):
        if VERBOSE: print(f'VERBOSE: hint or solution too long')
        return 'unsure'
    
    if S == 'no': return 'unsure' 
    # extract G,L from I, and convert to correct data types etc.
    (G,L) = I.split(';')
    g = Graph(G, directed=False); L = int(L)

    graph_nodes = list(g.getNodesAsSet())
    path_nodes = H.split(',')
    if VERBOSE: print(f'path = {path_nodes}')
    if len(path_nodes) < len(graph_nodes)/2 or len(path_nodes) > len(graph_nodes):
        if VERBOSE: print(f'VERBOSE -- Path wrong size: {H}')
        return 'unsure'

    for pn in path_nodes:
        if pn not in graph_nodes:
          if VERBOSE: print(f'VERBOSE -- node {pn} not in  {G}')
          return 'unsure'
        tmp = path_nodes[:]  # clone path_nodes
        tmp.remove(pn)
        if pn in tmp:
            if VERBOSE: print(f'VERBOSE -- multiple occurrences of {pn} in {path_nodes}')
            return 'unsure'
        
    # split the hint string into a list of vertices, which will
    # form a Hamilton cycle of length at most L, if the hint is correct
    path = Path.fromString(H)

    # verify the hint is a Hamilton path, and has length at most L
    if g.isPath(path) and \
               g.pathLength(path) <= L:
        return 'correct'
    else:
        if VERBOSE: print(f'VERBOSE -- {H} is not a simple path or it is too long')
        return 'unsure' 

if __name__ == '__main__':

    def test_case(I,S,H,expected,num,comment=''):
            err = '** '
            result = verifyHalfSspD(I,S,H)
            func = f'''verifyHalfSspD(("{I}","{S}","{H}")'''
            if result == expected: err = ''
            e = expected
            print (f'{err}test #{num} {func}: expected "{e}", received "{result}"')
            print (f'test #{num} Explanation: {comment}\n')
            return num + 1
        
    num = 1
    exp  = 'Hamiltonian path'
    num = test_case('a,b,3 b,c,2 c,a,7;12','yes','a,b,c','correct',num,exp)

    exp = 'path has too few nodes'
    num = test_case('a,b,3 b,c,2 c,a,7 c,d,1 d,e,1 ;12','yes','a,b','unsure',num,exp)

    exp = 'path comprises 3 of 5 nodes'
    num = test_case('a,b,3 b,c,2 c,a,7 c,d,1 d,e,1 ;12','yes','a,b,c','correct',num,exp)

    exp = 'a,b,c is not a simple path'
    num = test_case('a,b,3 b,d,2 c,e,1 c,d,1 d,e,1 ;12','yes','a,b,c','unsure',num,exp)

    exp = 'path is too long'
    num = test_case('a,b,3 b,c,2 c,a,7 c,d,1 d,e,1 ;2','yes','a,b,c','unsure',num,exp)
    
