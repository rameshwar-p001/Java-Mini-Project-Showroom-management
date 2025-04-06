@echo off
echo Compiling...
javac -cp "lib/sqlite-jdbc-3.49.1.0.jar" -d bin src\*.java

echo Running app...
java -cp ".;bin;lib/sqlite-jdbc-3.49.1.0.jar" Main

pause
