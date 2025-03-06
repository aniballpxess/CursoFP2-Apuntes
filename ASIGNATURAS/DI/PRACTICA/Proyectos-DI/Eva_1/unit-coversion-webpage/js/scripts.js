function convertCelsiusToFahrenheit() {
    const celsius_input = document.getElementById("c").value;
    const celsius_value = parseFloat(celsius_input);
    const fahrenheit_value = (celsius_value * 9 / 5) + 32;
    document.getElementById("f").value = fahrenheit_value;
}

function convertKilogramsToPounds() {
    const kg_input = document.getElementById("kg").value;
    const kg_value = parseFloat(kg_input);
    const lbs_value = kg_value * 2.205;
    document.getElementById("lbs").value = lbs_value;
}

function convertKilometersToMiles() {
    const km_input = document.getElementById("km").value;
    const km_value = parseFloat(km_input);
    const miles_value = km_value / 1.609;
    document.getElementById("m").value = miles_value;
}