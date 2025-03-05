@echo off 
echo Running JDBC Project... 
mvn clean package 
java -jar target/JDBCProject-1.0-SNAPSHOT.jar 
pause 
