#include <iostream>
#include <vector>
using namespace std;

/* Define your functions here */
bool IsVectorMult10(vector<int>& myVec) {
    // Returns if all the numbers are multiples of 10
    for (int& num : myVec) {
        if (num % 10 != 0)
            return false;
    }
    return true;
}

bool IsVectorNoMult10(vector<int>& myVec) {
    // Returns false if all the numbers are not multiples of 10
    for (int& num : myVec) {
        if (num % 10 == 0)
            return false;
    }
    return true;
}

int main() {
    /* Type your code here. */
    int numElements;
    vector<int> inputVec;
    cin >> numElements;

    for (int i = 0; i < numElements; i++) {
        int temp;
        cin >> temp;
        inputVec.push_back(temp);
    }

    // Three values:
    // ALl numbers are mults of 10
    // All numbers are not mults of 10
    // Mixed values (both functions above return false)
    if (IsVectorMult10(inputVec)) {
        cout << "all multiples of 10";
    }
    else if (IsVectorNoMult10(inputVec)) {
        cout << "no multiples of 10";
    } else {
        cout << "mixed values";
    }
    
    cout << endl;
    return 0;
}
