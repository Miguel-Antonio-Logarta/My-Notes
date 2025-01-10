% November 19 NOtes

A = [3 1 2; 1 3 1; 2 1 3;]

function [A, Q, R, Q_hat, R_hat] = spectralTheorem(M, k)
    % Calculate A^0 = Q^1R^1
    A = M;
    [Q, R] = qr(A);
    Q_hat = Q;
    R_hat = R;
    for i=1:k-1
        A = R*Q;
        [Q, R] = qr(A);
        Q_hat = Q_hat*Q;
        R_hat = R*R_hat;
    end
end

% Question 1
[Q, L] = eig(A);
test_A = Q * L * transpose(Q);
disp("Test vs A:")
test_A
A

% Question 2
[A, q, r, Q_hat] = spectralTheorem(A, 100);
disp("A^(100):")
A

% Question 3
disp("Q_hat^(100)")
Q_hat

% Question 4
disp("Repeating for another matrix A")
A
A = [31 -1 30 -9; -1 14 -2 -1; 30 -2 31 -4; -9 -1 -4 22;]
[Q, L] = eig(A);
test_A = Q * L * transpose(Q);
disp("Test vs A:")
test_A
A

[A, q, r, Q_hat] = spectralTheorem(A, 100);
disp("A^(100):")
A

disp("Q_hat^(100)")
Q_hat

% Extra questions
% Extra question 1
A = [31 -1 30 -9; -1 14 -2 -1; 30 -2 31 -4; -9 -1 -4 22;]
[A, q, r, Q_hat, R_hat] = spectralTheorem(A, 100);
result = transpose(Q_hat)*A*Q_hat;
disp("Check if A^(k) = (Q_hat^(k))^T * A * Q_hat^(k)")
A
result

% Extra question 2
A = [31 -1 30 -9; -1 14 -2 -1; 30 -2 31 -4; -9 -1 -4 22;]
A_copy = A;
disp("power")
A_copy^100
[A_result, q, r, Q_hat, R_hat] = spectralTheorem(A, 100);
disp("Check if A^k = Q_hat^(k) * R_hat^(k)")
Q_hat*R_hat
