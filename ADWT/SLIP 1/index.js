
// 2. MongoDB Database Schema and Queries


// Insert the following into MongoDB:

// owners Collection
db.owners.insertMany([
    { owner_id: 1, name: "Mr. Patil", contact: "1234567890", properties_owned: [101, 102] },
    { owner_id: 2, name: "Ms. Sharma", contact: "9876543210", properties_owned: [103] },
    { owner_id: 3, name: "Mr. Mehta", contact: "5678901234", properties_owned: [104, 105] },
    { owner_id: 4, name: "Ms. Khan", contact: "6789012345", properties_owned: [106] },
    { owner_id: 5, name: "Mr. Desai", contact: "8901234567", properties_owned: [107] }
]);

// Properties Collection
db.properties.insertMany([
    { property_id: 101, owner_id: 1, area: "Area1", rate: 80000, city: "Nashik" },
    { property_id: 102, owner_id: 1, area: "Area2", rate: 150000, city: "Mumbai" },
    { property_id: 103, owner_id: 2, area: "Area3", rate: 95000, city: "Pune" },
    { property_id: 104, owner_id: 3, area: "Area4", rate: 110000, city: "Nashik" },
    { property_id: 105, owner_id: 3, area: "Area5", rate: 70000, city: "Nagpur" },
    { property_id: 106, owner_id: 4, area: "Area6", rate: 120000, city: "Pune" },
    { property_id: 107, owner_id: 5, area: "Area7", rate: 60000, city: "Nashik" }
]);




// #### Queries:

// 1. **Display area-wise property details:**

db.properties.aggregate([
    { $group: { _id: "$area", properties: { $push: "$$ROOT" } } }
]);


// 2. **Display properties owned by 'Mr. Patil' with a minimum rate:**


db.owners.aggregate([
    { $match: { name: "Mr. Patil" } },
    { $lookup: { from: "properties", localField: "properties_owned", foreignField: "property_id", as: "property_details" } },
    { $unwind: "$property_details" },
    { $sort: { "property_details.rate": 1 } },
    { $project: { _id: 0, "property_details.area": 1, "property_details.rate": 1 } }
]);


// 3. **Provide details of owners with properties in "Nashik":

db.properties.aggregate([
    { $match: { city: "Nashik" } },
    { $lookup: { from: "owners", localField: "owner_id", foreignField: "owner_id", as: "owner_details" } },
    { $unwind: "$owner_details" },
    { $project: { _id: 0, "owner_details.name": 1, "owner_details.contact": 1 } }
]);


// 4. **Display property areas with rates < 100,000:**


db.properties.find(
    { rate: { $lt: 100000 } },
    { _id: 0, area: 1, rate: 1 }
);
