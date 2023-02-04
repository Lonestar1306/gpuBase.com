DROP DATABASE IF EXISTS gpuBase;
CREATE DATABASE gpuBase;
USE gpuBase;

CREATE TABLE Scheda (
    	idScheda 		varchar(32) not null,
    	produttore 		varchar(32) not null,
    	dataUscita 		date not null,
    	nomeCore 		varchar(32) not null,
	    numeroCore 		int not null,
    	TDP 			int not null,
    	frequenzaCore 		int not null,
    	frequenzaMemoria 	int not null,
    	generazioneMemoria 	varchar(32) not null,
    	dimensioneMemoria 	double not null,
		primary key(idScheda)
);

CREATE TABLE Custom (	

	idCustom 		int not null AUTO_INCREMENT,
	idScheda 		varchar(32) not null,
	profondita 		double  not null,
	larghezza 		double not null,
	lunghezza 		double not null,
	peso 			double not null,
	nomeCustom 		varchar(32) not null,
	azienda 		varchar(32) not null,
	risoluzioneMax 		varchar(32) not null,
	porteDP 		int not null,
	porteVGA 		int not null,
	porteDVI 		int not null,
	porteHDMI 		int not null,
	primary key (idCustom),
	foreign key (idScheda) references Scheda(idScheda)
);


CREATE TABLE Amministratore(
  nomeAmministratore VARCHAR(32) NOT NULL,
  password VARCHAR(32) NOT NULL,
  PRIMARY KEY (nomeAmministratore));

CREATE TABLE Cliente(
  mailCliente VARCHAR(32) NOT NULL,
  Password VARCHAR(32) NOT NULL,
  Indirizzo VARCHAR(45) NOT NULL,
  Telefono VARCHAR(16) NOT NULL,
  PRIMARY KEY(mailCliente));

CREATE TABLE Ordinazione(
 idOrdine INT NOT NULL AUTO_INCREMENT, 
 mailCliente VARCHAR(45) NOT NULL,
 idCustom INT NOT NULL,
 pezzi INT NOT NULL,
 iva DOUBLE NOT NULL,
 prezzo DOUBLE NOT NULL,
 data VARCHAR(10) NOT NULL,
 PRIMARY KEY (idOrdine));

CREATE TABLE Vendita(
 idVendita INT NOT NULL,
 nomeAmministratore VARCHAR(45) NOT NULL,
 pezzi INT NOT NULL,
 iva DOUBLE NOT NULL,
 prezzo DOUBLE NOT NULL,
 sconto DOUBLE NOT NULL,
 PRIMARY KEY (idVendita),
 FOREIGN KEY (idVendita) REFERENCES Custom(idCustom)
 );

CREATE TABLE Foto (

  path VARCHAR(32) not null ,
  file mediumblob not null,
  idCustom int NOT NULL,
 PRIMARY KEY(path),

FOREIGN KEY (idCustom) REFERENCES Custom(idCustom)
);




/******************************************************/


USE gpuBase;
INSERT INTO Scheda values("rtx2080ti","nvidia","2018-09-20","tu102",4352,250,1545,1750,"gddr6",11);
INSERT INTO Scheda values("rx5700xt","amd","2019-07-07","navi 10 ",2560,225,1755,1750,"gddr6",8);

insert into Custom values(1,"rtx2080ti",57,140,328,1545,"gaming z trio","msi","7680x4320",3,0,0,1); 
insert into Custom values(2,"rtx2080ti",57,140,328,1545,"rog strix","asus","7680x4320",3,0,0,1); 
insert into Custom values(3,"rtx2080ti",57,140,328,1545,"ftw3 ultra","evga","7680x4320",3,0,0,1); 

insert into Custom values(4,"rx5700xt",53,145,324,1400," taichi x oc+","asrock","7680x4320",4,0,0,2); 
insert into Custom values(5,"rx5700xt",53,145,324,1400," thicc III ultra","xfx","7680x4320",4,0,0,2); 
insert into Custom values(6,"rx5700xt",53,145,324,1400," nitro +","sapphire","7680x4320",4,0,0,2); 

INSERT INTO Amministratore
values("luigi1","psw1");
INSERT INTO Amministratore
values("luigi2","psw2");
INSERT INTO Amministratore
values("giovanni","psw3");
INSERT INTO Cliente
values("gioacchino@hotmail.com","psw4","mercato san serverino 85","+39 111 222 3333");
INSERT INTO Cliente
values("girandola@gmail.com","psw5","via delle rose 666","+39 444 555 6666");

INSERT INTO Vendita values (1,"luigi1",50,22,800,15);
INSERT INTO Vendita values (2,"giovanni",100,22,700,10);
INSERT INTO Vendita values (3,"luigi1",150,22,600,5);
INSERT INTO Vendita values (4,"giovanni",20,22,500,15);
INSERT INTO Vendita values (5,"luigi1",40,22,400,10);
INSERT INTO Vendita values (6,"giovanni",60,22,300,5);



INSERT INTO Ordinazione values(1,"gioacchino@hotmail.com",1,1,22,720,"2012-12-12");
INSERT INTO Ordinazione values(2,"gioacchino@hotmail.com",1,1,22,720,"2017-12-12");
INSERT INTO Ordinazione values(3,"gioacchino@hotmail.com",1,1,22,720,"2020-12-12");