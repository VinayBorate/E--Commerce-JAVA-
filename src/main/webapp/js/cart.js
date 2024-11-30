// Function to update quantity and total price dynamically
function updateQuantity(itemId, change) {
    const quantityInput = document.getElementById(`quantity-${itemId}`);
    let currentQuantity = parseInt(quantityInput.value);
    const newQuantity = Math.max(0, currentQuantity + change); // Ensure quantity doesn't go below 0
    quantityInput.value = newQuantity;

    // Update the total price of the individual item dynamically
    const itemCard = document.getElementById(itemId);
    const price = parseFloat(itemCard.getAttribute('data-price'));
    const totalItemPrice = price * newQuantity;
    document.getElementById(`total_price_${itemId}`).textContent = totalItemPrice.toFixed(2); // Update total price for individual item

    // Update the total price for all items dynamically
    updateTotal(); // Recalculate total price for all items in the cart
}

// Function to remove an item and update the total dynamically
function removeItem(itemId) {
    const item = document.getElementById(itemId);
    item.remove(); // Remove the item from the DOM
    updateTotal(); // Update total after item removal
}

// Function to update the total price of all items in the cart
function updateTotal() {
    let total = 0; // Initialize total price
    document.querySelectorAll('.card').forEach(card => {
        const price = parseFloat(card.getAttribute('data-price')); // Get price from data attribute
        const quantity = parseInt(card.querySelector('.quantity-input').value); // Get quantity from input
        if (!isNaN(price) && !isNaN(quantity)) {
            total += price * quantity; // Accumulate total
        }
    });
    // Update the total price in the DOM dynamically
    document.getElementById('total-price').textContent = total.toFixed(2); // Ensure two decimal places
}
