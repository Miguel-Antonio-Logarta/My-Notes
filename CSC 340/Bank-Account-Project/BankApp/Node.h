#pragma once
template <class Data> class Node
{
public:
    Node(Data newData);
    Node(Data newData, Node<Data>* initPrev, Node<Data>* initNext);

    Data getData();             // Get data inside the node
    void setData(Data);         // Replace the data inside the node

    void setNext(Node<Data>*);  // Set next node
    void setPrev(Node<Data>*);  // Set previous noed

    Node<Data>* getNext();         // Returns next node
    Node<Data>* getPrev();         // Returns previous node

private: 
    Data data;                  // Holds object information
    Node<Data>* nextNode;       // Pointer to the next node element
    Node<Data>* prevNode;       // Pointer to the previous node element
};

template<class Data>
inline Node<Data>::Node(Data newData) : data(newData), prevNode(nullptr), nextNode(nullptr) {}

template<class Data>
inline Node<Data>::Node(Data newData, Node<Data>* initPrev, Node<Data>* initNext) : data(newData), prevNode(initPrev), nextNode(initNext)
{
}

template<class Data>
inline Data Node<Data>::getData()
{
    return data;
}

template<class Data>
inline void Node<Data>::setData(Data newData)
{
    data = newData;
}

template<class Data>
inline void Node<Data>::setNext(Node<Data>* node)
{
    nextNode = node;
}

template<class Data>
inline void Node<Data>::setPrev(Node<Data>* node)
{
    prevNode = node;
}

template<class Data>
inline Node<Data>* Node<Data>::getNext()
{
    return nextNode;
}

template<class Data>
inline Node<Data>* Node<Data>::getPrev()
{
    return prevNode;
}
