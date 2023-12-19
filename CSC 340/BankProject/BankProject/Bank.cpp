#include "Bank.h"
#include <string>
#include <sstream>
#include <fstream>
#include <vector>

Bank::Bank(const std::string& userFileName, 
		   const std::string& checkingAccountsFileName, 
		   const std::string& savingsAccountsFileName, 
		   const std::string& creditAccountsFileName)
{
	// Save filenames
	bankDatabase.users = userFileName;
	bankDatabase.checking = checkingAccountsFileName;
	bankDatabase.savings = savingsAccountsFileName;
	bankDatabase.credit = creditAccountsFileName;

	// Initial setup. Load the database into memory
	loadUsers();
	loadCreditAccounts(); 
	loadCheckingAccounts(); 
	loadSavingAccounts(); 
}

Bank::~Bank()
{
	
}

void Bank::start()
{
	// Authenticate the user, then load their bank accounts
	if (authenticateUser() && loadUserBankAccounts()) {
		promptUser();

		// After user is done, save all changes into database
		saveDB();
	}
}

// Authenticate user returns true if user was successfully authenticated and false otherwise
bool Bank::authenticateUser()
{
	std::string input;
	int choice;

	std::cout << "Welcome to our Banking App!" << std::endl;
	while (true) {
		// Prompt User
		std::cout 
			<< "Select one of the following options to access your bank account:" << std::endl
			<< "1. Login" << std::endl
			<< "2. Sign up" << std::endl
			<< "3. Exit the application" << std::endl
			<< "Your choice: ";
		
		std::stringstream ss;
		std::getline(std::cin, input);
		ss << input;
		ss >> choice;

		switch (choice) {
			case 1:
				loginUser();
				return true;
				break;
			case 2:
				signUpUser();
				return true;
				break;
			case 3:
				std::cout << "Bye!" << std::endl;
				return false;
			default:
				std::cout << "Please select a valid option" << std::endl;
				break;
		}
	}
}

void Bank::promptUser()
{
	int choice;
	std::cout << "Welcome " << currUser->getData().getUsername() << "!" << std::endl;
	while (true) {
		std::stringstream ss;
		std::string choiceInput;
		std::cout << "Select an option below:" << std::endl;
		std::cout << "1. Access your Checking Account" << std::endl;
		std::cout << "2. Access your Savings Account" << std::endl;
		std::cout << "3. Access your Credit Account" << std::endl;
		std::cout << "4. Transfer money between your bank accounts" << std::endl;
		std::cout << "5. Transfer money to another user" << std::endl;
		std::cout << "6. Change your pin" << std::endl;
		std::cout << "7. Exit the application" << std::endl;
		std::cout << "Your choice: ";

		std::getline(std::cin, choiceInput);
		ss << choiceInput;
		ss >> choice;
		
		switch (choice) {
		case 1:
			currCheckingAccount->getData().promptUser();
			break;
		case 2:
			currSavingsAccount->getData().promptUser();
			break;
		case 3:
			currCreditAccount->getData().promptUser();
			break;
		case 6:
			changeUserPin();
			break;
		case 7:
			std::cout << "Bye!" << std::endl;
			return;
			break;
		default:
			std::cout << "Please enter a valid choice" << std::endl;
			break;
		}
	}
}

void Bank::loginUser()
{
	while (true) {
		std::string email;
		std::string password;

		std::cout << "Enter your email: ";
		std::getline(std::cin, email);
		std::cout << "Enter your password: ";
		std::getline(std::cin, password);

		// Find user with email
		Node<User>* userFound = findUser(email);

		// Check if user is found and if passwords match
		if (userFound != nullptr && password == userFound->getData().getPassword()) {
			currUser = userFound;
			break;
		}
		else {
			std::cout << "Username or password field is incorrect" << std::endl;
		}
	}
}

void Bank::signUpUser()
{
	UserRow newInfo;
	std::stringstream ss;
	std::string pinString;
	
	// Get info from user
	std::cout << "Enter a username: ";
	std::getline(std::cin, newInfo.username);
	std::cout << "Enter an email: ";
	std::getline(std::cin, newInfo.email);
	std::cout << "Enter a your actual government name: ";
	std::getline(std::cin, newInfo.governmentName);
	std::cout << "Enter a secure password: ";
	std::getline(std::cin, newInfo.password);
	std::cout << "Enter a pin for your account: ";
	std::getline(std::cin, pinString);
	ss << pinString;
	ss >> newInfo.pin;

	// Add new user to the database
	User newUser;
	newUser.setUsername(newInfo.username);
	newUser.setEmail(newInfo.email);
	newUser.setGovernmentName(newInfo.governmentName);
	newUser.setPassword(newInfo.password);
	newUser.setPin(newInfo.pin);

	currUser = users.pushBack(newUser);

	// Create bank accounts for the user
	CheckingAccount newCheckingAccount(newUser.getUsername(), 0, 0, DEFAULT_OVERDRAFT_LIMIT, DEFAULT_OVERDRAFT_FEE);
	SavingsAccount newSavingsAccount(newUser.getUsername(), 0, DEFAULT_INTEREST_RATE);
	CreditAccount newCreditAccount(newUser.getUsername(), DEFAULT_CREDIT_LIMIT, 0);
	currCheckingAccount = checkingAccounts.pushBack(newCheckingAccount);
	currSavingsAccount = savingsAccounts.pushBack(newSavingsAccount);
	currCreditAccount = creditAccounts.pushBack(newCreditAccount);
}

void Bank::changeUserPin()
{
	std::string input;
	std::stringstream ss;
	int newPin;

	std::cout << "Enter a new pin: ";
	std::getline(std::cin, input);
	
	ss << input;
	ss >> newPin;
	currUser->getData().setPin(newPin);

	std::cout << "Pin successfully changed." << std::endl;
}

Node<User>* Bank::findUser(const std::string& email)
{
	// The linked list is going to compare its current user to the email. If the match is found, it returns that user
	// Else it returns null
	auto findFunc = [&email](Node<User>* currUser) -> bool { 
		return currUser->getData().getEmail() == email; 
	};
	Node<User>* existingUser = users.findIf(findFunc);

	return existingUser;
}

bool Bank::loadUsers()
{
	std::fstream fs;
	fs.open(bankDatabase.users, std::fstream::in);

	if (fs.is_open()) {

		// Read each line
		std::string line;
		std::getline(fs, line); // skip the first line
		while (std::getline(fs, line)) {
			std::stringstream ss(line);
			UserRow row;
			// Parse each column
			while (ss.good()) {
				std::string pin;
				std::getline(ss, row.username, ',');
				std::getline(ss, row.email, ',');
				std::getline(ss, row.password, ',');
				std::getline(ss, pin, ',');
				std::getline(ss, row.governmentName, ',');

				row.pin = std::stoi(pin);

				User currUser;
				currUser.setUsername(row.username);
				currUser.setEmail(row.email);
				currUser.setPassword(row.password);
				currUser.setPin(row.pin);
				currUser.setGovernmentName(row.governmentName);

				users.pushBack(currUser);
			}

		}

		fs.close();
		return true;
	}
	else {
		fs.close();
		return false;
	}

	fs.close();
}

// This has not been finished yet
bool Bank::loadCreditAccounts() 
{
	std::fstream fs;
	fs.open(bankDatabase.credit, std::fstream::in);

	if (fs.is_open()) {

		// Read each line
		std::string line;
		std::getline(fs, line); // skip the first line
		while (std::getline(fs, line)) {
			std::stringstream ss(line);
			CreditRow row;
			// Parse each column
			while (ss.good()) {
				std::string creditLimit;
				std::string creditUsed;

				std::getline(ss, row.username, ',');
				std::getline(ss, creditLimit, ',');
				std::getline(ss, creditUsed, ',');

				row.creditLimit = std::stoi(creditLimit);
				row.creditUsed = std::stoi(creditUsed);

				CreditAccount currCreditAccount;
				currCreditAccount.setUsername(row.username);
				currCreditAccount.setCreditLimit(row.creditLimit);
				currCreditAccount.setCreditUsed(row.creditUsed);

				creditAccounts.pushBack(currCreditAccount);
			}
		}

		fs.close();
		return true;
	}
	else {
		fs.close();
		return false;
	}

	fs.close();
}

bool Bank::loadCheckingAccounts()
{
	std::fstream fs;
	fs.open(bankDatabase.checking, std::fstream::in);

	if (fs.is_open()) {

		// Read each line
		std::string line;
		std::getline(fs, line); // skip the first line
		while (std::getline(fs, line)) {
			std::stringstream ss(line);
			CheckingRow row;
			// Parse each column
			while (ss.good()) {
				std::string balance;
				std::string overdraftFee;

				std::getline(ss, row.username, ',');
				std::getline(ss, balance, ',');
				std::getline(ss, overdraftFee, ',');

				row.balance = std::stoi(balance);
				row.overdraftFee = std::stoi(overdraftFee);

				CheckingAccount currCheckingAccount;
				currCheckingAccount.setUsername(row.username);
				currCheckingAccount.setBalance(row.balance);
				currCheckingAccount.setOverdraftFeesOwed(row.overdraftFee);
				currCheckingAccount.setBankOverdraftFee(DEFAULT_OVERDRAFT_LIMIT);

				checkingAccounts.pushBack(currCheckingAccount);
			}

		}

		fs.close();
		return true;
	}
	else {
		fs.close();
		return false;
	}

	fs.close();
}

bool Bank::loadSavingAccounts()
{
	std::fstream fs;
	fs.open(bankDatabase.savings, std::fstream::in);

	if (fs.is_open()) {

		// Read each line
		std::string line;
		std::getline(fs, line); // skip the first line
		while (std::getline(fs, line)) {
			std::stringstream ss(line);
			SavingsRow row;
			// Parse each column
			while (ss.good()) {
				std::string balance;
				std::string interestRate;
				std::getline(ss, row.username, ',');
				std::getline(ss, balance, ',');
				std::getline(ss, interestRate, ',');


				row.balance = std::stoi(balance);
				row.interestRate = std::stoi(interestRate);

				SavingsAccount currSavingsAccount(row.username, row.balance, row.interestRate);

				savingsAccounts.pushBack(currSavingsAccount);
			}
		}

		fs.close();
		return true;
	}
	else {
		fs.close();
		return false;
	}

	fs.close();
}

bool Bank::loadUserBankAccounts()
{
	// This function searches the database for checking, credit, and savings accounts that belong to the user
	std::string username = currUser->getData().getUsername();
	auto findChecking = [&username](Node<CheckingAccount>* currUser) -> bool {
		return currUser->getData().getUsername() == username;
	};
	auto findSavings = [&username](Node<SavingsAccount>* currUser) -> bool {
		return currUser->getData().getUsername() == username;
	};
	auto findCredit = [&username](Node<CreditAccount>* currUser) -> bool {
		return currUser->getData().getUsername() == username;
	};

	Node<CheckingAccount>* existingChecking = checkingAccounts.findIf(findChecking);
	Node<SavingsAccount>* existingSavings = savingsAccounts.findIf(findSavings);
	Node<CreditAccount>* existingCredit = creditAccounts.findIf(findCredit);
	
	if (!(existingChecking || existingSavings || existingCredit)) {
		std::cout << "Error: Some bank accounts are missing from the db!" << std::endl;
		return false;
	}
	else {
		currCheckingAccount = existingChecking;
		currSavingsAccount = existingSavings;
		currCreditAccount = existingCredit;

		return true;
	}
}

bool Bank::saveDB()
{
	saveUserBankAccounts();
	saveCheckingAccounts();
	saveSavingAccounts();
	saveCreditAccounts();
	return true;
}

bool Bank::saveCreditAccounts()
{
	std::fstream fout;
	Node<CreditAccount>* head = creditAccounts.front();

	fout.open(bankDatabase.credit, std::ios::out);

	if (fout.is_open()) {
		while (head != nullptr) {
			fout << head->getData().printCSV();
			fout << "\n";

			head = head->getNext();
		}
		fout.close();
		return true;
	}
	else {
		fout.close();
		std::cout << "Error writing credit accounts" << std::endl;
		return false;
	}
	fout << "username,credit limit,credit used\n";

}

bool Bank::saveCheckingAccounts()
{
	std::fstream fout;
	Node<CheckingAccount>* head = checkingAccounts.front();

	fout.open(bankDatabase.checking, std::ios::out);

	if (fout.is_open()) {
		fout << "username,balance,overdraft fees\n";
		while (head != nullptr) {
			fout << head->getData().printCSV();
			fout << "\n";

			head = head->getNext();
		}
		fout.close();
		return true;
	}
	else {
		fout.close();
		std::cout << "Error writing checking accounts" << std::endl;
		return false;
	}
}

bool Bank::saveSavingAccounts()
{
	std::fstream fout;
	Node<SavingsAccount>* head = savingsAccounts.front();

	fout.open(bankDatabase.savings, std::ios::out);


	if (fout.is_open()) {
		fout << "username,balance,interest rate\n";
		while (head != nullptr) {
			fout << head->getData().printCSV();
			fout << "\n";

			head = head->getNext();
		}
		fout.close();
		return true;
	}
	else {
		std::cout << "Trouble writing savings accounts" << std::endl;
		fout.close();
		return false;
	}
}

bool Bank::saveUserBankAccounts()
{
	std::fstream fout;
	Node<User>* head = users.front();

	fout.open(bankDatabase.users, std::ios::out);

	if (fout.is_open()) {
		fout << "username,email,password,pin,government name\n";
		while (head != nullptr) {
			fout << head->getData().printCSV();
			fout << "\n";

			head = head->getNext();
		}
		fout.close();
		return true;
	}
	else {
		std::cout << "Error writing users" << std::endl;
		fout.close();
		return false;
	}
}
