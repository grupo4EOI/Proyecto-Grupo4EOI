function seleccionarGeneroUnico(event, elemento){
    event.preventDefault();

    const inputId = elemento.dataset.inputId;
    const input = document.getElementById(inputId);

    const selectedValue = elemento.dataset.id;

    const siblings = elemento.closest("ul").querySelectorAll(".dropdown-item");
    siblings.forEach(item => item.classList.remove("active"));
    elemento.classList.add("active");

    if (input) {
        input.value = selectedValue;
    }

    const button = elemento.closest(".dropdown").querySelector("button");
    button.textContent = elemento.textContent;
}
