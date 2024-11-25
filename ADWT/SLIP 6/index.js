// ### **Sample Documents**
// #### **Customers Collection**
[
  {
    "_id": 1,
    "name": "Amit Sharma",
    "age": 35,
    "email": "amit.sharma@example.com",
    "policy_id": 101
  },
  {
    "_id": 2,
    "name": "Komal Patil",
    "age": 28,
    "email": "komal.patil@example.com",
    "policy_id": 102
  },
  {
    "_id": 3,
    "name": "Rajesh Deshmukh",
    "age": 42,
    "email": "rajesh.deshmukh@example.com",
    "policy_id": 101
  },
  {
    "_id": 4,
    "name": "Sneha Kulkarni",
    "age": 30,
    "email": "sneha.kulkarni@example.com",
    "policy_id": 103
  },
  {
    "_id": 5,
    "name": "Anil Kumar",
    "age": 50,
    "email": "anil.kumar@example.com",
    "policy_id": 102
  }
]

// #### **Policies Collection**
[
  {
    "_id": 101,
    "name": "Jeevan Anand",
    "premium": 15000,
    "payment_frequency": "Yearly"
  },
  {
    "_id": 102,
    "name": "Komal Jeevan",
    "premium": 12000,
    "payment_frequency": "Half Yearly"
  },
  {
    "_id": 103,
    "name": "Child Future Plan",
    "premium": 8000,
    "payment_frequency": "Monthly"
  },
  {
    "_id": 104,
    "name": "Term Life Plan",
    "premium": 10000,
    "payment_frequency": "Quarterly"
  },
  {
    "_id": 105,
    "name": "Health Insurance",
    "premium": 6000,
    "payment_frequency": "Monthly"
  }
]

// ### **Queries**
// #### 1. **List customers with the "Komal Jeevan" policy.**
// First, find the `_id` for the "Komal Jeevan" policy:

db.policies.findOne({ name: "Komal Jeevan" }, { _id: 1 });
db.customers.find({ policy_id: 102 }, { _id: 0, name: 1 });

// #### 2. **Display the average premium amount.**
// Use the aggregation framework to calculate the average premium:

db.policies.aggregate([
  { $group: { _id: null, avgPremium: { $avg: "$premium" } } }
]);

// #### 3. **Increase premium by 5% for "Monthly" policies.**
// Find and update policies with "Monthly" payment frequency:
db.policies.updateMany(
  { payment_frequency: "Monthly" },
  { $mul: { premium: 1.05 } }
);
db.policies.find({ payment_frequency: "Monthly" }, { _id: 0, name: 1, premium: 1 });

// #### 4. **Count customers with "Half Yearly" policies.**
// First, find the `_id` for policies with "Half Yearly" payment frequency:
db.policies.findOne({ payment_frequency: "Half Yearly" }, { _id: 1 });
db.customers.countDocuments({ policy_id: 102 });

