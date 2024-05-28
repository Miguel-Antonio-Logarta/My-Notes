/********************************************************************************
 *		DICTIONARY
 *		author(s): Miguel Logarta
 *		citations:
 *
 *		goal: To read a file, and copy all of its contents to a new file
 *
 *
 *		overview: Program opens a file, checks if it is open, reads the file,
 *                  streams words into a vector. Then opens a new output file,
 *                  streams words from the vector into the new output file, then closes it
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
#define NEW_DICT_FILE_PREFIX "dict-"
#define NEW_DICT_FILE_SUFFIX ".txt"

void populateDict(std::string dictFileName, std::vector<std::string>& dict) {
    std::fstream fileInput;
    fileInput.open(dictFileName);

    if (fileInput.is_open()) {
        // Read all words into a vector
        while (!fileInput.eof()) {
            std::string word;
            fileInput >> word;
            dict.push_back(word);
        }
        fileInput.close();
    }
    else {
        std::cout << "Could not open file" << std::endl;
    }
}

void outputDictToFile(std::string dictName, std::vector<std::string>& dict) {
    std::fstream fileOutput;
    std::string outputFileName = NEW_DICT_FILE_PREFIX + dictName + NEW_DICT_FILE_SUFFIX;
    
    fileOutput.open(outputFileName, std::fstream::out);

    if (fileOutput.is_open()) {
        for (std::string& word : dict) {
            fileOutput << word << std::endl;
        }
        fileOutput.close();
    }
    else {
        std::cout << "Could not open file" << outputFileName << std::endl;
    }
}

void insertWord(std::string& word, std::vector<std::string>& dict) {
    std::cout << " Adding word to dictionary..." << std::endl;
    int i = 0;
    
    // What if the word inserted is inserted at the last spot?

    while (i < dict.size()) {
        if (word < dict[i]) {
            dict.insert(dict.begin() + i, word);
            std::cout << "Inserted!";
            break;
        }
        i++;
    }
    
    //
    if (i >= dict.size() - 1) {
        dict.push_back(word);
        std::cout << "Inserted!";
    }
}

bool findWord(std::string& word, std::vector<std::string>& dict) {
    bool found = false;
    int i = 0;

    // Linear search O(n)
    while (i < dict.size()) {
        if (dict[i] == word) {
            found = true;
            break;
        }
        i++;
    }

    if (found) {
        std::cout << "Your word was '" << word << "'."; 
        if (i < dict.size() - 1) {
            std::cout << "The next word would be '" << dict[i+1] << "'." << std::endl;
        }
        else {
            std::cout << " It is the last word in the dictionary." << std::endl;
        }
    }
    else {
        std::cout << "Your word was '" << word << "'. We did not find your word.";
    }

    return found;
}

std::string getWord() {
    std::string returnVal;
    std::cin >> returnVal;
    return returnVal;
}

int main()
{
    std::vector<std::string> dictionary;
    std::string word;

    populateDict(DICT_FILE_NAME, dictionary);

    word = getWord();

    if (word.empty()) {
        std::cout << "User did not enter a word";
    }
    else {
        if (!findWord(word, dictionary)) {
            insertWord(word, dictionary);
        }
    }

    outputDictToFile(word, dictionary);

    return 0;
}