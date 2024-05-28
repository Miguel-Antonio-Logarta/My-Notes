/*
  Program for Question 3: Array Processing (Eliminatino of three largest values)
  We are given an array a of length n. Our task is to write a function reduce(a[], n) that 
  deletes all values that are equal to the three largest numbers in the array.

  For example, if a = { 9,1,1,6,7,1,2,3,3,5,6,6,6,6,7,9 }
    reduce(a, n) should change array a to { 1,1,1,2,3,3,5 }
    the three largest numbers are 6,7, and 9 and were removed from the array

  My solution is optimal because it has a runtime complexity of O(n).
  It does 1 initial pass through the array to find the top 3 largest numbers.
  It then stores these numbers into a set which have a lookup complexity of O(1).

  The algorithm then loops through the array again and checks if the current value
  is equal to one of the 3 largest numbers. If it is, the value is deleted, then the next value
  is shifted forward based on how many times an element in the array has been deleted.
*/

#include <iostream>
#include <vector>
#include <unordered_set>
#include <limits.h>

std::unordered_set<int> find3Largest(std::vector<int> a, int n) {
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

    // Insert into a set
    maximums.insert(max1);
    maximums.insert(max2);
    maximums.insert(max3);

    return maximums;
}

std::vector<int> reduce(std::vector<int> &a, int n) {
    std::unordered_set<int> maximums = find3Largest(a, n);
    int deletions = 0;

    for (int i = 0; i < n; i++) {
        if (maximums.find(a[i]) != maximums.end())  // Maximum found
            deletions++;                   // Value is "deleted"
        else
            a[i-deletions] = a[i];          // If not maximum, shift it forward in the array based on number of deleetions
    }

    a.resize(a.size() - deletions);        // Array is now (n - deletions) shorter 
    
    return a;

}

double sec() {
    return double(clock())/double(CLOCKS_PER_SEC);
}

void timeTestCase() {
    std::vector<int> a = { 9,1,1,6,7,1,2,3,3,5,6,6,6,6,7,9 };

    std::cout << "a = ";
    for (int& value : a) {
        std::cout << value << " ";
    }
    std::cout << "n = " << a.size() << ", reduce(a, n) = ";

    double T1 = sec();
    reduce(a, a.size());
    double T2 = sec();

    for (int& value : a) {
        std::cout << value << " ";
    }
    std::cout << std::endl;


    std::cout << "Run time of reduce(a, n): " << T2 - T1 << "s" << std::endl << std::endl;
}

int main() {
    timeTestCase();
    return 0;
}