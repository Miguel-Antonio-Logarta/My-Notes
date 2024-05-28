#include "User.h"

User::User() : username{ "" }, email{ "" }, password{ "" }, pin{ 0 }, governmentName{ "" }
{
}

std::string User::getUsername()
{
	return username;
}

std::string User::getEmail()
{
	return email;
}

std::string User::getPassword()
{
	return password;
}

int User::getPin()
{
	return pin;
}

std::string User::getGovernmentName()
{
	return governmentName;
}

void User::setUsername(std::string newUsername)
{
	username = newUsername;
}

void User::setEmail(std::string newEmail)
{
	email = newEmail;
}

void User::setPassword(std::string newPassword)
{
	password = newPassword;
}

void User::setPin(int newPin)
{
	pin = newPin;
}

void User::setGovernmentName(std::string newGovernmentName)
{
	governmentName = newGovernmentName;
}

std::string User::printCSV()
{
	std::string csvString = username + ","
		+ email + ","
		+ password + ","
		+ std::to_string(pin) + "," 
		+ governmentName;
	return csvString;
}

