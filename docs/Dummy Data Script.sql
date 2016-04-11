insert into title (id, name) values (1,"Mr.");
insert into title (id, name) values (2,"Mrs.");
insert into title (id, name) values (3,"Dr.");

insert into reason (id,name) values (1,"Student Error");
insert into reason (id,name) values (2,"Curriculum Change");
insert into reason (id,name) values (3,"Finnancial");

INSERT INTO `user` (`id`, `bnumber`, `email`, `personal_email`, `password`, `salt`, `first_name`, `last_name`, `role`,`title_id`, `graduation_year`, `occupation`, `suffix`, `biography`, `experience`, `hidden`, `active`, `created`, `last_active`, `last_modified`, `social_media`)
VALUES
	(1, NULL, 'example@ben.edu', NULL, 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Chris', 'Detloff', 2, 0, NULL,1,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(2, NULL, 'example2@ben.edu', NULL, 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Donald', 'Kirk', 2, NULL,1,  NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(3, 2160102, 'alumni@ben.edu', 'alumni@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Bob', 'Doe', 2, 2014, NULL,1, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(4, 2160102, 'alumni2@ben.edu', 'blah2@yahoo.com', 'code', 'null', 'Joe', 'Chang', 2, 2004, NULL,1,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(5, 2160102, 'alumni3@ben.edu', 'blah3@yahoo.com', 'code', 'null', 'Matt', 'Wang', 2, 2006, NULL,1,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(6, 2160102, 'alumni4@ben.edu', 'blah4@yahoo.com', 'code', 'null', 'Miguel', 'Magoo', 2, 2009, NULL,1,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(7, 2160102, 'alumni5@ben.edu', 'blah5@yahoo.com', 'code', 'null', 'Peter', 'Hebda', 2, 2011, NULL,1,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(8, 2160102, 'alumni6@ben.edu', 'blah6@yahoo.com', 'code', 'null', 'John', 'Duh', 2, 2012, NULL,1,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(9, 2160102, 'alumni7@ben.edu', 'blah7@yahoo.com', 'code', 'null', 'Frank', 'Tank', 2, 2013, NULL,1,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(10, 2160102, 'alumni8@ben.edu', 'blah8@yahoo.com', 'code', 'null', 'Don', 'Juan', 2, 1969, NULL,1,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(11, 2160102, 'alumni9@ben.edu', 'blah9@yahoo.com', 'code', 'null', 'Andrew', 'Gregory', 2, 1935,  NULL,1, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(14, 2160102, 'alumni10@ben.edu', 'blah10@yahoo.com', 'code', 'null', 'Andy', 'Boy', 2, 2014, NULL,1,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(15, 2160102, 'alumni11@ben.edu', 'blah11@yahoo.com', 'code', 'null', 'Hines', 'Ward', 2, 2004, NULL, 1, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(16, 2160102, 'alumni12@ben.edu', 'blah12@yahoo.com', 'code', 'null', 'Brett', 'Favre', 2, 2006, NULL,1,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(17, 2160102, 'alumni13@ben.edu', 'blah13@yahoo.com', 'code', 'null', 'John', 'Madden', 2, 2009, NULL,1,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(18, 2160102, 'alumni14@ben.edu', 'blah14@yahoo.com', 'code', 'null', 'Michael', 'Jordan', 2, 2011, NULL,1, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(19, 2160102, 'alumni15@ben.edu', 'blah15@yahoo.com', 'code', 'null', 'Walter', 'Payton', 2, 2012, NULL,1, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(20, 2160102, 'alumni16@ben.edu', 'blah16@yahoo.com', 'code', 'null', 'Sammy', 'Sosa', 2, 2013, NULL,1, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(22, 2160102, 'alumni18@ben.edu', 'blah18@yahoo.com', 'code', 'null', 'Harry', 'Potter', 2, 1935, NULL,1, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(23, 2160102, 'alumni19@ben.edu', 'blah19@yahoo.com', 'code', 'null', 'Lisa', 'Hotwagner', 2, 2002, NULL,1, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(24, 2160102, 'alumni20@ben.edu', 'blah20@yahoo.com', 'code', 'null', 'Kate', 'Upton', 2, 1943, NULL,1,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(25, 2160102, 'alumni21@ben.edu', 'blah21@yahoo.com', 'code', 'null', 'Michael', 'Jackson', 2, 2001, NULL, 1, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(26, 2160102, 'alumni22@ben.edu', 'blah22@yahoo.com', 'code', 'null', 'Brian', 'Mcknight', 2, 2012, NULL,1,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(27, 2160102, 'alumni23@ben.edu', 'blah23@yahoo.com', 'code', 'null', 'Mariah', 'Carey', 2, 2008, NULL,1,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(28, 2160102, 'alumni24@ben.edu', 'blah24@yahoo.com', 'code', 'null', 'Barrack', 'Obama', 2, 1976, NULL, 1, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL);


insert into user (id, email,password,salt,first_name, last_name, role, title_id) values (29,'student@ben.edu', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Sam', 'Student', 1, 1);
insert into user (id, email,password,salt,first_name, last_name, role, title_id) values (30,'alumni.account@ben.edu', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Al', 'Alumni', 2, 1);
insert into user (id, email,password,salt,first_name, last_name, role, title_id) values (31,'faculty@ben.edu', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Phil', 'Phaculty', 3, 1);
insert into user (id, email,password,salt,first_name, last_name, role, title_id) values (32,'admin@ben.edu', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Alice', 'Admin', 4, 2);





INSERT INTO `event` (`id`, `name`, `date`, `description`, `user_id`, `hidden`)
VALUES
	(1, 'job fair', '2016-02-26', 'Job fair stuff', 1, 0),
	(2, 'Sprint Review', '2016-02-26', 'go over the sprint that we did.', 31, 0),
	(3, 'Sprint Review', '2016-02-26', 'Going over the sprint.', 31, 0),
	(4, 'Example 2', '2016-03-01', 'This is a test.', 31, 0),
	(5, 'Example 5', '2016-03-01', 'This is a test again.', 31, 0),
	(6, 'Example 3', '2016-03-01', 'This is a test and again.', 31, 0),
	(7, 'Test', '2016-03-01', 'This is a Test', 31, 0),
	(8, 'Review Sprint 3', '2016-03-02', 'Go over this at 3pm', 31, 0);

INSERT INTO `interest` (`id`, `name`, `hidden`)
VALUES
	(1, 'Coding', 0),
	(2, 'Clerical', 0),
	(3, 'Management', 0),
	(4, 'Physical Therapy', 0),
	(5, 'Athletic Training', 0),
	(6, 'Human Resource', 0),
	(7, 'Marketing', 0),
	(8, 'Nursing', 0),
	(9, 'Fine Arts', 0),
	(10, 'Music', 0);


INSERT INTO `event_interest` (`interest_id`, `event_id`)
VALUES
	(1, 1),
	(2, 1);



INSERT INTO `major` (`id`, `name`, `concentration`, `major_id`)
VALUES
	(1, 'Management & Organizational Behavior', 0, NULL),
	(2, 'Excercise and Sports Studies', 0, NULL),
	(3, 'Physical Education', 0, NULL),
	(4, 'Computer Science', 0, NULL),
	(5, 'Computer Information Systems', 0, NULL),
	(6, 'Business Analytics', 0, NULL),
	(7, 'Marketing', 0, NULL),
	(8, 'Criminal Justice', 0, NULL),
	(9, 'Mathematics', 0, NULL),
	(10, 'Sports Marketing', 1, 7),
	(11, 'Discrete Mathematics', 1, 9),
	(12, 'Human Resource Management', 1, 1),
	(13, 'Elementary Education', 1, 3);
	
	
	INSERT INTO `user_major` (`user_id`, `major_id`, `minor`)
VALUES
	(1, 1, 0),
	(1, 2, 0),
	(1, 3, 0),
	(1, 4, 1),
	(1, 6, 1),
	(2, 1, 0),
	(2, 2, 0),
	(2, 6, 0),
	(3, 3, 0),
	(4, 4, 0),
	(5, 1, 0),
	(6, 2, 0),
	(7, 3, 0),
	(8, 4, 0),
	(9, 1, 0),
	(10, 2, 0),
	(11, 3, 0),
	(14, 4, 0),
	(15, 1, 0),
	(16, 2, 0),
	(17, 3, 0),
	(18, 4, 0),
	(19, 1, 0),
	(20, 2, 0),
	(22, 4, 0),
	(23, 1, 0),
	(24, 2, 0),
	(25, 3, 0),
	(26, 4, 0),
	(27, 1, 0),
	(28, 2, 0),
	(29, 1, 0),
	(31, 3, 0);
	
	
	
INSERT INTO hours (id,classification) values (1, "part time");
INSERT INTO hours (id,classification) values (2, "full time");


INSERT INTO `user_interest` (`user_id`, `interest_id`)
VALUES
	(1, 1);

