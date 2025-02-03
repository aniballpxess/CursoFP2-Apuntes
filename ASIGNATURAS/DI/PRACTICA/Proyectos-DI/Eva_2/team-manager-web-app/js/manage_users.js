/* manage_users.js */

document.addEventListener('DOMContentLoaded', () => {
    const usersTableBody = document.getElementById('usersTable').querySelector('tbody');
    const teams = loadTeams();
    let users = loadUsers();
    
    // Helper function to create teams multi-select (checkboxes)
    function createTeamsMultiSelect(userTeams) {
      const container = document.createElement('div');
      teams.forEach(team => {
        const label = document.createElement('label');
        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.value = team.id;
        if (userTeams && userTeams.includes(team.id)) {
          checkbox.checked = true;
        }
        label.appendChild(checkbox);
        label.appendChild(document.createTextNode(' ' + team.name));
        container.appendChild(label);
        container.appendChild(document.createElement('br'));
      });
      return container;
    }
    
    // Helper function to create role dropdown
    function createRoleDropdown(selectedRole) {
      const select = document.createElement('select');
      const roles = ['', 'senior', 'junior', 'freelancer', 'tester', 'manager'];
      roles.forEach(role => {
        const option = document.createElement('option');
        option.value = role;
        option.textContent = role === '' ? '--None--' : role;
        if (role === selectedRole) {
          option.selected = true;
        }
        select.appendChild(option);
      });
      return select;
    }
    
    // Populate table rows
    users.forEach(user => {
      const tr = document.createElement('tr');
      
      // ID (not editable)
      const tdId = document.createElement('td');
      tdId.textContent = user.id;
      tr.appendChild(tdId);
      
      // Name (editable)
      const tdName = document.createElement('td');
      const nameInput = document.createElement('input');
      nameInput.type = 'text';
      nameInput.value = user.name;
      tdName.appendChild(nameInput);
      tr.appendChild(tdName);
      
      // Surname (editable)
      const tdSurname = document.createElement('td');
      const surnameInput = document.createElement('input');
      surnameInput.type = 'text';
      surnameInput.value = user.surname;
      tdSurname.appendChild(surnameInput);
      tr.appendChild(tdSurname);
      
      // Birth Date (editable)
      const tdBD = document.createElement('td');
      const bdInput = document.createElement('input');
      bdInput.type = 'date';
      bdInput.value = user.birthDate;
      tdBD.appendChild(bdInput);
      tr.appendChild(tdBD);
      
      // Signup Date (not editable)
      const tdSD = document.createElement('td');
      tdSD.textContent = user.signupDate;
      tr.appendChild(tdSD);
      
      // Teams (multi-select checkboxes)
      const tdTeams = document.createElement('td');
      const teamsMulti = createTeamsMultiSelect(user.teams);
      tdTeams.appendChild(teamsMulti);
      tr.appendChild(tdTeams);
      
      // Role (dropdown)
      const tdRole = document.createElement('td');
      const roleSelect = createRoleDropdown(user.role);
      tdRole.appendChild(roleSelect);
      tr.appendChild(tdRole);
      
      tr.dataset.userId = user.id;
      usersTableBody.appendChild(tr);
    });
    
    // Save changes button handler
    document.getElementById('saveUsersBtn').addEventListener('click', () => {
      const rows = usersTableBody.querySelectorAll('tr');
      rows.forEach(row => {
        const userId = parseInt(row.dataset.userId);
        let user = users.find(u => u.id === userId);
        if (user) {
          user.name = row.cells[1].querySelector('input').value;
          user.surname = row.cells[2].querySelector('input').value;
          user.birthDate = row.cells[3].querySelector('input').value;
          const teamCheckboxes = row.cells[5].querySelectorAll('input[type="checkbox"]');
          const selectedTeamIds = [];
          teamCheckboxes.forEach(cb => {
            if (cb.checked) {
              selectedTeamIds.push(parseInt(cb.value));
            }
          });
          user.teams = selectedTeamIds;
          const teamsUserLeads = teams.filter(team => team.leader === userId);
          forEach(teamsUserLeads, team => {
            if (!user.teams.includes(team.id)) {
              user.teams.push(team.id);
              alert('User is the team leader. Cannot delete from team.');
            }
          });
          user.role = row.cells[6].querySelector('select').value;
        }
      });
      saveUsers(users);
      
      let teamsData = loadTeams();
      teamsData.forEach(team => {
        let count = 0;
        users.forEach(user => {
          if (user.teams && user.teams.includes(team.id)) {
            count++;
          }
        });
        team.memberCount = count;
      });
      saveTeams(teamsData);
      
      alert('User changes saved.');
      location.reload();
    });
  });
  