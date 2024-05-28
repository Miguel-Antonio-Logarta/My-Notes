#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main() {
    fstream file;
    string filename;
    cin >> filename;
    
    file.open(filename);

    if (file.is_open()) {

        while (!file.eof()) {
            string category;
            string name;
            string desc;
            string availability;

            getline(file, category, '\t');
            getline(file, name, '\t');
            getline(file, desc, '\t');
            getline(file, availability, '\n');

            if (availability == "Available") {
                cout << name << " (" << category << ") -- " << desc << endl;
            }
        }

        file.close();
    } 
    else {
        cout << "File didn't open" << endl;
    }

    

    return 0;
}