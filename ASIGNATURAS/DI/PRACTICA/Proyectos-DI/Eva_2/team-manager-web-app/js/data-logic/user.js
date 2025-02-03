/* user.js */

// User class definition
class User {
    constructor(name, surname, birthDate, role = '', teams = []) {
      this.id = getNextUserId(); // auto increment id from storage
      this.name = name;
      this.surname = surname;
      this.birthDate = birthDate;
      this.signupDate = new Date().toISOString(); // current time
      this.teams = teams; // list of team ids
      this.role = role; // role as string
    }
  }
  