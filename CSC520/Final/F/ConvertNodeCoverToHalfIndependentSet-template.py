from graph import Graph
from VfyHalfIndependentSet import VfyHalfIndependentSet
from ConvertCliqueToHalfIndependentSet import ConvertCliqueToHalfIndependentSet

DEV = True
VERBOSE = True

#identifier for verbose output
CNCtoHlfIS = 'VERBOSE: ConvertNodeCoverToHalfIndependentSet()'

neg_HlfIS_instance = 'a,c b,f c,d c,e c,f d,e d,f e,f' 

def base_new_node_name(nodes):
    #Use names starting  with 'Z' to form added nodes
    base_name = 'Z'
    for node in nodes:
        if node == base_name:
            base_name += 'Z'

    return base_name
# Global variable set and read in ConvertNodeCoverToHalfIndependentSet, and
# read in vfyNodeCoverViaVfyHalfIndependentSet().
count_new_nodes = None 

'''An node cover is a subset of the nodes in an unweighted, undirected
graph, specified as a white space delimited sequence of edges, such that
there is every edge in the graph has at least one end point in a node in the subset.

An instance of HasNodeCover is an unweighted, undirected graph specified
as a series of edges, separated by a semicolon (';') from a integer > 0.
For example, 'a,b a,c b,c d,e;3' is an instance of HasNodeCover. We call
the graph portion g and the integer node_cover_size.

An instance of HasNodeCover is a positive instance iff g contains a
node covering that is no larger than node_cover_size.  For
example, 'a,b a,c b,c d,e;3' is a positive instance because {a, b, dc} is
a node covering of size 3.  'a,b a,c b,c d,e;2' is a negative instance because
the graph contains no node covering smaller than 3.

An independent set is a subset of the nodes in an unweighted, undirected
graph, specified as a white space delimited sequence of edges, such that
there are no edges between any two nodes in the subset. For example, the
graph "a,a b,c b,d b,e c,d c,e d,e" has an independent set of size 2,
{a,b} since there is no link between nodes a and b. Note that loop back
nodes are disregarded when looking for independent sets.
    
An instance of HalfIndependentSet is also an unweighted, undirected
graph specified as a series of edges. For example, "a,d a,e a,f b,d b,e
b,f c,c c,f e,f".

A HalfIndependentSet instance is a positive instance iff at least half
the nodes graph form an independent set. "a,d a,e a,f b,d b,e b,f c,c
c,f e,f" is a positive instance because {a, b, c} forms an independent
set of size 3, out of a total of 6 graph nodes.  "a,a a,c a,d a,e b,b
b,c b,d b,e c,d c,e d,e" is a negative instance, because it has no
independent sets larger than 2, which is one less than the number of
graph nodes.

'''
def graphComplement(nodes,edges):

    complement_edge_list = []
    
    ## Add the complement of clique_graph to the HlfIS graph 
    #
    for idx,node_1 in enumerate(nodes[:-1]):
        for node_2 in nodes[idx+1:]:
            edge = f'{node_1},{node_2}'
            if  f'{node_1},{node_2}' in edges or \
                f'{node_1},{node_2}' in edges:    
                continue # edge in clique instance graph so not in complement
            complement_edge_list.append(edge)

    
    return  f"{' '.join(complement_edge_list)}"
    
def ConvertNodeCoverToHalfIndependentSet(nc_instance):
    nc_graph, nc_size =  nc_instance.split(';')
    try:
        nc_size = int(nc_size)
    except:
        if VERBOSE:
            print(f'{CNCtoHlfIS} node covering size "{nc_size}" not integer')
            return neg_HlfIS_instance

    if nc_size < 1:
        if VERBOSE:
            print(f'{CNCtoHlfIS} node coveringe size "{nc_size}" < 1')
            return neg_HlfIS_instance

    edges = nc_graph.split()
    
    g = Graph(nc_graph,directed=False,weighted=False)
    nodes = list(g.nodes.keys())

    ## ** Final exam: Use ConvertCliqueToHalfIndependentSet(), and anything else
    ## that is needed to make transformed instance into a positive instance of
    ## HasHalfIndependentSet iff nc_instance if a positive instance of HasNodeCover
    ##
    transformed_instance = nc_instance
    clique_instance = f"{graphComplement(nodes,edges)};{len(nodes)-nc_size}"

    if VERBOSE:
        print(f'{CNCtoHlfIS} transformed instance: {transformed_instance}')
    return transformed_instance

C = ConvertNodeCoverToHalfIndependentSet

def vfyNCViaVfyHalfIndependentSet(nc_instance, S, H):
    nc_graph, nc_size = nc_instance.split(';')
    nc_size = int(nc_size)
    g = Graph(nc_graph,directed=False, weighted=False)
    nc_nodes = list(g.nodes.keys())

    hint_nodes = H.split()
    hlfIs_hint_nodes = set(nc_nodes) - set(hint_nodes)
    hlfIs_hint_nodes = list(hlfIs_hint_nodes)
    hlfIs_hint_nodes =  ' '.join(hlfIs_hint_nodes)  

    hlfIS_instance = C(nc_instance)
    g = Graph(hlfIS_instance,directed=False, weighted=False)
    hlfIS_nodes = list(g.nodes.keys())
    new_nodes = []
    # Bugfix to template, will discuss in class
    if len(hlfIS_nodes) > (2*len(hlfIs_hint_nodes)):
        
        for idx in range( len(hlfIS_nodes) - (2*len(hlfIs_hint_nodes)) ):
            new_nodes.append(f'Z{idx}')
    hlfIS_hint = f'{hlfIs_hint_nodes} {" ".join(new_nodes)}'
    if DEV:
        print(f'DEV: vfyNodeCoverViaVfyHalfIndependentSet() - hint "{hlfIS_hint}"')

    return VfyHalfIndependentSet(hlfIS_instance, S, hlfIS_hint)            


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

    F = vfyNCViaVfyHalfIndependentSet
    num = 1
    
    I = 'a,b a,c a,d a,e b,f;2'
    exp = '{a f} is node cover'
    num = test_case(F, I, 'yes', 'a f', 'correct', num, exp)

    I = 'a,b a,c a,d a,e b,f b,g;2'
    exp = '{a f} is not a node cover'
    num = test_case(F, I, 'yes', 'a f', 'unsure', num, exp)

    I = 'a,b b,c c,d d,e d,f;4'
    exp = '{a b c d} is node cover'
    num = test_case(F, I, 'yes', 'a b c d', 'correct', num, exp)

    I = 'a,b a,c a,d a,e b,f b,g;4'
    exp = '{a b c d } is not a node cover'
    num = test_case(F, I, 'yes', 'a f', 'unsure', num, exp)
    
'''
This proves that HalfIndependentSet is NP-hard because ...

'''

