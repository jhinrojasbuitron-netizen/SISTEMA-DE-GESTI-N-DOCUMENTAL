create database bd_proyectofinal;

USE bd_proyectofinal;

-- Creación de las tablas
CREATE TABLE `tbl_alumno` (
  `codAlumno` int(11) NOT NULL AUTO_INCREMENT,
  `nameAlumno` varchar(30) NOT NULL,
  `apellidoAlumno` varchar(30) NOT NULL,
  `correoAlumno` varchar(40) NOT NULL,
  `dniAlumno` varchar(20) NOT NULL,
  `codGenero` int(11) NOT NULL,
  `estadoAlumno` int(11) NOT NULL,
  PRIMARY KEY (`codAlumno`),
  KEY `codGenero` (`codGenero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tbl_curso` (
  `codCurso` int(11) NOT NULL AUTO_INCREMENT,
  `nameCurso` varchar(30) NOT NULL,
  PRIMARY KEY (`codCurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tbl_genero` (
  `codGenero` int(11) NOT NULL AUTO_INCREMENT,
  `nameGenero` varchar(30) NOT NULL,
  PRIMARY KEY (`codGenero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tbl_matricula` (
  `codMatricula` int(11) NOT NULL AUTO_INCREMENT,
  `codAlumno` int(11) NOT NULL,
  `codProfesor` int(11) NOT NULL,
  `cursoMatricula` int(11) NOT NULL,
  `turnoEstudio` varchar(20) NOT NULL,
  `gradoEscolar` varchar(20) NOT NULL,
  `observacion` varchar(400) NOT NULL,
  `estadoMatricula` int(11) NOT NULL,
  PRIMARY KEY (`codMatricula`),
  KEY `codAlumno` (`codAlumno`),
  KEY `codProfesor` (`codProfesor`),
  KEY `cursoMatricula` (`cursoMatricula`),
  CONSTRAINT `tbl_matricula_ibfk_1` FOREIGN KEY (`codProfesor`) REFERENCES `tbl_profesor` (`codProfesor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tbl_matricula_ibfk_2` FOREIGN KEY (`codAlumno`) REFERENCES `tbl_alumno` (`codAlumno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tbl_matricula_ibfk_3` FOREIGN KEY (`cursoMatricula`) REFERENCES `tbl_curso` (`codCurso`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tbl_profesor` (
  `codProfesor` int(11) NOT NULL AUTO_INCREMENT,
  `nameProfesor` varchar(40) NOT NULL,
  `apellidoProfesor` varchar(40) NOT NULL,
  `telefonoProfesor` varchar(20) NOT NULL,
  `correoProfesor` varchar(40) NOT NULL,
  `dniProfesor` varchar(20) NOT NULL,
  `codCurso` int(11) NOT NULL,
  `estadoProfesor` int(11) NOT NULL,
  PRIMARY KEY (`codProfesor`),
  KEY `codEspecial` (`codCurso`),
  CONSTRAINT `tbl_profesor_ibfk_1` FOREIGN KEY (`codCurso`) REFERENCES `tbl_curso` (`codCurso`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tbl_usuario` (
  `codUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `codVista` int(11) NOT NULL,
  `codProfesor` int(11) NOT NULL,
  PRIMARY KEY (`codUsuario`),
  KEY `codProfesor` (`codProfesor`),
  KEY `codVista` (`codVista`),
  CONSTRAINT `tbl_usuario_ibfk_1` FOREIGN KEY (`codProfesor`) REFERENCES `tbl_profesor` (`codProfesor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tbl_usuario_ibfk_2` FOREIGN KEY (`codVista`) REFERENCES `tbl_vista` (`codVista`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tbl_vista` (
  `codVista` int(11) NOT NULL AUTO_INCREMENT,
  `nameVista` varchar(30) NOT NULL,
  PRIMARY KEY (`codVista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Inserción de los datos
INSERT INTO `tbl_alumno` (`codAlumno`, `nameAlumno`, `apellidoAlumno`, `correoAlumno`, `dniAlumno`, `codGenero`, `estadoAlumno`) VALUES
(1, 'Jorge Junn', 'Bullon Ccoo', 'jorge.bullon@usil.pe', '19362548', 1, 1),
(2, 'Aaron Eduardo', 'Ortiz Valdivia', 'aaron.ortiz@usil.pe', '78142536', 2, 1),
(3, 'Axel Hassan', 'Cordova Gonzales', 'axel.cordova@usil.pe', '78568912', 2, 1),
(4, 'Jhin Cristopher', 'Rojas Buitron', 'jhin.rojas@usil.pe', '78456912', 1, 1),
(5, 'Fabian Alonso', 'Mansilla Aqyquipa', 'fabian.mansilla@usil.pe', '78565869', 1, 1),
(6, 'Diego Michael', 'Vasquez Acosta', 'diego.vasquez@usil.pe', '69582364', 2, 1),
(7, 'Aldo Antoni', 'Alegria Meza', 'aldo.alegria@usil.pe', '78561239', 2, 1),
(8, 'Alexan Deron', 'Michul Guila', 'jorge.bullon@usil.pe', '78945612', 2, 1),
(9, 'Juanito Juanete', 'Bellaco Bilca', 'juanito.bellaco@usil.pe', '12568978', 2, 1);

INSERT INTO `tbl_curso` (`codCurso`, `nameCurso`) VALUES
(1, 'AdministracionColegio'),
(2, 'Matematica'),
(3, 'Comunicacion'),
(4, 'Educacion Fisica'),
(5, 'Ingles'),
(6, 'Arte y Cultura'),
(7, 'Religion'),
(8, 'D.P.C.C'),
(9, 'Computacion');

INSERT INTO `tbl_genero` (`codGenero`, `nameGenero`) VALUES
(1, 'Masculino'),
(2, 'Femenino');

INSERT INTO `tbl_profesor` (`codProfesor`, `nameProfesor`, `apellidoProfesor`, `telefonoProfesor`, `correoProfesor`, `dniProfesor`, `codCurso`, `estadoProfesor`) VALUES
(1, 'Administracion', 'Colegio', '999999999', 'administracion.colegio', '88888888', 1, 1),
(2, 'Daniel Jesus', 'Diaz Arenas', '789456123', 'daniel.diaz@usil.pe', '12345678', 9, 1),
(3, 'Javier Henry', 'Dioses Sarate', '785619029', 'javier.dioses@usil.pe', '16283745', 6, 1);

INSERT INTO `tbl_vista` (`codVista`, `nameVista`) VALUES
(1, 'Administracion'),
(2, 'Profesor');

INSERT INTO `tbl_usuario` (`codUsuario`, `usuario`, `password`, `codVista`, `codProfesor`) VALUES
(1, 'admin.colegio', 'admin', 1, 1),
(2, 'x', 'x', 1, 1),
(3, 'daniel.diaz', '12345678', 2, 2);