
import './App.css'

function App() {
  return (
    <div className="app">
      <header className="navbar">
        <a className="logo" href="/">
          PlantCare
        </a>

        <nav>
          <a href="#explore">Explore Plants</a>
          <a href="#about">About</a>
        </nav>
      </header>

      <main>
        <section className="hero" id="explore">
          <p className="eyebrow">GROW SOMETHING GOOD</p>

          <h1>
            Find the right plants
            <br />
            for your space.
          </h1>

          <p className="hero-description">
            Explore plant species, discover their names, and
            start building your own plant collection.
          </p>

          <form className="search-form">
            <input
              type="text"
              placeholder="Search plants, e.g. Monstera"
              aria-label="Search plants"
            />
            <button type="submit">Search</button>
          </form>
        </section>

        <section className="results">
          <div className="section-heading">
            <div>
              <p className="eyebrow">YOUR NEXT GREEN FRIEND</p>
              <h2>Explore plants</h2>
            </div>
            <p>Discover your next favorite plant.</p>
          </div>

          <div className="empty-state">
            <h3>Your plant discoveries start here.</h3>
            <p>
              Search for a plant to explore species and their details.
            </p>
          </div>
        </section>
      </main>

      <footer id="about">
        <p>PlantCare · A little more green, every day.</p>
      </footer>
    </div>
  )
}

export default App
