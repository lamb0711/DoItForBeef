# 06 Do it for Beef

## 1. How to import database system
  - First, make a new database 'DB' to your local mysql server.
  
  - Import the exported database, "DoItForBeef.sql" using following code:
  
    mysql -u root -p DB < DoItForBeef.sql
    
  - Go to your mysql server, and check whether all the tables are loaded.
  
## 2. How to execute the program
  - Pull the current repository to your local machine.
  
  - Open the current project, change the password of your local mysql server on line 18 and check whether your imported database name is 
  same as the value stored in url on line 21. It should be
  
    static String url = "jdbc:mysql://localhost/DB";

  - Build and Run the program on Main.java.
  
  - Remeber, __DO NOT USE KOREAN__ OR OTHER LANGUAGES. PLEASE follow the manual as shown on the program.
  
  - Our team use Gradle, so if it is not working, please check Import->Import... -> Gradle -> Existing Gradle Project. 
  After clicking next button, check your root directory is correct.
