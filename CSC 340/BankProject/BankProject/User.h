#pragma once

#include <string>

class User
{
public:
	User();
	
	std::string getUsername();
	std::string getEmail();
	std::string getPassword();
	int getPin();
	std::string getGovernmentName();

	void setUsername(std::string);
	void setEmail(std::string);
	void setPassword(std::string);
	void setPin(int);
	void setGovernmentName(std::string);

	std::string printCSV();

private:
	std::string username;
	std::string email;
	std::string password;
	int pin;
	std::string governmentName;
};

