#include <iostream>

/*
 * Return the length of the longest sequence of identical numbers
 * in array a of size n
 * */
int maxLen(int a[], int n) {
  int maxLength = 0;
  int currentLength = 1;

  for (int i = 1; i < n; i++) {
    if (a[i] == a[i-1]) {
      currentLength++;
    } else {
      currentLength = 1;
    }

    if (currentLength > maxLength) {
      maxLength = currentLength;
    }
  }
  
  return maxLength;
}

int main() {
  int arr1[13] = { 1,1,1,2,3,3,5,6,6,6,6,7,9 };
  int arr2[10] = { 1,4,5,5,6,6,6,6,6,9 };
  int arr3[15] = { 0,0,0,1,2,3,3,4,4,8,8,8,8,9,9 };
  int arr4[5] = { 1,1,1,2,2 };

  std::cout << maxLen(arr1, 13) << std::endl;
  std::cout << maxLen(arr2, 10) << std::endl;
  std::cout << maxLen(arr3, 15) << std::endl;
  std::cout << maxLen(arr4, 5) << std::endl;

  return 0;
}
