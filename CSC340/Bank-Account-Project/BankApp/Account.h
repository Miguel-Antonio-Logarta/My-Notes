#pragma once
#include <string>
#include <vector>

// Abstract class that has withdraw and deposit functions
class Account
{
public: 
  Account();
	virtual void deposit(double amount) = 0;
	virtual void withdraw(double amount) = 0;
  double getBalance() const;
  static std::vector<Account*> accounts;


	virtual void setUsername(const std::string&) = 0;
	virtual std::string getUsername() = 0;
  virtual void transferToAccount(Account* destinationAccount, double amount);
  virtual void transferToUser(const std::string& recipientUsername, double amount);
	//virtual void outputFields() = 0;
};

