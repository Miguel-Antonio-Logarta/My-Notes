#include "Account.h"
#include <iostream>
#include <iomanip>
#include <vector>

std::vector<Account*> Account::accounts;

Account::Account()
{
}

void Account::deposit(double amount)
{
}

void Account::withdraw(double amount)
{
}

void Account::setUsername(const std::string& newUsername)
{
}

std::string Account::getUsername()
{
	return std::string{};
}

double Account::getBalance() const
{
    return 0.0;
}



void Account::transferToAccount(Account* destinationAccount, double amount)
{
    if (getBalance() >= amount) {
        withdraw(amount);
        destinationAccount->deposit(amount);
        std::cout << std::fixed << std::setprecision(2)
                  << "Successfully transferred $" << amount
                  << " to the account of " << destinationAccount->getUsername() << std::endl;
    } else {
        std::cout << "Insufficient balance to transfer funds." << std::endl;
    }
}

void Account::transferToUser(const std::string& recipientUsername, double amount)
{
    // Find the recipient account based on the username
    Account* recipientAccount = nullptr;
    for (Account* account : accounts) {
        if (account->getUsername() == recipientUsername) {
            recipientAccount = account;
            break;
        }
    }

    if (recipientAccount) {
        if (getBalance() >= amount) {
            withdraw(amount);
            recipientAccount->deposit(amount);
            std::cout << std::fixed << std::setprecision(2)
                      << "Successfully transferred $" << amount
                      << " to the account of " << recipientAccount->getUsername() << std::endl;
        } else {
            std::cout << "Insufficient balance to transfer funds." << std::endl;
        }
    } else {
        std::cout << "Recipient account not found." << std::endl;
    }
}