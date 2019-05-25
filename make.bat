@echo off
set /p lang=Input which language directory to make: 
set /p makeArgs=Makefile arguments: 

echo [%date% %time%] %lang% Makefile running: 

cd src/%lang% && mingw32-make %makeArgs% && ^
echo [%date% %time%] %lang% Makefile complete. || ^
echo [%date% %time%] %lang% Makefile failed.

cd ../..