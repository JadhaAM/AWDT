// Here’s how you can model a graph database for scholarships and students, along with queries using Cypher, the query language for Neo4j (a graph database). 

// ### 1. Graph Database Modeling
// The graph model would consist of `Student` and `Scholarship` nodes, connected with relationships indicating which students are benefiting from which scholarships. The relationships might also carry properties such as the year a scholarship was granted.

// #### Nodes:
// - **Student**: Contains information about students (e.g., name, category, etc.).
// - **Scholarship**: Contains information about scholarships (e.g., scholarship name, category, income limit, etc.).

// #### Relationships:
// - **BENEFITS**: Connects `Student` nodes to `Scholarship` nodes, indicating that a student is receiving a scholarship.

// ### Example Data:
// #### Student Nodes:

CREATE (s1:Student {id: 1, name: 'John Doe', category: 'OBC', year: '2020-2021'})
CREATE (s2:Student {id: 2, name: 'Jane Smith', category: 'GEN', year: '2020-2021'})
CREATE (s3:Student {id: 3, name: 'Anil Kumar', category: 'OBC', year: '2020-2021'})
CREATE (s4:Student {id: 4, name: 'Priya Mehta', category: 'SC', year: '2021-2022'})
CREATE (s5:Student {id: 5, name: 'Rajesh Reddy', category: 'OBC', year: '2020-2021'})

// #### Scholarship Nodes:

CREATE (sch1:Scholarship {name: 'Merit-Based Scholarship', category: 'OBC', income_limit: 500000, year: '2020-2021'})
CREATE (sch2:Scholarship {name: 'Government Scholarship', category: 'GEN', income_limit: 600000, year: '2020-2021'})
CREATE (sch3:Scholarship {name: 'Minority Scholarship', category: 'OBC', income_limit: 400000, year: '2020-2021'})
CREATE (sch4:Scholarship {name: 'Research Grant', category: 'SC', income_limit: 300000, year: '2021-2022'})

// #### Relationships (BENEFITS):

CREATE (s1)-[:BENEFITS]->(sch1)
CREATE (s1)-[:BENEFITS]->(sch3)
CREATE (s3)-[:BENEFITS]->(sch1)
CREATE (s5)-[:BENEFITS]->(sch1)


// ### 2. Cypher Queries

// #### Query 1: List scholarships for the OBC category.
// This query finds all scholarships where the category is "OBC."

MATCH (s:Scholarship)
WHERE s.category = 'OBC'
RETURN s.name, s.category, s.income_limit, s.year
```

#### Query 2: Count students benefiting from a scholarship in 2020-2021.
This query counts the number of students who received a scholarship during the 2020-2021 academic year.

```cypher
MATCH (st:Student)-[:BENEFITS]->(sc:Scholarship)
WHERE st.year = '2020-2021'
RETURN COUNT(DISTINCT st) AS num_students
```

// #### Query 3: Update the income limit for a specific scholarship.
// This query updates the income limit for the scholarship "Merit-Based Scholarship."

MATCH (s:Scholarship {name: 'Merit-Based Scholarship'})
SET s.income_limit = 550000
RETURN s.name, s.income_limit


// #### Query 4: Identify the most popular scholarship.
// This query finds the scholarship that has been given to the most students (i.e., the scholarship with the most `BENEFITS` relationships).

MATCH (st:Student)-[:BENEFITS]->(sc:Scholarship)
RETURN sc.name, COUNT(st) AS num_beneficiaries
ORDER BY num_beneficiaries DESC
LIMIT 1

