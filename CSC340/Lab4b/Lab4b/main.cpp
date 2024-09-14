#define FILE_ONE "short_dict.txt"
#define FILE_TWO "short_dict2.txt"
#define FILE_THREE "short_dict3.txt"
#define NEW_DICT_FILE "Updated Dictionary.txt"
#define NEW_DICT_FILE_TWO "Updated Dictionary2.txt"
#define NEW_DICT_FILE_THREE "Updated Dictionary3.txt"

#include <iostream>
#include <string>
#include <vector>
#include <fstream>

using std::cout;
using std::cin;
using std::endl;
using std::string;
using std::vector;

// Node for linked list
struct node {
    string str;
    node* next = nullptr;
    node* prev = nullptr;
};

// Functions that modify the linked list
node* performOperation(node*, int&, int, bool&);
node* createLinkedList(std::vector<string>&);
node* insertNode(node*, string);
node* deleteNode(node*, string);
node* deleteLinkedList(node*);
node* findNode(node*, string);
void swapNodes(node*, node*);
node* bubbleSort(node*);

// Read operations
void printLinkedList(node*);
void printWord(node* node, bool newLine = false);
void printAdjacentWords(node* node, bool newLine = false, bool dots = true);

// User prompts to get input
node* selectDictionary(int&);
node* searchDictionary(node*, bool);
node* deleteInDictionary(node*);
int selectOption();
node* swapWords(node*);
string selectOutputFile(int choice);
void writeToFile(node*, string);

// Functions for creating our dictionary
vector<string> fillVectorFromFile(int choice);

// Functions already made for us
vector<string> fillVector(int choice);

int main() {
    int choice;
    bool stopProgram = false;
    int dictionarySelected;
    node* linkedList = selectDictionary(dictionarySelected);
    while (!stopProgram) {
        choice = selectOption();
        linkedList = performOperation(linkedList, dictionarySelected, choice, stopProgram);
    }
    return 0;
}

node* performOperation(node* head, int& dictionarySelected, int choice, bool& stopProgram)
{
    node* dictionary = head;
    /* Value of choice are mapped to the following procedures:
        (1) Print words
        (2) Find a word
        (3) Find word, insert if found (assumes words are sorted alphabetically)
        (4) Find word, delete if found
        (5) Swap two words
        (6) Sort words (Bubble Sort or Selection Sort)
        (7) Find a word - Binary Search (assumes words are sorted alphabetically)
        (8) Merge two dictionaries (will sort first)
        (9) Write current dictionary to file
    */
    switch (choice) {
    case 1:
        printLinkedList(head);
        break;
    case 2:
        dictionary = searchDictionary(dictionary, false);
        break;
    case 3:
        dictionary = searchDictionary(dictionary, true);
        break;
    case 4:
        dictionary = deleteInDictionary(dictionary);
        break;
    case 5:
        dictionary = swapWords(dictionary);
        break;
    case 6:
        dictionary = bubbleSort(dictionary);
        break;
    case 7:
        cout << "Coming soon!\n";
        break;
    case 8:
        cout << "Coming soon!\n";
        break;
    case 9:
        writeToFile(dictionary, selectOutputFile(dictionarySelected));
        break;
    case 0:
        cout << "Thank you! Bye!";
        stopProgram = true;
        break;
    default:
        stopProgram = true;
        break;
    }

    return dictionary;
}

node* createLinkedList(std::vector<string>& words) {
    node* head = new node;
    node* tail = head;

    for (string& word : words) {
        // Attach a new node to the end of the linked list
        node* newNode = new node;
        newNode->str = word;
        newNode->prev = tail;

        // Update the tail
        tail->next = newNode;
        tail = tail->next;
    }

    head = head->next;
    head->prev = nullptr;

    return head;
}

node* insertNode(node* head, string word) {
    // Creates a new node and inserts it into the linked list. Inserts word in alphabetical order

    // Traverse the linked list
    node* currNode = head;

    // Move forward if not end of linked list and current string is less than the current word
    while (currNode->next != nullptr && currNode->next->str < word) {
        currNode = currNode->next;
    }

    if (currNode->prev == nullptr) {
        // Case 1: Insert at front
        node* newNode = new node;
        newNode->str = word;

        currNode->prev = newNode;
        newNode->next = currNode;

        return newNode;
    }
    else {
        // Case 2: Insert in between nodes or at end of linked list
        node* newNode = new node;
        newNode->str = word;

        newNode->next = currNode->next;
        newNode->prev = currNode;
        currNode->next = newNode;

        if (newNode->next != nullptr) {
            newNode->next->prev = newNode;
        }

        return head;
    }
}

node* deleteNode(node* head, string word) {
    // Deletes a node that contains the specified word from the linked list
    node* curr = head;
    while (curr != nullptr && curr->str != word) {
        curr = curr->next;
    }

    if (curr == nullptr) {
        // Return if we got an empty linked list
        return head;
    }

    if (curr->prev == nullptr) {
        // Case 1: We delete the head node
        curr = curr->next;
        curr->prev = nullptr;
        return curr;
    }
    else if (curr->str == word) {
        // Case 2: Delete in middle or at end of linked list
        curr->prev->next = curr->next;

        if (curr->next != nullptr) {
            curr->next->prev = curr->prev;
        }

        curr = nullptr;
        return head;
    }
    else {
        // Case 3: No match found
        return head;
    }
}

node* deleteLinkedList(node* head) {
    node* curr = head;
    while (curr != nullptr) {
        delete curr->prev;
        curr = curr->next;
    }
    return nullptr;
}

node* findNode(node* head, string word) {
    // Returns address of node that matches the word. Else, it returns nullptr
    node* curr = head;
    while (curr != nullptr && curr->str != word) {
        curr = curr->next;
    }
    return curr;
}

void swapNodes(node* p1, node* p2) {
    if (p1 == nullptr || p2 == nullptr) {
        return;
    }

    // Swaps contents of both nodes
    string tempword = p1->str;
    p1->str = p2->str;
    p2->str = tempword;
}

node* bubbleSort(node* head) {
    // Find number of nodes in the list
    int count = 0;
    node* curr = head;
    while (curr != nullptr) {
        count++;
        curr = curr->next;
    }

    cout << "sorting...\n";

    int swaps = 0;
    int passes = 0;

    // Begin bubble sort
    int i = 0;
    int j = 0;
    node* p1 = head;
    node* p2 = head;
    bool swapped = false;
    while (i < count - 1) {
        p2 = head;
        j = 0;
        swaps = 0;
        swapped = false;
        while (j < count - i - 1) {
            if (p2->str > p2->next->str) {
                swapNodes(p2, p2->next);
                swaps++;
                swapped = true;
            }
            p2 = p2->next;
            j++;
        }
        p1 = p1->next;
        i++;
        passes++;

        cout << "***swaps = " << swaps << ", count = " << passes << "***\n";
        curr = head;
        while (curr != nullptr) {
            cout << curr->str << "\n";
            curr = curr->next;
        }

        if (swapped == false) {
            break;
        }
    }
    
    cout << "...Done!\n";
    return head;
}

void printLinkedList(node* head) {
    node* curr = head;
    while (curr != nullptr) {
        cout << curr->str << endl;
        curr = curr->next;
    }
}

node* selectDictionary(int& dictionarySelected) {
    int choice;
    vector<string> vec;
    node* linkedList;

    cout << "Which Dictionary should be opened? Enter \"1\", \"2\", or \"3\": ";
    cin >> choice;
    cout << endl;

    dictionarySelected = choice;
    vec = fillVectorFromFile(choice);
    linkedList = createLinkedList(vec);

    return linkedList;
}

node* deleteInDictionary(node* head)
{
    string word;
    cout << "Enter a word to delete from the chosen Dictionary: ";
    cin >> word;
    cout << endl;

    node* found = findNode(head, word);

    if (found) {
        cout << "Your word was '" << word << "'.";
        cout << "The word '" << word << "' has been deleted." << endl;

        printAdjacentWords(found, true, false);

        head = deleteNode(head, found->str);
    }
    else {
        cout << "Your word was '" << word << "'. We did not find your word." << endl;
    }

    return head;
}

int selectOption()
{
    int choice = 0;
    cout << "\n--------------------------------------------\n"
        << "Options menu: \n"
        << "(1) Print words\n"
        << "(2) Find a word\n"
        << "(3) Find word, insert if found (assumes words are sorted alphabetically)\n"
        << "(4) Find word, delete if found \n"
        << "(5) Swap two words\n"
        << "(6) Sort words (Bubble Sort or Selection Sort)\n"
        << "(7) Find a word - Binary Search (assumes words are sorted alphabetically)\n"
        << "(8) Merge two dictionaries (will sort first)\n"
        << "(9) Write current dictionary to file\n"
        << "Enter a number from 1 to 9, or 0 to exit: ";
    cin >> choice;
    return choice;
}

node* swapWords(node* head)
{
    // Find the first word
    string findWord;
    cout << "Enter a word to search for in the chosen Dictionary:";
    cout << endl;
    cin >> findWord;
    node* firstFound = findNode(head, findWord);

    if (firstFound) {
        printWord(firstFound, true);
        printAdjacentWords(firstFound, true, false);
    }
    else {
        cout << "Your word was '" << findWord << "'. We did not find your word.\n";
        cout << "Can't swap - word not found!\n";
        return head;
    }

    // Find second word
    cout << "Enter a word to search for in the chosen Dictionary:";
    cin >> findWord;
    cout << endl;

    node* secondFound = findNode(head, findWord);

    if (secondFound) {
        printWord(secondFound, true);
        printAdjacentWords(secondFound, true, false);
    }
    else {
        cout << "Your word was '" << findWord << "'. We did not find your word.\n";
        cout << "Can't swap - word not found!\n";
        return head;
    }

    // Check if they are the same word
    if (firstFound == secondFound) {
        cout << "Hey! Those are the same word!\n";
        return head;
    }

    swapNodes(firstFound, secondFound);
    cout << "Words '" << secondFound->str << "' and '" << firstFound->str << "' have been swapped!\n";
    return head;
}

string selectOutputFile(int choice)
{
    string outputFilename;
    switch (choice) {
    case 1:
        outputFilename = NEW_DICT_FILE;
        break;
    case 2:
        outputFilename = NEW_DICT_FILE_TWO;
        break;
    default:
        outputFilename = NEW_DICT_FILE_THREE;
        break;
    }
    return outputFilename;
}

void writeToFile(node* head, string filename) {
    cout << "Writing to file...\n";
    
    // Check if the file exists
    std::ifstream inFile(filename);
    if (inFile.good()) {
        cout << "Error! File " << filename << " already exists.\n";
        inFile.close();
        return;
    }
    inFile.close();

    // Write to file
    std::ofstream outFile;
    outFile.open(filename);

    if (!outFile.is_open()) {
        cout << "Error! New dictionary " << filename << " failed to open.\n";
    }
    else {
        node* curr = head;
        while (curr != nullptr) {
            outFile << curr->str << "\n";
            curr = curr->next;
        }
        cout << "...Done!\n";
    }
    outFile.close();
}

node* searchDictionary(node* head, bool insert) {
    // Searches for a word in the dictionary, if not found, it inserts the word and returns head of modified dictionary
    // If word was found, returns head of dictionary.
    string findWord;
    node* found;

    cout << "Enter a word to search for in the chosen Dictionary: ";
    cin >> findWord;
    cout << endl;

    found = findNode(head, findWord);


    if (found) {
        printWord(found);
        printAdjacentWords(found, true, false);
        return head;
    }
    else {
        cout << "Your word was '" << findWord << "'. We did not find your word.\n";

        if (insert) {
            cout << " Adding word to dictionary..." << endl;
            node* newHead = insertNode(head, findWord);
            cout << "Inserted!" << endl;

            node* insertedWord = findNode(newHead, findWord);

            printAdjacentWords(insertedWord, true, false);
            return newHead;
        }
        else {
            return head;
        }
    }
}

vector<string> fillVectorFromFile(int choice) {
    vector<string> dict;
    std::ifstream dictFile;
    string filename;

    switch (choice) {
    case 1:
        filename = FILE_ONE;
        break;
    case 2:
        filename = FILE_TWO;
        break;
    default:
        filename = FILE_THREE;
        break;
    }

    dictFile.open(filename);

    if (dictFile.is_open()) {
        string temp;
        while (true) {
            dictFile >> temp;
            dict.push_back(temp);
            if (dictFile.eof()) {
                break;
            }
        }
        dictFile.close();
    }

    return dict;
}

///* function returns a vector of dictionary words - possible inputs are 1, 2, & 3 *
//* representing three different word lists. Function will return word list #3 if *
//* any integer besides 1 or 2 is passed in.                                      *
//*/
///* Note: not all versions of C++ support direct definition of a vector, hence a *
// * temporary array is used here.                                                *
// */
vector<string> fillVector(int choice) {
    vector<string> dict;

    string temp1[] = { "airy", "aisle", "aisles", "ajar", "akimbo", "akin", "juveniles",
                    "juxtapose", "knowledges", "known", "president", "tries", "trifle",
                    "tugs", "wrongdoers", "wroth", "wyvern", "xenophon", "xylol", "yodle",
                    "yurt", "zeugma", "ziggurat", "zootomy" };
    unsigned int size1 = 24;
    string temp2[] = { "aback", "abased", "acknowledgers", "administers", "affair",
                    "aforementioned", "aggrieving", "agitating", "agree", "airlines", "ajar",
                    "basin", "bawdy", "cheap", "cheated", "examiner", "excel",
                    "lewdness", "liberal", "mathematician", "ordered", "president", "sandwich",
                    "swagger", "swarm", "vomit", "yell", "zero", "zodiac", "zoo" };
    unsigned int size2 = 30;
    string temp3[] = { "ajar", "anachronism", "bleed", "bystander", "chariot", "clay",
                    "contrive", "critiques", "databases", "derivative", "dog", "earthenware",
                    "basin", "bawdy", "cheap", "cheated", "examiner", "excel",
                    "echo", "fatiguing", "floppy", "goldsmith", "halt", "implies",
                    "jam", "klutz", "lively", "malt", "meteor", "nonsense", "orphans",
                    "paint", "playful", "railroad", "revolt", "shark", "spook", "syntax",
                    "tablet", "thing", "ugly", "vigilant", "whirr", "yell", "zap", "zoo" };
    unsigned int size3 = 46;
    switch (choice) {
    case 1:
        for (unsigned int i = 0; i < size1; i++) {
            dict.push_back(temp1[i]);
        }
        break;
    case 2:
        for (unsigned int i = 0; i < size2; i++) {
            dict.push_back(temp2[i]);
        }
        break;
    default:
        for (unsigned int i = 0; i < size3; i++) {
            dict.push_back(temp3[i]);
        }
    }//end switch


    return dict;
} //end fillVector()

void printWord(node* node, bool newLine) {
    if (node == nullptr) {
        cout << "We did not find your word.\n";
    }
    else {
        cout << "Your word was '" << node->str << "'.\n";
    }

    if (newLine) {
        cout << "\n";
    }
}

void printAdjacentWords(node* node, bool newLine, bool dots) {
    if (node == nullptr) {
        cout << "We did not find your word. \n";
        return;
    }

    // Print next word
    if (node->next != nullptr) {
        cout << "The next word would be '" << node->next->str << "'";
        if (dots) {
            cout << ". ";
        }
        cout << "\n";
    }
    else {
        cout << "There is no word following '" << node->str << "'";
        if (dots) {
            cout << ". ";
        }
        cout << "\n";
    }

    // Print previous word
    if (node->prev != nullptr) {
        cout << "The previous word would be '" << node->prev->str << "'";
        if (dots) {
            cout << ". ";
        }
        cout << "\n";
    }
    else {
        cout << "There is no word before '" << node->str << "'";
        if (dots) {
            cout << ". ";
        }
        cout << "\n";
    }

    if (newLine) {
        cout << "\n";
    }
}