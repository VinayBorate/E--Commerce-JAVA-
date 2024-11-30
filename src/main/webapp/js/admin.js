function showSection(sectionId) {
          document.querySelectorAll('.section').forEach(section => {
              section.classList.remove('active');
          });
          document.getElementById(sectionId).classList.add('active');
      }

      function togglePersonalEdit() {
          const form = document.getElementById('personalDetailsForm');
          const inputs = form.querySelectorAll('input, textarea');
          inputs.forEach(input => {
              input.readOnly = !input.readOnly;
          });
          document.getElementById('updatePersonalButton').innerText = form.querySelector('input').readOnly ? 'UPDATE' : 'SAVE';
      }

      function previewProfilePicture(event) {
          const profilePic = document.getElementById('profilePic');
          profilePic.src = URL.createObjectURL(event.target.files[0]);
      }