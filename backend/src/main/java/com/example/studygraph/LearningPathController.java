package com.example.studygraph;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin (origins = "http://localhost:5173")
@RestController 
public class LearningPathController {
        private  final FalkorDBService service;

        public LearningPathController(){
            this.service = new FalkorDBService();
        }

        //sare data access kar rahe hai
        @GetMapping("/learning-path")
        /*public String Studentdata(){
            return "Inheritance->Polimorphism";
        } ye hum phele use test kiye ki browser pe ye return hardcodec kata hai ya nahi */
        // ab hum direct database se connect karenge
        public List<String> getLearningPath1(){
            return service.getLearningPath();
        }

        //only spicific data access karenge user se concept liye or falkordb database me check kar ke response borwser pr return kar diy
        /*@GetMapping("/prerequisite")
        public String getPrerequisite(@RequestParam String concept){
            return concept;
        }*/
       //user se concept le ke edge se end concept return kare browser pe
        @GetMapping ("/prerequisite")
        public List<String> getPrerequisite(@RequestParam String concept){
            return service.getPrerequisite(concept);
        }
        
        //user se data le ke database me store karenge
        @PostMapping ("/concepts")
        public Map<String, String> addConcept(@RequestBody ConceptRequest request){
            service.addConcept(request.getName());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Concept added successfully");
            response.put("name", request.getName());
            
            return response;
        }

        //list check kar ke return karta hai
        @GetMapping ("/prerequisite-check")
        public ResponseEntity<List<String>> getPrerequisEntity(@RequestParam String concept){
            if (concept == null || concept.isBlank()) {
                return ResponseEntity.badRequest().build();
            }
            if(!service.conceptExists(concept)){
                return ResponseEntity.notFound().build();
            }
            List<String> prerequisites = service.getPrerequisite(concept);
            return ResponseEntity.ok(prerequisites);
        }

        //relationship create karenge user se le ke
        @PostMapping ("/prerequisites")
        public Map<String, String> addPrerequisiteColtroller(@RequestParam String concept, @RequestParam String prerequisite){
            service.addPrerequisite(concept, prerequisite);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Prerequisite relationship is created");
            response.put("prerequisite", prerequisite);
            response.put("concept", concept);
            return response;
        }

       
        
}
