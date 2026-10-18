import React, { useState } from 'react'
import "./InputUrl.css"
import ReactMarkdown from 'react-markdown'

const InputUrl = () => {
    const [question, setQuestion] = useState('');
    const [answer, setAnswer] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    async function askAI(event) {
        event.preventDefault();

        if (!question.trim()) {
            setError('Please enter your question');
            setAnswer('');
            return;
        }

        try {
            setLoading(true);
            setError('');
            setAnswer('');

            const response = await fetch(
                `http://localhost:8080/ask-ai?question=${encodeURIComponent(
                    question.trim()
                )}`
            );

            if (!response.ok) {
                throw new Error(`Request failed: ${response.status}`);
            }

            const result = await response.text();
            setAnswer(result);
            setQuestion("");

        } catch (error) {
            console.error(error);
            setError(
                'Could not get an AI answer. Check whether Spring Boot is running.'
            );
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className="analyzer-container">
            <h2>AI Personal Learning Assistant</h2>

            <p className="subtitle">
                Ask questions and explore your learning roadmap.
            </p>

            {error && (
                <p className="result-message">{error}</p>
            )}

            {answer && (
                <div className="result-message">
                    <ReactMarkdown>{answer}</ReactMarkdown>
                </div>
            )}


            <form onSubmit={askAI} className="analyzer-form">
                <input
                    type="text"
                    placeholder="e.g. What should I learn before Inheritance?"
                    value={question}
                    onChange={(event) => setQuestion(event.target.value)}
                    className="url-input"
                />

                <button
                    type="submit"
                    disabled={loading}
                    className="analyze-button"
                >
                    {loading ? 'Thinking...' : 'Ask AI'}
                </button>
            </form>

            
        </div>
    );


}



export default InputUrl