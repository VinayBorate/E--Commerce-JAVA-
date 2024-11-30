const orderHistory = {
         'VEG-123456': {
             orderNumber: "VEG-123456",
             orderDate: "2023-09-15",
             status: "Delivered",
             total: 15.25,
             items: [
                 { name: "Carrots", quantity: 2, price: 1.99 },
                 { name: "Tomatoes", quantity: 1, price: 2.49 },
                 { name: "Broccoli", quantity: 3, price: 1.79 }
             ]
         },
         'VEG-123457': {
             orderNumber: "VEG-123457",
             orderDate: "2023-09-20",
             status: "In Transit",
             total: 22.97,
             items: [
                 { name: "Spinach", quantity: 1, price: 3.99 },
                 { name: "Bell Peppers", quantity: 2, price: 2.99 },
                 { name: "Cucumbers", quantity: 3, price: 1.49 }
             ]
         },
         'VEG-123458': {
             orderNumber: "VEG-123458",
             orderDate: "2023-09-25",
             status: "Processing",
             total: 18.45,
             items: [
                 { name: "Lettuce", quantity: 1, price: 2.99 },
                 { name: "Onions", quantity: 2, price: 1.29 },
                 { name: "Potatoes", quantity: 5, price: 0.99 }
             ]
         }
     };

     function showOrderDetails(orderNumber) {
         const order = orderHistory[orderNumber];
         const modalBody = document.getElementById('orderDetailsBody');
         modalBody.innerHTML = `
             <h5>Order #${order.orderNumber}</h5>
             <p><strong>Date:</strong> ${order.orderDate}</p>
             <p><strong>Status:</strong> ${order.status}</p>
             <h6>Items:</h6>
             <ul>
                 ${order.items.map(item => `
                     <li>${item.name} - Quantity: ${item.quantity}, Price: $${item.price.toFixed(2)}</li>
                 `).join('')}
             </ul>
             <p><strong>Total:</strong> $${order.total.toFixed(2)}</p>
         `;

         const modal = new bootstrap.Modal(document.getElementById('orderDetailsModal'));
         modal.show();
     }