/********************************************************************************
 *		MERGE
 *		author(s): Miguel Antonio Logarta
 *		citations:
 *
 *		goal: Read in two files of words into two vectors. Merge the two vectors
 *			  into one vector, keeping the order and discarding duplicates.
 *			  Output to a new file, being careful not to overwrite an existing file.
 *		overview: Reads two dictionaries, iterates through both of them, inserts each
 *                  word in alphabetical order while getting rid of duplicates. Finally,
 *                  prints out words to console.
 *
 *
 *
 *
 ********************************************************************************/


#include <iostream> 
#include <vector>
#include <fstream>
#include <string>


#define NEW_DICT_FILE "Merged Dictionary.txt"

using namespace std;

 /* These two functions hard-code word lists for stage 1
  * They will be replaced in later stages of development */
vector<string> hardCodeDict1();
vector<string> hardCodeDict2();
vector<string> mergeDict(vector<string>& dict1, vector<string>& dict2);
void outputDict(vector<string>& dict);

int main()
{
    vector<string> dict1 = hardCodeDict1();
    vector<string> dict2 = hardCodeDict2();
    vector<string> newDict = mergeDict(dict1, dict2);

    outputDict(newDict);

    return 0;
}



vector<string> hardCodeDict1() {
    vector<string> dict;
    string temp[] = { "airy", "aisle", "aisles", "ajar", "akimbo", "akin", "hooray", "juveniles",
                    "juxtapose", "knowledges", "known", "president",  "sandwich", "tries", "trifle",
                    "tugs", "wrongdoers", "wroth", "wyvern", "xenophon", "xylol", "yodle",
                    "yurt", "zeugma", "ziggurat", "zootomy" };

    for (unsigned int i = 0; i < 26; i++) {
        dict.push_back(temp[i]);
    }

    return dict;
} //end hardCodeDict1()


vector<string> hardCodeDict2() {
    vector<string> dict;
    string temp[] = { "aback", "abased", "acknowledgers", "administers", "affair",
                    "aforementioned", "aggrieving", "agitating", "agree", "airlines", "ajar",
                    "alerted", "bond", "bone", "cheap", "cheated", "examiner", "excel",
                    "lewdness", "liberal", "ordered", "president", "sandwich", "swagger",
                    "swarm", "vomit", "yell", "zero", "zodiac", "zoo" };

    for (unsigned int i = 0; i < 30; i++) {
        dict.push_back(temp[i]);
    }

    return dict;
} //end hardCodeDict2()

vector<string> mergeDict(vector<string>& dict1, vector<string>& dict2)
{
    vector<string> newDict;
    unsigned int i = 0;
    unsigned int j = 0;

    // Merge two dictionaries in alphabetical order
    while (i < dict1.size() && j < dict2.size()) {
        if (dict1[i] < dict2[j]) {
            newDict.push_back(dict1[i]);
            i++;
        }
        else if (dict1[i] > dict2[j]) {
            newDict.push_back(dict2[j]);
            j++;
        }
        else {
            // Insert word into new dictionary once, then increment both indexes
            newDict.push_back(dict1[i]);
            i++;
            j++;
        }
    }

    // Merge remaining words from dict1
    while (i < dict1.size()) {
        newDict.push_back(dict1[i]);
        i++;
    }

    // Merge remaining words from dict2
    while (j < dict2.size()) {
        newDict.push_back(dict2[j]);
        j++;
    }

    return newDict;
}

void outputDict(vector<string>& dict)
{
    for (auto& word : dict) {
        cout << word << endl;
    }
}


