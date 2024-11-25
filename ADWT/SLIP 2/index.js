
// ### **Data Insertion**

// #### 1. `newspapers`
db.newspapers.insertMany([
    { _id: 1, name: "Lokmat", language: "Marathi", publisher_id: 1, city_id: 1, sales: 150000 },
    { _id: 2, name: "Maharashtra Times", language: "Marathi", publisher_id: 2, city_id: 2, sales: 120000 },
    { _id: 3, name: "The Times of India", language: "English", publisher_id: 3, city_id: 3, sales: 180000 },
    { _id: 4, name: "Gujarat Samachar", language: "Gujarati", publisher_id: 4, city_id: 4, sales: 100000 },
    { _id: 5, name: "Divya Bhaskar", language: "Gujarati", publisher_id: 5, city_id: 5, sales: 130000 }
]);

// #### 2. `publishers`
db.publishers.insertMany([
    { _id: 1, name: "Lokmat Media Group", state: "Maharashtra" },
    { _id: 2, name: "Bennett Coleman & Co. Ltd.", state: "Maharashtra" },
    { _id: 3, name: "Jagran Prakashan", state: "Uttar Pradesh" },
    { _id: 4, name: "Shree Saraswati Trust", state: "Gujarat" },
    { _id: 5, name: "Dainik Bhaskar Group", state: "Gujarat" }
]);

// #### 3. `cities`
db.cities.insertMany([
    { _id: 1, name: "Nashik", state: "Maharashtra" },
    { _id: 2, name: "Mumbai", state: "Maharashtra" },
    { _id: 3, name: "Pune", state: "Maharashtra" },
    { _id: 4, name: "Ahmedabad", state: "Gujarat" },
    { _id: 5, name: "Surat", state: "Gujarat" }
]);

// ### **Queries**
// #### 1. List all newspapers available in Nashik
db.newspapers.aggregate([
    {
        $lookup: {
            from: "cities",
            localField: "city_id",
            foreignField: "_id",
            as: "city_info"
        }
    },
    { $unwind: "$city_info" },
    { $match: { "city_info.name": "Nashik" } },
    { $project: { name: 1, _id: 0 } }
]);

// #### 2. List all Marathi newspapers
db.newspapers.find({ language: "Marathi" }, { name: 1, _id: 0 });

// #### 3. Count publishers in Gujarat
db.publishers.countDocuments({ state: "Gujarat" });

// #### 4. Show newspapers with the highest sales in Maharashtra
db.newspapers.aggregate([
    {
        $lookup: {
            from: "cities",
            localField: "city_id",
            foreignField: "_id",
            as: "city_info"
        }
    },
    { $unwind: "$city_info" },
    { $match: { "city_info.state": "Maharashtra" } },
    { $sort: { sales: -1 } },
    { $limit: 1 },
    { $project: { name: 1, sales: 1, _id: 0 } }
]);
