from graph import Graph

DEV = False
VERBOSE = True
vfy = 'VERBOSE: vfyHasClique()'  #id to use for verbose output

def VfyHasClique(I,S,H):
    
    if len(I) < len(S) or len(I) < len(H):
        if VERBOSE: print(f'{vfy} solution or hint has unreasonable length.')
        return 'unsure'
    
    '''  
    H is a comma delimited list of nodes in the graph
    portion of the problem instance.
    '''
    (G,N) = I.split(';')
    if VERBOSE and G == '':
        print(f'{vfy} Any single node, or no nodes, ' +\
              'is considered a clique in an empty graph')
    N = int(N)
    clique = H.split()
    # clique = H.split(',')
    n = len(clique)
    if n < N:
        if VERBOSE:
            print(f'{vfy} {n} node(s) in Hint but {N} are required.')
            return 'unsure'

    g = Graph(G,directed=False,weighted=False)
    if DEV: print(f'DEV: graph = "{g}"; clique="{clique}"')
    if g.isClique(clique): return 'correct'
    if VERBOSE: print(f'{vfy} "{set(clique)}" is not a clique.')
    return 'unsure'


if __name__ == '__main__':

    def test_case(func,I,S,H,expected,num,comment=''):
        err = '** '
        result = func(I,S,H)
        func_name = str(func).split()[1]
        func_call = f'{func_name}("{I}","{S}","{H}"'
        if result == expected: err = ''
        e = expected
        print (f'{err}test #{num} {func_call}: expected "{e}", received "{result}"')
        print (f'test #{num} Explanation: {comment}\n')
        return num + 1
    
    
    F = VfyHasClique
    I = 'a,b a,c b,c c,d;3'
    num = 1
    exp = '|{a,b,c}| = 3 = target size'
    num = test_case(F,I,'yes','a,b,c','correct',num,exp)

    I = 'a,b  a,c b,c c,d;4'
    exp = '|{a,b,c}| = 3 < target size'
    num = test_case(F,I,'yes','a,b,c','unsure',num,exp)

    I = 'a,b a,c b,c c,d;3'
    exp = '{a,b,d} is not a clique'
    num = test_case(F,I,'yes','a,b,d','unsure',num,exp)

    I = 'a,b a,c b,c c,d;3'
    exp = '{a,b,d} is not a clique'
    num = test_case(F,I,'yes','a,b,d','unsure',num,exp)

    
