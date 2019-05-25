@echo off
set /p lang=Input which language directory to make: 
set /p makeArgs=Makefile arguments: 
cd src/%lang% && mingw32-make %makeArgs%
cd ../..
echo Makefile running: 
