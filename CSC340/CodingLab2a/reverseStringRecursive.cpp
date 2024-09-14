#include <iostream>
using namespace std;

/* TODO: Write recursive ReverseString() function here. */
string ReverseString(string input) {
    if (input.size() <= 1) {
        cout << "Base case reached" << endl;
        return input;
    }
    else {
        // Take the first character and add it to end of ReverseString(input.subtr(1));
        char startChar = input[0];
        string result = ReverseString(input.substr(1)) + startChar;
        cout << "startChar: " << startChar << ", result before call: " << input << ", result after call: " << result << endl;
        return result;
    }
}

int main() {
   string input, result;
   
   getline(cin, input);
   result = ReverseString(input);
   cout << "Reverse of \"" << input << "\" is \"" << result << "\"." << endl;
   
   return 0;
}
