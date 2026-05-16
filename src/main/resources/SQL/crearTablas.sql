/*
Ordenar en que orden se crean las tablas
revisar que los tipos de datos esten correctos
Ajustar para ver si es necesario ajustar alguna columna

FALTA AÑADIR EL CONSTRAIN DE LA FOREIGN KEY Y EL DELETE ON CASCADE
*/

CREATE DATABASE IF NOT EXISTS empresa;
USE empresa;

CREATE TABLE roles(
id_rol INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
nombre_rol VARCHAR(100) NOT NULL
);

CREATE TABLE maquinas(
id_maquina INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
tipo_maquina VARCHAR(100) NOT NULL
);

CREATE TABLE tareas(
id_tarea INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  
id_maquina INT NOT NULL, 
dia DATE NOT NULL
);

CREATE TABLE usuarios(
id_usr INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
id_rol INT NOT NULL,
nombre VARCHAR(100) NOT NULL, 
apellido VARCHAR(100) NOT NULL, 
dni VARCHAR(10) NOT NULL, 
telefono INT, 
contraseña VARCHAR(255) NOT NULL,
CONSTRAINT `fk_id_rol`
FOREIGN KEY (id_rol) REFERENCES roles (id_rol)
ON DELETE CASCADE
);

CREATE TABLE reportes(
id_reporte INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
id_user INT NOT NULL, 
id_maquina INT NOT NULL, 
descripcion VARCHAR(255), 
fecha DATE, 
hora_inicio TIME, 
hora_final TIME,
CONSTRAINT `fk_id_user`
FOREIGN KEY (id_user) REFERENCES usuarios(id_usr)
ON DELETE CASCADE,
CONSTRAINT `fk_id_maquina`
FOREIGN KEY (id_maquina) REFERENCES maquinas (id_maquina)
ON DELETE CASCADE
);




