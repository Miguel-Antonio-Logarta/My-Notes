#include <iostream>
#include <vector>

using std::vector;
using std::cout;
using std::cin;
using std::endl;

void isPalindrome(vector<int>& nums) {
    //Prints out no if nums is not a palindrome
    for (int i = 0; i < nums.size() / 2; i++) {
        if (nums[i] != nums[nums.size() - 1 - i]) {
            cout << "no" << endl;
            return;
        }
    }
    cout << "yes" << endl;
}

int main() {
   
    /* Type your code here. */
    int numOfElements;
    int temp;
    vector<int> nums;

    cin >> numOfElements;

    for (int i = 0; i < numOfElements; i++) {
        cin >> temp;
        nums.push_back(temp);
    }

    isPalindrome(nums);

    return 0;
}