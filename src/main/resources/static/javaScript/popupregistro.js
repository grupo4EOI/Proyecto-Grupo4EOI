    const loginBtn = document.getElementById('loginBtn');
    const registerBtn = document.getElementById('registerBtn');
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    const signupLink = document.getElementById('signupLink');

    // "Iniciar Sesión" está seleccionado por defecto
    loginBtn.classList.add('active');

    loginBtn.addEventListener('click', () => {
    loginForm.style.display = 'block';
    registerForm.style.display = 'none';
    loginBtn.classList.add('active');
    registerBtn.classList.remove('active');
});

    registerBtn.addEventListener('click', () => {
    loginForm.style.display = 'none';
    registerForm.style.display = 'block';
    registerBtn.classList.add('active');
    loginBtn.classList.remove('active');
});

    signupLink.addEventListener('click', (event) => {
    event.preventDefault(); // Prevenir la navegación por defecto
    loginForm.style.display = 'none';
    registerForm.style.display = 'block';
    registerBtn.classList.add('active');
    loginBtn.classList.remove('active');


});
    document.getElementById("togglePassword").addEventListener("click", function () {
        let passwordInput = document.getElementById("exampleInputPassword1");
        let icon = this;
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
            icon.classList.replace("bx-hide", "bx-show-alt");
        } else {
            passwordInput.type = "password";
            icon.classList.replace("bx-show-alt", "bx-hide");
        }
    });
