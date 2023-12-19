/*
readfile.cpp
Reads a file filled with integers and doubles and outputs values into separate files called doubles.txt
and ints.txt

Group members:
    Miguel Logarta
    Brahamjyot Kaur
    Chen Yi Chang
*/

#include <iostream>
#include <fstream>
#include <vector>
#include <string>

using namespace std;

bool isWhole(double val) {
    return val == (int)val;
}

int main() {
    const string inputFileName = "input.txt";
    const string outputDoubleFileName = "doubles.txt";
    const string outputIntFileName = "ints.txt";

    vector<double> doubleValues;
    vector<int> intValues;

    // Open the file
    ifstream myFile;
    ofstream outFile;
    myFile.open(inputFileName);
    if (!myFile.is_open()) {
        cout << "Could not find file" << endl;
        return 0;
    }

    // Read from file
    while (!myFile.eof()) {
        double temp;
        myFile >> temp;
        if (isWhole(temp)) {
            intValues.push_back(temp);
        }
        else {
            doubleValues.push_back(temp);
        }
    }

    // Close the file
    myFile.close();

    // Print out the values
    cout << "Integers: ";
    for (int& num : intValues) {
        cout << num << " ";
    }
    cout << endl;

    cout << "Doubles: ";
    for (double& num : doubleValues) {
        cout << num << " ";
    }
    cout << endl;

    // Output to output files
    // Put ints into intFile
    outFile.open(outputIntFileName);
    if (!outFile.is_open()) {
        cout << "Cannot write to file" << endl;
        return 0;
    }

    for (int& num : intValues) {
        outFile << num << " ";
    }

    outFile.close();

    // Put doubles into doubleFile
    outFile.open(outputDoubleFileName);
    if (!outFile.is_open()) {
        cout << "Cannot write to file" << endl;
    }

    for (double& num : doubleValues) {
        outFile << num << " ";
    }

    outFile.close();

    return 0;
}