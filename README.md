# RewardsCalculator

## How to run the project
## Importing the project to local system

1. Using the given link open the github repository. LInk - "https://github.com/Cap-N3M0/RewardsCalculator/"
2. Copy the url and use
     > git clone "https://github.com/Cap-N3M0/RewardsCalculator"
     command to clone the project into the local machine.
3. Open Eclipse IDE and File > Open project from File System. And navigate to the project directory.

Great, the project is now imported to your local machine.

## Configuring the project beforing starting

1. Make sure the MySql server is installed.
2. Start MySQL server and create new database for the project like "rewardsCalculator".
3. Copy the connection string to the database created.
4. In the project navigate to the "RewardsCalculator\src\main\resources\application.properties" file and open it.
   -  update the spring.database.url, spring.database.user, spring.database.password.
   -  while running the project
     >  for the very first time, use spring.jpa.hibernate.ddl-auto = create-drop
     >  and second time onwards use spring.jpa.hibernate.ddl-auto = update
5. Run the project and use the SQL script place in "RewardsCalculator\src\main\resources" folder to insert dummy data.

## To make sure every thing is working fine, use the below api request to fetch results

1. localhost:8080/customers/1001/rewards
2. localhost:8080/customers/1002/rewards
3. localhost:8080/customers/1003/rewards

# Congratulations you have successfully configured the project.

<br/><br/>
# Screenshots for reference
![image](https://github.com/user-attachments/assets/4c4d5ec3-2a1e-4fd5-8893-1d96f74e57e8) 
<br/>
![image](https://github.com/user-attachments/assets/6f7ef1d1-5c1e-41b7-b603-2381cbd3bc84)

<br/>
### Invalid Customer ID
![image](https://github.com/user-attachments/assets/9b7fccfd-44c7-4e40-87f6-dad73dd65ac7)

<br/>
### Validation Failure for customer ID
![image](https://github.com/user-attachments/assets/a88531cf-b73b-413d-9d4b-ae42eaa3924d)

### logs on console
![image](https://github.com/user-attachments/assets/785b81c8-08eb-46fc-8097-a6ab4ea97e2e)





