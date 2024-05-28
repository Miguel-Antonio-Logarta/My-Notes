%! FACTS SECTION
m([ethan, lucas, max, bill, dante, raymond, harold]).
f([olivia, haley, ivy, gloria, sophie, ember, mildred, dorothy]).

family([harold, dorothy, [raymond]]).
family([raymond, mildred, [gloria, dante]]).
family([dante, ember, [ivy]]).

%! Some Backstory: 
%! Bill and Sophie married young and had two children
%! Unfortunately, Sophie died early on, due to complications with her second childbirth. 
%! Bill was devasted, but managed to find love again. He remarried to Gloria 
%! and had three more children. The children all get along with each other. :D
family([bill, sophie, [haley, max]]).
family([bill, gloria, [ethan, olivia, lucas]]).


%! RULES SECTION
male(X) :- m(List), member(X, List).
% Examples:
% ?- male(dante).
% true 
% ?- male(ember).
% false.

female(X) :- f(List), member(X, List).
% Examples:
% ?- female(ember).
% true 
% ?- female(dante).
% false.

%! They're a parent if the family that the child belongs in has that parent's name
parent(P, C) :- 
    family([P1, P2 | ChildrenList]), 
    member(P, [P1, P2]), 
    flatten(ChildrenList, ChildrenFlat),
    member(C, ChildrenFlat).
% Examples:
% ?- parent(bill, ethan).
% true 
% ?- parent(dante, ethan).
% false.

father(X, Y) :- male(X), parent(X, Y).
% Examples:
% ?- father(bill, ethan).
% true 
% ?- father(gloria, ethan).
% false.

mother(X, Y) :- female(X), parent(X, Y).
% Examples:
% ?- mother(gloria, ethan).
% true 
% ?- mother(bill, ethan).
% false.

%! Checking siblings have one parent in common (step-siblings are included)
siblings1(S1, S2) :- parent(P1, S1), parent(P1, S2), not(S1=S2).
% Examples:
% ?- siblings1(ethan, haley).
% true .
% ?- siblings1(ethan, ivy).
% false.

%! Checking if siblings have two parents in common (step-siblings are not included)
siblings2(S1, S2) :- 
    parent(P1, S1), 
    parent(P1, S2), 
    parent(P2, S1), 
    parent(P2, S2), 
    not(S1=S2),
    not(P1=P2).
% Examples:
% ?- siblings2(ethan, olivia).
% true .
% ?- siblings2(ethan, haley).
% false.

%! S2 is a brother of S1 of S1 if they are male and a sibling (step-siblings included) 
brother1(S1, S2) :- male(S2), siblings1(S1, S2).

%! S2 is a brother of S1 of S1 if they are male and a sibling (step-siblings not included) 
brother2(S1, S2) :- male(S2), siblings2(S1, S2).

%! S2 is a sister of S1 of S1 if they are female and a sibling (step-siblings included) 
sister1(S1, S2) :- female(S2), siblings1(S1, S2).

%! S2 is a sister of S1 of S1 if they are female and a sibling (step-siblings not included) 
sister2(S1, S2) :- female(S2), siblings2(S1, S2).

%! C1 and C2 are cousins if they have a parent that happen to be sibling (cannot be the same parent) 
cousins(C1, C2) :- parent(P1, C1), parent(P2, C2), siblings1(P1, P2), not(P1=P2).

uncle(U, C) :- 
    male(U), 
    parent(P, C), 
    siblings1(P, U), 
    not(parent(U, C)).

aunt(U, C) :- 
    female(U), 
    parent(P, C), 
    siblings1(P, U), 
    not(parent(U, C)).

grandchild(GC, GP) :- parent(GP, P), parent(P, GC).

grandson(GC, GP) :- male(GC), grandchild(GC, GP).

granddaughter(GC, GP) :- female(GC), grandchild(GC, GP).

greatgrandparent(GGP, GGC) :- 
    parent(GGP, GP), 
    parent(GP, P), 
    parent(P, GGC).


ancestor(A, D) :- parent(A,D).
ancestor(A, D) :- parent(A, D1), ancestor(D1, D).