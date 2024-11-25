
// 1. **List customers whose names start with "D":**
db.customers.find({ "name": { $regex: "^D", $options: "i" } })

// 2. **List customers in descending order who took loans from Pimpri:**
db.customers.aggregate([
  {
    $lookup: {
      from: "loans",
      localField: "_id",
      foreignField: "customer_id",
      as: "loan_details"
    }
  },
  {
    $unwind: "$loan_details"
  },
  {
    $match: { "loan_details.loan_provider": "Pimpri" }
  },
  {
    $sort: { "name": -1 }
  }
])

// 3. **Display customer details with the maximum loan amount:**
db.loans.aggregate([
  { $sort: { "amount": -1 } },
  { $limit: 1 },
  {
    $lookup: {
      from: "customers",
      localField: "customer_id",
      foreignField: "_id",
      as: "customer_details"
    }
  }
])

// 4. **Update the address for "Mr. Patil" if the loan amount > 100,000:**
db.customers.updateOne(
  { "name": "David Patil" },
  { $set: { "address": "New Address" } },
  { 
    $or: [
      { "loan_ids": { $in: [ObjectId("4"), ObjectId("5")] } }
    ]
  }
)
