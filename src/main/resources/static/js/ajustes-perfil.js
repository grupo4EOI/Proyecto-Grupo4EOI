function seleccionarGeneroUnico(event, elemento) {
    event.preventDefault();

    const dropdown = elemento.closest(".dropdown");
    const input = dropdown.querySelector("input[type='hidden']");

    const selectedValue = elemento.textContent.trim();

    const siblings = elemento.closest("ul").querySelectorAll(".dropdown-item");
    siblings.forEach(item => item.classList.remove("active"));
    elemento.classList.add("active");

    if (input) {
        input.value = selectedValue;
    }

    const button = dropdown.querySelector("button");
    button.textContent = selectedValue;
}
