CREATE TABLE alumnos (
	id_alumno INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(32) NOT NULL,
	apellidos VARCHAR(64) NOT NULL,
	fecha_nac DATE NOT NULL,
	UNIQUE KEY id_alumno_uq (id_alumno)
);

CREATE TABLE profesores (
	id_profesor INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(32) NOT NULL,
	apellidos VARCHAR(64) NOT NULL,
	UNIQUE KEY id_profesor_uq (id_profesor)
);

CREATE TABLE asignaturas (
	id_asignatura INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(64) NOT NULL,
	profesor INT NOT NULL,
	UNIQUE KEY id_asignatura_uq(id_asignatura),
	FOREIGN KEY profesor_asignatura(profesor) REFERENCES profesores(id_profesor)
);

CREATE TABLE matriculas (
	alumno INT NOT NULL,
	asignatura INT NOT NULL,
	fecha YEAR NOT NULL,
	nota INT,
	PRIMARY KEY (alumno, asignatura, fecha),
	FOREIGN KEY alumno_matriculado(alumno) REFERENCES alumnos(id_alumno),
	FOREIGN KEY asignatura_matriculada(asignatura) REFERENCES asignaturas(id_asignatura)
);

INSERT INTO alumnos VALUES
    (1,'Elena','Blanco','1993-02-18'),
    (2,'David','Pedroche','1992-11-13'),
    (3,'Miguel','Jimenez','1992-12-05'),
    (4,'Daniel','Ruiz','1993-04-15'),
    (5,'Ana','Diaz','1992-09-29');

INSERT INTO profesores VALUES
    (1,'Javier','Moreno'),
    (2,'Teresa','Romero'),
    (3,'Agustín','Torres');

INSERT INTO asignaturas VALUES
    (6,'Fundamentos de las Cosas',1),
    (7,'Cliente y Servidor',2),
    (8,'Bases de Datos',2),
    (9,'Fundamentos de Algoritmos',3);