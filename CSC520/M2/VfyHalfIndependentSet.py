from graph import Graph
from math import ceil

DEV = False
VERBOSE = True

#identifier for verbose output
VHlfIS = 'VERBOSE: VfyHalfIndependentSet()'



def  VfyHalfIndependentSet(I,S,H):
    edges = I.split()
    g = Graph(I,directed=False,weighted=False)

    nodes = list(g.nodes.keys())
    if DEV: print(f'DEV: graph nodes "{nodes}"')    
    hint_nodes = H.split()
    if DEV: print(f'DEV: hint nodes "{hint_nodes}"')    
    
    needed = ceil(len(nodes)/2)
    set_size = len(hint_nodes)
    
    if set_size < needed:
        if VERBOSE:
            print(f'{VHlfIS}s {set_size} nodes in hint, {needed} needed')
            return 'unsure'
        
    for idx,node_1 in enumerate(hint_nodes[:-1]):
        for node_2 in hint_nodes[idx+1:]:
            if f'{node_1},{node_2}' in edges or f'{node_2},{node_1}' in edges:
                if VERBOSE:
                    e =  f'{node_1},{node_2}'
                    msg = f'"{H}" not independent set, edge "{e}" exists'
                    print(f'{VHlfIS} "{msg}"')
                    return 'unsure'
  
    return 'correct'

if __name__ == '__main__':
    def test_case(F, I, S, H, expected, num, comment=''):
        err = '** '
        result = F(I, S, H)
        func_name = str(F).split()[1]
        func_call = f'{func_name}("{I}", "{S}", "{H}")'
        if result == expected:
            err = ''
        e = expected
        print(f'{err}test #{num} {func_call}: expected "{e}", received "{result}"')
        print(f'test #{num} Explanation: {comment}\n')
        return num + 1

    F = VfyHalfIndependentSet
    num = 1

    I = 'a,b a,c a,d a,e a,f'
    exp = '{b c d e f} is independent set'
    num = test_case(F, I, 'yes', 'b d f', 'correct', num, exp)

    I = 'a,b a,c a,d a,e a,f'
    exp = '{b d} is less than half nodes'
    num = test_case(F, I, 'yes', 'b d', 'unsure', num, exp)

    I = 'a,b a,c a,d a,e a,f d,f'
    exp = '{b d f } is not an independent set'
    num = test_case(F, I, 'yes', 'b d f', 'unsure', num, exp)


    
    
