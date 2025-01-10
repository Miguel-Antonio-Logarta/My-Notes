% MATH 425 Homework3matlab.m by Miguel Antonio Logarta

% Question 2
augmented_matrix = [1 0 2 3; 2 -1 0 0; 0 3 1 -1; 1 0 -1 -2;]
rref(augmented_matrix)

% Yes based on the matrix achieved from rref(augmented_matrix), 
% it is a linear combination since there are scalars c1, c2, c3, c4 such
% that v = c1v1 + c2v2 + c3v3 + c4v4. We can clearly see from the result of
% rref that there is no zero row. All the vectors span the entirety of R3
% which also captures our vector which is also in R3.

% Question 3a
v1 = [1; 0; 2;];
v2 = [3; -1; 1;];
v3 = [2; -1; -1;];
v4 = [1; 0; 2;];
V = [v1 v2 v3 v4]
rref(V)

% Question 4a
function [B] = myGS(A)
    [m, n] = size(A);
    if rank(A) ~= n || n > m
        disp("Can't find orthonormal basis for matrix")
    end
    
    % Set v1 = w1
    % B holds our V vectors, A holds our W vectors
    B(:,1) = A(:,1)
    
    for i=2:n
        w = A(:,i);
        vk = w;
        for j=2:i
            v_prev = B(:,j-1);
            vk = vk - dot(w,v_prev)/dot(v_prev,v_prev) * v_prev;
            
        end
        B(:,i) = vk
    end
end

% Question 4b
w1=[1;0;1;0];
w2=[0;1;0;-1];
w3=[1;0;0;1];
w4=[1;1;1;1];
gs1_input = [w1 w2 w3 w4]
gs1_orthonormal = myGS(gs1_input)
