% Question 4

V = [-1 2 0; 1 -1 1; 0 1 3;]
L = [0 0 0; 0 2 0; 0 0 -2;]

A = V*L*transpose(V)

% Check if eigenvalues and eigenvectors match
[X, Y, Z] = eig(A)