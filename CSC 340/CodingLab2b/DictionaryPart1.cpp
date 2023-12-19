/********************************************************************************
 *		DICTIONARY
 *		author(s): 
 *		citations:
 *
 *		goal:  
 *			  
 *			  
 *		overview: 
 *
 *
 *		note: 
 *
 *
 *
 *
 ********************************************************************************/


#include <iostream>
#include <fstream>
#include <vector>

#define DICT_FILE_NAME "dict.txt"
#define NEW_DICT_FILE "dict-updated.txt"

int main() 
{ 
// Read contents of file into vector
   // Output vector's contents onto a new file
   // Check if file exists
    std::vector<std::string> dictionary;
    std::fstream fileInput;
    std::fstream fileOutput;

    fileInput.open(DICT_FILE_NAME);

    if (fileInput.is_open()) {
        // Read all words into a vector
        while (!fileInput.eof()) {
            std::string word;
            fileInput >> word;
            dictionary.push_back(word);
        }
        fileInput.close();
    }
    else {
        std::cout << "Could not open file" << std::endl;
        return -1;
    }

    fileOutput.open(NEW_DICT_FILE, std::fstream::out);

    if (fileOutput.is_open()) {
        for (std::string& word : dictionary) {
            fileOutput << word << " ";
        }
        fileOutput.close();
    }
    else {
        std::cout << "Could not open file" << std::endl;
        return -1;
    }
    
    return 0; 
} 