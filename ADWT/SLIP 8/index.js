// ### Queries:
// 1. **List items with quantities > 300:**
db.items.find({ "quantity": { $gt: 300 } })
// 2. **List items with < 5 tags:**
db.items.find({ "tags": { $size: { $lt: 5 } } })

// 3. **Identify items with status "B" or quantity < 50 and height > 8:**
db.items.find({
  $or: [
    { "status": "B" },
    { $and: [{ "quantity": { $lt: 50 } }, { "height": { $gt: 8 } }] }
  ]
})

// 4. **Find warehouses storing "Planner" items with quantities < 20:**
db.warehouses.aggregate([
  {
    $unwind: "$items"
  },
  {
    $lookup: {
      from: "items",
      localField: "items.item_id",
      foreignField: "_id",
      as: "item_details"
    }
  },
  {
    $match: {
      "item_details.name": "Planner",
      "items.quantity": { $lt: 20 }
    }
  }
])
