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

  // Simula el tablero vacio
  let tablero = Array(9).fill("");
  // Jugador actual a jugar
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
    turnoTexto.textContent = `Turno de: ${jugadores[jugadorActual].nombre} (${jugadores[jugadorActual].ficha})`;
  }

  // Recorre las combinaciones ganadoras, si coincide retorna X u O, sino null
  function matchPoint() {
    for (const [a,b,c] of movimientosGanadores) {
      if (tablero[a] && tablero[a] === tablero[b] && tablero[a] === tablero[c]) return tablero[a];
    }
    return null;
  }

  // Alerta si termina el juego
  function gameOver(msg) {
    terminaJuego = true;
    alert(msg);
  }

  function playAt(i) {
    if (terminaJuego) return;
    if (tablero[i] !== "") return;

    tablero[i] = jugadores[jugadorActual].ficha;
    celdas[i].textContent = tablero[i];

    const game = matchPoint();
    if (game) return gameOver(`Ganó: ${jugadores[jugadorActual].nombre} (${game})`);

    if (tablero.every(v => v !== "")) return gameOver("Empate kps");

    jugadorActual = 1 - jugadorActual;
    actualizarTurno();

    // Turno de la compu (jcc)
    if (modo === "jcc" && jugadores[jugadorActual].nombre.toLowerCase() === "computadora") {
      setTimeout(cpuMoveRandom, 250);
    }
  }

  function cpuMoveRandom() {
    if (terminaJuego) return;
    const libres = tablero
      .map((v, i) => (v === "" ? i : null))
      .filter(i => i !== null);

    if (libres.length === 0) return;
    const i = libres[Math.floor(Math.random() * libres.length)];
    playAt(i);
  }

  // Clicks en celdas
  celdas.forEach((td, i) => {
    td.addEventListener("click", () => {
      // Si es turno de la compu, no dejamos jugar
      if (modo === "jcc" && jugadores[jugadorActual].nombre.toLowerCase() === "computadora") return;
      playAt(i);
    });
  });

  actualizarTurno();
});
