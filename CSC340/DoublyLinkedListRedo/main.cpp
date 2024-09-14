// Redo the linked list from the lab
#include <iostream>
#include <string>
#include <vector>

using std::cout;
using std::cin;
using std::endl;
using std::string;
using std::vector;

// Node for linked list
struct node {
    string str;    
    node* next;
    node* prev;
};

// CRUD operations
node* createLinkedList(std::vector<string>&);
node* insertNode(node*, string);
node* deleteNode(string);
node* deleteLinkedList(node*);
node* findNode(string);

// Read operations
void printLinkedList(node*);
void printWord(node*);
void printAdjacentWords(node*);

// User prompts to get input

int main() {
    vector<string> sampleVec = {"b", "c", "e"};
    node* linkedList = createLinkedList(sampleVec);
    linkedList = insertNode(linkedList, "a");
    linkedList = insertNode(linkedList, "d");
    linkedList = insertNode(linkedList, "f");
    return 0;
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

    return head->next;
}

node* insertNode(node* head, string word) {
    // Creates a new node and inserts it into the linked list. Inserts word in alphabetical order

    // Traverse the linked list
    node* currNode = head;

    // Move forward if not end of linked list and current string is less than the current word
    while (currNode->next != nullptr && currNode->str < word) {
        currNode = currNode->next;
    }

    if (currNode->prev == nullptr) {
        // Case 1: Insert at front
        node* newNode = new node;
        newNode->str = word;

        currNode->prev = newNode;
        newNode->next = currNode;

        return newNode;
    } else {
        // Case 2: Insert in middle or at end
        node* newNode = new node;
        newNode->str = word;

        newNode->next = currNode->next;
        newNode->prev = currNode;
        currNode->next = newNode;

        if (newNode->next) {
            newNode->next->prev = newNode;
        }
        return head;
    }


}

void printLinkedList(node* head) {
    node* curr = head;
    while (curr != nullptr) {
        cout << curr->str << endl;
        curr = curr->next;
    }
}