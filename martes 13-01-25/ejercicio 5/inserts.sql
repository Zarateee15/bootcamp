INSERT INTO "Ejercicio5"."Editorial" ("nombre")
VALUES
  	('Alianza'),
	('Temu'),
	('La Enciclopedia');


INSERT INTO "Ejercicio5"."Colegio" ("nombre","direccion","tipo")
VALUES
  	('Colegio Tecnico Nacional',
	'Calle R.I. 3 Corrales, barrio Mariscal Estigarribia, Asunción (Paraguay).'
	,'A');


INSERT INTO "Ejercicio5"."Aula" ("nombre")
VALUES
  	('PC-01'),
	('PC-02'),
	('PC-03'),
  	('EIK-01'),
	('EIK-02'),
	('EIK-03');


INSERT INTO "Ejercicio5"."Profesor" ("nombreCompleto","cedula","direccion","idColegio")
VALUES
  ('Martha Mojoli',1234567,'Asuncion 505',1),
  ('Oscar Villalba',2345678,'Barrio Obrero 200',1),
  ('Lourdes Persano',3456789,'Villa Morra 300',1);


INSERT INTO "Ejercicio5"."Asignatura" ("nombre","cargaHoraria","idColegio")
VALUES
  ('Electrotecnia', 8, 1),
  ('Electronica Analogica', 8, 1),
  ('Electronica Digital', 6, 1);


INSERT INTO "Ejercicio5"."AsignaturaProfesor" ("idAsignatura","idProfesor","tipo","curso","idAula")
VALUES
  (1,1,'T','1er Anho', 4),
  (2,2,'T', '2do Anho', 5),
  (3,3,'T', '3er Anho', 5);


INSERT INTO "Ejercicio5"."Prestamo" ("idColegio","idProfesor","fechaPrestamo","fechaDevolucion","estado")
VALUES
  (1,1,'2026-01-10','2026-01-15','DEVUELTO'),
  (1,2,'2026-01-05','2026-01-12','DEVUELTO'),
  (1,3,'2026-01-08','2026-01-14','DEVUELTO'),
  (1,2,'2026-01-15',NULL,'PRESTADO');


INSERT INTO "Ejercicio5"."Libro" ("nombre","fechaPublicacion","disponible","idPrestamo")
VALUES
  ('Principos de la Electrotecnia','2018-03-15',TRUE,1),
  ('Electronica I','2020-07-01',TRUE,2),
  ('Electronica II','2016-11-20',TRUE,3),
  ('Laboratorio Analogica','2019-02-10',FALSE,4);


INSERT INTO "Ejercicio5"."LibroEditorial" ("idEditorial","idLibro")
VALUES
  (1,1),
  (2,2),
  (3,3),
  (3,4);




