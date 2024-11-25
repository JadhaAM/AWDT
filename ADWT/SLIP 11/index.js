
// ### Queries:
// 1. **List all products in the inventory:*
db.products.find()

// 2. **Display details of orders with a value > 20,000:**
//    Assuming the total value of an order is the sum of the product prices multiplied by quantities, this query will find orders with a total value above 20,000.
db.orders.aggregate([
  {
    $lookup: {
      from: "products",
      localField: "products.product_id",
      foreignField: "_id",
      as: "product_details"
    }
  },
  {
    $project: {
      _id: 1,
      customer_id: 1,
      products: 1,
      order_date: 1,
      total_value: {
        $sum: {
          $map: {
            input: "$products",
            as: "product",
            in: {
              $multiply: [
                { $arrayElemAt: [{ $filter: { input: "$product_details", as: "item", cond: { $eq: ["$$item._id", "$$product.product_id"] } } }, 0] },
                "$$product.quantity"
              ]
            }
          }
        }
      }
    }
  },
  {
    $match: { "total_value": { $gt: 20000 } }
  }
])

// 3. **List unprocessed orders (without invoices):**
db.orders.aggregate([
  {
    $lookup: {
      from: "invoices",
      localField: "_id",
      foreignField: "order_id",
      as: "invoices"
    }
  },
  {
    $match: { "status": "unprocessed", "invoices": { $size: 0 } }
  }
])

// 4. **List orders along with invoices for "Mr. Rajiv":**
db.orders.aggregate([
  {
    $match: { "customer_id": ObjectId("1") }
  },
  {
    $lookup: {
      from: "invoices",
      localField: "_id",
      foreignField: "order_id",
      as: "invoices"
    }
  }
])
