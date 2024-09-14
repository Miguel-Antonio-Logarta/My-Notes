#include <iostream>
#include <vector>
#include <string>
#include <string_view>

#include "LinkedList.h"
#include "Node.h"
#include "Bank.h"

int main() {
	const std::string usersFile{ "users.csv" };
	const std::string checkingAccountsFile{ "checkingAccounts.csv" };
	const std::string savingsAccountsFile{ "savingsAccounts.csv" };
	const std::string creditAccountsFile{ "creditAccounts.csv" };

	Bank bank(usersFile, checkingAccountsFile, savingsAccountsFile, creditAccountsFile);
	bank.start();
	
	return 0;
}