#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int comparisons{ 0 };
int recursions{ 0 };

// Read integers from input and store them in a vector.
// Return the vector.
vector<int> ReadIntegers() {
    int size;
    cin >> size;
    vector<int> integers(size);
    for (int i = 0; i < size; ++i) {               // Read the numbers
        cin >> integers.at(i);
    }
    sort(integers.begin(), integers.end());
    return integers;
}

int BinarySearch(int target, vector<int> integers, int lower, int upper) {
    /* Type your code here. */
    recursions++;
    // Return -1 if element was not found
    if (lower > upper) {
        return -1;
    }
    else {
        int middle = (lower + upper) / 2;

        comparisons++;
        if (target == integers[middle]) {
            return middle;
        }

        comparisons++;
        if (target < integers[middle]) {
            return BinarySearch(target, integers, lower, middle - 1);
        }

        comparisons++;
        if (target > integers[middle]) {
            return BinarySearch(target, integers, middle + 1, upper);
        }
    }
}

int main() {
    int target;
    int index;
    vector<int> integers = ReadIntegers();

    cin >> target;

    index = BinarySearch(target, integers, 0, integers.size() - 1);
    printf("index: %d, recursions: %d, comparisons: %d\n", index, recursions, comparisons);

    return 0;
}
