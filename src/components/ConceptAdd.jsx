import React from 'react'
import { useState } from 'react'


const ConceptAdd = () => {
    const [ newConcept, setNewConcept]=useState("");
    const [ message, setMessage]=useState("");
    const [addError, setAddError]=useState("");

    async function addConcept() {
        if(!newConcept.trim()){
            setAddError("Please enter a concept name");
            setMessage("");
            return;
        }
        setAddError("");
        setMessage("");
        try{
            const response = await fetch("http://localhost:8080/concepts", {
                method: "POST",
                headers:{
                    "Content-Type":"application/json",
                },
                body: JSON.stringify({
                    name:newConcept.trim(),
                }),
            });
            if(!response.ok){
                throw new Error("Failed to add concept");
            }
            const data = await response.json();
            setMessage(data.message);
            setNewConcept("");
        }catch(error){
            setAddError(error.message);
        }
        
    }
  return (
    <div>
        <h2>Add New Concept</h2>
        <input type="text" 
            placeholder="Enter a new cocept"
            value={newConcept}
            onChange={(event)=>setNewConcept(event.target.value)} />
        <button onClick={addConcept}>Add Concept</button>

        {message && <p>{message}</p>}
        {addError && <p>Error: {addError}</p>}
    </div>
  )
}

export default ConceptAdd