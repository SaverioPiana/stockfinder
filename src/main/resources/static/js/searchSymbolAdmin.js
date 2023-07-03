let searchTimer;

function handleSearchInput() {
    clearTimeout(searchTimer);
    const searchInput = document.getElementById('searchInput').value.trim();

    if (searchInput === '') {
        return; // Skip API call if input is empty
    }

    searchTimer = setTimeout(() => {
        searchSymbol(searchInput);
    }, 1000); // Adjust the delay here (in milliseconds)
}
async function searchSymbol() {
    const searchInput = document.getElementById('searchInput');
    const searchText = searchInput.value.trim();
    const url = `/api/searchSymbol/${searchText}`; // URL to Spring Boot rest controller

    try {
        const response = await fetch(url);
        const data = await response.json();

        const bestMatches = data.bestMatches;

        const searchResults = document.getElementById('searchResults');
        searchResults.innerHTML = '';

        bestMatches.forEach((match) => {
            const symbol = match['1. symbol'];
            const name = match['2. name'];
            const region = match['4. region'];

            const listItem = document.createElement('a')
            listItem.className='list-group-item list-group-item-action';
            listItem.href = `/admin/stock/symbol/${symbol}`;
            listItem.textContent = `${symbol}  -  ${name}  - [${region}]`;
            searchResults.appendChild(listItem);
        });
    } catch (error) {
        console.error('Error:', error);
    }
}