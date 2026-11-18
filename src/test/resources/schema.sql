-- Table formateurs
CREATE TABLE formateurs(
	email NVARCHAR(200) PRIMARY KEY,
	nom NVARCHAR(250) NOT NULL,
	prenom NVARCHAR(250) NOT NULL) ;

-- Tables avec jointure
CREATE TABLE filieres(id NVARCHAR(5) PRIMARY KEY, nom NVARCHAR(150));
CREATE TABLE promotions(id NVARCHAR(20) PRIMARY KEY, id_filiere NVARCHAR(5));
ALTER TABLE promotions add constraint FK_PROMOTIONS_FILIERES foreign key (id_filiere) references filieres(id);
