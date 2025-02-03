/* team.js */

// Team class definition
class Team {
    constructor(name, leaderId) {
      this.id = getNextTeamId(); // auto increment id from storage
      this.name = name;
      this.leader = leaderId; // user id of leader
      this.creationTime = new Date().toISOString(); // current time
      this.memberCount = 1;
    }
  }
  