document.addEventListener("DOMContentLoaded", () => {
  const btnSeleccionar = document.getElementById("btnSeleccionar");
  const modal = document.getElementById("modalDatosJugadores");
  const btnCancelar = document.getElementById("btnCancelar");
  const form = document.getElementById("datosJugadores");
  const modalFondo = modal.querySelector(".modal-fondo");

  function abrirModal() { modal.classList.remove("hidden");}

  function cerrarModal() { modal.classList.add("hidden");
    form.reset(); // opcional
  }

  // Abrir modal al apretar "Seleccionar"
  btnSeleccionar.addEventListener("click", abrirModal);

  // Cerrar con "Cancelar"
  btnCancelar.addEventListener("click", cerrarModal);

  // Cerrar si clickeás el fondo (afuera del cuadro)
  modalFondo.addEventListener("click", cerrarModal);

  // Al enviar el form (botón "Jugar")
  form.addEventListener("submit", (e) => {
    e.preventDefault();

    const nombre = document.getElementById("nombre").value.trim();
    const ficha = document.getElementById("ficha").value;

    if (!nombre) return; // required ya ayuda, pero por las dudas

    console.log("Jugador:", nombre, "Ficha:", ficha);

    // acá podrías guardar datos / ir a la pantalla del tablero, etc.
    cerrarModal();
  });
});
