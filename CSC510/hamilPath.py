
# M is our graph
# i is the row
# j is the column
# VerticesVisited is the set of nodes to visit
# S is our current hamiltonian path e.g. Pk = {M[0][1], M[1][3], ... }
# P is the set of all hamiltonian paths for matrix M
def DFSHamiltonian(
        M: list[list[int]], 
        i: int, 
        j: int, 
        verticesVisited: set, 
        S: list[list[int]], 
        P: list[list[list[int]]]
) -> list[list[int]] | None:
    # Make a new copy of vertices
    newVerticesVisited = set(verticesVisited)
    newVerticesVisited.add(i)

    print(f"M[{i}][{j}] = {M[i][j]}, Nodes Visited: {newVerticesVisited}")

    next_row = j    # Rule 3
    no_new_nodes = True
    for j in range(len(M[next_row])):
        if next_row == 2 and j == 3:
            debug = 0

        if next_row == j:
            # Vertices cannot have edges into themselves
            continue
        
        # We need to check if we have visited all our vertices
        if j in newVerticesVisited:
            # You can only visit each vertex once
            continue        

        if M[next_row][j] == 0:
            continue
        
        no_new_nodes = False
        path = DFSHamiltonian(M, next_row, j, newVerticesVisited, S, P)
        # If there is a hamiltonian path, it will return an array
        # else it will return none
        if path is not None:
            return [[next_row, j]]+path

    if no_new_nodes:
        if M[next_row][0] == 0:
            print(f"M[{next_row}][0] = {M[next_row][0]} No hamiltonian path found")
        else:
            print(f"M[{next_row}][0] = {M[next_row][0]} Hamiltonian path found")
            return [[next_row, 0]]
    
def findHamiltonianPath(
        M: list[list[int]],         # M is matrix of size NxN 
) -> list[list[list[int]]]:
    P: list[list[list[int]]] = [] # P is set of all solutions
    for j in range(len(M[0])):
        verticesVisited = set()
        S = []
        if M[0][j] > 0:
            S = DFSHamiltonian(M, 0, j, verticesVisited, S, P)
            if S is not None:
                S.insert(0, [0,j])
            P.append(S)
    return P

if __name__ == "__main__":
    M1 =  [[0,3,6,0], [3,0,2,5], [6,2,0,1], [0,5,1,0]]
    M2 = [[0,7,1,0,0,8],[7,0,5,0,9,6],[1,5,0,4,0,0],[0,0,4,0,2,0],[0,9,0,2,0,3],[8,6,0,0,3,0]]
    P1 = findHamiltonianPath(M1)
    P2 = findHamiltonianPath(M2)
    print("P1: ", P1)
    print("P2: ", P2)