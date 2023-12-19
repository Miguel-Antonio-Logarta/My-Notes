/********************************************************************************
 *		vDIAMONDS
 *		author(s): 
 *		adapted from: array version from class
 *		other citations:
 *
 *		goal: Output a diamond of user determined size – must use a 2D vector.
 *		example: for input of 5	  *       for an input of 6       /\
 *										 ***                             //\\
 *									   *****                           ///\\\
 *										 ***                            \\\///
 *										  *                              \\//
 *                                                              \/
 *		overview:(1)  ...
 ********************************************************************************/


#include <iostream> 
#include <vector>

using namespace std;

// Draw 4 quadrants separately
// 2 functions. Q1 and Q3 will use the same function, but chars are reversed
// Q2 and Q4 will use the same function, but chars are reversed
// Validate user input 0 <= x <= 80
// If odd, use chars 
void printDiamond(vector<vector<char>>& diamondVec) {
	cout << "Entered again!";
	for (vector<char>& row : diamondVec) {
		for (char& cell : row) {
			cout << cell;
		}
		cout << endl;
	}
}

void fillDiamond(vector<vector<char>> diamond, int width, char backgroundChar, char diamondChar) {
	cout << "Entered1";

	if (width % 2 == 0) {
		// Print an even diamond with slashes
	}
	else {
		// Print an odd diamond with dots.
		// When the width is 1, return a start
		// When the width is at least 3, there should be a start in the middle
		// So left: fill until i = 3/2 = 1. Right: fill from i = 3/2 + (row + 1) + 1
		for (int i = 0; i <= width; i++) {
			cout << i;
			// Left
			for (int j = 0; j < width / 2; j++) {
				diamond[i][j] = '*';
			}

			// Center
			for (int j = width / 2; j < (width / 2) + (i + 1); j++) {
				diamond[i][j] = diamondChar;
			}

			// Right
			for (int j = (width / 2) + (i + 1); j < diamond.size(); j++) {
				diamond[i][j] = '*';
				cout << j << "";
			}
			cout << endl;
		}
	}

	// return diamond;
}

int getUserInput(int low, int high) {
	int output;
	while (true) {
		std::cout << "Enter a number between 1 and 80: ";
		std::cin >> output;
		if (output < low || output > high) {
			std::cout << "The size must be between " << low << " and " << high << "." << endl;
		} else {
			return output;
		}
	}
	return -1;
}

int main() 
{ 
	const int MAX_WIDTH = 80;
	const char BACKGRND_CHAR = '.';
	const char ODD_CHAR = '*';
	int width{ 0 };

	width = getUserInput(1, 80);
	cout << width;
	// vector<vector<char>> diamond(80'')
	vector<vector<char>> diamond(width, vector<char>(width, BACKGRND_CHAR));
	// vector<vector<char>> diamond = fillDiamond(width, BACKGRND_CHAR, ODD_CHAR);
	fillDiamond(diamond, width, BACKGRND_CHAR, ODD_CHAR);

	cout << "Diamond returned";

	printDiamond(diamond);
	
	return 0; 
 } // end main()