#  Full-Stack Mini App (Task Tracker)
## About
Task Tracker is a simple full-stack web application to manage tasks.
Users can:
- Create a task
- View all tasks
- Update a task
- Change task status
- Delete a task
- Filter tasks by status
- Sort tasks by due date
---
## Tech Stack
### Frontend
- React
- Vite
### Backend
- Java
- Spring Boot
### Database
- MySQL
### Tools
- Postman
- Git
- GitHub
---
## Project Structure
```
task-tracker/
│
├── backend/
├── frontend/
└── docker-compose.yml
```
---
## Database Setup
Create a MySQL database.
```sql
CREATE DATABASE task_tracker;
```
Update the database username and password in the backend configuration if needed.
---
## Run the Backend
Open the backend folder.
```bash
cd backend
```
Run the application.
```bash
mvn spring-boot:run
```
The backend runs at:
```
http://localhost:8080
```
---
## Run the Frontend
Open the frontend folder.
```bash
cd frontend
```
Install the required packages.
```bash
npm install
```
Start the React application.
```bash
npm run dev
```
The frontend runs at:
```
http://localhost:5173
```
---
## API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/tasks | Get all tasks |
| GET | /api/tasks/{id} | Get a task by ID |
| POST | /api/tasks | Create a new task |
| PUT | /api/tasks/{id} | Update a task |
| DELETE | /api/tasks/{id} | Delete a task |
---
## Features
- Create a task
- View all tasks
- Update a task
- Delete a task
- Change task status
- Filter tasks by status
- Sort tasks by due date
- Pagination
- Server-side validation
- REST API
- Project and Task relationship using a foreign key
---
## Database Design
This project uses two related tables.
- **Projects**
- **Tasks**
Each task belongs to one project using a foreign key relationship.
---
## Design Notes
I kept the project simple and easy to understand.
The backend is organized using controllers, services, repositories, and entities.
The frontend uses React to communicate with the backend through REST APIs.
If I continue improving this project, I would add:
- JWT authentication
- Better UI design
- Search functionality
- Better exception handling
- CI/CD pipeline
- More automated tests
---
## Testing
The application was tested manually using Postman and the React frontend.
The following functions were tested:
- Create a task
- View all tasks
- Update a task
- Change task status
- Delete a task
- Filter tasks
- API responses and validation
---
## AI Assistant Usage
I used Claude AI during the development of this project.
Claude helped me with:
- Understanding the assignment requirements
- Solving setup and configuration issues
- Debugging errors
- Getting guidance while implementing some features
I reviewed the suggestions, made the required changes myself, and tested the application manually using Postman and the React frontend.
---
## Future Improvements
- User authentication
- Search tasks
- Dashboard
- Notifications
- File attachments
- Responsive UI improvements
- More automated tests
---
## Author

**Gopika S**
