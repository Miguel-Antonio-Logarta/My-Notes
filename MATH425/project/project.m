% Load the two images
toaster1 = imread("toaster1.png");
toaster2 = imread("toaster2.png");

% We manually picked out 8 point correspondences from both images
toaster1_features = [
    945 2328 1;
    2435 2583 1;
    1587 2695 1;
    2297 2038 1;
    3358 2046 1;
    2929 3803 1;
    2774 4199 1;
    1391 3821 1;
];

toaster2_features = [
    838 2243 1;
    1810 2550 1;
    1178 2587 1;
    2478 2040 1;
    3530 2116 1;
    2652 3855 1;
    2379 4194 1;
    1116 3587 1;
];

% Convert to row major order since \bold{x} = (x, y, 1)^T and \bold{x'} =
% (x', y', 1)^T
toaster1_features = transpose(toaster1_features);
toaster2_features = transpose(toaster2_features);


function draw_features(img1, img2, features1, features2)
    % Plot the first image
    subplot(1, 2, 1); % 1 row, 2 columns, first subplot
    imshow(img1); % Display the first image
    title('Toaster 1');
    hold on; % Allow plotting over the image

    % Plot dots on the first image
    scatter(features1(1, :), features1(2, :), 100, "red", "filled", "o")

    % Plot the second image
    subplot(1, 2, 2); % 1 row, 2 columns, second subplot
    imshow(img2); % Display the second image
    title('Toaster 2');
    hold on; % Allow plotting over the image

    % Plot dots on the second image
    scatter(features2(1, :), features2(2, :), 100, "red", "filled", "o")
end


function [T, scale] = get_normalizing_transformation(pts) 
    [rows, cols] = size(pts);
    isTwoByN = (rows == 2) && (cols > 0);
    if ~isTwoByN
        disp("Not a 2xn matrix of coordinates")
        return 
    end


    % disp("These are the points")
    % disp(pts)

    % Get average distance of points (\bold{x} bar)
    average_distance = mean(pts, 2);
    N = length(pts)
    sum = 0.0;
    
    % disp("This is the average distance for x and y")
    % disp(average_distance)
    
    for i=1:N
        pt_shifted = pts(:, i) - average_distance;
        
        % Since we are getting ||x_i - x_bar||^2, we won't do the square
        % root part. We'll do x^2 + y^2 instead
        % norm = normalize(pt_shifted, 1).^2
        norm = pt_shifted(1, :).^2 + pt_shifted(2, :).^2;
        sum = sum + norm;
    end
   
    scale = sqrt(2*N/sum);

    T = [
        scale 0 (-scale * average_distance(1));
        0 scale (-scale * average_distance(2));
        0 0 1;
    ];
end

function [F] = eight_point_algorithm(pts1, pts2)
    % Normalize Points
    [T] = get_normalizing_transformation(pts1(1:2, :));
    [T2] = get_normalizing_transformation(pts2(1:2, :));
    
    normalized_1 = zeros(3, size(pts1, 2));
    normalized_2 = zeros(3, size(pts2, 2));
    for i=1:length(pts1)
        normalized_1(:, i) = T*pts1(:, i);
    end
    
    for i=1:length(pts2)
        normalized_2(:, i) = T2*pts2(:, i);
    end
  
    % Get the Least Squares Solution (8 point algorithm)
    
    % Solve for Af = 0
    % Construct matrix A
    N = size(normalized_1, 2); % Get number of rows (# of points)
    A = zeros(N, 9); % A will have N rows and 9 columns
    for i = 1:N
        x1 = normalized_1(1, i); 
        y1 = normalized_1(2, i);
        
        x2 = normalized_2(1, i);
        y2 = normalized_2(2, i);

        A(i, :) = [x2*x1 x2*y1 x2 y2*x1 y2*y1 y2 x1 y1 1];
    end

    % Solution f is found in the last column of SVD(A) = UDV^T
    [~, ~, V] = svd(A);
    f = V(:, end);
    
    
    % We need to reshape the 9x1 vector into a 3x3 fundamental matrix
    % Not sure why we transpose F, but without it, the lines are way off.
    F = reshape(f, 3, 3)';
    
    % We need to constrain F to be singular det(F) = 0 and of rank 2
    % We get this constraint by using the SVD of F again
    [U, D, V] = svd(F);
    D(3, 3) = 0; % Set the smallest singular value to 0
    F = U * D * V';
    
    % Denormalize F
    F = T2' * F * T;
    
    % Verify. x'Fx should be close to zero. 
    disp("x'Fx = ")
    disp(transpose(pts2(:, 1))*F*pts1(:, 1))
end

function plotEpipolarLines(F, pts1, pts2, img1, img2) 
    % First image on the left
    subplot(1, 2, 1); % 1 row, 2 columns, first subplot
    imshow(img1); % Display the first image
    title('Left Camera with Epipolar Lines');
    hold on; % Allow plotting over the image
    
    % Draw point correspondences x_i of the first image
    scatter(pts1(1, :), pts1(2, :), 50, "red", "filled", "o")

    % Epipolar lines in first image
    [~, n] = size(pts1);
    for i=1:n
        % Epipolar lines in the first image is l' = F*x_i
        l = F*pts1(:, i);
        
        % line l is of the form l = (s t u)^T. Which corresponds to the
        % homogenous equation sx + ty + uz = 0. We solve for y to get our
        % epipolar line
        s = l(1);
        t = l(2);
        u = l(3);
    
        x1 = 0;
        y1 = -u / t;
        x2 = size(img1, 2);
        y2 = (-s * x2 - u) / t;
    
        % Draw the epipolar line
        plot([x1, x2], [y1, y2], 'r', 'LineWidth', 1.5);
    end
    
    % Second image on the right
    subplot(1, 2, 2); % 1 row, 2 columns, second subplot
    imshow(img2); % Display the second image
    title('Right Camera with Epipolar Lines');

    hold on; % Allow plotting over the image
    
    % Draw point correspondences x_i of the second image
    scatter(pts2(1, :), pts2(2, :), 50, "red", "filled", "o")

    % Epipolar lines in second image
    [~, n] = size(pts2);
    for i=1:n
        % Epipolar lines in the first image is l = F^T*x_i'
        l = transpose(F)*pts2(:, i);
        
        % line l is of the form l = (s t u)^T. Which corresponds to the
        % homogenous equation sx + ty + uz = 0. We solve for y to get our
        % epipolar line. We set z to be 1 since the line is on a 2d image.
        s = l(1);
        t = l(2);
        u = l(3);
    
        x1 = 0;
        y1 = -u / t;
        x2 = size(img2, 2); % Width of the image
        y2 = (-s * x2 - u) / t;
    
        % Draw the epipolar line
        plot([x1, x2], [y1, y2], 'r', 'LineWidth', 1.5);
    end

    hold off;
end

% Main 
% draw_features(toaster1, toaster2, toaster1_features, toaster2_features);
[F] = eight_point_algorithm(toaster1_features, toaster2_features);
plotEpipolarLines(F, toaster1_features, toaster2_features, toaster1, toaster2);