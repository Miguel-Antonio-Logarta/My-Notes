#include <iostream>
#include <vector>
#include <unordered_set>
#include <limits.h>


std::unordered_set<int> find3Largest(std::vector<int> a, int n) {
    // Brute force solution
    // max1 > max2 > max3
    int max1 = INT_MIN;
    int max2 = INT_MIN;
    int max3 = INT_MIN;

    std::unordered_set<int> maximums;

    for (int& number : a) {
        if (number > max1) {
            max3 = max2;
            max2 = max1;
            max1 = number;
        }
        else if (number > max2 && number < max1) {
            max3 = max2;
            max2 = number;
        }
        else if (number > max3 && number < max2) {
            max3 = number;
        }
    }

    std::cout << max1 << " " << max2 << " " << max3;
    maximums.insert(max1);
    maximums.insert(max2);
    maximums.insert(max3);

    return maximums;
}

/*
    Use something similar to a queue data structure, if the size becomes
    too big, pop it out one by one
*/
std::vector<int> reduce(std::vector<int> a, int n) {
    // Brute Force solution
    // Go through every element
        // Remove number and shift everything forward
    std::vector<int> newVec;
    std::unordered_set<int> maximums = find3Largest(a, n);
    // for ()
    for (int& number : a) {
        if (maximums.find(number) == maximums.end()) {
            newVec.push_back(number);
        }
    }
    return newVec;


    // Optimal solution
    // Go through every element
        // After every n deletions, we shift the next object forward by n 
}

// std::unordered_set<int> findNLargest(std::vector<int> a, int n, int m) {
//     /*
//         Finds top m largest integers of the array of size n.
//         for example, if n is 3, it will find the top 3 largest numbers in the array a
//     */

//    /*
//     Aim for O(n)
//     (9,1,1,6,7,1,2,3,3,5,6,6,6,6,7,9)

//     9
//     9 1
//         1 9
//     1 9 6
//         1 6 9
//     1 6 9 7
//         1 6 7 9 (len > 3. pop front)
//         6 7 9
//         9 7 6

//     // Maybe it might be better if greatest is first?
//    */

//     std::vector<int> largest = {};

    
//     for (int& number : a) {

//     }
// }


int main() {
    std::vector<int> numbers = { 9,1,1,6,7,1,2,3,3,5,6,6,6,6,7,9 };
    std::vector<int> reduced = reduce(numbers, numbers.size());
    std::cout << std::endl;
    
    for (int& number : reduced) {
        std::cout << number << " ";
    }
    std::cout << std::endl;
    return 0;
}