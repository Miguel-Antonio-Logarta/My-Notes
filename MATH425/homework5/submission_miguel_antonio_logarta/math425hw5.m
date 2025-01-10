

% % Homework Q1
w1 = [1; 2; -1;  0;]
w2 = [0; 1; -2; -1;]
w3 = [1; 0;  3;  2;]
b = [1; 1; 2; -2;]

A = [w1 w2 w3]

% Since w1 w2 and w3 are not orthogonal to each other,
% I will need to orthogonalize it using gram-schmidt process
% v1 = w1
% v2 = w2 - dot(w2, v1)/(norm(v1)^2)*v1
% v3 = w3 - dot(w3, v2)/(norm(v2)^2)*v2 - dot(w3, v1)/(norm(v1)^2)*v1
% A = [v1 v2 v3]
% Note: I don't think this worked. I didn't have to do it.


K = transpose(A)*A
f = transpose(A)*b

x_min = linsolve(K, f)
A*x_min

% Unfortunately, it seems that there seems to be no solution. I found that
% the vectors that span the subspace are linearly dependent, which is 
% preventing me from finding closest point x_min. The reason is beacuse
% K is a singular matrix.

% However, I can pick a subspace in which the vectors are linearly
% independent. I used rref(A) and found that I can discard the last
% vector w3 to get a set of linearly independent vectors.
% K is now a nonsingular matrix which gives me the following result.
rref(A)
A = [w1 w2]
K = transpose(A)*A
f = transpose(A)*b

x_min = linsolve(K, f)
disp("This is the closest point")
A*x_min

disp("This is the minimum distance between the point and the subspace")
d_min = sqrt(norm(b)^2 - transpose(f)*x_min)

% Homework Q2
A = [1 2 -1; 0 -2 3; 1 5 -1; -3 1 1;]
b = [0; 5; 6; 8;]
% rank(A) % Checking if the vectors that make up A is linearly independent. It is

% Strategy 1: Closest Point Solution
K = transpose(A)*A
f = transpose(A)*b

x_min = linsolve(K, f)
disp("Strategy 1")
disp(x_min)

% Strategy 2: Orthonormalize then apply orthogonal projection formula
w1 = A(:, 1)
w2 = A(:, 2)
w3 = A(:, 3)

% Orthogonalize
v1 = w1
v2 = w2 - dot(w2, v1)/(norm(v1)^2)*v1
v3 = w3 - dot(w3, v2)/(norm(v2)^2)*v2 - dot(w3, v1)/(norm(v1)^2)*v1

% Normalize
u1 = v1 / norm(v1)
u2 = v2 / norm(v2)
u3 = v3 / norm(v3)

% Orthogonal projection formula
% w = c1u1 + ... + c_i*u_i where c_i = <v, u_i>, i = 1 ... n

% % Formula 1
x1 = dot(b, u1)*u1
x2 = dot(b, u2)*u2
x3 = dot(b, u3)*u3
x_min = x1 + x2 + x3
disp("Strategy 2")
disp(x_min)

% Homework Q3
A = [1 1989;
     1 1990;
     1 1991;
     1 1992;
     1 1993;
     1 1994;
     1 1995;
     1 1996;
     1 1997;
     1 1998;
     1 1999;]

y = [86.4;
       89.8;
       92.8;
       96.0;
       99.6;
       103.1;
       106.3;
       109.5;
       113.3;
       120.0;
       129.5;]

% Using least squares method to find the line of best fit
K = transpose(A)*A
f = transpose(A)*y
x_min = K\f

% Line of best fit is of the form y = mx + b
b = x_min(1)
m = x_min(2)

% Predict the housing price in 2005 and 2010 assuming trend continues
price_2005 = m*2005 + b % 147.4
price_2010 = m*2010 + b % 167.0

% Question 4a
function y = squared(x)
    y = x^2;
end

f = zeros(8, 1)
for j=0:7
    disp(j)
    f(j+1, 1) = squared(j*2*pi/8);
end
f % f is stored like [f0; f2; f3; ...; f7;]

% Question 4b
% w = zeros(8, 1)
% for k=0:7
%     % zeta k n = e^(k*i*2pi/n) k = 0, ..., n - 1
%     % n is 8.
%     w(k+1, 1) = exp(k*1i*2*pi/8)
% end
% k
% w = []
% for k=0:7
%     w_k = zeros(8, 1);
%     for kk=0:7
%         w_k(kk+1, 1) = exp(kk*1i*2*pi/8);
%     end
%     w = [w w_k]
% end
% w
w = []
for k=0:7
    w_k = []
    for kk=0:7
        value = exp((kk*k)*1i*2*pi/8)
        w_k = [w_k; value]
    end
    w = [w w_k]
end

% values inside w_k is stored like [zeta00_8; zeta01_8; ...; zeta0k_8;]
% w is stored like [w1 w2 w3 ... w7]
w 

% Question 4c
c = zeros(8, 1)
for k=0:7
    % disp("Here is f")
    % disp(f) 
    % disp("Here is w_k")
    % disp(w(:, k+1))
    c(k+1, 1) = dot(w(:, k+1), f)*(1/8) % dot(w, v)
end

c % c is stored like [c1; c2; c3; c4...]

% Question 4d
% The real terms are p_1(x) = 10.7949-0.3613cos(1x)
% -5.9568sin(1x)-1.8506cos(2x)-2.4674sin(2x)-2.1061cos(3x)-1.0220sin(3x)
% -2.1590cos(4x)-2.1061cos(5x)-1.0220sin(5x)-1.8506cos(6x)-2.4674sin(6x)
% -0.3613cos(7x)-5.9568sin(7x)

% Question 4e
function y = og_function(x)
    y = x^2;
end

function y = reconstructedFunction(x)
    y = 10.7949-0.3613*cos(1*x)-5.9568*sin(1*x)-1.8506*cos(2*x)-2.4674*sin(2*x)-2.1061*cos(3*x)-1.0220*sin(3*x)-2.1590*cos(4*x)-2.1061*cos(5*x)-1.0220*sin(5*x)-1.8506*cos(6*x)-2.4674*sin(6*x)-0.3613*cos(7*x)-5.9568*sin(7*x);
end

fplot(@og_function, [0, 2*pi])
hold on;
fplot(@reconstructedFunction, [0, 2*pi])
hold off;