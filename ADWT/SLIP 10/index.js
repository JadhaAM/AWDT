
// ### Queries:
// 1. **List products with a 1-year warranty:**
db.products.find({ "warranty_period": 1 })

// 2. **Display customers who purchased on 15/08/2023:**
//    Assuming there's an additional `purchase_date` field in the `purchases` documents (not included in the sample data), the query might look like this:

db.customers.aggregate([
  { 
    $unwind: "$purchases" 
  },
  {
    $lookup: {
      from: "products",
      localField: "purchases",
      foreignField: "_id",
      as: "purchased_products"
    }
  },
  {
    $match: {
      "purchased_products.purchase_date": "2023-08-15"
    }
  }
])

// 3. **Identify products and brands with the highest ratings:**
db.products.aggregate([
  {
    $lookup: {
      from: "brands",
      localField: "brand_id",
      foreignField: "_id",
      as: "brand_details"
    }
  },
  { 
    $sort: { "rating": -1 }
  },
  { 
    $limit: 1 
  }
])

// 4. **Display customers in a city with bill amounts > 50,000:**
db.customers.find({ "total_bill_amount": { $gt: 50000 } })
