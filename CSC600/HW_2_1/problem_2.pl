% ---- membership testing ---- 
my_member(X, [H | _]) :- X=H.
my_member(X, [H | T]) :- X=H, my_member(X, T).
% Example:
% ?- my_member(a, [a,b,c,d]).
% true .
% ?- my_member(e, [a,b,c,d]).
% false.

% ---- first element ----
first_element([H | _], X) :- X=H.
% Example:
% ?- first_element([a,b,c,d], X).
% X = a.

% ---- last element ----
% Fact says that X is a list of one element
% Recursively traverse tail of a list, until tail is a list of one element
last_element([X], X).
last_element([_ | T], X) :- last_element(T, X).
% Example:
% ?- last_element([a,b,c,d], X).
% X = d ;

% ---- two adjacent elements ----
two_adjacent([X, Y | _], X, Y).
two_adjacent([_ | T], X, Y) :- two_adjacent(T, X, Y).
% Example:
% ?- two_adjacent([a,b,c,d,e,f], c, d).
% true .
% ?- two_adjacent([a,b,c,d,e,f], X, Y).
% X = a,
% Y = b ;
% X = b,
% Y = c ;
% X = c,
% Y = d ;
% X = d,
% Y = e ;
% X = e,
% Y = f ;

% ---- three adjacent elements ----
three_adjacent([X, Y, Z | _], X, Y, Z).
three_adjacent([_ | T], X, Y, Z) :- three_adjacent(T, X, Y, Z).
% Example:
% ?- three_adjacent([a,b,c,d,e,f], X, Y, Z).
% X = a,
% Y = b,
% Z = c ;
% X = b,
% Y = c,
% Z = d ;
% X = c,
% Y = d,
% Z = e ;
% X = d,
% Y = e,
% Z = f ;

% ---- append list1 to list2 producing list3 ----
% Go to the end of list1 recursively (until list is empty)
% Then the empty list will become L2 and is stored in L3
merge_lists([], X, X).
merge_lists([X | L1], L2, [X | L3]) :- merge_lists(L1, L2, L3).
% Example:
% ?- merge_lists([a,b,c,d],[e,f,g,h], X).
% X = [a, b, c, d, e, f, g, h].


% ---- delete element from a list ----
% my_delete(L1, D, L2)
% Removes element D from L1. The result is stored in L2

% Base case: If the list is empty, just return nothing
my_delete([], _, []).

% If the head=character to be removed, then just return the tail
my_delete([D | T], D, T2) :- my_delete(T, D, T2).

% Else if the head is not the character to be removed, keep traversing the list
my_delete([H | T], D, [H | T2]) :- H \= D, my_delete(T, D, T2).

% Example:
% ?- my_delete([a,b,c,d], a, X).
% X = [b, c, d] ;

% ---- append element to a list ----
% my_append(List1, Element, List2). List2 is the result after appending Element to List1

% Base case: If list is empty, create a list with that element in it
my_append([], X, [X]).

% Traverse to end of the list
my_append([H | T], X, [H | T2]) :- my_append(T, X, T2).

% Example
% ?- my_append([a,b,c], d, X).
% X = [a, b, c, d].

% ---- insert element in a list ----



% ---- compute the length of the list ----

% Base case: Empty list has length of 0
my_length([], 0).

% Traverse list, add 1 to N after every recursion 
my_length([_ | T], N) :- my_length(T, N2), N is N2+1.

% Example
% ?- my_length([a,b,c,d], X).
% X = 4.

% ---- reverse a list ----
% Need to use an accumulator for efficiency

% my_reverse/2 will initialize an accumulator that builds the reversed list
my_reverse(L, R) :- my_reverse(L, [], R).

% Base case: original list is empty, accumulator contains reversed list. Return that list
my_reverse([], R, R).

% Put the head of the first list as the head of the second list. Accumulator will build
% The list backwards as we recurse through the original list
my_reverse([H | T], C, R) :- my_reverse(T, [H | C], R).

% Example:
% ?- my_reverse([a,b,c,d], X).
% X = [d, c, b, a].


% ---- check whether a list is a palindrome ----
palindrome(L) :- my_reverse(L, L).
% Example:
% ?- palindrome([a,b,b,a]).
% true.
% ?- palindrome([a,b,a]).
% true.
% ?- palindrome([a,b,c,d]).
% false.

% ---- display a list ----
display_list(L) :- write(L).
% Example:
% ?- display_list([a,b,c,d]).
% [a,b,c,d]
