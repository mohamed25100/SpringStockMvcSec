-- ------------------------------------------------------------------------------
-- - Gestion des droits d'accès                                     ---
-- ------------------------------------------------------------------------------
USE stock;

-- -----------------------------------------------------------------------------
-- - Construction de la table des Users                               ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Users (
	username			varchar(25)		PRIMARY KEY,
	password			varchar(250),
	active				boolean
) ENGINE = InnoDB;

INSERT INTO T_Users (username, password, active) VALUES ( 'mohamed',  '$2a$12$hzdjXpWfCSifyBqZDJK6N.z/Cc2q/dIpojZomzW9w6Sn7nAbPif7C' , TRUE );	-- pwd 12345
INSERT INTO T_Users (username, password, active) VALUES ( 'aymene',  '$2a$12$hzdjXpWfCSifyBqZDJK6N.z/Cc2q/dIpojZomzW9w6Sn7nAbPif7C' , TRUE );

SELECT * FROM T_Users;

-- -----------------------------------------------------------------------------
-- - Construction de la table avec 2 Roles principaux                        ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Roles (
	role 		varchar(25)		PRIMARY KEY
) ENGINE = InnoDB;

INSERT INTO T_Roles (role) VALUES ('ADMIN');
INSERT INTO T_Roles (role) VALUES ('USER');

-- -----------------------------------------------------------------------------
-- - Construction de la table des rôles par utilisateur	                     ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Users_Roles (
	username 		varchar(25),
	role 			varchar(25),
	PRIMARY KEY(username,role)
) ENGINE = InnoDB;

INSERT INTO T_Users_Roles (username,role) VALUES ('mohamed','ADMIN');
INSERT INTO T_Users_Roles (username,role) VALUES ('mohamed','USER');
INSERT INTO T_Users_Roles (username,role) VALUES ('aymene','USER');

