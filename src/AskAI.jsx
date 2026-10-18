import React from 'react'

const AskAI = () => {
        const [question, setQuestion] = useState("");
        const [answer, setAnswer] = useState("");
        const [loading, setLoading] = useState(false);
        const [error, setError] = useState("");

        async function handleSubmit(event) {
            event.preventDefault();

            if (!question.trim()) {
                return;
            }

            setLoading(true);
            setAnswer("");
            setError("");

            try {
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
            } catch (err) {
                setError(
                    "AI answer nahi mil saka. Backend aur API configuration check karo."
                );
                console.error(err);
            } finally {
                setLoading(false);
            }
    }
  return (
    <div className="ask-ai">
      <h2>Ask StudyGraph AI</h2>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={question}
          onChange={(event) => setQuestion(event.target.value)}
          placeholder="Ask about inheritance, polymorphism..."
        />

        <button type="submit" disabled={loading}>
          {loading ? "Thinking..." : "Ask AI"}
        </button>
      </form>

      {error && <p className="error">{error}</p>}

      {answer && (
        <div className="ai-answer">
          <h3>AI Answer</h3>
          <p>{answer}</p>
        </div>
      )}
    </div>
  )
}

export default AskAI