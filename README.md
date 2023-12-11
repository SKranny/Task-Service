Use Dockerfile to run the application. 

Specify variables SECRET _KEY - secret key for token, DB_PASSWORD and DB_USERNAME - database user authorization, TASK_SERVICE_DATABASE_URL- url of your PostgreSQL database,  default values: 
- SECRET_KEY = secret;
- DB_PASSWORD = password;
- DB_USERNAME = postgres;
- TASK_SERVICE_DATABASE_URL = jdbc:postgresql://localhost:5432/task_service;

The Application is running on port 8081.
