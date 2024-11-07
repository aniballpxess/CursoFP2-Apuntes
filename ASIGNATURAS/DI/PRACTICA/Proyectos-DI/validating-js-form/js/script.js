function checkdata() 
{
    const username = document.getElementById("name");
    const emailid = document.getElementById("email");
    if (username.value === "")
    {
        alert("Please enter the name");
        username.focus();
        return false;
    }
    if (email_address.value === "")
    {
        alert("Please enter the email");
        email_address.focus();
        return false;
    }
    return true;
}