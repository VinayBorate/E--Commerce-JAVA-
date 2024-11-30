function validatePasswordMatch() {
    debugger;
    // Get the password and confirm password fields
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm-password").value;

    // Get the confirm password input element
    var confirmPasswordField = document.getElementById("confirm-password");

    // Check if passwords match
    if (password !== confirmPassword) {
        // Change the background color to red if they don't match
        confirmPasswordField.style.backgroundColor = "lightcoral";
    } else {
        // Reset the background color if they match
        confirmPasswordField.style.backgroundColor = "";
    }
}


/* Middle content  */

function capitalize(str) {
  return str.charAt(0).toUpperCase() + str.slice(1);
}


function myFunction() {
    if (confirm("Customer Please Log-In To Shop. Do you want to log in now?")) {
        // Redirect to login page if "OK" is clicked
        window.location.href = "login.jsp"; // Change to your login page URL
    } else {
        // Do nothing if "Cancel" is clicked
   
    }
}




  
   // Sort by Vendor
   debugger;
   window.onload = function () {
   document.getElementById("sort-vendor").addEventListener("change", (e) => {
	
     const vendor = e.target.value;
     const productCards = document.querySelectorAll(".product-card");
     productCards.forEach((card) => {
       if (vendor === "all" || card.getAttribute("data-vendor") === vendor) {
         card.style.display = "block";
       } else {
         card.style.display = "none";
       }
     });
   });

   
   }
   // Sort by Price
   // Sort by Price
   document.getElementById("sort-price").addEventListener("change", (e) => {
     const sortValue = e.target.value;
     const activeGrid = document.querySelector(
       ".grid:not([style*='display: none'])"
     ); // Find the visible product grid
     const productCards = Array.from(activeGrid.children);

     if (sortValue !== "none") {
       productCards.sort((a, b) => {
         const priceA = parseInt(a.getAttribute("data-price"));
         const priceB = parseInt(b.getAttribute("data-price"));

         if (sortValue === "low-to-high") {
           return priceA - priceB;
         } else if (sortValue === "high-to-low") {
           return priceB - priceA;
         }
       });

       // Append sorted cards back to the active grid
       productCards.forEach((card) => {
         activeGrid.appendChild(card);
       });
     }
   });
   
   

   document
        .getElementById("category-list")
        .addEventListener("click", (e) => {
          if (e.target.tagName === "LI") {
            const category = e.target.getAttribute("data-category");
            document.querySelectorAll(".grid").forEach((grid) => {
              grid.style.display = "none";
            });
            document.getElementById(category).style.display = "grid";
            document.getElementById(
              "category-title"
            ).innerText = `Buy Fresh ${capitalize(category)} Online`;

            // Update active class
            document.querySelectorAll("#category-list li").forEach((li) => {
              li.classList.remove("active");
            });
            e.target.classList.add("active");
          }
        });

  