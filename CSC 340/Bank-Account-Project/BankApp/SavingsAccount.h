#pragma once

#include <iostream>
#include <iomanip>
#include <string>
#include <sstream>

#include "Account.h"

class SavingsAccount : public Account
{
public:
	SavingsAccount();
	SavingsAccount(const std::string&, double, double);
	~SavingsAccount();

	// Override base class methods
	void withdraw(double) override;
	void deposit(double) override;
	void setUsername(const std::string&) override;
	std::string getUsername() override;

	void setBalance(double);
	void setInterestRate(double);

	double getBalance();
	double getInterestRate();

	// Functions that print out and takes input from the user
	void promptUser();
	double calculateInterestRate();
	std::string printCSV();

private:
	std::string username;
	double balance;
	double interestRate;
};