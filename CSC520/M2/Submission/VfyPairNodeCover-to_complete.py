from graph import Graph

'''
In an instance of PairNodeCover has the syntax
I = G;N

where 

G is an undirected, unweighted graph defind as a sequence of edges (for
example: 'a,b a,c c,d' )
 
I is a positive instance of Pair NodeCover is there is a subset S of the
nodes of G, |S| <= 2, such that every edge in G has a an endpoint in
S. For example, 'a,b a,c c,d' is a positive instance of PairodeCover,
because every edge in the graph has an endpoint in {a,c}, and
|{a,c}| = 2; 'a,b a,c' is also a positive instance of PairNodeCover because
every edge in the graph has an endpoint in {a}. 'a,b a,c d,e f,g' is a
negative instance of PairNodeCover

 '''

DEV = True
VERBOSE = True

def vfyPairNodeCover(I,S,H):
    edges = I.split()
    g = Graph(I, directed=False,weighted=False)
    nodes = list(g.nodes.keys())
    node_cover = H.split()
    n = len(node_cover)

    ##### Midterm #2 Complete the verifier, returning 'unsure' as needed
    # We need to find at all nodes that connect to every other node (node cover)
    # The number of nodes that fulfill this criteria has to be less than or equal to two

    # My algorithm
    # Check every node in G,
        # If the node in G is not connected to a node in H, then return "unsure"
    
    # node_cover is provided by H
    # Get set of nodes in the node cover
    nodes_set = set(node_cover)
    
    if n > 2:
        if VERBOSE: print("The number of nodes in hint exceed 2")
        return 'unsure'
    
    for edge in edges:
        first_node, second_node = edge.split(',')
        # If these nodes are not connected to nodes in H, then that means
        # that H is not a valid node cover. It's not connected to every node in G
        if first_node not in nodes_set and second_node not in nodes_set:
            if VERBOSE: print("The nodes in H set is not a valid node cover.")
            return 'unsure'

    return 'correct'

if __name__ == '__main__':

    def test_case(func,I,S,H,expected,num,comment=''):
        err = '** '
        result = func(I,S,H)
        func_name = str(func).split()[1]
        func_call = f'''{func_name}("{I}","{S}","{H}")'''
        if result == expected: err = ''
        e = expected
        print (f'{err}test #{num} {func_call}: expected "{e}", received "{result}"')
        print (f'test #{num} Explanation: {comment}\n')
        return num + 1

    
    F = vfyPairNodeCover
    I = 'a,b  a,c b,c'
    num = 1
    exp = '|{a,b}| = 2'
    num = test_case(F,I,'yes','a b','correct',num,exp)

    I = 'a,b  a,c b,c'
    exp = '{a} does not cover graph'
    num = test_case(F,I,'yes','a','unsure',num,exp)

    I = 'a,b  a,c a,d a,e'
    exp = '{a} covers graph'
    num = test_case(F,I,'yes','a','correct',num,exp)

    I = 'a,b  a,c b,c c,d'
    exp = 'Nodes {a,b} do not cover edge c,d'
    num = test_case(F,I,'yes','a b','unsure',num,exp)

    I = 'a,b  c,d e,f'
    exp = '|{a,c e}| > 2'
    num = test_case(F,I,'yes','a c e','unsure',num,exp)
    
    
