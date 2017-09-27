@echo Off

echo .

reg Query "HKLM\Hardware\Description\System\CentralProcessor\0" | find /i "x86" > NUL && set OS=32BIT || set OS=64BIT

IF %OS% == 32BIT (

	echo 32-bit detected!!!

)

IF %OS% == 64BIT (

	echo 64-bit detected!!!

)

echo.
echo Checking for valid openJDK installation...
javac > nul 2>&1

IF NOT %errorlevel% == 2 (

	echo.
	echo There appears to be no valid installation of openJDK!!!
	echo If you know for sure you have openJDK installed, make sure it is in your path
	start http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
	echo.
	exit /B 1

)

echo Valid openJDK installation found!!!
echo.

echo Compiling iOSDeviceQuery.java ... ( 1/2 )
javac iOSDeviceQuery.java -d ../ > nul 2>&1

IF NOT %errorlevel% == 0 (

	echo.
	echo Failed to compile iOSDeviceQuery!!!
	echo.
	exit /B 1

)

echo Compiling iOSKeyQuery.java ... ( 2/2 )

javac iOSKeyQuery.java -d ../ > nul 2>&1

IF NOT %errorlevel% == 0 (

	echo.
	echo Failed to compile iOSKeyTool!!!
	echo.
	exit /B 1

)

echo.
echo Compilation successfull!!!
exit /B 1
