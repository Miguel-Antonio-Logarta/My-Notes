#include "CheckingAccount.h"

CheckingAccount::CheckingAccount() : username{ "" }, balance{ 0 }, overdraftFeesOwed{ 0 }, overdraftLimit{ 0 }, bankOverdraftFee{ 0 }
{
}

CheckingAccount::CheckingAccount(const std::string& newUsername, 
	double newBalance, double newOverdraftFeesOwed, int newOverdraftLimit, int newBankOverdraftFee)
{
	username = newUsername;
	balance = newBalance;
	overdraftFeesOwed = newOverdraftFeesOwed;
	overdraftLimit = newOverdraftLimit;
	bankOverdraftFee = newBankOverdraftFee;
}

CheckingAccount::~CheckingAccount()
{
}

void CheckingAccount::withdraw(double amount)
{
	// When user withdraws:
	// If new balance does not reach the negatives, it is valid
	// else if new balance is negative, charge them an overdraft fee
	// else if the withdraw amount is too negative, deny the withdrawal

	double newBalance = balance - amount;
	bool inOverdraftLimitRange = newBalance > -1 * (overdraftLimit - overdraftFeesOwed) && newBalance < 0;

	// This checks whether the difference between balance and amount is less than 1 cent. This handles rounding errors
	bool isZero = (std::fabs(newBalance) < 0.01);

	if (isZero || newBalance >= 0 && amount >= 0) {
		balance = newBalance;
		std::cout << "Your new balance is $" << std::fixed << std::setprecision(2) << balance << std::endl;
	} 
	else if (inOverdraftLimitRange) {
		while (true) {
			std::stringstream ss;
			std::string input;
			int choice;

			std::cout << std::fixed << std::setprecision(2)
				<< "Withdrawing this amount will cause your balance to become negative. Are you sure you want to withdraw? You will incur an overdraft fee of $" 
				<< bankOverdraftFee << " on top of the $" << -1*newBalance << " you will owe." << std::endl;
			std::cout << "1. Withdraw $" << std::fixed << std::setprecision(2) << amount << std::endl;
			std::cout << "2. Cancel withdrawal and go back" << std::endl;
		
			std::getline(std::cin, input);
			ss << input;
			ss >> choice;

			switch (choice) {
			case 1:
				overdraftFeesOwed += bankOverdraftFee + -1*newBalance;
				balance = 0;

				std::cout << std::fixed << std::setprecision(2)
					<< "Your new balance is $" << balance
					<< " and your overdraft fees are now $" << overdraftFeesOwed << std::endl;
				return;
				break;
			case 2:
				return;
				break;
			default:
				std::cout << "Please enter a valid choice" << std::endl;
				break;
			}
		}
	}
	else {
		// The amount withdrawed exceeds the balance + maximum overdraft limit
		std::cout << "Insufficient funds. You cannot withdraw this amount." << std::endl;
	}
}

void CheckingAccount::deposit(double amount)
{
	setBalance(balance += amount);
	std::cout << std::fixed << std::setprecision(2)
		<< "Your new balance is $" << balance << std::endl;
}

void CheckingAccount::setUsername(const std::string& newUsername)
{
	username = newUsername;
}

std::string CheckingAccount::getUsername()
{
	return username;
}

void CheckingAccount::setBalance(double newBalance)
{
	balance = newBalance;
}

void CheckingAccount::setOverdraftFeesOwed(double newFees)
{
	overdraftFeesOwed = newFees;
}

void CheckingAccount::setBankOverdraftFee(int bankOverdraftFee)
{
	this->bankOverdraftFee = bankOverdraftFee;
}

double CheckingAccount::getBalance()
{
	return balance;
}

double CheckingAccount::getOverdraftFeesOwed()
{
	return overdraftFeesOwed;
}

int CheckingAccount::getBankOverdraftFee()
{
	return bankOverdraftFee;
}

bool CheckingAccount::promptUser()
{
	std::cout << "Your balance: $" << std::fixed << std::setprecision(2) << balance << std::endl
			  << "Current overdraft fees owed: $" << std::fixed << std::setprecision(2) << overdraftFeesOwed << std::endl;
	std::string input;
	int choice;
	int amount;
	while (true) {
		std::cout << "Please choose an option:" << std::endl;
		std::cout << "1. Withdraw money" << std::endl;
		std::cout << "2. Deposit money" <<std:: endl;
		std::cout << "3. Pay your overdraft fees" << std::endl;
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
			std::cout << "How much would you like to deposit? Your current balance is $"
				<< std::fixed << std::setprecision(2) << balance << std::endl;
			std::cout << "Amount: $";
			std::getline(std::cin, input);
			ssAmount << input;
			ssAmount >> amount;

			payOverdraftFees(amount);
			break;
		case 4:
			return true;
			break;
		default:
			std::cout << "Please select a valid choice" << std::endl;
			break;
		}
	}
	return false;
}

void CheckingAccount::payOverdraftFees(int amount)
{
	if (amount > balance) {
		std::cout << "The amount exceeds what you currently have in your balance" << std::endl;
	}
	else if (amount < 0) {
		std::cout << "You cannot deposit a negative amount" << std::endl;
	}
	else {
		double newOverdraftFeesOwed = overdraftFeesOwed - amount;
		if (newOverdraftFeesOwed < 0) {
			// Overdrafts cleared
			overdraftFeesOwed = 0;
			// Take remaining money and put it back into balance
			balance += -1 * newOverdraftFeesOwed;
			std::cout << std::fixed << std::setprecision(2) << 
				"You deposited more than you owe. Your remaining overdraft fee is now $" 
					<< overdraftFeesOwed << " and your balance is $" << balance << std::endl;
		}
		else {
			balance -= amount;
			overdraftFeesOwed = newOverdraftFeesOwed;
			std::cout << "Successfully deposited $" << amount << ". You have a remaining overdraft fee of $"
			<< std::fixed << std::setprecision(2) << overdraftFeesOwed << "." << std::endl;
		}
	}
}

std::string CheckingAccount::printCSV()
{
	std::string csvString;
	std::stringstream ss;
	ss << username << "," << std::fixed << std::setprecision(2) << balance << "," << overdraftFeesOwed;
	ss >> csvString;
	return csvString;
}
