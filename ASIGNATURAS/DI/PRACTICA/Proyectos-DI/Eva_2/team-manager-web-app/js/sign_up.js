/* sign_up.js */

document.addEventListener('DOMContentLoaded', () => {
  // Populate teams dropdown from storage
  const teamsSelect = document.getElementById('teams');
  const teams = loadTeams();

  teams.forEach(team => {
    let option = document.createElement('option');
    option.value = team.id;
    option.textContent = team.name;
    teamsSelect.appendChild(option);
  });

  // Handle form submission
  const form = document.getElementById('signupForm');
  form.addEventListener('submit', (e) => {
    e.preventDefault();

    // Get form values
    const name = document.getElementById('name').value;
    const surname = document.getElementById('surname').value;
    const birthDate = document.getElementById('birthDate').value;
    const role = document.getElementById('role').value;

    // Get selected teams as array of ids (strings to numbers)
    const selectedOptions = Array.from(teamsSelect.selectedOptions);
    const selectedTeamIds = selectedOptions.map(opt => parseInt(opt.value));

    // Create new user instance
    const newUser = new User(name, surname, birthDate, role, selectedTeamIds);

    // Load existing users and add new user
    const users = loadUsers();
    users.push(newUser);
    saveUsers(users);

    // Update team member counts
    const teamsData = loadTeams();
    selectedTeamIds.forEach(teamId => {
      let team = teamsData.find(t => t.id === teamId);
      if (team) {
        team.memberCount = team.memberCount + 1;
      }
    });
    saveTeams(teamsData);

    // Reset form
    form.reset();
    alert('User signed up successfully.');
  });
});
