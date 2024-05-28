#pragma once

#include <iostream>
#include <string>

#include "LinkedList.h"
#include "Node.h"

#include "User.h"
#include "CheckingAccount.h"
#include "SavingsAccount.h"
#include "CreditAccount.h"

// Holds our file names
struct BankDB {
	std::string users;
	std::string checking;
	std::string savings;
	std::string credit;
};

// For Reading CSV files
struct UserRow {
	std::string username;
	std::string email;
	std::string password;
	std::string governmentName;
	int pin;
};

struct CreditRow {
	std::string username;
	double creditLimit;
	double creditUsed;
};

struct CheckingRow {
	std::string username;
	double balance;
	double overdraftFee;
};

struct SavingsRow {
	std::string username;
	double balance;
	double interestRate;
};

class Bank
{
public:
	// Startup
	Bank(const std::string&, const std::string&, const std::string&, const std::string&);
	~Bank();
	void start();

	// Prompts
	void promptUser();

	// User authentication
	bool authenticateUser();
	void loginUser();
	void signUpUser();

private:
	void changeUserPin();
	Node<User>* findUser(const std::string&);

	// Functions for loading data
	bool loadUsers();
	bool loadCreditAccounts();
	bool loadCheckingAccounts();
	bool loadSavingAccounts();
	bool loadUserBankAccounts();

	// Functions for saving data
	bool saveDB();
	bool saveCreditAccounts();
	bool saveCheckingAccounts();
	bool saveSavingAccounts();
	bool saveUserBankAccounts();

	Node<User>* currUser;
	Node<CheckingAccount>* currCheckingAccount;
	Node<SavingsAccount>* currSavingsAccount;
	Node<CreditAccount>* currCreditAccount;

	BankDB bankDatabase;
	LinkedList<User> users;
	LinkedList<CheckingAccount> checkingAccounts;
	LinkedList<SavingsAccount> savingsAccounts;
	LinkedList<CreditAccount> creditAccounts;

	const int DEFAULT_OVERDRAFT_LIMIT = 100;
	const int DEFAULT_OVERDRAFT_FEE = 20;
	const double DEFAULT_INTEREST_RATE = 1.5;
	const int DEFAULT_CREDIT_LIMIT = 10'000;
};

