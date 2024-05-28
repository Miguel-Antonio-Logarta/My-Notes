#include "CreditAccount.h"

CreditAccount::CreditAccount() : username{ "" }, creditLimit{ 0 }, creditUsed{ 0 }
{
}

CreditAccount::CreditAccount(const std::string& newUsername, int newCreditLimit, double newCreditUsed)
{
	username = newUsername;
	creditLimit = newCreditLimit;
	creditUsed = newCreditUsed;
}

CreditAccount::~CreditAccount()
{
}

void CreditAccount::withdraw(double amount)
{
	// Can't withdraw more than your credit limit
	double maxWithdrawLimit = creditLimit - creditUsed;
	if (amount > maxWithdrawLimit) {
		std::cout << "Can't withdraw this amount. You already maxed out your credit card!" << std::endl;
	}
	else {
		creditUsed -= amount;
		std::cout << std::fixed << std::setprecision(2) 
			<< "Succesfully withdrew credit. The amount of credit used is now $" << creditUsed
			<< " Out of $" << creditLimit << std::endl;
	}
}

void CreditAccount::deposit(double amount)
{
	double newCreditUsed = creditUsed - amount;
	if (newCreditUsed < 0) {
		creditUsed = 0;
		std::cout << std::fixed << std::setprecision(2) <<
			"You deposited more money than you owe. Your debt is now $" 
			<< creditUsed << " and we will return you $" << -1 * newCreditUsed << std::endl;
	}
	else {
		creditUsed = newCreditUsed;
		std::cout << std::fixed << std::setprecision(2) << "Successfully deposited $" << amount
			<< ". Your debt is now $" << newCreditUsed << std::endl;
	}
}

void CreditAccount::setUsername(const std::string& newUsername)
{
	username = newUsername;
}

std::string CreditAccount::getUsername()
{
	return username;
}

void CreditAccount::setCreditLimit(double creditLimit)
{
	this->creditLimit = creditLimit;
}

void CreditAccount::setCreditUsed(double creditUsed)
{
	this->creditUsed = creditUsed;
}

double CreditAccount::getCreditLimit()
{
	return creditLimit;
}

double CreditAccount::getCreditUsed()
{
	return creditUsed;
}

void CreditAccount::promptUser()
{
	std::cout << "Credit used: $" << std::fixed << std::setprecision(2) << creditUsed << std::endl
		<< "Current credit card limit: $" << std::fixed << std::setprecision(2) << creditLimit << std::endl;
	std::string input;
	int choice;
	int amount;
	while (true) {
		std::cout << "Please choose an option:" << std::endl;
		std::cout << "1. Withdraw money" << std::endl;
		std::cout << "2. Pay back debt" << std::endl;
		std::cout << "3. Go back to the account selection menu" << std::endl;
		std::cout << "Your choice: ";
		std::stringstream ss;
		std::stringstream ssAmount;
		std::getline(std::cin, input);
		ss << input;
		ss >> choice;

		switch (choice) {
		case 1:
			std::cout << "How much would you like to withdraw? Your limit is $" 
				<< std::fixed << std::setprecision(2) << creditLimit - creditUsed << std::endl;
			std::cout << "Amount: $";
			std::getline(std::cin, input);
			ssAmount << input;
			ssAmount >> amount;

			withdraw(amount);
			break;
		case 2:
			std::cout << "How much would you like to deposit? Your current debt is $"
				<< std::fixed << std::setprecision(2) << creditUsed << std::endl;
			std::cout << "Amount: $";
			std::getline(std::cin, input);
			ssAmount << input;
			ssAmount >> amount;

			deposit(amount);
			break;
		case 3:
			return;
			break;
		default:
			std::cout << "Please pick a valid choice" << std::endl;
			break;
		}
	}
	return;
}

std::string CreditAccount::printCSV()
{
	std::string csvString;
	std::stringstream ss;
	ss << username << "," << std::fixed << std::setprecision(2) << creditLimit << "," << creditUsed;
	ss >> csvString;
	return csvString;
}
