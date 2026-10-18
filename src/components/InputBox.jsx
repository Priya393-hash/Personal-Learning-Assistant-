import React from 'react'
import { useState } from 'react'

const InputBox = () => {
    const [concept, setConcept]=useState("");
    const [prerequisites, setPrerequisites] = useState([]);
    const [loading, setLoading]=useState(false);
    const [error, setError]=useState("");

    async function searchPrerequisites() {
        if(!concept.trim()){
            setError("Please enter a concept name.");
            setPrerequisites([]);
            return;
        }

        setLoading(true);
        setError("");
        setPrerequisites([]);

        try{
            const url = `http://localhost:8080/prerequisite-check?concept=${encodeURIComponent(concept.trim())}`;
            const response = await fetch(url);
            if(response.status === 404){
                throw new Error ("Concept not found");
            }
            if(!response.ok){
                throw new Error("Failed to fetch prerequisited");
            }
            const data = await response.json();
            setPrerequisites(data);
        }catch(err){
            setError(err.message);
        }finally{
            setLoading(false);
        }
    }
  return (
    <div>
        <h1>StudyGraph AI</h1>
        <h2>Find Prerequisites</h2>

        <input type="text" placeholder="Enter concept name" value={concept} 
            onChange={(event)=>setConcept(event.target.value)}/>
        <button onClick={searchPrerequisites}>Search</button>
        {/*loading && <p>Searching...</p>}
        {error && <p>Error: {error}</p>}
        {!loading && !error && prerequisites.length>0 && (
            <div>
                <h3>Prerequisites for {concept}</h3>
                <ul style={{listStyle:"none",padding:0}}> 
                    {prerequisites.map((item, index) =>(
                     <li key={index}>
                       {item} 
                    </li>))}
                </ul>
            </div>
        )}
        {!loading&&!error&&prerequisites.length==0&&concept&&(
            <p>No prerequisites found</p>
        )*/}
        {!loading && !error && prerequisites.length>0 && (
            <div>
                <h3>Prerequisites for {concept}</h3>
                <ul style={{listStyle:"none",padding:0}}>
                    {prerequisites.map((item,index)=>
                    (
                        <li key={index}>{item}</li>
                    ))}
                </ul>
            </div>
        )}
        {!loading && !error && concept.trim() !== "" && prerequisites.length === 0 && (
            <p>No prerequisites found</p>
        )}
    </div>
  );
}

export default InputBox