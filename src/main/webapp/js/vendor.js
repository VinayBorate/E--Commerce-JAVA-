  function showSection(sectionId) {
            document.querySelectorAll('.section').forEach(section => {
                section.classList.remove('active');
            });
            document.getElementById(sectionId).classList.add('active');
        }

        function calculateNetPrice() {
            const price = parseFloat(document.getElementById('vegetablePrice').value);
            const discount = parseFloat(document.getElementById('vegetableDiscount').value);
            if (!isNaN(price) && !isNaN(discount)) {
                const netPrice = price - discount;
                document.getElementById('vegetableNetPrice').value = netPrice.toFixed(2);
            }
        }

        function populateForm(vegetable_id ,name, category, stock, description, price, discount ) {
			debugger;
            document.getElementById('vegetableName').value = name;
            document.getElementById('vegetableCategory').value = category;
            document.getElementById('vegetableStock').value = stock;
            document.getElementById('vegetableDescription').value = description; 
            document.getElementById('vegetablePrice').value = price;
            document.getElementById('vegetableDiscount').value = discount;
            document.getElementById('vegetableNetPrice').value = (price - discount).toFixed(2);
            // Assuming the image is in local storage, you can update the file input field manually if necessary
			document.getElementById('veg_id').value = vegetable_id;
            document.getElementById('formAction').value = 'update';  		
		 // document.getElementById('imagePreview').src = image;
            document.getElementById('submitButton').textContent = 'Update Vegetables'; 
        }

        function resetForm() {
        // Reset all form fields
        document.getElementById('vegetableForm').reset();
        
        // Reset the hidden input field value to "add"
        document.getElementById('formAction').value = 'add';
        
        // Reset the form button text back to "Add Vegetables"
        document.getElementById('submitButton').textContent = 'Add Vegetables';
    }

    function togglePersonalEdit() {
        const fields = ['firstName', 'lastName', 'address', 'state', 'country', 'zipCode'];
        const button = document.getElementById('updatePersonalButton');
        
        fields.forEach(field => {
            const element = document.getElementById(field);
            element.readOnly = !element.readOnly; // Toggle readOnly attribute
        });
        
        button.textContent = button.textContent === 'UPDATE' ? 'SAVE' : 'UPDATE';
        if (button.textContent === 'SAVE') {
            // Enable Check Username button when personal details are editable
            document.getElementById('checkUsernameButton').disabled = false;
        }
    }

    function toggleLoginEdit() {
        const fields = ['username', 'password', 'email'];
        const button = document.getElementById('updateLoginButton');

        fields.forEach(field => {
            const element = document.getElementById(field);
            element.readOnly = !element.readOnly; // Toggle readOnly attribute
        });

        button.textContent = button.textContent === 'UPDATE' ? 'SAVE' : 'UPDATE';
        if (button.textContent === 'SAVE') {
            // Enable Check Username button when login details are editable
            document.getElementById('checkUsernameButton').disabled = false;
        }
    }

    function updatePersonalDetails(event) {
        event.preventDefault();
        // Handle personal details update logic here
        console.log('Updating personal details...');
        togglePersonalEdit(); // Toggle edit mode back
    }

    function updateLoginDetails(event) {
        event.preventDefault();
        // Handle login details update logic here
        console.log('Updating login details...');
        toggleLoginEdit(); // Toggle edit mode back
    }

    


	// JavaScript to handle opening and closing the dialog box dynamically
	function openDialog(action, vegName, quantity, vegId, vendorId) {
	    // Update the dialog title based on the action (update or refill)
	    document.getElementById("dialogTitle").innerText = action === 'update' ? 'Update Stock' : 'Refill Stock';

	    // Set the hidden input values with the selected vegetable's details
	    document.getElementById("inventory_action").value = action;
	    document.getElementById("veg_id").value = vegId;
	    document.getElementById("veg_name").value = vegName;
	    document.getElementById("vendor_id").value = vendorId;

	    // Pre-fill the stock input field with the existing quantity if updating, or 0 for refill
	    document.getElementById("newStock").value = quantity;

	    // Display the dialog box
	    document.getElementById("dialog").style.display = "block";
	}

	// Close the dialog box
	function closeDialog() {
	    document.getElementById("dialog").style.display = "none";
	}



