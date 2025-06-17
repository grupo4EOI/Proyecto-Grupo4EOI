document.addEventListener('DOMContentLoaded', function() {
    // Inicializar todos los popovers
    var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
    var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl, {
            container: 'body',
            trigger: 'click' // Se activa solo con click
        });
    });

    // Cerrar popovers al hacer clic en cualquier parte del documento
    document.addEventListener('click', function(e) {
        // Verificar si el clic fue fuera de un popover o su trigger
        var isPopover = e.target.closest('[data-bs-toggle="popover"]');
        var isInsidePopover = e.target.closest('.popover');

        if (!isPopover && !isInsidePopover) {
            // Cerrar todos los popovers abiertos
            popoverList.forEach(function(popover) {
                popover.hide();
            });
        }
    });
});