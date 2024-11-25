
// #### Movie Nodes:

CREATE (m1:Movie {title: 'Movie A', business: 1000000000, awards: 5})
CREATE (m2:Movie {title: 'Movie B', business: 500000000, awards: 3})
CREATE (m3:Movie {title: 'Movie C', business: 1500000000, awards: 7})
CREATE (m4:Movie {title: 'Movie D', business: 200000000, awards: 1})
CREATE (m5:Movie {title: 'Movie E', business: 800000000, awards: 4})

// #### Actor Nodes:

CREATE (a1:Actor {name: 'Shahrukh Khan'})
CREATE (a2:Actor {name: 'Aamir Khan'})
CREATE (a3:Actor {name: 'Salman Khan'})
CREATE (a4:Actor {name: 'Katrina Kaif'})
CREATE (a5:Actor {name: 'Deepika Padukone'})

// #### Relationships (ACTED_IN):

CREATE (a1)-[:ACTED_IN]->(m1)
CREATE (a2)-[:ACTED_IN]->(m1)
CREATE (a3)-[:ACTED_IN]->(m2)
CREATE (a4)-[:ACTED_IN]->(m2)
CREATE (a1)-[:ACTED_IN]->(m3)
CREATE (a5)-[:ACTED_IN]->(m3)
CREATE (a2)-[:ACTED_IN]->(m4)
CREATE (a3)-[:ACTED_IN]->(m5)


// ### 2. Cypher Queries

// #### Query 1: Find the movie with the highest business.
// This query finds the movie with the highest box office business.

MATCH (m:Movie)
RETURN m.title, m.business
ORDER BY m.business DESC
LIMIT 1


// #### Query 2: Display movie details along with actors.
// This query lists all movies along with the actors who starred in them.

MATCH (a:Actor)-[:ACTED_IN]->(m:Movie)
RETURN m.title AS movie_title, a.name AS actor_name, m.business AS business, m.awards AS awards


// #### Query 3: List movies starring "Shahrukh Khan."
// This query finds all movies where "Shahrukh Khan" acted.

MATCH (a:Actor {name: 'Shahrukh Khan'})-[:ACTED_IN]->(m:Movie)
RETURN m.title AS movie_title, m.business AS business, m.awards AS awards


// #### Query 4: Display movies with more than two awards.
// This query finds movies that have won more than two awards.

MATCH (m:Movie)
WHERE m.awards > 2
RETURN m.title AS movie_title, m.business AS business, m.awards AS awards

