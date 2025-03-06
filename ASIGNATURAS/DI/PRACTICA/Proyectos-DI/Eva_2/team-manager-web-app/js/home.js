/* home.js */

document.addEventListener('DOMContentLoaded', () => {
  // Button elements for sample data and reset data
  const sampleDataBtn = document.getElementById('sampleDataBtn');
  const resetDataBtn = document.getElementById('resetDataBtn');

  // Function to generate a random integer in the range [min, max]
  function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

  // Sample data arrays for names and roles
  const sampleTeamNames = [
    "Frontend Wizards",
    "Backend Builders",
    "DevOps Dynamos",
    "QA Crusaders",
    "UX/UI Innovators",
    "Mobile Mavericks",
    "Data Science Gurus",
    "Security Sentinels"
  ];
  const sampleFirstNames = [
    "Alice", "Bob", "Carol", "David", "Eve",
    "Frank", "Grace", "Heidi", "Ivan", "Judy"
  ];
  const sampleLastNames = [
    "Smith", "Johnson", "Williams", "Jones", "Brown",
    "Davis", "Miller", "Wilson", "Moore", "Taylor"
  ];
  const sampleRoles = ["senior", "junior", "freelancer", "tester", "manager", ""];

  // Handler for adding sample data
  sampleDataBtn.addEventListener('click', () => {
    // Reset storage before adding sample data
    resetStorage();

    const sampleUsers = [];
    const sampleTeams = [];

    // Generate 20 sample users
    for (let i = 0; i < 12; i++) {
      // Choose a random first and last name
      const firstName = sampleFirstNames[getRandomInt(0, sampleFirstNames.length - 1)];
      const lastName = sampleLastNames[getRandomInt(0, sampleLastNames.length - 1)];
      // Generate a random birth date between 1970 and 2000
      const year = getRandomInt(1970, 2000);
      const month = getRandomInt(1, 12);
      const day = getRandomInt(1, 28);
      const birthDate = `${year}-${('0' + month).slice(-2)}-${('0' + day).slice(-2)}`;
      // Choose a random role (could be an empty string)
      const role = sampleRoles[getRandomInt(0, sampleRoles.length - 1)];

      // Create a new user with an initially empty teams array
      const newUser = new User(firstName, lastName, birthDate, role, []);
      sampleUsers.push(newUser);
    }

    // Choose 3 random team names and create teams with random leaders
    for (let i = 0; i < 4; i++) {
      const teamName = sampleTeamNames[getRandomInt(0, sampleTeamNames.length - 1)];
      // Temporarily assign leader as 0; will update after creation
      const newTeam = new Team(teamName, 0);
      sampleTeams.push(newTeam);
    }

    // Randomly assign a leader for each team from sample users
    sampleTeams.forEach(team => {
      const randomUser = sampleUsers[getRandomInt(0, sampleUsers.length - 1)];
      team.leader = randomUser.id;
      randomUser.teams.push(team.id);
    });

    // For each user, randomly decide team memberships (each team has a 30% chance)
    sampleUsers.forEach(user => {
      sampleTeams.forEach(team => {
        if (Math.random() < 0.3) {
          // Avoid duplicate team ids in the user's teams array
          if (!user.teams.includes(team.id)) {
            user.teams.push(team.id);
          }
        }
      });
    });

    // Recalculate team member counts based on user memberships
    sampleTeams.forEach(team => {
      let count = 0;
      sampleUsers.forEach(user => {
        if (user.teams.includes(team.id)) {
          count++;
        }
      });
      team.memberCount = count;
    });

    // Save updated sample users and teams to storage
    saveUsers(sampleUsers);
    saveTeams(sampleTeams);

    alert('Sample data added successfully.');
    location.reload();
  });

  // Handler for resetting all data
  resetDataBtn.addEventListener('click', () => {
    resetStorage();
    alert('All data has been reset.');
    location.reload();
  });
});
