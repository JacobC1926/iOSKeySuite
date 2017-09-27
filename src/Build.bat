@echo Off

echo.
echo Checking for valid openJDK installation...
javac > nul 2>&1

IF NOT %errorlevel% == 2 (

	echo.
	echo There appears to be no valid installation of openJDK!!!
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
