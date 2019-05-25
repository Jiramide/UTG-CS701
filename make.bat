@echo off
set lang=%1

echo [%date% %time%] %lang% Makefile running: 

cd src/%lang% && mingw32-make %2 && ^
echo [%date% %time%] %lang% Makefile complete. || ^
echo [%date% %time%] %lang% Makefile failed.

cd ../..