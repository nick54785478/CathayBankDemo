DROP TABLE  IF EXISTS bitcoin;

CREATE TABLE bitcoin(
	code varchar(5) NOT NULL, 
	codezh varchar(50), 
	symbol varchar(10), 
	rate varchar(30) NOT NULL, 
	description varchar(max), 
	rate_float double NOT NULL, 
	updatetime SMALLDATETIME
	
);
