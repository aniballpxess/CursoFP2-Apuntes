/* data_init.js */

// Keys for localStorage
const USERS_KEY = 'app_users';
const TEAMS_KEY = 'app_teams';
const USER_ID_KEY = 'app_user_id';
const TEAM_ID_KEY = 'app_team_id';

// Initialize storage if not exists
function initStorage() {
  if (!localStorage.getItem(USERS_KEY)) {
    localStorage.setItem(USERS_KEY, JSON.stringify([]));
  }
  if (!localStorage.getItem(TEAMS_KEY)) {
    localStorage.setItem(TEAMS_KEY, JSON.stringify([]));
  }
  if (!localStorage.getItem(USER_ID_KEY)) {
    localStorage.setItem(USER_ID_KEY, '1');
  }
  if (!localStorage.getItem(TEAM_ID_KEY)) {
    localStorage.setItem(TEAM_ID_KEY, '1');
  }
}

// Reset all storage data to initial state
function resetStorage() {
  localStorage.setItem(USERS_KEY, JSON.stringify([]));
  localStorage.setItem(TEAMS_KEY, JSON.stringify([]));
  localStorage.setItem(USER_ID_KEY, '1');
  localStorage.setItem(TEAM_ID_KEY, '1');
}

function getNextUserId() {
  let id = parseInt(localStorage.getItem(USER_ID_KEY));
  localStorage.setItem(USER_ID_KEY, (id + 1).toString());
  return id;
}

function getNextTeamId() {
  let id = parseInt(localStorage.getItem(TEAM_ID_KEY));
  localStorage.setItem(TEAM_ID_KEY, (id + 1).toString());
  return id;
}

function loadUsers() {
  return JSON.parse(localStorage.getItem(USERS_KEY));
}

function loadTeams() {
  return JSON.parse(localStorage.getItem(TEAMS_KEY));
}

function saveUsers(users) {
  localStorage.setItem(USERS_KEY, JSON.stringify(users));
}

function saveTeams(teams) {
  localStorage.setItem(TEAMS_KEY, JSON.stringify(teams));
}

// Initialize storage on load
initStorage();
