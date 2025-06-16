document.addEventListener('DOMContentLoaded', function() {
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

    const likeButtons = document.querySelectorAll('.like-button');

    likeButtons.forEach(button => {
        button.addEventListener('click', function() {
            const resenaId = this.getAttribute('data-resena-id');

            const headers = new Headers();
            headers.append('Content-Type', 'application/json');
            headers.append(csrfHeader, csrfToken);

            fetch(`/ficha-objeto/like-resena/${resenaId}`, {
                method: 'POST',
                headers: headers,
                credentials: 'include'
            })
                .then(response => {
                    if (!response.ok) throw new Error('HTTP error: ' + response.status);
                    return response.json();
                })
                .then(data => {
                    // Actualizar estado del botón
                    this.setAttribute('data-liked', data.liked);

                    // Cambiar icono y texto
                    const icon = this.querySelector('i');
                    icon.className = data.liked ? 'bi bi-heart-fill' : 'bi bi-heart';

                    const likeText = this.querySelector('.like-text');
                    likeText.textContent = data.liked ? ' Quitar Me gusta' : ' Me gusta';

                    // Actualizar contador
                    this.querySelector('.like-count').textContent = data.likeCount;

                    // Cambiar clases de estilo
                    if (data.liked) {
                        this.classList.add('bg-danger', 'text-white');
                    } else {
                        this.classList.remove('bg-danger', 'text-white');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error al actualizar el like');
                });
        });
    });
});