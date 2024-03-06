#include <iostream>
#include <string>
#include <vector>
#include <stdexcept>

#define BIG_INT_WIDTH 7
#define BIG_INT_HEIGHT 7

std::vector<std::string> one = {
  "...@@..",
  "..@@@..",
  "...@@..",
  "...@@..",
  "...@@..",
  "...@@..",
  "@@@@@@@"
};

std::vector<std::string> two = {
  "@@@@@@.",
  ".....@@",
  ".....@@",
  "@@@@@@@",
  "@@.....",
  "@@.....",
  "@@@@@@@"
};

std::vector<std::string> three = {
  "@@@@@@.",
  ".....@@",
  ".....@@",
  "..@@@@.",
  ".....@@",
  ".....@@",
  "@@@@@@."
};

std::vector<std::string> four = {
  "@@...@@",
  "@@...@@",
  "@@...@@",
  ".@@@@@@",
  ".....@@",
  ".....@@",
  ".....@@"
};

std::vector<std::string> five = {
  "@@@@@@.",
  "@@.....",
  "@@.....",
  "@@@@@@.",
  ".....@@",
  ".....@@",
  "@@@@@@."
};

std::vector<std::string> six = {
  ".@@@@@.",
  "@@.....",
  "@@.....",
  "@@@@@@.",
  "@@...@@",
  "@@...@@",
  ".@@@@@."
};

std::vector<std::string> seven = {
  "@@@@@@@",
  ".....@@",
  "....@@.",
  "...@@..",
  "..@@...",
  ".@@....",
  "@@.....",
};

std::vector<std::string> eight = {
  ".@@@@@.",
  "@@...@@",
  "@@...@@",
  ".@@@@@.",
  "@@...@@",
  "@@...@@",
  ".@@@@@."
};

std::vector<std::string> nine = {
  ".@@@@@.",
  "@@...@@",
  "@@...@@",
  ".@@@@@@",
  ".....@@",
  ".....@@",
  ".@@@@@."
};

std::vector<std::string> zero = {
  ".@@@@@.",
  "@@...@@",
  "@@...@@",
  "@@...@@",
  "@@...@@",
  "@@...@@",
  ".@@@@@."
};


double msec() {
  return (1000.*clock())/CLOCKS_PER_SEC;
}

std::vector<std::string> getBigInt(unsigned int n) {
  std::vector<std::string> invalid = {{""}};
  // std::cout << "current digit: " << n << std::endl;
  switch (n)
  {
  case 0:
    return zero;
    break;
  case 1:
    return one;
    break;
  case 2:
    return two;
    break;
  case 3:
    return three;
    break;
  case 4:
    return four;
    break;
  case 5:
    return five;
    break;
  case 6:
    return six;
    break;
  case 7:
    return seven;
    break;
  case 8:
    return eight;
    break;
  case 9:
    return nine;
    break;
  default:
    std::invalid_argument("Values have to be less than 10");
    return invalid;
    break;
  }
  return invalid;
}

void printBigInt(int n) {
  /*
   * Take the number of places that the number has and put it into var a
   * The width of the text will be (a*7 + (a-1)) <- length of each char plus 1 space between each char
   * The max height will be 7 characters
   * */

  // Loop 7 times for each row
    // For each digit, print the current row and then add a space


  // Method 1: conversion from int to string
  for (int i = 0; i < BIG_INT_HEIGHT; i++) {
    std::string nString = std::to_string(n);
    for (auto& digit : nString) {
      std::cout << getBigInt(digit - '0')[i] << " ";
    }
    std::cout << std::endl;
  }
  
  // Method 2: Reverse the number, then get each digit to print
}

int main() {
  printBigInt(1234);
  return 0;
}
