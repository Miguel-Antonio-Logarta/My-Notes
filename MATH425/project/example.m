function [ F ] = eightpoint( pts1, pts2,width,height)
    X = 1:width;
    Y = 1:height;
    [X, Y] = meshgrid(X, Y);
    x0 = [mean(X(:)); mean(Y(:))];
    X = X - x0(1);
    Y = Y - x0(2);
    denom = sqrt(mean(mean(X.^2+Y.^2)));
    N = size(pts1, 1);
    
    %Normalized data
    T = sqrt(2)/denom*[1 0 -x0(1); 0 1 -x0(2); 0 0 denom/sqrt(2)];
    norm_x = T*[pts1(:,1)'; pts1(:,2)'; ones(1, N)];
    norm_x_ = T*[pts2(:,1)';pts2(:,2)'; ones(1, N)];
    x1 = norm_x(1, :)';
    y1= norm_x(2, :)';
    x2 = norm_x_(1, :)';
    y2 = norm_x_(2, :)';
    
    A = [x1.*x2, y1.*x2, x2, ...
           x1.*y2, y1.*y2, y2, ...
           x1,       y1,     ones(N,1)];
    
    % compute the SVD
    [~, ~, V] = svd(A);
    F = reshape(V(:,9), 3, 3)';
    [FU, FS, FV] = svd(F);
    FS(3,3) = 0; %rank 2 constrains
    F = FU*FS*FV';
    
    % rescale fundamental matrix
    F = T' * F * T;
end

toaster1 = imread("toaster1.png");
toaster2 = imread("toaster2.png");

% We picked out 8 manual points from both images
toaster1_features = [
    945 2328 1;
    2435 2583 1;
    1587 2695 1;
    2297 2038 1;
    3358 2046 1;
    2929 3803 1;
    2774 4199 1;
    1391 3821 1;
    2212 4621 1; % Remove this
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
    1676 4458 1; % Remove this
];
toaster1_features = transpose(toaster1_features);
toaster2_features = transpose(toaster2_features);

[F] = eightpoint(toaster1_features(1:2, :), toaster2_features(1:2, :), size(toaster1, 1), size(toaster1, 2))

transpose(toaster2_features(:, 1))*F*toaster1_features(:, 1)

% % Plot the first image
subplot(1, 2, 1); % 1 row, 2 columns, first subplot
imshow(toaster1); % Display the first image
title('Toaster 1');
hold on; % Allow plotting over the image
% 
% % Epipolar lines in first image
[~, n] = size(toaster1_features);
for i=1:n
    % It seems that the line ends up being too small
    F
    toaster1_features
    epipolar_line = F*toaster1_features(:, i)
    % line(epipolar_line)
    % Define the range of x for plotting
    % x_range = [1, size(toaster1_features, 2)]

    % Compute y coordinates using the line equation
    % y1 = -(l(1) * x_range(1) + l(3)) / l(2)
    % y2 = -(l(1) * x_range(2) + l(3)) / l(2)

    s = epipolar_line(1);
    t = epipolar_line(2);
    u = epipolar_line(3);

    x1 = 0
    y1 = -u / t
    x2 = size(toaster1, 2)
    y2 = (-s * x2 - u) / t

    % Plot the line
    plot([x1, x2], [y1, y2], 'r', 'LineWidth', 1.5);
end

% % Plot the second image
subplot(1, 2, 2); % 1 row, 2 columns, second subplot
imshow(toaster2); % Display the secondimage
title('Toaster 2');
hold on; % Allow plotting over the image
% 
% % Epipolar lines in second image
[~, n] = size(toaster2_features);
for i=1:n
    % It seems that the line ends up being too small
    F
    toaster2_features
    epipolar_line = transpose(F)*toaster2_features(:, i)
    % line(epipolar_line)
    % Define the range of x for plotting
    % x_range = [1, size(toaster1_features, 2)]

    % Compute y coordinates using the line equation
    % y1 = -(l(1) * x_range(1) + l(3)) / l(2)
    % y2 = -(l(1) * x_range(2) + l(3)) / l(2)

    s = epipolar_line(1);
    t = epipolar_line(2);
    u = epipolar_line(3);

    x1 = 0
    y1 = -u / t
    x2 = size(toaster2, 2)
    y2 = (-s * x2 - u) / t

    % Plot the line
    plot([x1, x2], [y1, y2], 'r', 'LineWidth', 1.5);
end

