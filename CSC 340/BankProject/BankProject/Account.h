#pragma once
#include <string>

// Abstract class that has withdraw and deposit functions
class Account
{
public: 
	virtual void deposit(double amount) = 0;
	virtual void withdraw(double amount) = 0;

	virtual void setUsername(const std::string&) = 0;
	virtual std::string getUsername() = 0;
	//virtual void outputFields() = 0;
};

