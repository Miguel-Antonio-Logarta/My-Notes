#include "SavingsAccount.h"

SavingsAccount::SavingsAccount() : username{ "" }, balance{ 0 }, interestRate{ 0 }
{
}

SavingsAccount::SavingsAccount(const std::string& newUsername, double newBalance, double newInterestRate)
{
	username = newUsername;
	balance = newBalance;
	interestRate = newInterestRate;
}

SavingsAccount::~SavingsAccount()
{
}

void SavingsAccount::withdraw(double withdrawAmount)
{
	double newBalance = balance - withdrawAmount;
	if (newBalance < 0) {
		std::cout << "Withdrawal amount goes over the limit. Not allowed." << std::endl;
	}
	else if (withdrawAmount < 0) {
		std::cout << "You cannot withdraw a negative amount." << std::endl;
	}
	else {
		balance = newBalance;
		std::cout << std::fixed << std::setprecision(2)
			<< "Successfully withdrew $" << withdrawAmount << ". Your new balance is $" << balance << std::endl;
	}
}

void SavingsAccount::deposit(double depositAmount)
{
	if (depositAmount > 0) {
		balance += depositAmount;
		std::cout << std::fixed << std::setprecision(2) << "Succesfully deposited $" << depositAmount
			<< ". Your new balance is $" << balance << "." << std::endl;
	}
	else {
		std::cout << "You cannot deposit a negative amount." << std::endl;
	}
}

void SavingsAccount::setUsername(const std::string& username)
{
	this->username = username;
}

std::string SavingsAccount::getUsername()
{
	return username;
}

void SavingsAccount::setBalance(double balance)
{
	this->balance = balance;
}

void SavingsAccount::setInterestRate(double interestRate)
{
	this->interestRate = interestRate;
}

double SavingsAccount::getBalance()
{
	return balance;
}

double SavingsAccount::getInterestRate()
{
	return interestRate;
}

void SavingsAccount::promptUser()
{
	std::cout << "Your balance: $" << std::fixed << std::setprecision(2) << balance << std::endl
		<< "Your account interest rate: %" << std::fixed << std::setprecision(2) << interestRate << std::endl;
	std::string input;
	int choice;
	int amount;
	while (true) {
		std::cout << "Please choose an option:" << std::endl;
		std::cout << "1. Withdraw money" << std::endl;
		std::cout << "2. Deposit money" << std::endl;
		std::cout << "3. Calculate earnings from interest" << std::endl;
		std::cout << "4. Go back to the account selection menu" << std::endl;
		std::cout << "Your choice: ";
		std::stringstream ss;
		std::stringstream ssAmount;
		std::getline(std::cin, input);
		ss << input;
		ss >> choice;

		switch (choice) {
		case 1:
			std::cout << "How much would you like to withdraw? Your limit is $"
				<< std::fixed << std::setprecision(2) << balance << std::endl;
			std::cout << "Amount: $";
			std::getline(std::cin, input);
			ssAmount << input;
			ssAmount >> amount;

			withdraw(amount);
			break;
		case 2:
			std::cout << "How much would you like to deposit? Your current balance is $"
				<< std::fixed << std::setprecision(2) << balance << std::endl;
			std::cout << "Amount: $";
			std::getline(std::cin, input);
			ssAmount << input;
			ssAmount >> amount;

			deposit(amount);
			break;
		case 3: 
		{
			std::cout << "Your current balance is $"
				<< std::fixed << std::setprecision(2) << balance << std::endl
				<< "Your APY is %" << interestRate << std::endl;

			double interestEarned = calculateInterestRate();
			std::cout << std::fixed << std::setprecision(2)
				<< "After a year, you will have earned $" << interestEarned << std::endl;
			break;
		}
		case 4:
			return;
			break;
		default:
			std::cout << "Please select a valid choice" << std::endl;
			break;
		}
	}
	return;
}

double SavingsAccount::calculateInterestRate()
{
	return interestRate*balance;
}

std::string SavingsAccount::printCSV()
{
	std::string csvString;
	std::stringstream ss;
	ss << username << "," << std::fixed << std::setprecision(2) << balance << "," << interestRate;
	ss >> csvString;
	return csvString;
}
