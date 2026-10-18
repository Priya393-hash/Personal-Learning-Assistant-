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
