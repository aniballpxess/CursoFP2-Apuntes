import { Student } from './student.js'; // Import the Student class

// Define the classroom object to store student data
const classroom = {
  students: [],
};

// Function to load and parse the CSV file
function loadCSV(filePath, callback) {
  fetch(filePath)
    .then((response) => response.text())
    .then((data) => {
      const rows = data.split("\n").map(row => row.trim()).filter(row => row);
      callback(rows); // Pass parsed rows to the callback
    })
    .catch((error) => console.error("Error loading CSV:", error));
}

// Function to create a student cell
function createStudentCell(student) {
  const studentCell = document.createElement("div");
  studentCell.className = "student-cell";

  // Add photo
  const img = document.createElement("img");
  img.src = student.imagePath || "../resources/img/placeholder.webp"; // Local image path
  img.alt = `Photo of ${student.name}`;
  img.className = "student-photo";
  studentCell.appendChild(img);

  // Add student name
  const nameDiv = document.createElement("div");
  nameDiv.className = "student-name";
  nameDiv.textContent = student.name;
  studentCell.appendChild(nameDiv);

  // Add attendance options (J, I, R)
  const attendanceOptions = document.createElement("div");
  attendanceOptions.className = "attendance-options";

  // Justificada (J) checkbox
  const jLabel = document.createElement("label");
  const jCheckbox = document.createElement("input");
  jCheckbox.type = "checkbox";
  jCheckbox.checked = student.getAttendanceLabel() === "J";
  jLabel.appendChild(jCheckbox);
  jLabel.append(" J");
  attendanceOptions.appendChild(jLabel);

  // Injustificada (I) checkbox
  const iLabel = document.createElement("label");
  const iCheckbox = document.createElement("input");
  iCheckbox.type = "checkbox";
  iCheckbox.checked = student.isInjustificada(); // Marked as "I" if the student is absent
  iCheckbox.addEventListener("change", () => {
    student.toggleInjustificada(); // Toggle attendance (I)
    renderStudents(); // Re-render the grid with updated attendance
  });
  iLabel.appendChild(iCheckbox);
  iLabel.append(" I");
  attendanceOptions.appendChild(iLabel);

  // Retraso (R) checkbox
  const rLabel = document.createElement("label");
  const rCheckbox = document.createElement("input");
  rCheckbox.type = "checkbox";
  rCheckbox.checked = student.getAttendanceLabel() === "R";
  rLabel.appendChild(rCheckbox);
  rLabel.append(" R");
  attendanceOptions.appendChild(rLabel);

  studentCell.appendChild(attendanceOptions);

  return studentCell;
}

// Function to render students into the grid
function renderStudents() {
  const grid = document.getElementById("attendance-grid");
  const studentCells = classroom.students.map(createStudentCell);
  
  // Clear existing content and replace with new content
  grid.replaceChildren(...studentCells);
}

// On window load, fetch the CSV and populate the grid
window.onload = () => {
  const csvPath = "../resources/classroom.csv";
  loadCSV(csvPath, (students) => {
    // Create Student instances from CSV data with a placeholder image path
    classroom.students = students.map(name => new Student(name, "../resources/img/placeholder.png"));
    renderStudents();
  });
};
