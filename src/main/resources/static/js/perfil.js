function mostrarFormularioBiografia() {
    document.getElementById("form-biografia").style.display = "block";
    document.getElementById("mostrar-biografia").style.display = "none";
    actualizarContadorEstado();
}

function cancelarEdicion() {
    document.getElementById("form-biografia").style.display = "none";
    document.getElementById("mostrar-biografia").style.display = "block";
}

function actualizarContadorEstado() {
    const textarea = document.getElementById("biografia");
    const contador = document.getElementById("contador");

    if (textarea && contador) {
        contador.textContent = `${textarea.value.length} / 255`;
        textarea.removeEventListener("input", actualizarContadorEstado);
        textarea.addEventListener("input", actualizarContadorEstado);
    }
}
//Actualiza al recargar la página.
document.addEventListener("DOMContentLoaded", actualizarContadorEstado);
