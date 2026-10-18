import { useEffect,useState } from "react";

const Display = () => {
     const [learningPath, setLearningPath]=useState([]);
      const [loading, setLoading] = useState(true);
      const [error, setError] = useState("");
      
      useEffect(()=>{
        async function fetchLearningPath() {
          try{
            const response = await fetch("http://localhost:8080/learning-path");
            if(!response.ok){
              throw new Error ("Failed to fetch learning path");
            }
            const data = await response.json();
            setLearningPath(data);
          }catch(err){
            setError(err.message);
          }finally{
            setLoading(false);
          }
        }
        fetchLearningPath();
      },[]);
      if(loading){
        return <p>Loading learning path...</p>;
      }
      if(error){
        return <p>Error:{error}</p>;
      }
  return (
    <div>
      <h1>StudyGraph AI</h1>
      <h2>Your Learning Path</h2>

      {learningPath.length === 0 ? (
        <p>No prerequisite relationships found</p>):(
          <ul style={{listStyle:"none",padding:0}}>
            {learningPath.map((item,index) => (
            <li Key={index}>{item}</li>
          ))}
          </ul>
        )}
    </div>
  );
}

export default Display