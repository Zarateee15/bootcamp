document.addEventListener("DOMContentLoaded", () => {
  const selectModo = document.getElementById("opcion");
  const btnSeleccionar = document.getElementById("btnSeleccionar");

  const modalJcj = document.getElementById("modalDatosJcj");
  const modalJcc = document.getElementById("modalDatosJcc");

  const formJcj = document.getElementById("datosJcj");
  const formJcc = document.getElementById("datosJcc");

  function abrirModalCorrecto() {
    const modo = selectModo.value;
    const modal = (modo === "jcj") ? modalJcj : modalJcc;
    modal.classList.remove("hidden");
  }

  function cerrarModal(modal) {
    modal.classList.add("hidden");
    const form = modal.querySelector("form");
    form?.reset();
  }

  // abrir modal según opción actual
  btnSeleccionar.addEventListener("click", abrirModalCorrecto);

  // cancelar / fondo para cada modal
  [modalJcj, modalJcc].forEach((modal) => {
    const btnCancelar = modal.querySelector(".btnCancelar"); // mejor clase, no id repetido
    const fondo = modal.querySelector(".modal-fondo");

    btnCancelar?.addEventListener("click", () => cerrarModal(modal));
    fondo?.addEventListener("click", () => cerrarModal(modal));
  });

  // submit JCJ
  formJcj.addEventListener("submit", (e) => {
    e.preventDefault();
    const modo = "jcj";

    const nombre1 = document.getElementById("nombre1").value.trim();
    const ficha1  = document.getElementById("ficha1").value;

    const nombre2 = document.getElementById("nombre2").value.trim();
    const ficha2  = document.getElementById("ficha2").value;

    if (!nombre1 || !nombre2) return alert("Los nombres no pueden estar vacíos.");
    if (nombre1 === nombre2) return alert("Los nombres no pueden ser iguales.");
    if (ficha1 === ficha2) return alert("Cada jugador tiene que elegir una ficha diferente.");

    const jugador1 = { nombre: nombre1, ficha: ficha1 === "jugarX" ? "X" : "O" };
    const jugador2 = { nombre: nombre2, ficha: ficha2 === "jugarX" ? "X" : "O" };

    sessionStorage.setItem("tateti_config", JSON.stringify({ modo, jugador1, jugador2 }));
    window.location.href = "../frontend/tateti.html";
  });

  // submit JCC
  formJcc.addEventListener("submit", (e) => {
    e.preventDefault();
    const modo = "jcc";

    const nombre = document.getElementById("nombre").value.trim().toLowerCase();
    const ficha  = document.getElementById("ficha").value;

    if (!nombre) return alert("Tu nombre no puede estar vacío.");
    if (nombre === "computadora") return alert("No puedes llamarte Computadora!!!!");

    const jugador1 = { nombre, ficha: ficha === "jugarX" ? "X" : "O" };
    const jugador2 = { nombre: "Computadora", ficha: jugador1.ficha === "X" ? "O" : "X" };

    sessionStorage.setItem("tateti_config", JSON.stringify({ modo, jugador1, jugador2 }));
    window.location.href = "../frontend/tateti.html";
  });
});
