# I, Miguel Logarta, has neither given nor received inappropriate help
# with this homework assignment
from graph import Graph
from VfyHasClique import VfyHasClique

DEV = True     # Show data useful to developer
VERBOSE = True  # Show data useful to inquisitive user

# A clique occurs when every node in a subgraph is connected to
# every other node.
def convert_HasNodeCoverD_To_HasCliqueD(hasNodeCover_instance):
    # Implements the conversion function used to show HasNodeCover ≤p HasClique
    # That is, modify hasNodeCover_instance to create hasClique_instance such
    # that hasClique_instance is positive instance of HasClique iff hasNodeCover_instance
    # is a positive instance of HasNodeCover
    #
    print(f"Node cover instance: {hasNodeCover_instance}")
    graph_str, NodeCover_size = hasNodeCover_instance.split(';')
    NodeCover_size = int(NodeCover_size)

    # Create WCBC graph object
    g = Graph(graph_str, weighted=False, directed=False)
    
    nodes = list(g.nodes.keys())  #get list of nodes in problem instance graph

    # get list of edges in problem instance graph, with each edge represented
    # as a strings with the format: <start_node>,<end_node>
    edges = [str(edge) for edge in g.edges()]
    
    if DEV: print(f'VERBOSE: nodes = "{nodes}", edges = "{edges}"')
    
    #### L1017 -- change to set hasClique_edges appropriately
    # Just leaving the instance graph unchanged won't work 
   
    # We are given a positive instance of hasNodeCover which is a graph that contains a node cover
    # A node cover is just a complement of a clique.
    # So if we have a graph G' that contains a node cover, we take the complement of that
    # to get G which is a graph that contains a clique.

    # Create a set of all possible edges
    all_possible_edges = set()
    for src_node in nodes:
        for dest_node in nodes:
            # We don't want nodes that connect to themselves
            if src_node == dest_node: continue
            edge_1 = f"{src_node},{dest_node}"
            edge_2 = f"{dest_node},{src_node}"
            # NOR statement to ensure that edge doesn't already exist
            if not (edge_1 in all_possible_edges or edge_2 in all_possible_edges):
                all_possible_edges.add(edge_1)

    # XOR that set with edges of the node cover graph
    hasClique_edges = all_possible_edges.difference(set(edges))
    
    hasClique_graph = ' '.join(hasClique_edges)

    ### L1017 -- change to set clique size appropriately -- leaving it
    ### unchanged won't work
    # Node cover size l = |N| - k
    # Clique size k = |N| - l
    clique_size = len(nodes) - NodeCover_size 

    hasClique_instance = f' {hasClique_graph};{clique_size}'
    if VERBOSE: print(f'VERBOSE: hasClique_instance = "{hasClique_instance}"')
    
    return hasClique_instance

C =  convert_HasNodeCoverD_To_HasCliqueD

def vfyHasNodeCoverViaVfyHasClique(hasNodeCover_instance,S,H):
    hasClique_instance = C(hasNodeCover_instance)
    graph_str, NodeCover_size = hasNodeCover_instance.split(';')

    g = Graph(graph_str, weighted=False, directed=False)
    nodes = list(g.nodes.keys()) #Convert to list
    
    # The hint for  hasClique consists of whatever nodes are not in the hint for
    # nodeCover
    # 
    if DEV: print(f'VERBOSE: nodes = {nodes}, H = {H.split()}')
    H_clique = ' '.join([node for node in nodes if node not in H.split()])

    if VERBOSE:
        print('VERBOSE: Hint for clique verifier to use on converted ' + \
              f'instance = "{H_clique}" (nodes not in node cover hint)')
    result = VfyHasClique(hasClique_instance,S,H_clique)
    return result
    

if __name__ == '__main__':

    def test_case(F,I,S,H,expected,num,comment=''):
        err = '** '
        result = F(I,S,H)
        func_name = str(F).split()[1]
        func_call = f'''{func_name}("{I}","{S}","{H}")'''
        if result == expected: err = ''
        e = expected
        print (f'{err}test #{num} {func_call}: expected "{e}", received "{result}"')
        print (f'test #{num} Explanation: {comment}\n')
        return num + 1

    F = vfyHasNodeCoverViaVfyHasClique
    num = 1
    I= 'a,b  a,c a,d;1'                    
    exp = '|{a}| = 1 = target node cover set size'
    num = test_case(F,I,'yes','a','correct',num,exp)

    I= 'a,b  a,c a,d d,e;2'                    
    exp = '|{a,e}| = 2 = target node cover set size'
    num = test_case(F,I,'yes','a e','correct',num,exp)    

    I= 'a,b  a,c a,d d,e;1'                    
    exp = '|{a,e}| = 2 > 1 = target node cover set size'
    num = test_case(F,I,'yes','a e','unsure',num,exp)    
    
    I= 'a,b  a,c a,d;1'                    
    exp = '{d} is not a node cover for graph'
    num = test_case(F,I,'yes','d','unsure',num,exp)

    I= 'a,b  a,c c,d;1'                    
    exp = '|{a,b}| = |{a,c}| = 2 > 1 = target node cover set size'
    num = test_case(F,I,'yes','a b','unsure',num,exp)
