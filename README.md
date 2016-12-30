# gurukulaSelin
Selenium Test Automation

Resources	Java 1.8
Dependencies	Environment needs to be ready 

The  main focus area of gurukulaSelin project test strategy is to test main features.

## Functions that will be tested as follows:

1. The application is used to maintain Branches and Staff information (can be seen from Entities Menu). 
2. You can view, edit, delete and query both Staff and Branches. 
3. Further, Pagination is enabled when viewing the Staff/Branch. 
4. The logged in account information can be viewed/edited from the Account Menu. 
5. Login/Logout as existing user
6. Register a new User

## Test Suites & Test Cases

###### 1.BranchTests</br>
1.1 TC001_checkNewBranchTest --> Adds new branch to system</br>
1.2 TC002_checkNewBranchNegativeTest --> Checks validation errors when incorrect input is given for branch form</br>
1.3 TC003_deleteBranchTest --> Checks validation errors when incorrect input is given for branch form</br>
1.4 TC004_editBranchTest--> Checks if data can be edited on branches.</br>
1.5 TC005_searchBranchTest --> Checks search function </br>
###### 2.HomePageTests</br>
2.1 TC001_checkHomePageTest --> Checks if homepage can be opened successfully.</br>
###### 3.LoginTests</br>
3.1  TC001_checkHomePageTest --> Checks homepage</br>
3.2  TC002_loginPositiveTest --> Complete login function successfully</br>
3.3  TC003_loginNegativeTest --> Check validation error when logging in</br>
3.4  TC004_logOutTest --> Check whether logout works</br>
###### 4.PaginationTests</br>
4.1  TC001_checkPaginationTest --> Checks whether pagination works with 25 entries on staff page</br>
4.2  TC002_paginationTest --> Checks whether pagination go previous page works with 21 entries</br>
###### 5.RegistrationTests</br>
5.1  TC001_registerANewAccountTest --> Register new account successfully (This test will be failed, because of functionality does not work! It is a bug!)</br>
5.2  TC002_registerANewAccountNegativeLoginTest --> Check the validation for registration fail message</br>
5.3  TC003_registerANewAccountNegativeEmailTest --> Checks if user enters incorrect email address</br>
5.4  TC004_registerANewAccountNegativePasswordMatchTest --> Checks if user enters incorrect password</br>
###### 6.StaffTests</br>
6.1  TC001_checkStaffTest --> Checks if user can create staff successfully</br>
6.2  TC002_checkStaffDetailTest --> Control if the informations are correct in staf detail page</br>
6.3  TC003_checkStaffNegativeTest --> Check the validation messages when creating staff</br>
6.4  TC004_editStaffTest --> Checks if user can edit data on staff</br>
###### 7.UserAccountTests</br>
7.1  TC001_updateUserAccountInfoTest --> Checks if user can update account data</br>
7.2  TC002_updateUserAccountInfoNegativeTest --> Checks the validations on update user account page</br>

## TEST EXECUTION

###### To run the entire test</br>
mvn test
