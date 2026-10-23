const API_BASE_URL = 'http://localhost:8080/api/v1/plants'

export async function searchPlants(query) {
  const response = await fetch(
    `${API_BASE_URL}/search?q=${encodeURIComponent(query)}`
  )

  if (!response.ok) {
    throw new Error('Unable to search for plants. Please try again.')
  }

  return response.json()
}

export async function getPlantDetails(id) {
  const response = await fetch(`${API_BASE_URL}/${id}`)

  if (!response.ok) {
    throw new Error('Unable to load plant details. Please try again.')
  }

  return response.json()
}
