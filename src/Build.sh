#!/bin/bash

#Color stuff
INFO="\e[0m\e[97;1m[\e[0m\e[94;1m ${0} \e[0m\e[97;1m]\e[0m \e[97m"
SUCCESS="\e[0m\e[97;1m[\e[0m\e[92;1m ${0} \e[0m\e[97;1m]\e[0m \e[97m"
FAIL="\e[0m\e[97;1m[\e[0m\e[91;1m ${0} \e[0m\e[97;1m]\e[0m \e[97m"
KEY1="\e[0m(\e[93;1m"
KEY2="\e[0m)"

echo
echo -e "${INFO}Checking for valid openJDK installation..."
command -v javac

if [ [ ${?} != 0 ] ]; then

	echo
	echo -e "${FAIL}There appears to be no valid installation of openJDK!!!"
	echo
	exit 1

fi

echo
echo -e "${SUCCESS}Valid openJDK installation found!!!"
echo

echo -en "${INFO}Compiling... ${KEY1} 1/2 ${KEY2}"
javac iOSDeviceQuery.java -d ../

if [ [ ${?} != 0 ] ]; then

	echo
	echo -e "${FAIL}Failed to compile iOSDeviceQuery!!!"
	echo
	exit 1

fi

echo -en "\b\b\b\b\b2/2 ${KEY2}"

javac iOSKeyTool.java -d ../

if [ [ ${?} != 0 ] ]; then

	echo
	echo -e "${FAIL}Failed to compile iOSKeyTool!!!"
	echo
	exit 1

fi

echo -e "${SUCCESS}Compilation successfull!!!"
exit 0