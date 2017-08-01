
# Reminder Service


APIs exposed
==========
Below are the APIs exposed by this project
- API to add a reminder
- API to update a reminder
- API to get a reminder
- API to get get reminders filtered by due date and/or status.


Build Management
-----------
Uses gradle to build the application. Clone the project on the workspace and then run execute the following command from root directory. It clears all the temporary files, compiles and generates the war file.

- gradle clean war


Test cases
----------
Due to the time constraint, only few integration test cases are developed mainly to test the 'add a reminder' functiomality.

----------------------
Supported APIs 
------------------
Due to time constraint, did not use the API documentation tool like Swagger. Below is the brief description of the APIs with the sample request/response (does NOT provide request/response for all success/failure scenarios):



- API to add a reminder

```
Http Method: POST
http://<hostname>:<port>/v1/reminderservice/reminder (example - http://localhost:8080/v1/reminderservice/reminder)
{
  "name":"My first reminder",
  "description":"First reminder's description",
  "due_date":"2017-08-30",
  "status":"NOTDONE"
}

Successful Response:
{
	"message": "Reminder created successfully",
	"id": 1
}

Un-successful response
	{
	"statusMessage": "Bad Request",
	"statusCode": 400,
	"message": "Reminder name can't be null or empty"
	}

```
- API to update a reminder
```
Http Method: PUT
http://<hostname>:<port>/v1/reminderservice/reminder/{id}  (example - http://localhost:8080/v1/reminderservice/reminder/1)
{
	"name":"My modified first reminder",
	"description":"First reminder's description",
	"due_date":"2017-08-30",
	"status":"NOTDONE"
}

Successful Response:
{
	"message": "Reminder updated successfully",
	"id": 1
}


Un-successful Response
{
	"statusMessage": "Bad Request",
	"statusCode": 400,
	"message": "Reminder status should not be null"
}
```

- API to retrieve reminders
1) Retrieve all reminders (no filter)
```
Http Method: GET
https://<base url>:<port>/v1/reminderservice/reminder
Successful Response:

[
	{
		"name": "My first reminder",
		"description": "First reminder's description",
		"status": "NOTDONE",
		"links":[
		{
		"rel": "self",
		"href": "http://localhost:8080/v1/reminderservice/reminder/1"
		}
		],
		"due_date": "2017-08-30",
		"id": 1
	},
	{
		"name": "My second reminder",
		"description": "Second reminder's description",
		"status": "NOTDONE",
		"links":[
		{
		"rel": "self",
		"href": "http://localhost:8080/v1/reminderservice/reminder/2"
		}
		],
		"due_date": "2017-08-30",
		"id": 2
	},
	{
		"name": "My third reminder",
		"description": "Third reminder's description",
		"status": "NOTDONE",
		"links":[
		{
		"rel": "self",
		"href": "http://localhost:8080/v1/reminderservice/reminder/3"
		}
		],
		"due_date": "2017-08-29",
		"id": 3
	},
	{
		"name": "My third reminder",
		"description": "Third reminder's description",
		"status": "NOTDONE",
		"links":[
		{
		"rel": "self",
		"href": "http://localhost:8080/v1/reminderservice/reminder/4"
		}
		],
		"due_date": "2017-08-29",
		"id": 4
	}
]


Un-successful Response:
{
	"statusMessage": "Internal Server Error",
	"statusCode": 500,
	"message": "We were unable to process your request. Please try again later."
}


```
2) Retrieve all reminders after applying filters
```
Http Method: GET
https://<base url>:<port>/v1/reminderservice/reminder?status={DONE/NOTDONE}&dueDate={dueDate} (example - http://localhost:8080/v1/reminderservice/reminder?status=NOTDONE&dueDate=2017-08-30)

Successful Response:
[
	{
		"name": "My first reminder",
		"description": "First reminder's description",
		"status": "NOTDONE",
		"links":[
		{
		"rel": "self",
		"href": "http://localhost:8080/v1/reminderservice/reminder/1"
		}
		],
		"due_date": "2017-08-30",
		"id": 1
	},
	{
		"name": "My second reminder",
		"description": "Second reminder's description",
		"status": "NOTDONE",
		"links":[
		{
		"rel": "self",
		"href": "http://localhost:8080/v1/reminderservice/reminder/2"
		}
		],
		"due_date": "2017-08-30",
		"id": 2
	}
]

Un-successful Responses:

{
	"statusMessage": "Bad Request",
	"statusCode": 400,
	"message": "Status code should be either DONE or NOTDONE"
}

{
	"statusMessage": "Bad Request",
	"statusCode": 400,
	"message": "Due date is invalid. It should be in yyyy-mm-dd format"
}


```
- API to retrieve a reminder
```
Http Method: GET
https://<hostname>:<port>/v1/reminderservice/reminder/{id}

Successful Response:
{
	"name": "My first reminder",
	"description": "First reminder's description",
	"status": "NOTDONE",
	"due_date": "2017-08-30",
	"id": 1
}

Un-successful response:
{
	"statusMessage": "Bad Request",
	"statusCode": 400,
	"message": "No record found. Please check the reminder id"
}
```




```

More APIs provided by the SpringBoot Actuator

```
	Health (Application status UP|Down):		http://<hostname>:<port>/health
	Metrics and Counters: 				http://<hostname>:<port>/metrics
	Thread Dump:					http://<hostname>:<port>/dump
	Trace (Few of last HTTP requests):		http://<hostname>:<port>/trace
	Configuration Properties:			http://<hostname>:<port>/env

```


--------------------------------
Technology Stack
-------------------------------
- SpringBoot
- Spring Hateaos : Hateaos link created for certain API response. For example, the call to GET request for reminders with optional filters.
- JPA
- HSQL DB : In memory relational database used to perform CRUD operation on reminder. Can be easiliy replaced with more sophisticated database.
- REST Assured for integration testing.




Local Environment Setup:
------------------------------------------------------------------------------------------------------------------------

- Clone the repo from the GitLab.

- Import it as Gradle project to your IDE 

- For running the application in the embedded tomcat container follow the below command:

```sh			
	gradle bootRun  -Dspring.profiles.active=<dev|stage|prod>
```

- To make sure embedded container is up and running, use the following URL to check the state of application.
	`http://<hostname>:<port>/health`

--------------------------------
Limitation in this release
-------------------------------
Below are some of the limitations in this release.
1. Logging not implemented (per instruction)
2. Non-consistent implementation of Hateaos.
3. No unit testcases. Few interation test cases are developed though.
4. Uses in-memory DB which has the life span sames as that of the JVM. But this application uses JPA and hence can easility migrate to more sophisticated RDBMS.
5. No API documentation using the tools like Swagger.
6. No metrics collection.
7. API security not implemented. Could be implemented using JWT token, OAuth token etc.
8. Endpoint does not use secure http (https) protocol.
9. Does some basic validation. Few validations like 'due date should not be a past date' not implemented.
10. Currently accepts the due date in format 'yyyy-mm-dd'. May be enhanced to accept the due date in the UTC format yyyy-mm-ddThh:mm:ssZ
