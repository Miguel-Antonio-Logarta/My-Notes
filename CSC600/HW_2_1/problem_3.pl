% THIS SOLUTION WAS TAKEN FROM THE WEBBER CHAPTER 22 SLIDES

/*
    nocheck(X/Y,L) takes a queen X/Y and a list
    of queens. We succeed if and only if the X/Y
    queen holds none of the others in check.
*/
nocheck(_, []).
nocheck(X / Y, [X1/Y1 | Rest]) :-
    X =\= X1,
    Y =\= Y1,
    abs(Y1 - Y) =\= abs(X1 - X),
    nocheck(X/Y, Rest).

/*
    legal(L) succeeds if L is a legal placement of
    queens: all coordinates in range and no queen
    in check.
*/
legal([]).
legal([X / Y | Rest]) :-
    legal(Rest),
    member(X, [1,2,3,4,5,6,7,8]),
    member(Y, [1,2,3,4,5,6,7,8]),
    nocheck(X / Y, Rest).

% Example
% Define an X that holds a list of N queens, then call Legal(X)
% To find all possible configurations of a 8x8 chessboard with 8 queens
% ?- X = [_,_,_,_,_,_,_,_], legal(X).
% X = [8/4, 7/2, 6/7, 5/3, 4/6, 3/8, 2/5, 1/1].

% ?- X = [_,_,_], legal(X).
% X = [3/5, 2/3, 1/1] 