# Payslip application with Java and AngularJS

A payslip generator application reads the employee info from CSV and exports the payslip as CSV.

## Assumptions

- Payslip dates are validated for the current year. It has to be the first day and the last day of the month.
- Payslip request dates can be more than one month. Ex: 01 March – 31 May
- The end month can not be less than the start month.
- Lines should be split by comma and be ordered as first name, last name, annual salary, super rate (%), payment date. 
- Payment date should be in the format of 01 March – 31 March else validation exception will be thrown
- Multiple line of employees are accepted and result CSVs would return separately.
- Tax rates are configurable and addable from tax rate UI. 
- Only minimum annual salary is kept for each tax rate record. The app finds the nearest match.

## Prerequisites

* Maven
* Java 8
* Bower

## Installation & Configuration

To keep it simple I kept the frontend in the src main resources static folder
Used hsql as a DB and the application starts with initial data according to data.sql
*	Run-> bower update from payslip-service/src/main/resources/static
*	Run-> mvn clean install (junit test will run also “mvn test” can be run)
*	Run-> java -jar payslip-service-0.0.1-SNAPSHOT.jar to run the application
*	Application runs on localhost:8080. port can be changed by providing external application.properties



## Testing

 run the following from your Command Prompt or Terminal:
 
 mvn test

Now when you visit **http://localhost:8080** from your web browser you will be able to use the application Upload csv and export. 

