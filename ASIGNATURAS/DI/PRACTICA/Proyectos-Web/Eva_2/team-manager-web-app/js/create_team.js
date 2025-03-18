/* create_team.js */

document.addEventListener('DOMContentLoaded', () => {
    // Populate leader dropdown with users from storage
    const leaderSelect = document.getElementById('leader');
    const users = loadUsers();

    users.forEach(user => {
        let option = document.createElement('option');
        option.value = user.id;
        option.textContent = user.name + ' ' + user.surname;
        leaderSelect.appendChild(option);
    });

    // Handle form submission
    const form = document.getElementById('createTeamForm');
    form.addEventListener('submit', (e) => {
        e.preventDefault();

        // Get form values
        const teamName = document.getElementById('teamName').value;
        const leaderId = parseInt(document.getElementById('leader').value);

        // Create new team instance
        const newTeam = new Team(teamName, leaderId);

        // Load existing teams and add new team
        const teams = loadTeams();
        teams.push(newTeam);
        saveTeams(teams);

        // Edit user to add team id
        const leader = users.find(user => user.id === leaderId);
        leader.teams.push(newTeam.id);
        saveUsers(users);

        // Reset form
        form.reset();
        alert('Team created successfully.');
    });
});
