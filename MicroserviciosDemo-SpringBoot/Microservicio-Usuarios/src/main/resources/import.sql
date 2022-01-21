INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('nelcamacho','$2a$12$w/gkge/j6.61dUo0VHoeiu7BkoVw/cXIBu/nr5pvUKne45FAArhZW', true, 'Nelson', 'Camacho', 'nd.camachoo@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('anabella','$2a$12$UDk.YlTPRsXucdlCIs08fuXBQ707VOAVeZTzOb74FWuEPhzINwOsy', true, 'Ana', 'Galarza', 'amgalarzal@gmail.com');

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);
