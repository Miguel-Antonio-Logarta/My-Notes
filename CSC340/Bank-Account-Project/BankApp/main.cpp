/*
CSC 340 Final Project: Bank Account Application
Group members: Kyle Nguyen, Awet Fikadu, Miguel Logarta, Brandon Flores
Our project is a banking application that allows users to use have a checking, savings, and credit account. This program uses a linked list to store a database of users and bank accounts. Users can withdraw or deposit money in a checking account, savings account, or a credit account. Each account has their own benefits and costs. 
- Checking account: Users can withdraw and deposit money often, however, if their balance goes negative, they'll get charged an overdraft fee
- Savings account: Users can deposit money into their savings account so that they can earn interest
- Credit account: Users can borrw money from the bank, but they have to pay it back later. There is a limit on how much you can borrow.

Features that we didn't get to add:
- In previous iterations of this program, users were able transfer money to other users and other bank accounts. However, since we reworked this program to support a linked list, this feature is no longer a thing.
*/

#include <iostream>
#include <vector>
#include <string>
#include <string_view>

#include "LinkedList.h"
#include "Node.h"
#include "Bank.h"

int main() {
	const std::string usersFile{ "users.csv" };
	const std::string checkingAccountsFile{ "checkingAccounts.csv" };
	const std::string savingsAccountsFile{ "savingsAccounts.csv" };
	const std::string creditAccountsFile{ "creditAccounts.csv" };

	Bank bank(usersFile, checkingAccountsFile, savingsAccountsFile, creditAccountsFile);
	bank.start();
	
	return 0;
}