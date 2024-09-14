function f = myMatrixMultiplication(A, B)
    [nrowA ncolA] = size(A)
    [nrowB ncolB] = size(B)

    if ncolA == nrowB
        A*B
    else
        disp("Dimensions do not match")
    end
end

M = [3 2 4; 1 2 3; 4 5 6]
N = [2 3; 4 5; 1 -2]
%myMatrixMultiplication(M, N)

f = []

for i=1:10 f(i)=i^2; end
for j=10:-1:1 g(11-j) = j^2; end

f
g

% Note that counting starts from 1 instead of 0
A = randi([-5,5], 4, 4);
A
A(1,1) % First column First row (starts from 1 not 0)
A(2,3)
A(3,3)

% for i=2:4 A(i,:) = -(A(i,1)/A(1,1))*A(1,:) + A(i,:);
A0=[0 0 0; 0 0 0; 0 0 0]
A1=[3 5 -2; 4 1 1; -4 -4 5]
A2=[-16 28 20 -4; 9 -12 -12 12; -15 25 19 -7]
%A3=[6 -3 3 -6; 2 -1 1 -2; 4 -2 2 -4]

% Q1
rref(A1)
rref(A2)
rref(A3)

% Q2
rank(A0)
rank(A1)
rank(A2)
rank(A3)

% Q3
% What is the relationship of mxn matrix to the rank of the matrix?
% The rank of a matrix can between 0 to m

% Q4
% To guarantee that x = 0 is the only solution in Ax=0, the rank of an mx
% matrix should be m
% This is because if it was less than m, there would be a free variable,
% Making it possible to have an infinite amount of solutions

% Q5

