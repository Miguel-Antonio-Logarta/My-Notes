#pragma once

#include <iostream>
#include <iomanip>
#include <sstream>
#include <string>
#include <cmath>

#include "Account.h"

class CheckingAccount : public Account
{
public:
	CheckingAccount();
	CheckingAccount(const std::string&, double, double, int, int);
	~CheckingAccount();

	// Override base class methods
	void withdraw(double) override;
	void deposit(double) override;
	void setUsername(const std::string&) override;
	std::string getUsername() override;

	void setBalance(double);
	void setOverdraftFeesOwed(double);
	void setBankOverdraftFee(int);

	double getBalance();
	double getOverdraftFeesOwed();
	int getBankOverdraftFee();

	// Functions that print to the console and take user input
	bool promptUser();
	void payOverdraftFees(int);

	std::string printCSV();

private:
	std::string username;
	double balance;
	double overdraftFeesOwed;
	int overdraftLimit;
	int bankOverdraftFee;
};

