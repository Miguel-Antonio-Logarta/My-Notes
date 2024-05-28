/*************************************************************************
 *	STRING_REPORT
 *	author: Miguel Logarta
 *	adapted from:   web question & answer
 *	other citations:
 *
 *	summary:  an efficient C++ program that takes a string input from the
 *			  user and displays the number of words, characters, alphabets, 
 *			  vowels, consonants and digits in that given string.
 *	overview: Asks user for input and stores it in a string.
 *           Loops over string and counts words, characters, alphabets,
 *           vowels, consonants, and digits in the string.
 *
 *************************************************************************/ 

#include <iostream>
#include <string>	//for using string data type and its functions

using namespace std;

bool isWhiteSpace(char c) {
   return c == ' ' || c == '\t';
}

bool isVowel(char c) {
   string vowels{ "aeiou" };
   return vowels.find(tolower(c)) != string::npos;
}

int main(){
   
   /** your code here - feel free to change variable names (used below)**/
   string inputString;
   int numAlphabets{ 0 };
   int numVowels{ 0 };
   int numConsonants{ 0 };
   int numDigits{ 0 };
   int numCharacters{ 0 };
   int numWords{ 0 };
   
   getline(cin, inputString);

   for (int i = 0; i < inputString.length(); i++) {
      if (!isWhiteSpace(inputString[i])) {
         // Count the current element as a character since it is not whitespace.
         numCharacters++;
         if (isalpha(inputString[i])) {
            // Count the letter. Check if it is a vowel or consonant
            numAlphabets++;
            if (isVowel(inputString[i])) {
               numVowels++;
            } else {
               numConsonants++;
            }
         }
         else if (isdigit(inputString[i])) {
            // Count as digit
            numDigits++;
         }
      }
      else {
         // Look ahead, if the next element is not a space or a tab, it is the beginning of a new word
         // This will not count the first word of the string, but we'll take care of that later
         if (i < inputString.length() - 1 && inputString[i+1] != ' ' && inputString[i] != '\t') {
         // if (i < inputString.length() - 1 && !isWhiteSpace(inputString[i+1]) && isWhiteSpace(inputString[i])) {
            numWords++;
         }
      }
   }

   // Check if the string starts with a word
   if (isWhiteSpace(inputString[0]))
      numWords++;
   
   //output in required format
   cout << "-*-*-*-\n"	;				
	cout << "Number of alphabets = " << numAlphabets << "\n";
	cout << "Number of vowels = "    << numVowels << "\n";
	cout << "Number of consonants = " << numConsonants << "\n";
	cout << "Number of digits = "    << numDigits << "\n";
	cout << "Number of characters = " << numCharacters << "\n";
	cout << "Number of words = "     << numWords << "\n";
   
   return 0;
}
  