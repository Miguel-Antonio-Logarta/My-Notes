% 5A
ARGB = imread("obama.jpg");
AGray = im2gray(ARGB);
A = im2double(AGray);

% 5B
[P, Sigma, Q] = svd(A);

k = 100;

% Truncate the matrices
Ak = P(:,1:k)*Sigma(1:k,1:k)*Q(:,1:k)';

% Show results
imshow(Ak)

% 5C
% I could predict a good value for k by choosing a higher number, 
% Since k truncates the A to be of rank(k), we end up losing
% some information. 