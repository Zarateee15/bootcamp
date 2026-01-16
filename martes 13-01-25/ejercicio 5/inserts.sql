-- =========================
-- INSERTS DE EJEMPLO
-- Schema: Ejercicio5
-- =========================

-- 1) Editorial
INSERT INTO "Ejercicio5"."Editorial" ("nombre")
VALUES
  ('Kapelusz'),
  ('Santillana'),
  ('Planeta');

-- 2) Profesor
INSERT INTO "Ejercicio5"."Profesor" ("nombre", "cedula")
VALUES
  ('Ana Gomez', 1234567),
  ('Bruno Perez', 2345678),
  ('Carla Diaz', 3456789);

-- 3) Colegio
INSERT INTO "Ejercicio5"."Colegio" ("nombre")
VALUES
  ('Colegio Nacional N°1'),
  ('Instituto San Martin');

-- 4) Curso
INSERT INTO "Ejercicio5"."Curso" ("nombre")
VALUES
  ('1A'),
  ('2B'),
  ('3C');

-- 5) Aula
INSERT INTO "Ejercicio5"."Aula" ("nombre")
VALUES
  ('A-01'),
  ('A-02'),
  ('B-01');

-- 6) Asignatura
INSERT INTO "Ejercicio5"."Asignatura" ("nombre")
VALUES
  ('Matematica'),
  ('Lengua'),
  ('Historia');

-- 7) Libro (requiere idEditorial)
--    OJO: asumo que los idEditorial son 1..3 por el orden de inserts de arriba.
INSERT INTO "Ejercicio5"."Libro" ("nombre", "cantidadCopias", "idEditorial")
VALUES
  ('Algebra 1', 10, 1),
  ('Lectura 2', 7,  2),
  ('Historia Universal', 5, 3),
  ('Geometria Basica', 8, 1);

-- 8) Prestamo (requiere idProfesor)
--    Asumo idProfesor 1..3
INSERT INTO "Ejercicio5"."Prestamo" ("fechaPrestamo", "idProfesor")
VALUES
  ('2026-01-10', 1),
  ('2026-01-12', 2);

-- 9) DetallePrestamo (requiere idPrestamo)
--    Asumo idPrestamo 1..2
--    Nota: "cantidad" es varchar en tu tabla, por eso va como texto.
INSERT INTO "Ejercicio5"."DetallePrestamo" ("idPrestamo", "cantidad")
VALUES
  (1, '2'),
  (1, '1'),
  (2, '3');

-- 10) PrestamoLibro (puente entre DetallePrestamo y Libro)
--     Asumo idDetalle 1..3 y idLibro 1..4
INSERT INTO "Ejercicio5"."PrestamoLibro" ("idDetalle", "idLibro")
VALUES
  (1, 1),  -- Detalle 1 -> Libro 1
  (1, 2),  -- Detalle 1 -> Libro 2
  (2, 3),  -- Detalle 2 -> Libro 3
  (3, 4);  -- Detalle 3 -> Libro 4

-- 11) AsignacionProfesor (usa PK compuesta + FK a Profesor)
--     Asumo:
--       idColegio: 1..2
--       idAsignatura: 1..3
--       idAula: 1..3
--       idCurso: 1..3
--       idProfesor: 1..3
INSERT INTO "Ejercicio5"."AsignacionProfesor"
  ("idColegio", "idAsignatura", "idAula", "idCurso", "idProfesor")
VALUES
  (1, 1, 1, 1, 1), -- Colegio 1, Matematica, Aula A-01, Curso 1A, Ana
  (1, 2, 2, 2, 1), -- Colegio 1, Lengua,     Aula A-02, Curso 2B, Ana
  (2, 3, 3, 3, 2), -- Colegio 2, Historia,   Aula B-01, Curso 3C, Bruno
  (2, 1, 3, 2, 3); -- Colegio 2, Matematica, Aula B-01, Curso 2B, Carla
