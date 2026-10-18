StudyGraph AI
StudyGraph AI is a graph-powered learning assistant designed to help students understand programming concepts and their prerequisite relationships. It uses a knowledge graph to retrieve concept connections and learning-path information.
Problem Statement
Students often struggle to determine which programming concepts they should learn first and which concepts they need to understand before moving to advanced topics.
Solution
StudyGraph AI represents programming concepts and their prerequisite relationships in a graph database. The backend queries this graph to retrieve relevant relationships and learning paths, while the frontend provides an interface for students to interact with the system.
For example:
Java Basics → Classes & Objects → Inheritance → Polymorphism
This is an illustrative learning sequence; actual results depend on the concepts and relationships stored in the graph.
Features
- Explore programming concepts and their relationships.
- Represent prerequisite relationships using a knowledge graph.
- Query graph data through a Spring Boot backend.
- Retrieve learning-path information based on graph relationships.
- Provide a React-based user interface for students.
- Build toward AI-powered, explainable learning recommendations.
Tech Stack
- Frontend: React, JavaScript
- Backend: Java, Spring Boot
- Build Tool: Maven
- Graph Database: FalkorDB
- AI Integration: LangChain4j / Groq integration is under development, if not yet completed.
System Architecture
Student
   ↓
React Frontend
   ↓
Spring Boot Backend
   ↓
Graph Query Logic
   ↓
FalkorDB Knowledge Graph
   ↓
Learning Path and Concept Relationships
   ↓
React Frontend

The application uses graph relationships to retrieve relevant learning information. The project aims to extend this functionality with an AI agent capable of selecting graph-query tools, reasoning across multiple relationships, and explaining its recommendations.
Project Structure
StudyGraph-AI/
├── backend/
│   ├── src/
│   ├── pom.xml
│   └── mvnw.cmd
├── src/
├── public/
├── package.json
└── README.md

The React frontend currently resides in the repository root, while the Spring Boot backend is located in the backend/ directory.
Getting Started
Prerequisites
- Java JDK compatible with the backend configuration
- Node.js and npm
- FalkorDB
- Git
1. Start the Backend
Open a terminal in the repository root:
cd backend
.\mvnw.cmd spring-boot:run

Ensure that FalkorDB is running and the backend database connection settings are configured correctly.
2. Start the Frontend
Open a second terminal in the repository root:
npm install
npm run dev

Open the local URL displayed in the terminal.
Graph Data
The knowledge graph must contain concept nodes and their relationships to produce meaningful learning paths.
Example concepts include:
- Java Basics
- Classes & Objects
- Inheritance
- Polymorphism
These concepts can be connected using prerequisite relationships. The learning-path results depend on the graph data available in FalkorDB.
Graph Hacks Track 1 Alignment
The project is being developed toward graph-based AI-agent capabilities, including:
- Multi-hop reasoning: Exploring relationships across multiple concepts.
- Graph queries as tools: Enabling an AI agent to retrieve relevant information from FalkorDB.
- Tool selection: Allowing the agent to select suitable graph-query tools for a request.
- Explainable results: Providing reasoning behind recommended learning paths.
- Graph-based actions: Using graph information to support learning-related tasks.
These capabilities should be considered complete only when implemented and tested.
Future Improvements
- Complete AI-agent and tool-calling integration.
- Improve multi-hop prerequisite reasoning.
- Provide explanations for recommended learning paths.
- Add automated tests and improve error handling.
- Create a demonstration of graph-based learning-path generation.
Security
API keys, database credentials, and other sensitive information should be stored in environment variables or local configuration files. Never commit secrets to GitHub.
License
No license has been specified yet.
