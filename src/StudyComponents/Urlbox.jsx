import React, { useState } from 'react'

const Urlbox = () => {
    const [youtubeUrl, setYoutubeUrl]=useState("");
    const [message, setMessage]=useState("");

    const handleAnalyze = () =>{
        if(!youtubeUrl.trim()){
            setMessage("Please enter a YouTube URL");
            return;
        }
        try{
            const url = new URL(youtubeUrl);
            const isYouTube = url.hostname === "youtube.com" ||
                              url.hostname === "www.youtube.com" ||
                              url.hostname === "m.youtube.com" ||
                              url.hostname === "youtu.be" ||
                              url.hostname === "www.youtube-nocookie.com";

            if(!isYouTube){
                setMessage("Please enter a valid YouTube URL");
                return;
            }
            setMessage("URL accepted ! Backed integration is our next step.");
        }catch{
            setMessage("Please enter a valid URL");
        }
    }
  return (
   <div className="app">
      <aside className="sidebar">
        <div className="brand">
          <span className="brand-icon">✳</span>
          <div>
            <h2>StudyGraph AI</h2>
            <p>Watch · Learn · Grow</p>
          </div>
        </div>

        <nav className="navigation">
          <a className="nav-item active" href="#home">
            🏠 Home
          </a>

          <a className="nav-item" href="#analysis">
            ▶ Video Analysis
          </a>

          <a className="nav-item" href="#graph">
            ◈ Knowledge Graph
          </a>

          <a className="nav-item" href="#learning">
            📚 My Learning
          </a>
        </nav>

        <div className="sidebar-bottom">
          <span>💡</span>
          <h3>Learn Smarter</h3>
          <p>Turn learning videos into knowledge.</p>
        </div>
      </aside>

      <main className="main-content" id="home">
        <header className="topbar">
          <div>
            <h1>Welcome to StudyGraph AI</h1>
            <p>Your personal video learning assistant</p>
          </div>

          <div className="profile">
            <span>👤</span>
            <span>My Workspace</span>
          </div>
        </header>

        <section className="hero" id="analysis">
          <div className="hero-content">
            <span className="eyebrow">AI-POWERED LEARNING</span>

            <h2>Turn YouTube Videos into Knowledge</h2>

            <p>
              Paste a learning video URL to extract its
              summary, important concepts and topics.
            </p>

            <div className="url-form">
              <input
                type="url"
                placeholder="Paste your YouTube video URL here..."
                value={youtubeUrl}
                onChange={(event) => {
                  setYoutubeUrl(event.target.value);
                  setMessage("");
                }}
                onKeyDown={(event) => {
                  if (event.key === "Enter") {
                    handleAnalyze();
                  }
                }}
              />

              <button onClick={handleAnalyze}>
                ✨ Analyze Video
              </button>
            </div>

            {message && (
              <p className="message" role="status">
                {message}
              </p>
            )}

            <small>
              Example: https://www.youtube.com/watch?v=...
            </small>
          </div>

          <div className="hero-illustration" aria-hidden="true">
            <div className="video-icon">▶</div>
            <div className="graph-icon">
              <span>●</span>
              <span>●</span>
              <span>●</span>
              <span>●</span>
              <span>●</span>
              <span>●</span>
            </div>
            <p>Video → Knowledge</p>
          </div>
        </section>

        <section className="workflow">
          <h3>How It Works</h3>

          <div className="workflow-grid">
            <div className="workflow-step">
              <span>🔗</span>
              <h4>1. Add a Video</h4>
              <p>Paste a YouTube URL</p>
            </div>

            <div className="workflow-step">
              <span>🧠</span>
              <h4>2. AI Analysis</h4>
              <p>Extract useful information</p>
            </div>

            <div className="workflow-step">
              <span>🔗</span>
              <h4>3. Build Knowledge</h4>
              <p>Connect related concepts</p>
            </div>

            <div className="workflow-step">
              <span>💡</span>
              <h4>4. Learn Smarter</h4>
              <p>Explore topics and prerequisites</p>
            </div>
          </div>
        </section>

        <section className="empty-state" id="learning">
          <span className="empty-icon">📖</span>
          <h3>Your learning journey starts here</h3>
          <p>
            Enter a YouTube URL above to get started.
            Your video analysis results will appear here
            once we connect the backend and OpenAI API.
          </p>
        </section>

        <footer>
          StudyGraph AI · Learn something new every day.
        </footer>
      </main>
    </div>
  )
}

export default Urlbox