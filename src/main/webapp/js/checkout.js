
document.addEventListener("DOMContentLoaded", function() {
    calculateTotal(); // Call when the page is loaded
});

document.getElementById('checkout-form').addEventListener('submit', function() {
    calculateTotal(); // Ensure the total is recalculated before form submission
});

function calculateTotal() {
    const prices = document.querySelectorAll('.item-price');
    let total = 0;

    prices.forEach(price => {
        total += parseFloat(price.innerText);
    });

    const totalFormatted = total.toFixed(2);

    // Update the total price display
    document.getElementById('total').innerText = totalFormatted;

    // Set the hidden input field value for total
    document.getElementById('totalInput').value = totalFormatted;
}
