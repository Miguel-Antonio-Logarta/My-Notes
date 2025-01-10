% Question 2a -----------------------------------------------------------
function findQnRn(n)
    sprintf("Hilbert matrix of size %d", n)
    A = hilb(n)
    [Q, R] = qr(A)
end

% findQnRn(5)
% findQnRn(10)
% findQnRn(20)

% Question 2b -----------------------------------------------------------
% Gaussian Elimination function from Homework 1. It doesn't have partial 
% pivoting :(
function [A] = myGaussianElimination(mat)
    % Check if the matrix we recieved is an n x (n + 1) matrix
    [rows, cols] = size(mat);
    if cols ~= rows + 1
        disp("Not a n x (n + 1) matrix!");
        return
    end

    % Check if our pivots are non-zero
    for i = 1:rows
        if mat(i, i) == 0
            disp("Not a regular matrix!");
            return
        end
    end

    % Perform Gaussian Elimination
    for i = 1:cols - 1
        for j = i + 1:rows
            % Calculate scale, skip if row is already zero.
            if mat(j, i) == 0
                continue
            end
            scale = (mat(i, i) / mat(j, i)) * -1;
            
            % Scale the entire row, then add previous row to current row
            for k = 1:cols
                mat(j, k) = (scale * mat(j, k)) + mat(i, k);
            end
        end
    end
    
    A = mat;
end

function [x] = myBackwardSubtitution(U, c)
    % Check if the matrix U is an n x n matrix
    % Check if the matrix c is an n x 1 matrix
    [Urows, Ucols] = size(U);
    [crows, ccols] = size(c);
    solution = zeros(crows, 1);

    if Urows ~= Ucols || crows ~= Urows || ccols ~= 1
        disp("U must be an n x n matrix and c must be n x 1 matrix")
        return
    end

    % Begin backwards subtitution
    % Start from the last row
    solution(crows, 1) = c(crows, 1) / U(Urows, Ucols);
    
    % Continue for rest of the rows
    for i = Urows - 1:-1:1
        % Copy value from c
        solution(i, 1) = c(i, 1);
        
        % Isolate unknown variable by subtracting the other values first
        % We might be off by one, so just watch out for that
        for j = Ucols:-1:i + 1
            solution(i, 1) = solution(i, 1) - (U(i, j) * solution(j, 1));
        end

        % Divide to get the unknown variable
        solution(i, 1) = solution(i, 1) / U(i, i);
    end

    x = solution;
end

function question2b(n)
    sprintf("Hilbert matrix of size n = %d", n)

    % Make a column vector of size n
    x = zeros(n, 1);
    for i=1:n
        x(i,1) = (-1)^i*(i/(i+1));
    end

    % Compute b^*=H_nx^*
    H_n = hilb(n);
    b = H_n*x;
    
    % Solve H_nx=b^* (Gaussian Elimination)
    % sol_1 = myLinearSolution(H_n, b)
    eliminated = myGaussianElimination([H_n, b]);
    [Hcols, Hrows] = size(H_n);
    backwardsub = myBackwardSubtitution(eliminated(:, 1:Hcols), eliminated(:, Hcols+1));
    gauss = backwardsub;
    gauss

    % Solve H_nx=b^* (QR Factorization)
    % H_nx = b -> QRx = b
    [Q, R] = qr(H_n);
    
    % QRx = b -> Rx = Q^Tb
    % First compute Q^T * b ' operator transposes Q
    Qt_b = Q' * b;
    
    % Since we already have R, solve x = Q^Tb * R^-1 (solution_2)
    % R is upper triangular
    qrfactor = R \ Qt_b;
    qrfactor
    
    x
end

question2b(5)
question2b(10)
question2b(20)
question2b(30)

% Question 3
function myHouseholder(v, w)
    if length(v) ~= length(w)
    % v_hat = v / norm(v);
    % w_hat = w / norm(w);
    u = v - w;
    u = u / norm(u)
    % u = (v - w) / norm(v - w);
    H = eye(length(v)) - 2 * (u * u');
    
    [H * w v]
    [H * v w]
end

% x1 = randi(100, 4, 1)
% x2 = randi(100, 4, 1)
% x3 = randi(100, 4, 1)
% v = []
% myHouseholder(x1, x2)
% myHouseholder(x2, x3)
% myHouseholder(x1, x3)
x1 = [1; 0; 0; 0;]
x2 = [0; 1; 0; 0;]
myHouseholder(x1, x2)