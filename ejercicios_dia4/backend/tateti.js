document.addEventListener("DOMContentLoaded", () => {
  // Turno de cada jugador
  const turnoTexto = document.getElementById("turnoTexto");
  // Celdas del "tablero"
  const celdas = Array.from(document.querySelectorAll(".celda"));
  if (!turnoTexto || celdas.length !== 9) return;

  // datosJuego guarda los datos del juego: modo, jugador1 y jugador2 (si hay)
  const datosJuego = JSON.parse(sessionStorage.getItem("tateti_config"));

  // Pone los datos de datosJuego en variables
  const modo = datosJuego.modo;
  const jugador1 = datosJuego.jugador1;
  const jugador2 = datosJuego.jugador2;

  // Array con los jugadores
  const jugadores = [jugador1, jugador2];

  const btnReiniciar = document.getElementById("reiniciar");

  // Simula el tablero vacio
  let tablero = Array(9).fill("");
  // Turno de cada jugador
  let jugadorActual = 0;
  // Cuando se hace un movimiento ganador cambia a true y termina el juego
  let terminaJuego = false;

  // Contiene las combinaciones que ganan el juego (linea de 3)
  const movimientosGanadores = [
    [0,1,2],[3,4,5],[6,7,8],
    [0,3,6],[1,4,7],[2,5,8],
    [0,4,8],[2,4,6]
  ];

  // Actualiza el turno del jugador segun se vaya jugando
  function actualizarTurno() {
    turnoTexto.textContent = `Turno de ${jugadores[jugadorActual].nombre}`;
  }

  // Recorre las combinaciones ganadoras, si coincide retorna X u O, sino null
  function matchPoint() {
    for (const [a,b,c] of movimientosGanadores) {
      if (tablero[a] && tablero[a] === tablero[b] && tablero[a] === tablero[c]) {
        return { ficha: tablero[a], indices: [a,b,c] };
      }
    }
    return null;
  }

  function marcarGanadores(indices) {
    indices.forEach(idx => celdas[idx].classList.add("jugada-ganadora"));
  }

  function limpiarGanadores() {
    celdas.forEach(td => td.classList.remove("jugada-ganadora"));
  }

  function mostrarReiniciar() {
    btnReiniciar.classList.remove("hidden");
    btnReiniciar?.addEventListener("click", () => reiniciarJuego());
  }

  function reiniciarJuego() {
    limpiarGanadores()
    terminaJuego = false;
    tablero = Array(9).fill("");
    celdas.forEach(c => {
      c.textContent = "";
      c.classList.remove("x","o","jugada-ganadora","pop");
    });
    location.reload();
    window.location.href = "../frontend/menu.html";
  }

  // Alerta si termina el juego
  function gameOver(msg) {
    terminaJuego = true;

    setTimeout(() => {
      alert(msg);
      mostrarReiniciar();
    }, 200);
  }

  function jugarFicha(i) {
    // Si terminaJuego = true, el juego termina
    if (terminaJuego) return;
    if (tablero[i] !== "") return;

    tablero[i] = jugadores[jugadorActual].ficha;
    celdas[i].textContent = tablero[i];

    // Animación "pop" al colocar una ficha
    celdas[i].classList.remove("pop");
    void celdas[i].offsetWidth; // fuerza reflow para reiniciar la animación
    celdas[i].classList.add("pop");

    // Colores por ficha
    celdas[i].classList.remove("x", "o");
    celdas[i].classList.add(tablero[i] === "X" ? "x" : "o");

    const mp = matchPoint();
    if (mp){
      marcarGanadores(mp.indices);
      return gameOver(`Ganó ${jugadores[jugadorActual].nombre}!`);
    }

    if (tablero.every(v => v !== "")) return gameOver("Empate kps");

    // Cambia el turno al otro jugador
    jugadorActual = 1 - jugadorActual;
    actualizarTurno();

    // Turno de la computadora
    if (modo === "jcc" && jugadores[jugadorActual].nombre.toLowerCase() === "computadora") {
      setTimeout(turnoComputadora, 450);
    }
  }

  function turnoComputadora() {
    // Si terminaJuego = true, el juego termina
    if (terminaJuego) return;

    // Array con las celdas disponibles del tablero
    const libres = tablero
      .map((v, i) => (v === "" ? i : null))
      .filter(i => i !== null);

    //Si no hay celdas libres, termina el juego
    if (libres.length === 0) return;

    // La computadora juega al azar en una de las celdas libres
    const i = libres[Math.floor(Math.random() * libres.length)];
    jugarFicha(i);
  }

  // Clicks en celdas
  celdas.forEach((td, i) => {
    td.addEventListener("click", () => {
      // Si es turno de la compu, no dejamos jugar
      if (modo === "jcc" && jugadores[jugadorActual].nombre.toLowerCase() === "computadora") return;
      jugarFicha(i);
    });
  });

  actualizarTurno();
});
