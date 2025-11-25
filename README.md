
                                                            Project Management System
Features
 User Authentication
•	Register new users
•	Secure login using JWT
•	Spring Security-based authentication
•	Password encryption using BCrypt
 Project Management
•	Create a project
•	Update project
•	Delete project
•	Fetch all projects of specific user
 Task Management
•	Create tasks under project
•	Update task
•	Change task status
•	Delete task
•	List all tasks of a project

  Technology Stack
Category	Technology
Language	Java 17
Framework	Spring Boot  4
Security	Spring Security + JWT
Database	MySQL
ORM	Spring Data JPA
Build Tool	Maven
Testing Tool	Postman
IDE	Eclipse

________________________________________

Authentication APIs
Register
POST /api/auth/register
Body:
{
  "name": "piyush",
  "email": "piyush@mail.com",
  "password": "12345"
}
Login
POST /api/auth/login
Body:
{
  "name": "piyush",
  "password": "12345"
}
Response includes JWT token:
{
  "token": "<JWT_TOKEN>"
}
________________________________________
 Project APIs (Protected)
Add this header in every request:
Authorization: Bearer <JWT_TOKEN>
Create Project
POST /api/projects
Get User Projects
GET /api/projects
Update Project
PUT /api/projects/{id}
Delete Project
DELETE /api/projects/{id}
________________________________________
 Task APIs (Protected)
Create Task
POST /api/tasks
Get Tasks by Project
GET /api/tasks/project/{projectId}
Update Task
PUT /api/tasks/{taskId}
Delete Task
DELETE /api/tasks/{taskId}

 
Authentication Flow (JWT)
User → /auth/login → Validate Credentials
       ↓
  Generate JWT Token
       ↓
Return Token to User
       ↓
User Sends Token in Authorization Header
       ↓
JwtFilter validates token per request
       ↓
Allow access to protected resources
________________________________________
 Project & Task Flow
Project Creation Flow
User → POST /projects → ProjectService → ProjectRepository → DB
Task Creation Flow
User → JWT → POST /projects/{id}/tasks → TaskService → TaskRepository → DB
________________________________________

