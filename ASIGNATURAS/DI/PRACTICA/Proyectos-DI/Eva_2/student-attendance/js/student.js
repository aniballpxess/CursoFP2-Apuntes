class Student {
    constructor(name, imagePath, attendance = false) {
      this.name = name;
      this.imagePath = imagePath;
      this.attendance = attendance;
    }
    
    toggleAttendance() {
      this.attendance = !this.attendance;
    }
  
    isInjustificada() {
      return !this.attendance;
    }
    
    getAttendanceLabel() {
      return this.isInjustificada() ? "I" : "";
    }
  }
  
  export { Student };
  