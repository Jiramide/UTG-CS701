@echo off
set lang=%1
set makeArgs=%2

echo [%date% %time%] %lang% Makefile running: 

cd src/%lang% && mingw32-make %makeArgs% && ^
echo [%date% %time%] %lang% Makefile complete. || ^
echo [%date% %time%] %lang% Makefile failed.

cd ../..