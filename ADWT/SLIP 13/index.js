
// ### 3. Queries

// #### Query 1: Display the average number of students in each competition.
// To calculate the average number of students in each competition, you can aggregate the `participants` field from the `competitions` collection.

db.competitions.aggregate([
  {
    $project: {
      competition_name: 1,
      participant_count: { $size: "$participants" }
    }
  },
  {
    $group: {
      _id: null,
      avg_participants: { $avg: "$participant_count" }
    }
  }
]);

// #### Query 2: Count students in the programming competition.
// To count the number of students in the "Programming" competition:

db.competitions.aggregate([
  {
    $match: { competition_name: "Programming" }
  },
  {
    $project: {
      student_count: { $size: "$participants" }
    }
  }
]);

// #### Query 3: List the first three winners of each competition.
// To get the first three winners of each competition:

db.winners.aggregate([
  {
    $lookup: {
      from: "students",
      localField: "first_winner",
      foreignField: "student_id",
      as: "first_winner_details"
    }
  },
  {
    $lookup: {
      from: "students",
      localField: "second_winner",
      foreignField: "student_id",
      as: "second_winner_details"
    }
  },
  {
    $lookup: {
      from: "students",
      localField: "third_winner",
      foreignField: "student_id",
      as: "third_winner_details"
    }
  },
  {
    $project: {
      competition_id: 1,
      first_winner: { $arrayElemAt: ["$first_winner_details.name", 0] },
      second_winner: { $arrayElemAt: ["$second_winner_details.name", 0] },
      third_winner: { $arrayElemAt: ["$third_winner_details.name", 0] }
    }
  }
]);


// #### Query 4: Display students from class "FY" participating in "E-Rangoli."
// To list students from class "FY" who participated in the "E-Rangoli" competition:

db.competitions.aggregate([
  {
    $match: { competition_name: "E-Rangoli" }
  },
  {
    $lookup: {
      from: "students",
      localField: "participants",
      foreignField: "student_id",
      as: "student_details"
    }
  },
  {
    $unwind: "$student_details"
  },
  {
    $match: { "student_details.class": "FY" }
  },
  {
    $project: {
      "student_details.name": 1,
      "student_details.class": 1
    }
  }
]);
