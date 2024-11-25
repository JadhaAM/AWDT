
// ### **Database Design**
// #### **Collections:**
// 1. **Employees**:
//    - Stores information about employees, including their projects.
//    - Example fields: `_id`, `name`, `designation`, `projects` (array of project IDs).

// 2. **Projects**:
//    - Stores information about projects.
//    - Example fields: `_id`, `name`, `type`, `duration` (in months), `employees` (array of employee IDs).

// ---

// ### **Sample Documents**
// #### **Employees Collection**

[
  {
    "_id": 1,
    "name": "Rajesh Patil",
    "designation": "Software Engineer",
    "projects": [101, 102]
  },
  {
    "_id": 2,
    "name": "Sneha Sharma",
    "designation": "Project Manager",
    "projects": [103, 104]
  },
  {
    "_id": 3,
    "name": "Amit Desai",
    "designation": "Data Scientist",
    "projects": [102, 105]
  },
  {
    "_id": 4,
    "name": "Neha Kulkarni",
    "designation": "UI/UX Designer",
    "projects": [101, 104]
  },
  {
    "_id": 5,
    "name": "Anil Kumar",
    "designation": "Backend Developer",
    "projects": [103]
  }
]

// #### **Projects Collection**
[
  {
    "_id": 101,
    "name": "E-Commerce Platform",
    "type": "Web Development",
    "duration": 6,
    "employees": [1, 4]
  },
  {
    "_id": 102,
    "name": "AI Chatbot",
    "type": "AI/ML",
    "duration": 4,
    "employees": [1, 3]
  },
  {
    "_id": 103,
    "name": "Inventory Management",
    "type": "Web Development",
    "duration": 3,
    "employees": [2, 5]
  },
  {
    "_id": 104,
    "name": "Mobile Banking App",
    "type": "Mobile Development",
    "duration": 5,
    "employees": [2, 4]
  },
  {
    "_id": 105,
    "name": "Data Analytics Dashboard",
    "type": "Data Science",
    "duration": 8,
    "employees": [3]
  }
]

// ### **Queries**

// #### 1. **List project names with a specific project type (e.g., "Web Development").**
db.projects.find({ type: "Web Development" }, { _id: 0, name: 1 });

// #### 2. **List projects with durations > 3 months.**

db.projects.find({ duration: { $gt: 3 } }, { _id: 0, name: 1, duration: 1 });

// #### 3. **Count employees working on a specific project (e.g., "AI Chatbot").**
// First, find the project ID for "AI Chatbot":
db.projects.findOne({ name: "AI Chatbot" }, { _id: 1 });
db.employees.countDocuments({ projects: 102 });

// #### 4. **List projects Mr. Patil is working on.**
// First, find the employee ID for "Rajesh Patil":
db.employees.findOne({ name: "Rajesh Patil" }, { _id: 1, projects: 1 });
db.projects.find({ _id: { $in: [101, 102] } }, { _id: 0, name: 1 });
