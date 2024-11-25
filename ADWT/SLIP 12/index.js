
// ### Queries:
// 1. **List movies with the highest budget:**
db.movies.find().sort({ "budget": -1 }).limit(1)

// 2. **Display producers with more than one movie per year:**
//    - We can calculate the number of movies produced by each producer per year. If a producer has more than one movie in a year, we return their details.
db.producers.aggregate([
  {
    $lookup: {
      from: "movies",
      localField: "movies",
      foreignField: "_id",
      as: "produced_movies"
    }
  },
  {
    $unwind: "$produced_movies"
  },
  {
    $project: {
      "name": 1,
      "year": { $year: "$produced_movies.release_date" }
    }
  },
  {
    $group: {
      _id: { producer: "$name", year: "$year" },
      movie_count: { $sum: 1 }
    }
  },
  {
    $match: { "movie_count": { $gt: 1 } }
  }
])

// 3. **List actors in movies with 'Akshay' as a co-star:**
db.movies.aggregate([
  {
    $lookup: {
      from: "actors",
      localField: "actors",
      foreignField: "_id",
      as: "actor_details"
    }
  },
  {
    $match: {
      "actor_details.name": "Akshay Kumar"
    }
  },
  {
    $project: {
      "actors": 1
    }
  },
  {
    $unwind: "$actors"
  },
  {
    $lookup: {
      from: "actors",
      localField: "actors",
      foreignField: "_id",
      as: "actor_info"
    }
  },
  {
    $unwind: "$actor_info"
  },
  {
    $group: {
      _id: "$actor_info.name"
    }
  }
])

// 4. **Display movies produced by multiple producers:**
db.movies.aggregate([
  {
    $match: {
      "producers": { $size: { $gt: 1 } }
    }
  },
  {
    $lookup: {
      from: "producers",
      localField: "producers",
      foreignField: "_id",
      as: "producer_details"
    }
  }
])
