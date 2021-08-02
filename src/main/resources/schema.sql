

Create table ONESTOP (
    id int primary key,
    groupname varchar(250) not null,
    text varchar(250) not null,
    url varchar(500) not null,
    islink bit,
    isspl bit
)