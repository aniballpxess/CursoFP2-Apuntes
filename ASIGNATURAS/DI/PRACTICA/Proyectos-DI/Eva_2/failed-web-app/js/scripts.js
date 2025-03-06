// Manage user registrations
const form = document.getElementById('registration-form');
const userTable = document.getElementById('user-table') ? document.getElementById('user-table').querySelector('tbody') : null;

// Handle registration
if (form) {
    document.getElementById('signup-button').addEventListener('click', () => {
        const formData = new FormData(form);
        const user = {
            fullName: formData.get('full-name'),
            nickname: formData.get('nickname'),
            country: formData.get('country'),
            phone: formData.get('phone'),
            gender: formData.get('gender'),
            birthDate: formData.get('birth-date'),
            role: formData.get('role'),
            description: formData.get('extra-description')
        };

        localStorage.setItem(new Date().getTime(), JSON.stringify(user));
        form.reset();
        alert('User registered successfully!');
    });
}

// Handle displaying users
if (userTable) {
    for (let key in localStorage) {
        if (localStorage.hasOwnProperty(key)) {
            const user = JSON.parse(localStorage.getItem(key));
            if (user) {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td><input type="checkbox" class="select-user"></td>
                    <td>${user.fullName}</td>
                    <td>${user.nickname}</td>
                    <td>${user.country}</td>
                    <td>${user.phone}</td>
                    <td>${user.gender}</td>
                    <td>${user.birthDate}</td>
                    <td>${user.role}</td>
                    <td>${user.description}</td>
                `;
                userTable.appendChild(row);
            }
        }
    }

    // Handle deletion
    document.getElementById('delete-button').addEventListener('click', () => {
        const checkboxes = document.querySelectorAll('.select-user:checked');
        checkboxes.forEach(checkbox => {
            const row = checkbox.closest('tr');
            const fullName = row.children[1].textContent;

            for (let key in localStorage) {
                if (localStorage.hasOwnProperty(key)) {
                    const user = JSON.parse(localStorage.getItem(key));
                    if (user && user.fullName === fullName) {
                        localStorage.removeItem(key);
                        break;
                    }
                }
            }

            row.remove();
        });
    });
}