import React from 'react'
import Display from './components/Display'
import InputBox from './components/InputBox'
import ConceptAdd from './components/ConceptAdd'
import PrerequisiteRelationship from './components/PrerequisiteRelationship'
import { Routes, Route, Link} from 'react-router-dom'
import Urlbox from './StudyComponents/Urlbox'
import InputUrl from './InputUrl'
import AskAI from './AskAI'

const App = () => {
  return (
    <div>
      <InputUrl/>
    </div>
  )
}

//ye temprory functions the check karne ke liye
/*function Home(){
  return <h2>Learning Path</h2>
}
function SearchPage(){
  return <h2>Search Concepts</h2>
}
function AddConceptPage(){
  return <h2>Add Concept</h2>
}
function RelationshipsPage(){
  return <h2>Create Relationships</h2>
}*/

export default App