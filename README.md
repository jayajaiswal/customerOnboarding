# customerOnboardingApp

## About the project
A Customer onboarding platform where a customer can register himself if he is above 18 years of age and does not belong to these countries (Netherlands, Belgium & Germany).
<br>He can also login to the system using the credentials recieved by him.<br>
An account overview is also available with an information like account type, balance, currency etc.
<br>

## Tools Used 

* Java (openjdk 20)
* Maven
* Springboot 3.0
* Postman 
* Swagger 
* Docker
* MySql

## Setting up the project

1. Clone the project to your local directory.
2. Add java to your project JDK.
3. Install docker desktop to run containers.
4. Go to your project directory and run _bash run.sh_ in the terminal to set up docker containers for the application and mysql.
5. This will also run the _schema.sql_ file which creates the tables "Account" and "Customer".
6. Application is started and available at http://localhost:8082/loyalbank
7. Swagger ui can be accessed on http://localhost:8082/swagger-ui/index.html

## REST API Endpoints

1. http://localhost:8082/register<br>
   This endpoint reegisters a customer after performing validations and also creates an account for him. A random password is also generated for the customer.
    
2. http://localhost:8082/logon<br>
   This endpoint validates the username and password and allows customer to login to the system.
   
3. http://localhost:8082/overview<br>
   This endpoint gives an account overview of the customer.

## To run unit tests<br>

1. Create a MySql schema with username and password as 'root'.
2. Change _spring.profiles.active_ from _docker_ to _local_
3. Right click on project and click on Run 'All Tests'.
