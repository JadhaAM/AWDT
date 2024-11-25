// Model a document database for employees and departments.
// Insert at least 5 documents for each collection.
// Queries:
// Find the employee with the highest salary.
// Identify the biggest department by employee count.
// Display department-wise employee information using a cursor.
// List employees in the Sales department earning > 50,000.

// Database Setup and Data Insertion
// #### 1. Insert Employees

db.employees.insertMany([
    { _id: 1, name: "Alice", salary: 60000, department_id: 1, position: "Manager", email: "alice@example.com" },
    { _id: 2, name: "Bob", salary: 45000, department_id: 2, position: "Engineer", email: "bob@example.com" },
    { _id: 3, name: "Charlie", salary: 70000, department_id: 1, position: "Senior Manager", email: "charlie@example.com" },
    { _id: 4, name: "David", salary: 52000, department_id: 3, position: "Analyst", email: "david@example.com" },
    { _id: 5, name: "Eve", salary: 48000, department_id: 2, position: "Engineer", email: "eve@example.com" }
]);

// #### 2. Insert Departments
db.departments.insertMany([
    { _id: 1, name: "Sales", location: "Building A" },
    { _id: 2, name: "Engineering", location: "Building B" },
    { _id: 3, name: "Finance", location: "Building C" },
    { _id: 4, name: "HR", location: "Building D" },
    { _id: 5, name: "Marketing", location: "Building E" }
]);


// ### Queries
// #### 1. Find the Employee with the Highest Salary
db.employees.find().sort({ salary: -1 }).limit(1);

// #### 2. Identify the Biggest Department by Employee Count
db.employees.aggregate([
    { $group: { _id: "$department_id", employee_count: { $sum: 1 } } },
    { $sort: { employee_count: -1 } },
    { $limit: 1 },
    { 
        $lookup: {
            from: "departments",
            localField: "_id",
            foreignField: "_id",
            as: "department_info"
        }
    }
]);

// #### 3. Display Department-Wise Employee Information Using a Cursor
db.departments.find().forEach(function(department) {
    print("Department:", department.name);
    db.employees.find({ department_id: department._id }).forEach(function(employee) {
        print("  Employee:", employee.name, "- Position:", employee.position, "- Salary:", employee.salary);
    });
});

// #### 4. List Employees in the Sales Department Earning > 50,000
db.employees.aggregate([
    { 
        $lookup: {
            from: "departments",
            localField: "department_id",
            foreignField: "_id",
            as: "department_info"
        }
    },
    { $unwind: "$department_info" },
    { $match: { "department_info.name": "Sales", salary: { $gt: 50000 } } },
    { $project: { name: 1, salary: 1, position: 1, _id: 0 } }
]);

