# 06 Do it for Beef

## 1. How to import database system

  - First, make a new database 'DB' to your local mysql server. The name of database depends on the user, __BUT__ we implement the program with 'DB'.
  
  - So if you want to use the different database name, please change the last part of String __url__ on the second code block in this page.
  
  - Import the exported database in this repository, "DoItForBeef.sql" using following code:
~~~  
    mysql -u root -p DB < DoItForBeef.sql
~~~    
  
  - __Remember__, you need to make database in your local mysql server first. 
 
  - Go to your mysql server, and check whether all the tables are loaded.
  
## 2. How to execute the program

  - Pull the current repository to your local machine.
  
  - Open the current project, __change the password of your local mysql server__ on line 18 in __DBA.java__ and check whether your imported database name is same as the value stored in url on line 21 in __DBA.java__. It should be

~~~Java
    static String url = "jdbc:mysql://localhost/DB";
~~~

  - Build and Run the program on Main.java.
  
  - Remeber, __DO NOT USE KOREAN__ OR OTHER LANGUAGES. PLEASE follow the manual as shown on the program.
  
  * Our team use Gradle, so if it is not working, please check Import -> Import... -> Gradle -> Existing Gradle Project. 
  After clicking few next buttons, check your root directory is correct.
