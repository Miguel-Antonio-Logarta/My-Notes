#include <string>
#include <vector>
#include <iostream>

using namespace std;

// TODO: Write the partitioning algorithm - pick the middle element as the
//       pivot, compare the values using two index variables l and h (low and high),
//       initialized to the left and right sides of the current elements being sorted,
//       and determine if a swap is necessary
int Partition(vector<string>& userIDs, int i, int k) {
    int l;
    int h;
    int midpoint;
    string pivot;
    string temp;
    bool done;

    /* Pick middle element as pivot */
    midpoint = i + (k - i) / 2;
    pivot = userIDs[midpoint];

    done = false;
    l = i;
    h = k;

    while (!done) {

        /* Increment l while numbers[l] < pivot */
        while (userIDs[l] < pivot) {
            ++l;
        }

        /* Decrement h while pivot < numbers[h] */
        while (pivot < userIDs[h]) {
            --h;
        }

        /* If there are zero or one elements remaining,
         all numbers are partitioned. Return h */
        if (l >= h) {
            done = true;
        }
        else {
            /* Swap numbers[l] and numbers[h],
             update l and h */
            temp = userIDs[l];
            userIDs[l] = userIDs[h];
            userIDs[h] = temp;

            ++l;
            --h;
        }
    }

    return h;
}

// TODO: Write the quicksort algorithm that recursively sorts the low and
//       high partitions
void Quicksort(vector<string>& userIDs, int i, int k) {
    int j;

    /* Base case: If there are 1 or zero elements to sort,
     partition is already sorted */
    if (i >= k) {
        return;
    }

    /* Partition the data within the array. Value j returned
     from partitioning is location of last element in low partition. */
    j = Partition(userIDs, i, k);

    /* Recursively sort low partition (i to j) and
     high partition (j + 1 to k) */
    Quicksort(userIDs, i, j);
    Quicksort(userIDs, j + 1, k);
}

//int main() {
//    vector<string> userIDList;
//    string userID;
//
//    cin >> userID;
//    while (userID != "-1") {
//        userIDList.push_back(userID);
//        cin >> userID;
//    }
//
//    // Initial call to quicksort
//    Quicksort(userIDList, 0, userIDList.size() - 1);
//
//    for (size_t i = 0; i < userIDList.size(); ++i) {
//        cout << userIDList.at(i) << endl;;
//    }
//
//    return 0;
//}