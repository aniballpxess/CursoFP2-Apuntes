/* manage_teams.js */

document.addEventListener('DOMContentLoaded', () => {
    const teamsTableBody = document.getElementById('teamsTable').querySelector('tbody');
    const users = loadUsers();
    let teams = loadTeams();

    // Helper function to create leader dropdown
    function createLeaderDropdown(selectedLeaderId) {
        const select = document.createElement('select');
        users.forEach(user => {
            const option = document.createElement('option');
            option.value = user.id;
            option.textContent = user.name + ' ' + user.surname;
            if (user.id === selectedLeaderId) {
                option.selected = true;
            }
            select.appendChild(option);
        });
        return select;
    }

    // Helper function to create team members multi-select (checkboxes)
    function createMembersMultiSelect(teamId) {
        const container = document.createElement('div');
        users.forEach(user => {
            const label = document.createElement('label');
            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.value = user.id;
            if (user.teams && user.teams.includes(teamId)) {
                checkbox.checked = true;
            }
            label.appendChild(checkbox);
            label.appendChild(document.createTextNode(' ' + user.name + ' ' + user.surname));
            container.appendChild(label);
            container.appendChild(document.createElement('br'));
        });
        return container;
    }

    // Populate table rows
    teams.forEach(team => {
        const tr = document.createElement('tr');

        // ID (not editable)
        const tdId = document.createElement('td');
        tdId.textContent = team.id;
        tr.appendChild(tdId);

        // Name (editable)
        const tdName = document.createElement('td');
        const nameInput = document.createElement('input');
        nameInput.type = 'text';
        nameInput.value = team.name;
        tdName.appendChild(nameInput);
        tr.appendChild(tdName);

        // Leader (dropdown editable)
        const tdLeader = document.createElement('td');
        const leaderSelect = createLeaderDropdown(team.leader);
        tdLeader.appendChild(leaderSelect);
        tr.appendChild(tdLeader);

        // Creation Time (not editable)
        const tdCT = document.createElement('td');
        tdCT.textContent = team.creationTime;
        tr.appendChild(tdCT);

        // Member Count (not editable display)
        const tdMC = document.createElement('td');
        tdMC.textContent = team.memberCount;
        tr.appendChild(tdMC);

        // Team Members (multi-select checkboxes)
        const tdMembers = document.createElement('td');
        const membersMulti = createMembersMultiSelect(team.id);
        tdMembers.appendChild(membersMulti);
        tr.appendChild(tdMembers);

        // Attach row data to team id for later update
        tr.dataset.teamId = team.id;

        teamsTableBody.appendChild(tr);
    });

    // Save changes button handler
    document.getElementById('saveTeamsBtn').addEventListener('click', () => {
        const rows = teamsTableBody.querySelectorAll('tr');
        rows.forEach(row => {
            const teamId = parseInt(row.dataset.teamId);
            let team = teams.find(t => t.id === teamId);
            if (team) {
                team.name = row.cells[1].querySelector('input').value;
                team.leader = parseInt(row.cells[2].querySelector('select').value);
                const memberCheckboxes = row.cells[5].querySelectorAll('input[type="checkbox"]');
                const selectedUserIds = [];
                memberCheckboxes.forEach(cb => {
                    if (cb.checked) {
                        selectedUserIds.push(parseInt(cb.value));
                    }
                });
                if (!selectedUserIds.includes(team.leader)) {
                    selectedUserIds.push(team.leader);
                    alert('Leader must be a team member. Adding leader to team members.');
                }
                team.memberCount = selectedUserIds.length;
                users.forEach(user => {
                    if (!user.teams) user.teams = [];
                    if (selectedUserIds.includes(user.id)) {
                        if (!user.teams.includes(teamId)) {
                            user.teams.push(teamId);
                        }
                    } else {
                        user.teams = user.teams.filter(tid => tid !== teamId);
                    }
                });
            }
        });
        saveTeams(teams);
        saveUsers(users);
        alert('Team changes saved.');
        location.reload();
    });
});
