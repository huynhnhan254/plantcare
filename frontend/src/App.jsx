import { useState } from 'react'
import './App.css'

import { searchPlants, getPlantDetails } from './services/plantServices'

function App() {
  const [query, setQuery] = useState('')
  const [plants, setPlants] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')
  const [searched, setSearched] = useState(false)
  const [selectedPlant, setSelectedPlant] = useState(null)
  const [detailsLoading, setDetailsLoading] = useState(false)

  async function handleSearch(event) {
    event.preventDefault()

    const searchTerm = query.trim()

    if (!searchTerm) {
      setError('Please enter a plant name to search.')
      return
    }

    setLoading(true)
    setError('')
    setSearched(true)
    setPlants([])

    try {
      const data = await searchPlants(searchTerm)
      setPlants(data)
    } catch (err) {
      setError(
        err instanceof TypeError
          ? 'Cannot connect to the server. Make sure your Spring Boot backend is running.'
          : err.message
      )
    } finally {
      setLoading(false)
    }
  }

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

          <form className="search-form" onSubmit={handleSearch}>
            <input
              type="text"
              value={query}
              onChange={(event) => setQuery(event.target.value)}
              placeholder="Search plants, e.g. Monstera"
              aria-label="Search plants"
            />
            <button type="submit" disabled={loading}>
              {loading ? 'Searching...' : 'Search'}
            </button>
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

          {error && <p className="error-message">{error}</p>}

          {loading && (
            <p className="status-message">Finding plants for you...</p>
          )}

          {!loading && !error && searched && plants.length === 0 && (
            <div className="empty-state">
              <h3>No plants found.</h3>
              <p>Try another plant name, such as Monstera or Rose.</p>
            </div>
          )}

          {!loading && !error && !searched && (
            <div className="empty-state">
              <h3>Your plant discoveries start here.</h3>
              <p>
                Search for a plant to explore species and their details.
              </p>
            </div>
          )}

          {!loading && plants.length > 0 && (
            <div className="plant-grid">
              {plants.map((plant, index) => (
                <article
                  className="plant-card"
                  key={plant.scientificName || `${plant.commonName}-${index}`}
                >
                  {plant.imageUrl ? (
                    <img
                      className="plant-image"
                      src={plant.imageUrl}
                      alt={plant.commonName || plant.scientificName}
                    />
                  ) : (
                    <div className="plant-image image-placeholder">
                      Plant image unavailable
                    </div>
                  )}

                  <div className="plant-card-content">
                    <h3>
                      {plant.commonName || plant.scientificName || 'Unknown plant'}
                    </h3>

                    {plant.commonName && plant.scientificName && (
                      <p className="scientific-name">
                        {plant.scientificName}
                      </p>
                    )}

                    {plant.family && (
                      <p className="plant-family">
                        Family: {plant.family}
                      </p>
                    )}
                  </div>
                </article>
              ))}
            </div>
          )}
        </section>
      </main>

      <footer id="about">
        <p>PlantCare · A little more green, every day.</p>
      </footer>
    </div>
  )
}

export default App