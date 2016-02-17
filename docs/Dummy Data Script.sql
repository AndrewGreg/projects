/*--Test Data--*/
insert into major (name,concentration) values ('business', 0);
insert into major (name,concentration) values ('math', 0);
insert into major (name,concentration) values ('science', 0);
insert into major (name,concentration) values ('health', 0);
insert into major (name,concentration) values ('religion', 0);
insert into major (name,concentration,major_id) values ('algorithm design', 1, 2);
insert into major (name,concentration,major_id) values ('sports marketing', 1, 1);
insert into major (name,concentration,major_id) values ('discrete garbage', 1, 2);

insert into user (email,password,salt,first_name, last_name, role) values ('example@ben.edu', 'pass', 'word', 'Chris', 'Detloff', 100);
insert into user (email,password,salt,first_name, last_name, role) values ('example2@ben.edu', 'pass', 'word', 'Donald', 'Kirk', 100);

insert into user_major (user_id,major_id,minor) values (2,1,0);
insert into user_major (user_id,major_id,minor) values (1,2,0);
insert into user_major (user_id,major_id,minor) values (1,3,0);
insert into user_major (user_id,major_id,minor) values (1,4,1);
insert into user_major (user_id,major_id,minor) values (1,6,1);

INSERT INTO interest (name) VALUES ("coding");
INSERT INTO interest (name) VALUES ("clerical");
INSERT INTO interest (name) VALUES ("management");

Insert into job (name,user_id) VALUES ("developer",2);

Insert into event (name,user_id) VALUES ("job fair", 1 );

Insert into job_interest (job_id,interest_id) VALUE (1,1);

Insert into event_interest (event_id, interest_id) VALUE (1,2);
Insert into event_interest (event_id, interest_id) VALUE (1,1);

INSERT INTO user_interest (user_id, interest_id) values (1,1);

