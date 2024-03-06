#include <iostream>
#include <vector>

int recursiveBinarySearch(std::vector<int> vec, int low, int high, int target) {
    if (low > high) {
        return -1;
    }

    // Change this to take care of integer overflow
    int mid = (low + high) / 2;

    if (vec[mid] == target) {
        return mid;
    } else if (vec[mid] > target) {
        return recursiveBinarySearch(vec, mid + 1, high, target);
    } else {
        return recursiveBinarySearch(vec, low, mid - 1, target);
    }
}

int iterativeBinarySearch(std::vector<int> vec, int low, int high, int target) {
    int mid;
    while (low < high) {
        mid = (low + high)/2;
        if (vec[mid] == target) {
            return mid;
        } else if (vec[mid] > target) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    return -1;
}

int main() {
    return 0;
}