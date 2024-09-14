#pragma once

#include <iostream>
#include <iomanip>
#include <string>
#include <sstream>

#include "Account.h"

class CreditAccount : public Account
{
public:
	CreditAccount();
	CreditAccount(const std::string&, int, double);
	~CreditAccount();

	// Override base class methods
	void withdraw(double) override;
	void deposit(double) override;
	void setUsername(const std::string&) override;
	std::string getUsername() override;

	void setCreditLimit(double);
	void setCreditUsed(double);

	double getCreditLimit();
	double getCreditUsed();

	void promptUser();
	std::string printCSV();

private:
	std::string username;
	int creditLimit;
	double creditUsed;
};

