var selected_input_base;
var unselected_bases;
var input_number;
var selected_output_base;

function getBase(selected_option) {
    selected_input_base = selected_option.value;
    unselected_bases = {
        bin: "Binario",
        oct: "Octal",
        dec: "Decimal",
        hex: "Hexadecimal"
    };
    delete unselected_bases[selected_option.value];
    const radio_buttons = document.getElementsByName("input_base");
    radio_buttons.forEach((btn) => { btn.disabled = true; });

    const numpad_buttons = document.getElementsByName("numpad_btn");
    let active_numpad;
    switch (selected_input_base) {
        case "bin":
            active_numpad = 2;
            break;
        case "oct":
            active_numpad = 8;
            break;
        case "dec":
            active_numpad = 10;
            break;
        case "hex":
            active_numpad = 16;
            break;
    }
    for (let i = 0; i < active_numpad; i++) {
        numpad_buttons[i].disabled = false;
    }
    document.getElementById("enter_btn").disabled = false;
    document.getElementById("num_input").disabled = false;
}

function addDigit(digit_btn) {
    const input_num_display = document.getElementById("num_input");
    input_num_display.value = input_num_display.value + digit_btn.textContent;
}

function getNumber(enter_btn) {
    input_number = parseInt(document.getElementById("num_input").value);
    numpad_buttons = document.getElementsByName("numpad_btn");
    numpad_buttons.forEach((btn) => { btn.disabled = true; });
    document.getElementById("num_input").disabled = true;
    enter_btn.disabled = true;

    const output_base = document.getElementById("output_base");
    output_base.disabled = false;
    output_base.innerHTML = null
    Object.entries(unselected_bases).forEach(([key, value]) => {
        const option = document.createElement("option");
        option.setAttribute("id", "base_out_" + key);
        option.setAttribute("value", key);
        option.textContent = value;
        output_base.appendChild(option);
    });
}