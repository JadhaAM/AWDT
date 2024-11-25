
// #### **Customers Collection**
[
  {
    "_id": 1,
    "name": "Sanjay Patil",
    "address": "Nashik",
    "account_type": "Savings",
    "branch_id": 101,
    "date_opened": "2020-01-01"
  },
  {
    "_id": 2,
    "name": "Sneha Kulkarni",
    "address": "Pune",
    "account_type": "Loan",
    "branch_id": 102,
    "date_opened": "2021-05-15"
  },
  {
    "_id": 3,
    "name": "Ravi Deshmukh",
    "address": "Mumbai",
    "account_type": "Savings",
    "branch_id": 103,
    "date_opened": "2020-01-01"
  },
  {
    "_id": 4,
    "name": "Sachin Tendulkar",
    "address": "Nashik",
    "account_type": "Current",
    "branch_id": 101,
    "date_opened": "2022-08-10"
  },
  {
    "_id": 5,
    "name": "Sunita Joshi",
    "address": "Pune",
    "account_type": "Savings",
    "branch_id": 102,
    "date_opened": "2019-09-25"
  }
]

// #### **Branches Collection**
[
  { "_id": 101, "name": "Nashik Main", "address": "Nashik City", "contact": "0253-123456" },
  { "_id": 102, "name": "Pune Branch", "address": "Shivaji Nagar", "contact": "020-987654" },
  { "_id": 103, "name": "Mumbai South", "address": "Nariman Point", "contact": "022-765432" },
  { "_id": 104, "name": "Delhi Central", "address": "Connaught Place", "contact": "011-654321" },
  { "_id": 105, "name": "Chennai West", "address": "T. Nagar", "contact": "044-876543" }
]

// #### **Transactions Collection**
[
  { "_id": 1, "customer_id": 1, "transaction_type": "Deposit", "amount": 5000, "date": "2023-01-15" },
  { "_id": 2, "customer_id": 2, "transaction_type": "Loan Payment", "amount": 10000, "date": "2023-03-10" },
  { "_id": 3, "customer_id": 3, "transaction_type": "Withdrawal", "amount": 2000, "date": "2023-02-20" },
  { "_id": 4, "customer_id": 4, "transaction_type": "Deposit", "amount": 3000, "date": "2023-04-01" },
  { "_id": 5, "customer_id": 5, "transaction_type": "Withdrawal", "amount": 1500, "date": "2023-05-05" }
]

// ### **Queries**
// #### 1. **List customers whose names start with "S."**
// Use a regular expression to match names starting with "S":
db.customers.find({ name: /^S/ }, { _id: 0, name: 1 });

// #### 2. **List customers who opened accounts on 1/1/2020 at a specific branch.**
// Filter by `date_opened` and `branch_id`:
db.customers.find(
  { date_opened: "2020-01-01", branch_id: 101 },
  { _id: 0, name: 1, branch_id: 1 }
);

// #### 3. **Display customers with savings accounts.**
// Filter by `account_type`:
db.customers.find({ account_type: "Savings" }, { _id: 0, name: 1, account_type: 1 });

// #### 4. **Count loan account holders in a specific branch.**
// Filter by `account_type` and `branch_id`:
db.customers.countDocuments({ account_type: "Loan", branch_id: 102 });
