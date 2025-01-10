% Assignment: Homework 1
% By: Miguel Antonio Logarta
% Due: Sep 14, 11:59 PM

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

function [x] = myLinearSolution(A, b)
    % Check if the matrix we recieved is an n x n regular matrix
    [Arows, Acols] = size(A);
    [brows, bcols] = size(b);

    if Arows ~= Acols
        disp("Dimensions of A is not n x n!");
        return 
    end

    if brows ~= Arows
        disp("Dimensions of b does not match matrix A");
        return
    end

    if bcols ~= 1
        disp("Dimensions of b does not match n x 1");
        return
    end
    
    eliminated = myGaussianElimination([A, b]);
    backwardsub = myBackwardSubtitution(eliminated(:, 1:Acols), eliminated(:, Acols+1));
    x = backwardsub;
end

A = [-8 -2 3 1; 1 -2 0 2; -4 -1 3 2;];
disp(linsolve([-8 -2 3; 1 -2 0; -4 -1 3;], [1; 2; 2;])); % Compare linsolve to my solution
x = myLinearSolution([-8 -2 3; 1 -2 0; -4 -1 3;], [1; 2; 2;]);
disp(x);
