# 🧠 Personal Learning Assistant

### A Graph-Powered Learning Assistant for Concepts

Personal Learning Assistant AI is a graph-powered learning assistant designed to help students understand programming concepts, explore prerequisite relationships, and discover a logical sequence for learning technical topics.

The project combines a React frontend, a Java Spring Boot backend, and the FalkorDB graph database to represent and retrieve relationships between programming concepts.

The long-term goal is to integrate an AI agent that can interact with the knowledge graph, select appropriate graph-query tools, reason across multiple relationships, and provide explainable learning recommendations.

---

## 🎯 Problem Statement

Learning programming languages involves understanding many connected concepts. Advanced topics often depend on knowledge of simpler concepts.

For example, understanding polymorphism is easier when a student already understands classes, objects, and inheritance.

However, students may struggle with questions such as:

- Which concept should I learn first?
- What prerequisites should I understand before studying a new topic?
- How are different programming concepts connected?
- What learning sequence should I follow?
- Why should one concept be learned before another?

A traditional list of topics does not always make these dependencies clear.

## 💡 Our Solution

Personal Learning Assistant AI represents programming concepts and their prerequisite relationships as a knowledge graph.

The graph stores concepts as nodes and relationships between concepts as edges. The Spring Boot backend queries this graph to retrieve relevant relationships and learning-path information. The React frontend provides the interface through which students interact with the application.

Instead of treating concepts as isolated topics, Personal learning assistant AI models how they relate to one another.

This graph-based approach provides a foundation for future AI-powered reasoning and personalized learning recommendations.

---

## 🎯 Project Objectives

The main objectives of Personal learning assistant AI are:

1. Represent programming concepts in a structured knowledge graph.
2. Store prerequisite relationships between concepts.
3. Retrieve connected concepts through graph queries.
4. Generate useful learning-path information from graph relationships.
5. Provide a user-friendly interface for exploring concepts.
6. Build a foundation for an AI agent that can use graph data to answer learning-related questions.
7. Make future learning recommendations more transparent by explaining the relationships behind them.

---

## ✨ Key Features

### 1. Graph-Based Knowledge Representation

Programming concepts can be represented as nodes in FalkorDB, with relationships connecting related concepts.

This structure makes it possible to query how concepts depend on or connect to one another.

### 2. Prerequisite Relationships

Concepts can be connected through prerequisite relationships.

For example, a graph may represent that understanding inheritance helps prepare a student to learn polymorphism.

The application can use these relationships to retrieve relevant learning information.

### 3. Learning-Path Retrieval

The backend queries the knowledge graph to retrieve concept relationships and learning-path information.

The resulting sequence depends on the nodes, edges, and query logic available in the application.

### 4. React-Based User Interface

The frontend provides a web interface for students to interact with the learning assistant and access the application's available functionality.

### 5. Spring Boot Backend

The Java backend handles application logic and graph queries. It connects the frontend with the graph database and returns results through backend endpoints. And support multi-hop prerequisite traversal.

### 6. Foundation for AI-Agent Integration

The project is being developed toward an AI agent that can use graph-query tools to retrieve relevant information and explain learning recommendations.

**Note:** AI-agent capabilities should be considered implemented only after the corresponding integration has been completed and tested.

---

## 🛠️ Technology Stack

| Technology and Role |

 React : Builds the frontend interface 
 JavaScript : Implements frontend functionality 
 Java : Backend programming language 
 Spring Boot : Backend application and API development 
 Maven : Backend dependency and build management 
 FalkorDB : Stores and queries the knowledge graph 
 Cypher-style graph queries : Retrieves graph relationships 
 LangChain4j : Intended AI-agent integration, depending on implementation status 


##  System Architecture

The current graph-query workflow can be represented as follows:
```

                 Student
                    |
                    v
             React Frontend
                    |
                    v
           Spring Boot Backend
                    |
                    v
             Graph Query Logic
                    |
                    v
             FalkorDB Graph
                    |
                    v
        Concept and Prerequisite Data
                    |
                    v
         Learning-Path Information
                    |
                    v
             React Frontend
```


### Planned AI-Agent Architecture

The project aims to extend this workflow with an AI agent:

```text
Student Question
       |
       v
    AI Agent
       |
       v
Graph Query Tool Selection
       |
       v
   FalkorDB Graph
       |
       v
Retrieved Graph Information
       |
       v
Reasoning and Explanation
       |
       v
Learning Recommendation
```

This second diagram represents the intended direction of development. It should not be interpreted as a fully implemented workflow unless all components are operational.

---

## 🔄 How It Works

The current graph-based workflow follows these general steps:

1. A student interacts with the React frontend.
2. The frontend sends a request to the Spring Boot backend.
3. The backend executes the relevant application logic.
4. Graph queries retrieve concepts and prerequisite relationships from FalkorDB.
5. The backend processes the query results.
6. The results are returned to the frontend.
7. The frontend displays the available learning-path or relationship information.

The exact workflow depends on the endpoint and functionality being used.

---

## 🗺️ Knowledge Graph Example

Consider the following programming concepts:

- Java Basics
- Classes & Objects
- Inheritance
- Polymorphism

An illustrative learning sequence is:

```text
Java Basics
     |
     v
Classes & Objects
     |
     v
Inheritance
     |
     v
Polymorphism
```

The graph may represent these connections using a prerequisite relationship such as:

`(Concept)-[:PREREQUISITE_OF]->(Concept)`

A corresponding illustrative graph query could be:

```cypher
MATCH (c1:concept)-[:PREREQUISITE_OF]->(c2:concept)
RETURN c1.name, c2.name
```

This query retrieves connected concept pairs from the graph.

The example illustrates the graph model and query approach. Actual results depend on the data stored in FalkorDB and the application's implementation.

---

## 📂 Project Structure

The repository currently keeps the React frontend in the root directory and the Spring Boot backend inside `backend/`.

```text
StudyGraph-AI/
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   └── test/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
├── src/
│   └── React application source
│
├── public/
│   └── Static frontend assets
│
├── package.json
├── package-lock.json
└── README.md
```
---

## 🚀 Getting Started

Follow these instructions to run the project locally.

### Prerequisites

Install or configure the following:

- Git
- Node.js and npm
- A Java JDK compatible with the backend project
- FalkorDB
- Access to the project's required configuration settings

### 1. Clone the Repository

```bash
git clone <GITHUB_REPOSITORY_URL>
cd Personal-Learning-Assistant
```

### 2. Configure FalkorDB

Start your FalkorDB instance.

Ensure that the backend's graph connection settings match your local environment.

The knowledge graph must contain the concept nodes and relationships required by the application's queries.

### 3. Start the Backend

Open a terminal in the repository root:

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

On macOS or Linux, use:

```bash
./mvnw spring-boot:run
```

If the Maven Wrapper is unavailable, use an installed Maven version:

```bash
mvn spring-boot:run
```

Check the backend configuration for the application port and database connection settings.

### 4. Start the Frontend

Open a second terminal in the repository root:

```bash
npm install
npm run dev
```

Open the local URL printed by the frontend development server.

### 5. Test the Application

Once the frontend, backend, and database are running:

1. Open the frontend in your browser.
2. Try the available concept and learning-path functionality.
3. Verify that backend requests return the expected results.
4. Check that the graph contains the relationships required by your queries.

If a request fails, verify the backend status, database connection settings, and graph data.

---

## 🔭 Future Improvements

Potential improvements include:

- Complete and test AI-agent integration.
- Expose graph queries as agent tools.
- Improve learning-path generation.
- Provide explanations grounded in retrieved graph relationships.
- Add automated tests for backend endpoints and graph queries.
- Improve validation and error handling.
- Add a demonstration showing the complete student workflow.

---

## 📄 License

No license has been specified yet.

---

## 👩‍💻 Project Status

Personal-Learning-Assistant AI is an ongoing project focused on graph-based learning paths and prerequisite relationships, with AI-agent capabilities as a development direction.

