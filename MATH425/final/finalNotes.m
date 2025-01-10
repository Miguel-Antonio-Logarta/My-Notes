% Final Notes

% Basic Commands to know
A = zeros(4, 5) % Create 4x5 matrix filled with zeroes
A = eye(4, 5) % Create a 4x5 identity matrix
A = randi(4, 5) % Create a 4x5 random matrix

[Q, R] = qr(A) % Qr factorization of A, Q is orthogonal matrix, R is mxn upper triangular matrix
e = eig(A) % Get eigenvalues of A. e is a column vector
[P, Sigma, Q] = svd(A) % Get SVD of A, A = PΣQ^T where P is orthonormal vectors of AA^T, Q is the orthonormal vectors of A^TA, and Σ are the singlular values of A 


% QR method of finding eigenvalues of a matrix
function [A, Q, R] = computeEigenvalues(M, k)
    A_k = M;
    [Q_k, R_k] = qr(A_k);
    Q_hat = Q_k;
    R_hat = R_k;
    for i=1:k-1
        % [Q_k, R_k] = qr(A_k);
        A_k = R_k*Q_k;
        [Q_k, R_k] = qr(A_k);
        Q_hat = Q_hat*Q_k;
        R_hat = R_k*R_hat;
    end
    A = A_k;
    Q = Q_hat;
    R = R_hat;
end

A = [3 1 2; 1 3 1; 2 1 3;];
[Q_verify, L_verify] = eig(A)

A
Q_verify * L_verify * transpose(Q_verify)

[A_result, Q_result, R_result] = computeEigenvalues(A, 100);
A_result
Q_result



% A^(100)
%   63.1368   -0.0000    0.0000   -0.0000
%    0.0000   20.8936   -0.0000    0.0000
%   -0.0000   -0.0000   13.6040   -0.0000
%    0.0000    0.0000   -0.0000    0.3656


% CHeck if orthogonal Exercise 4.41
% dot product should be zero

v1 = [1; 1; 0;]
v2 = [-2; 2; 2;]
v3 = [2; -1; -3]
v4 = [-1; 3; 4;]

line = [1; 3; -2]

v1'*line
v2'*line
v3'*line
v4'*line

plane1 = [1; -1; 1;]
plane2 = [2; 1; 1;]

v1'*plane1
v1'*plane2

v2'*plane1
v2'*plane2

v3'*plane1
v3'*plane2

v4'*plane1
v4'*plane2