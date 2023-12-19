#pragma once
#include "Node.h"
#include <functional>

// Linked list holds nodes that are of type T, we can manipulate data inside this linked list
// However, this is just a container, we need to implement algorithms outside 
template<class T>
class LinkedList
{
public:
	LinkedList();

	Node<T>* insert(const int pos, Node<T>* node);	// Inserts a node at the nth position of the linked list
	Node<T>* insert(const int pos, T data);			// Creates and inserts node at the nth position of the linked list
	void remove(Node<T>* node);						// Removes the matching node in the linked list
	void remove(const int pos);						// Removes the node at the nth position of the linked list
	
	Node<T>* front();				// Return the head of the linked list
	Node<T>* back();				// Return the tail of the linked list

	Node<T>* pushFront(T data);		// Insert a new node at head of the linked list
	Node<T>* pushBack(T data);		// Insert a new node at the tail end of the linked list

	void popFront();				// Remove the first node
	void popBack();					// Remove the last node

	int getSize();					// Get the number of nodes in the linked list

	// This function returns a pointer to a node based on a search condition
	// The parameter expects a lambda function with one parameter of type Node<T>*. The findIf function
	// will use this paramter to reference the current node in the search. This lambda function
	// has to return a boolean that determines if a search condition was fulfilled or not
	// If it is fulfilled, it returns a pointer to the current node. Else it returns nullptr.
	Node<T>* findIf(std::function<bool(Node<T>*)>);

private:
	Node<T>* head;
	Node<T>* tail;
};

template<class T>
inline LinkedList<T>::LinkedList() : head(nullptr), tail(nullptr) {};

template<class T>
inline Node<T>* LinkedList<T>::insert(const int pos, Node<T>* node)
{

	if (pos == 0) {
		return pushFront(node);
	}

	int i = 0;
	Node<T>* currNode = head;
	while (currNode->getNext() != nullptr && i < pos) {
		currNode = currNode->getNext();
		i++;
	}

	if (pos - i > 0) {
		// Append to the back
		currNode->setNext(node);
		node->setPrev(node);
		tail = node;
		return tail;
	}
	else {
		// Append to front of currNode
		Node<T>* prevNode = currNode->getPrev();

		// Connect new node
		node->setPrev(prevNode);
		node->setNext(currNode);

		// Insert new node 
		prevNode->setNext(node);
		currNode->setPrev(node);

		return node;
	}
}

template<class T>
inline Node<T>* LinkedList<T>::insert(const int pos, T data)
{
	// Three cases:
	// Case 1: pos is at front
	// Case 2: pos is somewhere in the middle of the linked list
	// Case 3: pos exceeds size of linked list

	// Case 1
	if (pos == 0) {
		return pushFront(data);
	}
	
	// Traverse to position i
	int i = 0;
	Node<T>* currNode = head;
	while (currNode->getNext() != nullptr && i < pos) {
		currNode = currNode->getNext();
		i++;
	}

	// Case 3
	if (pos - i > 0) {
		// Append to the back
		Node<T>* newNode = new Node<T>(data);
		currNode->setNext(newNode);
		newNode->setPrev(currNode);
		tail = newNode;
		return tail;
	}
	// Case 2
	else {
		// Append to front of currNode
		Node<T>* prevNode = currNode->getPrev();
		Node<T>* newNode = new Node<T>(data);
		
		// Connect new node
		newNode->setPrev(prevNode);
		newNode->setNext(currNode);

		// Insert new node 
		prevNode->setNext(newNode);
		currNode->setPrev(newNode);

		return newNode;
	}
}

template<class T>
inline void LinkedList<T>::remove(Node<T>* node)
{
}
template<class T>
inline void LinkedList<T>::remove(const int pos)
{
}
template<class T>
inline Node<T>* LinkedList<T>::front()
{
	return head;
}
template<class T>
inline Node<T>* LinkedList<T>::back()
{
	return tail;
}

template<class T>
inline Node<T>* LinkedList<T>::pushFront(T data)
{
	if (head == nullptr) {
		head = new Node<T>(data);
		tail = head;
	}
	else {
		Node<T>* newHead = new Node<T>(data);

		// Connect nodes
		newHead->setNext(head);
		head->setPrev(newHead);

		// Change to new head
		head = newHead;
	}

	return head;
}

template<class T>
inline Node<T>* LinkedList<T>::pushBack(T data)
{
	if (head == nullptr) {
		return pushFront(data);
	}
	else {
		Node<T>* newTail = new Node<T>(data);
		
		// Connect nodes
		newTail->setPrev(tail);
		tail->setNext(newTail);

		// Change to new tail
		tail = newTail;
		return tail;
	}
}

template<class T>
inline void LinkedList<T>::popFront()
{
	if (head == nullptr) {
		return;
	}

	Node<T>* temp = head;

	// Change the head
	head = head->getNext();
	head->setPrev(nullptr);
	
	// Deallocate memory
	delete temp;
	temp = nullptr;
}
template<class T>
inline void LinkedList<T>::popBack()
{
	if (tail == nullptr) {
		return;
	}

	Node<T>* temp = tail;

	// Change the tail
	tail = tail->getPrev();
	tail->setNext(nullptr);

	// Deallocate memory
	delete temp;
	temp = nullptr;
}
template<class T>
inline int LinkedList<T>::getSize()
{
	Node<T>* currNode = head;
	int size = 0;
	while (currNode->getNext() != nullptr) {
		currNode = currNode->getNext();
		size++;
	}
	return size;
}

template<class T>
inline Node<T>* LinkedList<T>::findIf(std::function<bool(Node<T>*)> comparisonSelector)
{
	// Linearily search the linked list and call the comparison selector on each node
	Node<T>* currNode = head;
	while (currNode != nullptr) {
		if (!comparisonSelector(currNode)) {
			currNode = currNode->getNext();
		}
		else {
			return currNode;
		}
	}
	return nullptr;
};