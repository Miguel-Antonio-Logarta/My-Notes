% % Question 1
A = [1 1 1 1; 1 2 1 0; 1 1 2 1; 1 0 1 1;]
b = [2 ; -1; 1; 2]

% part 1a
[Q, R] = qr(A)

% part 1b
x = inv(Q*R)*b


% Question 2
A = [0 0.5 0.5; 0.5 0 0.5; 0.5 0.5 0];
% part 2a
eig(A) % eigenvalues


% part 2b
u = randi(100, 3)
for i=0:99
    A = A*A;
end

% part 2c
A*u

% A = randomA
% [Q, R] = qr(A)
% k = 100
% for i=1:k-1
%     A = R*Q;
%     [Q, R] = qr(A);
% end
% 
% A


% Question 3
A = [4 -2 4.999 4; 11.001 -3 5 1; 8 -4.001 10 8; 15 -5 10 4.999];

% part 3a
[P, S, Q] = svd(A);
disp("SVD of A: ")
P
S
Q

% part 3b. The rank of A is the same as the rank of Sigma rank(A) =
% rank(S)
A
disp("rank of A: " + rank(A))

S
disp("rank of Sigma: " + rank(S))

% part 3c
% The true rank of A should actually be 2. We can see that for the last two
% diagonal values of S, the values are actually small. This is noise and we
% should be treating it like they're zeroes

% part 3d
[P, S, Q] = svd(A)
k = 2;

P(:,1:k)
S(:,1:k)
Ak = P(:,1:k)*S(:,1:k)*transpose(Q(:,1:k))


