CREATE TABLE USERS (
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
last_name VARCHAR(64),
first_name VARCHAR(64),
email VARCHAR(64),
login VARCHAR(64) NOT NULL,
password VARCHAR(64) NOT NULL
) ;

CREATE INDEX login_idx ON USERS(login);
CREATE INDEX password_idx ON USERS(password);

insert into SSLEDZ.USERS(first_name, last_name, email, login, password) values ('Root', 'Root', 'root@wp.pl', 'root', 'root');
