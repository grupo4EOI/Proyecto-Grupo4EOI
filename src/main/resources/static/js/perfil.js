window.addEventListener('DOMContentLoaded', () => {
    const textarea = document.getElementById("biografia");
    const contador = document.getElementById("contador");


    function actualizarContadorEstado() {
        if (textarea && contador) {
            contador.textContent = `${textarea.value.length} / 255`;
        }
    }

    if (textarea && contador) {
        actualizarContadorEstado();
        textarea.addEventListener("input", actualizarContadorEstado);
    }
});
