package com.example.studygraph;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

//import java.sql.Driver;
import com.falkordb.Driver;
import com.falkordb.FalkorDB;
import com.falkordb.Graph;
import com.falkordb.ResultSet;

import tools.jackson.databind.ser.std.ArraySerializerBase;

import com.falkordb.Record;


@Service 
public class FalkorDBService {
        private final Driver driver = FalkorDB.driver();
        private final Graph graph = driver.graph("student");

        public void test(){
        System.out.println("FalkorDB driver created");
        
        System.out.println("FalkorDB graph created");
       // ResultSet result = graph.query("MATCH (s:student) RETURN s");
       // System.out.println(result);
        
        /*for(Record record : result){
            Object student = record.getValue("s");
            System.out.println(student);
        }*/

        /*String query = """
                CREATE (:student {name:'Aman', level: 'beginner'})
                """;
        graph.query(query);
        System.out.println("student created");*/
        //driver.close();

        String name = "Riya";
        String level = "beginner";

        /*String query = """
                CREATE(:student {name:"%s", level: "%s"})
                """.formatted(name,level);
        graph.query(query);
        System.out.println("Student create: " + name );*/

        //create kar rahe hai
        /*String query = """
                MATCH (s:student {name:"Riya"})
                SET s.level = "intermediate"
                """;
        graph.query(query);
        System.out.println("student updated");*/

        //Delete kar rahe hai
        /*String  query = """
                MATCH (s:student {name:"Riya"})
                DELETE s
                """;
        graph.query(query);
        System.out.println("Student delete");
         */
   
        //subject create kiye and relation kiye aman se java
        /*String  query = """
                        MATCH (s:student {name:"Aman"})
                        MATCH (j:Subject {name:"Java"})
                        CREATE (s)-[:STUDIES]->(j)
                        """;
                graph.query(query);
                System.out.println("Relation Created");
        */
   
        //subject ko topic ke saath relationship bana rahe hai
        /*String  query = """
                        MATCH (j:Subject {name:"Java"})
                        MATCH (t:Topic {name:"OOP"})

                        CREATE (j)-[:HAS_TOPIC]->(t)
                        """;
        graph.query(query);
        System.out.println(" topic Relationship Created");*/

        //Topic se concept relationship bana rahe hai
        /*String query = """
                        MATCH(t:Topic {name:"OOP"})
                        MATCH(c:concept {name:"Inheritance"})

                        CREATE (t)-[:HAS_CONCEPT]->(c)
                        """;
        graph.query(query);
        System.out.println("concept relationship created");*/

        //do node ke bich relationship create hua
        /*String query = """
                        MATCH (c1:concept {name:"Inheritance"})
                        MATCH (c2:concept {name:"Polymorphism"})
                        CREATE (c1)-[:PREPREQUISITE_OF]->(c2)
                        """;
        graph.query(query);
        System.out.println("Prerequisite relationhilp created");*/

        
        //pura store student ka object data access kiye output me dikha
       /*String query = """
                       MATCH(s:student)-[:STUDIES]->(j:Subject) MATCH (j)-[:HAS_TOPIC]->(t:Topic) MATCH (t)-[:HAS_CONCEPT]->(c:concept) RETURN s.name, j.name, t.name, c.name
                       """;
       ResultSet result = graph.query(query);
       System.out.println(result);*/

       //java me individual stored student ka column ka data access kiya
       /*  String query = """
                        MATCH (c1:concept)-[:PREREQUISITE_OF]->(c2:concept) RETURN c1.name AS prerequisite, c2.name AS nextConcept
                        """;
        ResultSet result = graph.query(query);
        for(Record record: result){
                String prerequisite = record.getString(0);
                String nextConcept = record.getString(1);
                System.out.println("Revise first:"+ prerequisite);
                System.out.println("Learn next:" + nextConcept);
        }
       //System.out.println(result);*/

        }
        public List<String> getLearningPath(){
                List<String> concepts = new ArrayList<>();
                String query = """
                                MATCH path = (c1:concept)-[:PREREQUISITE_OF*1..5]->(c2:concept) RETURN [node IN nodes(path) | node.name] AS pathConcepts
                                """;
                ResultSet result = graph.query(query);

                for(Record record: result){
                        List <Object> pathConcepts = record.getValue("pathConcepts");
                        List <String> path = new ArrayList<>();
                        for(Object concept : pathConcepts){
                                path.add(String.valueOf(concept));
                        }
                       /*List<String> pathConcepts = new ArrayList<>();
                       for(int i = 0; i<record.size(); i++){
                        pathConcepts.add(record.getString(i));
                       }
                       concepts.add(String.join("->", pathConcepts));*/
                        concepts.add(String.join("->", path));
                }
                return concepts;
        }

                 
                
        public List<String> getPrerequisite(String concept){
                List<String> prerequisites = new ArrayList<>();
                String query = """
                                MATCH (p:concept)-[:PREREQUISITE_OF]->(c:concept) WHERE c.name = $concept RETURN p.name
                                """;
                Map<String, Object> params = new HashMap<>();
                params.put("concept", concept);
                ResultSet result = graph.query(query,params);
                for(Record record:result){
                        String prerequisite = record.getString(0);
                        prerequisites.add(prerequisite);

                }
                return prerequisites;
        }

        public void addConcept(String name){
                String query = """
                                MERGE(c:concept {name:$name}) RETURN c.name
                                """;
                Map<String, Object> params = new HashMap<>();
                params.put("name", name);
                graph.query(query,params);
        }

        public boolean conceptExists(String concept){
                String query = """
                                MATCH(c:concept {name:$concept})
                                RETURN c.name
                                """;
                Map<String, Object> params = new HashMap<>();
                params.put("concept", concept);
                ResultSet result = graph.query(query,params);
                return result.iterator().hasNext();
        }

        public void addPrerequisite(String concept, String prerequisite){
                String query = """
                                MATCH (p:concept {name:$prerequisite})
                                MATCH (c:concept {name:$concept})
                                MERGE (p)-[:PREREQUISITE_OF]->(c) 
                                """;
                Map<String,Object> params = new HashMap<>();
                params.put("prerequisite", prerequisite);
                params.put("concept", concept);
                graph.query(query,params);
        }

}
