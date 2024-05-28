from graph import Graph
from VfyHalfIndependentSet import VfyHalfIndependentSet

DEV = True
VERBOSE = True

#identifier for verbose output
CCtoHlfIS = 'VERBOSE: ConvertCliqueToHalfIndependentSet()'

neg_HlfIS_instance = 'a,c b,f c,d c,e c,f d,e d,f e,f' 

def base_new_node_name(nodes):
    #Use names starting  with 'Z' to form added nodes
    base_name = 'Z'
    for node in nodes:
        if node == base_name:
            base_name += 'Z'

    return base_name
# Global variable set and read in ConvertCliqueToHalfIndependentSet, and
# read in vfyCliqueViaVfyHalfIndependentSet().
count_new_nodes = None 

'''
A clique is a subset of the nodes in an unweighted, undirected
graph, specified as a white space delimited sequence of edges, such that
there is an edge between every two nodes in the subset.

An instance of HasClique is an unweighted, undirected graph specified
as a series of edges, separated by a semicolon (';') from a integer > 1.
For example, 'a,b a,c b,c d,e;3' is an instance of HasClique. We call
the graph portion g and the integer clique_size.

An instance of HasClique is a positive instance iff g contains a clique
at least as large as clique_size.  For example, 'a,b a,c b,c d,e;3' is
a positive instance because {a, b, c} is a clique of size 3.
 'a,b a,c b,c d,e;4' is a negative instance because the graph contains
 no clique of size 4.  'a,b  b,c d,e;3' is a negative instance because
 with the deletion of edge a,c, {a, b, c} is no longer a clique.

An independent set is a subset of the nodes in an unweighted, undirected
graph, specified as a white space delimited sequence of edges, such that
there are no edges between any two nodes in the subset. For example, the
graph "a,a b,c b,d b,e c,d c,e d,e" has an independent set of size 2,
{a,b} since there is no link between nodes a and b. Note that loop back
nodes are disregarded when looking for independent sets.
    
An instance of HalfIndependentSet is also an unweighted, undirected
graph specified as a series of edges. For example, "a,d a,e a,f b,d b,e
b,f c,c c,f e,f".

A HalfIndependentSet instance is a positive instance iff at least half the nodes graph form an independent set. "a,d a,e a,f b,d b,e b,f c,c c,f e,f" is a positive instance because {a, b, c} forms an independent set of size 3, out of a total of 6 graph nodes.  "a,a a,c a,d a,e b,b b,c b,d b,e c,d c,e d,e" is a negative instance, because it has no independent sets larger than 2, which is one less than the number of graph nodes.

'''

    
def ConvertCliqueToHalfIndependentSet(clique_instance):
    clique_graph, clique_size =  clique_instance.split(';')
    try:
        clique_size = int(clique_size)
    except:
        if VERBOSE:
            print(f'{CCtoHlfIS} clique size "{clique_size}" not integer')
            return neg_HlfIS_instance

    if clique_size < 2:
        if VERBOSE:
            print(f'{CCtoHlfIS} clique size "{clique_size}" < 2')
            return neg_HlfIS_instance

    edges = clique_graph.split()
    
    g = Graph(clique_graph,directed=False,weighted=False)
    nodes = list(g.nodes.keys())

    HlfIS_graph_edge_list = []
    
    ## Add the complement of clique_graph to the HlfIS graph 
    #
    for idx,node_1 in enumerate(nodes[:-1]):
        for node_2 in nodes[idx+1:]:
            edge = f'{node_1},{node_2}'
            if  f'{node_1},{node_2}' in edges or \
                f'{node_1},{node_2}' in edges:    
                continue # edge in clique instance graph so not in complement
            HlfIS_graph_edge_list.append(edge)

    global count_new_nodes
    count_new_nodes = abs(len(nodes) - (2 * clique_size))
    base_name = base_new_node_name(nodes)
    new_nodes = [f'{base_name}{idx}' for idx in range(count_new_nodes)]

    #clique_size is also the independent set size of the clique_instance graph complement
    if 2*clique_size < len(nodes):
        for new_node in new_nodes:
            #add node connected to itself -- must be part of independent set
            HlfIS_graph_edge_list.append(f'{new_node},{new_node}') 

    elif 2*clique_size > len(nodes):
         if len(new_nodes) == 1:
            for node in nodes:
                HlfIS_graph_edge_list.append(f'{new_nodes[0]},{node}') 
         else:
             # Connect new node to all the other nodes in the graph
             for idx,new_node_1 in enumerate(new_nodes[:-1]):
                for new_node_2 in new_nodes[idx+1:]:
                    HlfIS_graph_edge_list.append(f'{new_node_1},{new_node_2}') 
                for node in nodes:
                   HlfIS_graph_edge_list.append(f'{new_node_1},{node}') 
                   
    HlfIS_instance = ' '.join(HlfIS_graph_edge_list)
    if VERBOSE: print(f'{CCtoHlfIS} Half Independent Set instance: "{HlfIS_instance}"')
    return HlfIS_instance

C = ConvertCliqueToHalfIndependentSet

def vfyCliqueViaVfyHalfIndependentSet(clique_instance, S, H):
    clique_graph,clique_size = clique_instance.split(';')
    clique_size = int(clique_size)
    g = Graph(clique_graph,directed=False, weighted=False)
    clique_nodes = list(g.nodes.keys())

    new_nodes = []
    # Bugfix to template, will discuss in class
    if len(clique_nodes) > (2*clique_size):
        
        for idx in range( len(clique_nodes) - (2*clique_size) ):
            new_nodes.append(f'Z{idx}')
            
    hlfIS_instance = C(clique_instance)
    HlfIS_hint = f'{H} {" ".join(new_nodes)}'
    if DEV:
        print(f'DEV: vfyCliqueViaVfyHalfIndependentSet() - hint "{HlfIS_hint}"')
        
    return VfyHalfIndependentSet(hlfIS_instance, S, HlfIS_hint)

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

    F = vfyCliqueViaVfyHalfIndependentSet
    num = 1

    
    I = 'a,b a,c a,d a,e a,f;2'
    exp = '{a b} is clique'
    num = test_case(F, I, 'yes', 'a b', 'correct', num, exp)

    I = 'a,b a,c a,d a,e a,f;3'
    exp = '{a b} is a clique too small'
    num = test_case(F, I, 'yes', 'a b', 'unsure', num, exp)
 
    I = 'a,b a,c a,d a,e a,f;3'
    exp = '{a b c} is not a clique'
    num = test_case(F, I, 'yes', 'a b c', 'unsure', num, exp)

    I = 'a,b a,c b,c d,e;3'
    exp = '{a b c} is a clique'
    num = test_case(F, I, 'yes', 'a b c', 'correct', num, exp)

    I = 'a,b a,c b,c d,e d,f;3'
    exp = '{a b c} is clique'
    num = test_case(F, I, 'yes', 'a b c', 'correct', num, exp)

    I = 'a,b a,c b,c d,e;3'
    exp = '{a b d} is not a clique'
    num = test_case(F, I, 'yes', 'a b d', 'unsure', num, exp)

    I = 'a,b a,c b,c d,e;4'
    exp = '{a b c} is a clique too small'
    num = test_case(F, I, 'yes', 'a b c', 'unsure', num, exp)

    
            
            
