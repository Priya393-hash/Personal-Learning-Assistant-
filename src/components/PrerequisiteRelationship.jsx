import React, { useState } from 'react'

const PrerequisiteRelationship = () => {
    const [prerequisite, setPrerequiste]=useState("");
    const [targetConcept, setTargetConcept]=useState("");
    const [relationshipMessage,setRelationshipMessage]=useState("");
    const [relationshipError, setRelationshipError]=useState("");

    async function createRelationship() {
        if(!prerequisite.trim() || !targetConcept.trim()){
            setRelationshipError("Please enter both concept names.");
            setRelationshipMessage("");
            return;
        }
        setRelationshipError("");
        setRelationshipMessage("");

        try{
            const params = new URLSearchParams({prerequisite: prerequisite.trim(), concept: targetConcept.trim()});
            
            const response = await fetch(`http://localhost:8080/prerequisites?${params}`, {method: "POST"});
            
            if(!response.ok){
                throw new Error( "Could not create relationship. Check that both concept exist");   
            }
            const data = await response.json();
                setRelationshipMessage(data.message);
                setPrerequiste("");
                setTargetConcept("");
        }catch(error){
                setRelationshipError(error.message);
            }
        }
  return (
    <div>
        <h2>Create Prerequisite Relationship</h2>
        <input type="text" placeholder='Prerequisite Concept' value={prerequisite} onChange={(event)=>
            setPrerequiste(event.target.value)} />
        <input type="text" placeholder='Concept to learn' value={targetConcept} onChange={(event)=>setTargetConcept(event.target.value)}/>

        <button onClick={createRelationship}>Create Relationship</button>
        {relationshipMessage && 
        <p>{relationshipMessage}</p>}
        {relationshipError && 
        <p>Error:{relationshipError}</p>}
    </div>
  )
}

export default PrerequisiteRelationship