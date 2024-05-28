#define FILE_ONE "short_dict1.txt"
#define FILE_TWO "short_dict2.txt"
#define FILE_THREE "short_dict3.txt"
#define NEW_DICT_FILE "Updated Dictionary.txt"
#define NEW_DICT_FILE_TWO "Updated Dictionary2.txt"
#define NEW_DICT_FILE_THREE "Updated Dictionary3.txt"
#define FILE_SUFFIX ".txt"
#define FILE_NAME "dictionary"
#define MAX_DICTIONARY_NUM "\"9\""

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
void swapNodes(node*, node*);
node* createLinkedList(std::vector<string>&);
node* insertNode(node*, string);
node* deleteNode(node*, string);
node* deleteLinkedList(node*);
node* bubbleSort(node*, bool);
node* binarySearch(node*, string);
node* mergeLinkedLists(node*, node*);

// Read operations
void printLinkedList(node*);
void printWord(node* node, bool newLine = false);
void printAdjacentWords(node* node, bool newLine = false, bool dots = true);
node* findNode(node*, string);
node* findMiddleNode(node*);

// User prompts to get input
int selectOption();
void writeToFile(node*);
node* performOperation(node*, int&, int, bool&);
node* selectDictionary(int&);
node* searchDictionary(node*, bool);
node* deleteInDictionary(node*);
node* swapWords(node*);
node* searchDictionaryBinary(node*);
node* mergeTwoDictionaries(int, node*);

// Functions for creating our dictionary
vector<string> fillVectorFromFile(int choice);

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

    /* Value of choice are mapped to the following functions
        (0) Quit program
        (1) Print words
        (2) Find a word
        (3) Find word, insert if not found (assumes words are sorted alphabetically)
        (4) Find word, delete if found 
        (5) Swap two words
        (6) Sort words (Bubble Sort or Selection Sort)
        (7) Find a word - Binary Search (assumes words are sorted alphabetically)
        (8) Merge two dictionaries (will sort first)
        (11) Load a dictionary (closes current dictionary)
        (12) Write current dictionary to file

        -- The options below have not been implemented
        (9) Sort words (Merge Sort)
        (10) Sort words (Bucket Sort)

    */

    switch (choice) {
    case 0:
        cout << "Thank you! Bye!\n";
        stopProgram = true;
        break;
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
        dictionary = bubbleSort(dictionary, false);
        break;
    case 7:
        dictionary = searchDictionaryBinary(dictionary);
        break;
    case 8:
        dictionary = mergeTwoDictionaries(dictionarySelected, dictionary);
        break;
    case 9:
        cout << "Coming soon!\n";
        break;
    case 10:
        cout << "Coming soon!\n";
        break;
    case 11:
        deleteLinkedList(dictionary);
        dictionary = selectDictionary(dictionarySelected);
        break;
    case 12:
        writeToFile(dictionary);
        break;
    default:
        cout << "Error! Input must be a number between 1 and 12, or 0 to exit.\n";
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

node* bubbleSort(node* head, bool silent) {
    // Find number of nodes in the list
    int count = 0;
    node* curr = head;
    while (curr != nullptr) {
        count++;
        curr = curr->next;
    }

    if (!silent) {
        cout << "sorting...\n";
    }

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

        //cout << 
        //cout << "***swaps = " << swaps << ", count = " << passes << "***\n";
        /*curr = head;
        while (curr != nullptr) {
            cout << curr->str << "\n";
            curr = curr->next;
        }*/

        if (swapped == false) {
            break;
        }
    }

    if (!silent) {
        cout << "...Done!\n";
    }
    return head;
}

node* binarySearch(node* head, string word) {
    // Find middle of linked list
    // If word is greater than middle
        // Look in upper portion
    // If word is lower than middle
        // Look in lower portion

    // Get size of the linked list
    node* curr = head;
    int n = 0;
    while (curr->next != nullptr) {
        n++;
        curr = curr->next;
    }

    node* start = head;
    node* middle = head;
    node* end = head;

    // Get the middle
    for (int i = 0; i < n / 2; i++) {
        middle = middle->next;
    }

    // Get the end
    for (int i = 0; i < n; i++) {
        end = end->next;
    }

    // Begin the Binary Search

    while (start != end) {
        if (word == start->str) {
            cout << "start: " << start->str << ", mid: " << middle->str << ", end: " << end->str << endl;
            return start;
        }
        else if (word == middle->str) {
            cout << "start: " << start->str << ", mid: " << middle->str << ", end: " << end->str << endl;
            return middle;
        }
        else if (word == end->str) {
            cout << "start: " << start->str << ", mid: " << middle->str << ", end: " << end->str << endl;
            return end;
        }
        else {
            cout << "start: " << start->str << ", mid: " << middle->str << ", end: " << end->str << endl;
            // Look at upper or at look lower half
            if (word > middle->str) {
                start = middle->next;
                end = end->prev;
            }
            else if (word < middle->str) {
                end = middle->prev;
                start = start->next;
            }

            // Find length between start and end
            middle = start;
            int newN = 0;
            while (middle != end && middle != nullptr) {
                // BUG: Somehow, end ends up being greater than start. This causes middle to go all the way to the end
                newN++;
                middle = middle->next;
            }

            if (newN > n) {
                return nullptr;
            }

            n = newN;

            middle = start;
            for (int i = 0; i < n / 2; i++) {
                middle = middle->next;
            }
        }
    }

    cout << "start: " << start->str << ", mid: " << middle->str << ", end: " << end->str << endl;
    return nullptr;
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

    //cout << "Which Dictionary should be opened? Enter \"1\", \"2\", or \"3\": ";
    cout << "Which Dictionary should be opened? Enter a number between \"1\" and " << MAX_DICTIONARY_NUM << ": ";
    cin >> choice;
    cout << endl;

    dictionarySelected = choice;
    vec = fillVectorFromFile(choice);
    linkedList = createLinkedList(vec);

    cout << "Dictionary " << choice << " is open.";

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
        << " (1) Print words\n"
        << " (2) Find a word\n"
        << " (3) Find word, insert if not found (assumes words are sorted alphabetically)\n"
        << " (4) Find word, delete if found \n"
        << " (5) Swap two words\n"
        << " (6) Sort words (Bubble Sort or Selection Sort)\n"
        << " (7) Find a word - Binary Search (assumes words are sorted alphabetically)\n"
        << " (8) Merge two dictionaries (will sort first)\n"
        << " (9) Sort words (Merge Sort)\n"
        << "(10) Sort words (Bucket Sort)\n"
        << "(11) Load a dictionary (closes current dictionary)\n"
        << "(12) Write current dictionary to file\n"
        << "Enter a number from 1 to 12, or 0 to exit: ";
    cin >> choice;
    cout << endl;
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

node* searchDictionaryBinary(node* head) {
    string searchWord;
    cout << "Enter a word to search for in the chosen Dictionary: ";
    cin >> searchWord;
    cout << endl;
    
    // Sort the linked list    
    node* sortedList = bubbleSort(head, true);
    //printLinkedList(sortedList);
    //node* sortedList = head;

    // Binary search for linked list    
    node* found = binarySearch(sortedList, searchWord);

    if (found == nullptr) {
        cout << "Your word was '" << searchWord << "'. ";
        printWord(found, false);
    }
    else {
        printWord(found, false);
        printAdjacentWords(found, true, false);
    }

    return sortedList;

}

node* mergeTwoDictionaries(int choice, node* head)
{
    int secondDictionaryChoice;
    while (true) {
        cout << "Which dictionary should be opened? Enter a number between \"1\" and " << MAX_DICTIONARY_NUM << ": ";
        cin >> secondDictionaryChoice;
        cout << endl;

        if (secondDictionaryChoice == choice) {
            cout << "That dictionary is already open! Pick another." << endl;
        }
        else {
            break;
        }
    }

    vector<string> vec = fillVectorFromFile(secondDictionaryChoice);
    node* firstLinkedList = head;
    node* secondLinkedList = createLinkedList(vec);
    cout << "Dictionary " << secondDictionaryChoice << " is open.";

    cout << " Sorting..." << endl;
    firstLinkedList = bubbleSort(head, true);
    secondLinkedList = bubbleSort(secondLinkedList, true);

    cout << "Merging..." << endl;
    node* mergedList = mergeLinkedLists(firstLinkedList, secondLinkedList);
    cout << "...Done!" << endl;
    return mergedList;
}

void writeToFile(node* head) {
    string filename;
    cout << "Enter name of file (.txt will be appended automatically): ";
    cin >> filename;
    cout << "\n";

    filename.append(FILE_SUFFIX);

    cout << "Writing to file...\n";

    // Check if the file exists
    std::ifstream inFile(filename);
    if (inFile.good()) {
        cout << "Error! File '" << filename << "' already exists.\n";
        inFile.close();
        return;
    }
    inFile.close();

    // Write to file
    std::ofstream outFile;
    outFile.open(filename);

    if (!outFile.is_open()) {
        cout << "Error! New dictionary '" << filename << "' failed to open.\n";
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

    filename.append(FILE_NAME);
    filename.append(std::to_string(choice));
    filename.append(FILE_SUFFIX);

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

node* mergeLinkedLists(node* l1, node* l2) {
    node* mergedList = new node;
    node* curr = mergedList;
    node* prev = curr->prev;

    while (l1 != nullptr && l2 != nullptr) {
        if (l1->str < l2->str) {
            // Insert node from first list
            curr->next = l1;
            prev = curr;
            curr = curr->next;
            curr->prev = prev;

            l1 = l1->next;
        }
        else if (l1->str > l2->str) {
            // Insert node from the second list
            curr->next = l2;
            prev = curr;
            curr = curr->next;
            curr->prev = prev;

            l2 = l2->next;
        }
        else {
            // Pick one node and delete the other
            curr->next = l1;
            prev = curr;
            curr = curr->next;
            curr->prev = prev;

            l1 = l1->next;

            node* toDelete = l2;
            l2 = l2->next;
            delete toDelete;
        }


    }

    if (l1 != nullptr) {
        curr->next = l1;
        prev = curr;
        curr = curr->next;
        curr->prev = prev;
        l1 = l1->next;
    }

    if (l2 != nullptr) {
        curr->next = l2;
        prev = curr;
        curr = curr->next;
        curr->prev = prev;
        l2 = l2->next;
    }

    mergedList = mergedList->next;
    mergedList->prev = nullptr;

    return mergedList;
}