// ### **1. Collections**
// - **`hospitals`**: Stores hospital information.
// - **`doctors`**: Stores doctor information and their associated hospitals.
// - **`reviews`**: Stores reviews and ratings for hospitals.
// ### **Schema and Sample Documents**
// #### **a) `hospitals` Collection**

[
  {
    "_id": 1,
    "name": "Nashik Care Hospital",
    "city": "Nashik",
    "specializations": ["Cardiology", "Neurology"],
    "address": "Panchavati, Nashik"
  },
  {
    "_id": 2,
    "name": "MediLife Hospital",
    "city": "Pune",
    "specializations": ["Orthopedics", "Pediatrics"],
    "address": "Kothrud, Pune"
  },
  {
    "_id": 3,
    "name": "Shree Hospital",
    "city": "Nashik",
    "specializations": ["General Medicine", "Dermatology"],
    "address": "Gangapur Road, Nashik"
  }
]

// #### **b) `doctors` Collection**
[
  {
    "_id": 101,
    "name": "Dr. Deshmukh",
    "specialization": "Cardiology",
    "hospitals": [1, 3]
  },
  {
    "_id": 102,
    "name": "Dr. Patil",
    "specialization": "Orthopedics",
    "hospitals": [2]
  },
  {
    "_id": 103,
    "name": "Dr. Gupta",
    "specialization": "Dermatology",
    "hospitals": [3]
  }
]

// #### **c) `reviews` Collection**

[
  {
    "_id": 201,
    "hospital_id": 1,
    "rating": 4.5,
    "review": "Excellent care and service."
  },
  {
    "_id": 202,
    "hospital_id": 2,
    "rating": 3.8,
    "review": "Good but needs improvement in facilities."
  },
  {
    "_id": 203,
    "hospital_id": 3,
    "rating": 4.7,
    "review": "Friendly staff and skilled doctors."
  }
]

// ### **Queries**
// #### 1. **List hospitals by specialization**
db.hospitals.find({ specializations: "Cardiology" });

// #### 2. **List hospitals in a specific city**
db.hospitals.find({ city: "Nashik" });

// #### 3. **Identify hospitals visited by Dr. Deshmukh**
db.doctors.aggregate([
  { $match: { name: "Dr. Deshmukh" } },
  { $lookup: {
      from: "hospitals",
      localField: "hospitals",
      foreignField: "_id",
      as: "visited_hospitals"
    }
  },
  { $project: { _id: 0, name: 1, visited_hospitals: 1 } }
]);

// #### 4. **List hospitals with ratings >= 4**
db.hospitals.aggregate([
  { $lookup: {
      from: "reviews",
      localField: "_id",
      foreignField: "hospital_id",
      as: "hospital_reviews"
    }
  },
  { $match: { "hospital_reviews.rating": { $gte: 4 } } },
  { $project: { name: 1, city: 1, specializations: 1, "hospital_reviews.rating": 1 } }
]);
