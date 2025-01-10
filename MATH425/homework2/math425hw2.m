
function [U] = myPartialPivot(A)
    [rows, cols] = size(A);
    if cols ~= rows
        disp("Not a n x n matrix!");
        return
    end
    
    row_interchanges = 0;
    for i=1:rows
        % Find the absolute largest value below the pivot
        largest_value = A(i,i);
        largest_value_index = i;

        for m=i+1:rows
            if abs(A(m, i)) > abs(largest_value)
                row_interchanges = row_interchanges + 1;
                largest_value = A(m, i);
                largest_value_index = m;
            end
        end
        
        % Swap rows so that the largest pivot is on top
        if i ~= largest_value_index
            A([i largest_value_index], :) = A([largest_value_index i], :);
        end

        % Subtract rows
        for j = i + 1:rows
            % Calculate scale, skip if row is already zero.
            if A(j, i) == 0
                continue
            end
            scale = (A(i, i) / A(j, i)) * -1;

            % Scale the entire row, then add previous row to current row
            for k = 1:cols
                A(j, k) = (scale * A(j, k)) + A(i, k);
            end
        end
    end
    
    sprintf("Total row interchanges: %d", row_interchanges)
    U = A;
end

function [rank] = myRank(A)
    [rows, cols] = size(A);
    if cols ~= rows
        disp("Not a n x n matrix!");
        return
    end

    % Since we are recieving an n x n matrix, the maximum
    % rank of A is <= n
    A = myPartialPivot(A);

    % Now that A is in an upper triangular matrix, the zero rows should be
    % at the bottom
    rank = rows;
    for i = rows:-1:1
        if A(i,i) ~= 0
            break
        end
        rank = rank - 1;
    end
end

% Compare results myRank(A) is 3, and rank(A) is 3
P = rand(3, 5)
Q = rand(5, 3)
A = P*Q
myRank(A)
rank(A)

% Stricly column diagonally dominant matrix
scdd_matrix = [3 1 1 0; 2 4 1 0; 0 1 5 2; 1 1 3 6]
myRank(scdd_matrix)
