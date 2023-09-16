const emailUser = document.querySelector('#email');
const password = document.querySelector('#password'); // corrected ID
const confirmPassword = document.querySelector('#confirm-password'); // corrected ID
const toggleButton = document.querySelectorAll('.togglePassword');

document.querySelector('form').addEventListener('submit', function (event) {
    event.preventDefault();

    if (password.value !== confirmPassword.value) {
        alert("Passwords and Confirm Passwords are not the same. Try again.");
        return;
    }
    const data = {email: emailUser.value, password: password.value};
    console.log(data); // correct, delete after debugged
    const urlAPI = 'http://localhost:8080/signup';

    fetch(urlAPI, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then((response) => handleErrors(response))
        .then((data) => succeed(data))
        .catch((error) => fail(error));
});

const handleErrors = function (response) {
    if (response.status === 201) {
        return response.text();
    }
    return response.text().then(errorText => {
        throw {
            status: response.status,
            statusText: response.statusText,
            message: errorText
        };
    });
}

const succeed = function (data) {
    window.location.href = '../html/login.html';
}

const fail = function (error) {
    emailUser.value = '';
    password.value = '';
    confirmPassword.value = '';
    password.type = 'password';
    toggleButton.textContent = 'Show';

    if (error.status === 400) {
        console.error('Bad Request:', error.message);
    } else if (error.status === 409) {
        if (error.message.includes("User with this email already exists"))
            console.error('Conflict - User:', error.message);
    } else if (error.status === 503) {
        console.error('Service Unavailable:', error.message);
    } else {
        // for any other errors
        console.error('An error occurred:', error.status, error.statusText);
    }
}

toggleButton.forEach(btn => {
    btn.addEventListener('click', e => {
        const currentInput = e.target.parentElement.nextElementSibling;

        if (currentInput.type === 'password') {
           currentInput.type = 'text';
            btn.textContent = 'Hide';
        } else {
           currentInput.type = 'password';
            btn.textContent = 'Show';
        }
    });
});
