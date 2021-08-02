CREATE TABLE billionaires (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL
);

Create table ONESTOP (
id int primary key,
groupname varchar(250) not null,
text varchar(250) not null,
url varchar(500) not null,
islink bit,
isspl bit
)