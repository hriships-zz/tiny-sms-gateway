tiny-sms-gateway
==================

A android library project , which  turn the android phone into SMS gateway!

Prerequisite:
=============

1) Android phone with atleast one registered Google account

2) php-mysql stack on server

How to setup project:
=====================

1) Go to https://code.google.com/apis/console/ 

2) Login and create new project by filling simple form

3) Click overview tab and copy project number

On Android Project:
===================

1) Goto Init class in main package of library project

2) Assign project number to appid global variable

3) Include this library project in your main project and build main project

On Server:
==========

1) Create database and import the gateway.sql file into  database from this repository

2) In php goto to lib->config.php and configure your database parameters

3) Goto Google API console, click on API access option, copy the API key

4) Goto to lib->SmsManager.php, in SmsManager class assign the API key copied from in constructor function of class
   
   Exp:   $this->key = 'AIzaSyBtvXEVlpJO8pVyERs2_RXXXXXXXXXXX';

5) Initiate this SmsManager class and call sendSMS() method.

Done!

Your rock solid SMSgateway is ready now.



Please be open for any Suggestion and contribution.
