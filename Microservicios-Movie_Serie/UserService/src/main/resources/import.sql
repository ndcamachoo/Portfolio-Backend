INSERT INTO users (username, password, enabled, name, lastname, email) VALUES ('testadmin','$2a$12$qMfj4Ofm721delrj/9fKR.675JWT9qQ4bInBEcPsePOyO389PlgS.', true, 'Test', 'Admin', 'testadmin@gmail.com');
INSERT INTO users (username, password, enabled, name, lastname, email) VALUES ('testuser','$2a$12$CZUvZbamjP8CKqmFYJ09rOTVBIqJoC3yz6PlBVIG8B3JW9m9kvm3m', true, 'Test', 'User', 'testuser@gmail.com');

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);