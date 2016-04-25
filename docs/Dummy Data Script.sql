insert into title (id, name) values (1,"Mr.");
insert into title (id, name) values (2,"Mrs.");
insert into title (id, name) values (3,"Dr.");

insert into reason (id,name) values (1,"Student Error");
insert into reason (id,name) values (2,"Curriculum Change");
insert into reason (id,name) values (3,"Finnancial");

INSERT INTO `user` (`id`, `title_id`, `bnumber`, `email`, `personal_email`, `password`, `salt`, `first_name`, `last_name`, `role`, `graduation_year`, `occupation`, `suffix`, `biography`, `experience`, `hidden`, `active`, `created`, `last_active`, `last_modified`, `social_media`, `phone_number`)
<<<<<<< HEAD
VALUES (1, 1, 2160102, 'example@ben.edu', 'example@yahoo.com',        'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Chris', 'Detloff',   2, 1999, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(297)528-1989'),
=======
VALUES (1, 1, 2160102, 'example@ben.edu', 'example@yahoo.com',    'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Chris', 'Detloff',   2, 1999, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(297)528-1989'),
>>>>>>> sprint
	(2, 1, 2160102, 'example2@ben.edu', 'example2@yahoo.com',      'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Donald', 'Kirk',     2, 1999, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(414)616-3587'),
	(3, 1, 2160102, 'alumni@ben.edu', 'alumniuser@yahoo.com',      'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Bob', 'Doe',         2, 2014, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(524)244-7345'),
	(4, 1, 2160102, 'alumni2@ben.edu', 'alumniuser2@yahoo.com',    'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Joe', 'Chang',       2, 2004, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(793)628-4031'),
	(5, 1, 2160102, 'alumni3@ben.edu', 'alumniuser3@yahoo.com',    'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Matt', 'Wang',       2, 2006, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(359)511-2331'),
	(6, 1, 2160102, 'alumni4@ben.edu', 'alumniuser4@yahoo.com',    'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Miguel', 'Magoo',    2, 2009, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(176)118-7600'),
	(7, 1, 2160102, 'alumni5@ben.edu', 'alumniuser5@yahoo.com',    'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Peter', 'Hebda',     2, 2011, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(604)626-0040'),
	(8, 1, 2160102, 'alumni6@ben.edu', 'alumniuser6@yahoo.com',    'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'John', 'Duh',        2, 2012, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(834)405-4982'),
	(9, 1, 2160102, 'alumni7@ben.edu', 'alumniuser7@yahoo.com',    'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Frank', 'Tank',      2, 2013, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(490)218-8669'),
	(10, 1, 2160102, 'alumni8@ben.edu', 'alumniuser8@yahoo.com',   'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Don', 'Juan',        2, 1969, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(569)244-5043'),
	(11, 1, 2160102, 'alumni9@ben.edu', 'alumniuser9@yahoo.com',   'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Andrew', 'Gregory',  2, 1935, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(862)384-1492'),
	(12, 1, 2160102, 'alumni10@ben.edu', 'alumniuser10@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Andy', 'Boy',        2, 2014, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(558)211-9755'),
	(13, 1, 2160102, 'alumni11@ben.edu', 'alumniuser11@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Hines', 'Ward',      2, 2004, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(228)972-9242'),
	(14, 1, 2160102, 'alumni12@ben.edu', 'alumniuser12@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Brett', 'Favre',     2, 2006, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(760)910-2218'),
	(15, 1, 2160102, 'alumni13@ben.edu', 'alumniuser13@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'John', 'Madden',     2, 2009, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(287)568-0318'),
	(16, 1, 2160102, 'alumni14@ben.edu', 'alumniuser14@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Michael', 'Jordan',  2, 2011, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(445)853-6603'),
	(17, 1, 2160102, 'alumni15@ben.edu', 'alumniuser15@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Walter', 'Payton',   2, 2012, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(833)416-0671'),
	(18, 1, 2160102, 'alumni16@ben.edu', 'alumniuser16@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Sammy', 'Sosa',      2, 2013, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(892)823-3321'),
	(19, 1, 2160102, 'alumni18@ben.edu', 'alumniuser18@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Harry', 'Potter',    2, 1935, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(167)726-4327'),
	(20, 3, 2160102, 'alumni19@ben.edu', 'alumniuser19@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Lisa', 'Hotwagner',  2, 2002, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(327)541-2840'),
	(21, 1, 2160102, 'alumni20@ben.edu', 'alumniuser20@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Kate', 'Upton',      2, 1943, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(830)348-0868'),
	(22, 1, 2160102, 'alumni21@ben.edu', 'alumniuser21@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Michael', 'Jackson', 2, 2001, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(955)154-7290'),
	(23, 1, 2160102, 'alumni22@ben.edu', 'alumniuser22@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Brian', 'Mcknight',  2, 2012, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(220)592-8713'),
	(24, 2, 2160102, 'alumni23@ben.edu', 'alumniuser23@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Mariah', 'Carey',    2, 2008, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(668)982-6044'),
	(25, 1, 2160102, 'alumni24@ben.edu', 'alumniuser24@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Barrack', 'Obama',   2, 1976, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '(345)791-8272');

insert into user (id, email,password,salt,first_name, last_name, role, title_id) values (29,'student@ben.edu', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Sam', 'Student', 1, 1);
insert into user (id, email,password,salt,first_name, last_name, role, title_id) values (30,'alumni.account@ben.edu', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Al', 'Alumni', 2, 1);
insert into user (id, email,password,salt,first_name, last_name, role, title_id) values (31,'faculty@ben.edu', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Phil', 'Phaculty', 3, 1);
insert into user (id, email,password,salt,first_name, last_name, role, title_id) values (32,'admin@ben.edu', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Alice', 'Admin', 4, 2);

INSERT INTO hours (id,classification) values (1, "part time");
INSERT INTO hours (id,classification) values (2, "full time");



INSERT INTO `job` (`id`, `name`, `description`, `company`, `user_id`, `hidden`, `end_date`, `start_date`, `reference`, `public`, `hours_id`, `salary`, `start_wage`, `end_wage`, `start_salary`, `end_salary`, `link`, `location`)
VALUES
	(1, 'Software Developer', 'You are always coding forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever.', 'Microsoft', 31, 0, NULL, NULL, NULL, 1, 2, 0, 0, 0, 100000, 150000, NULL, 'California'),
	(2, 'Web Development (Front End)', 'You are always developing forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever.', 'Google Inc', 31, 0, NULL, NULL, NULL, 1, 2, 0, 0, 0, 100000, 150000, NULL, 'Illinois'),
    (3, 'Sql Developer', 'You are always developing forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever.', 'Geek Squad', 31, 0, NULL, NULL, NULL, 1, 2, 0, 0, 0, 210000, 305000, NULL, 'Iowa'),
	(4, 'I.T Support', 'You are always supporting forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever.', 'BP', 31, 0, NULL, NULL, NULL, 1, 2, 0, 0, 0, 5000, 10000, NULL, 'Maine'),
(5, 'Technical Support Specialist', 'You are always helping forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever.', 'McDonalds', 31, 0, NULL, NULL, NULL, 1, 2, 0, 0, 0, 1000, 2000, NULL, 'Florida'),
(6, 'Web Development (Back End)', 'You are always developing forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever.', 'Best Buy', 31, 0, NULL, NULL, NULL, 1, 2, 0, 0, 0, 60000, 70000, NULL, 'New York'),
(7, 'Java Tester', 'You are always testing forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever.', 'Target', 31, 0, NULL, NULL, NULL, 1, 2, 0, 0, 0, 50000, 65000, NULL, 'South Carolina'),
(8, 'Ruby Developer', 'You are always developing forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever.', 'Government', 31, 0, NULL, NULL, NULL, 1, 2, 0, 0, 0, 40000, 150000, NULL, 'Mexico'),
(9, 'Web Designer', 'You are always designing forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever and forever.', 'Apple', 31, 0, NULL, NULL, NULL, 1, 2, 0, 0, 0, 100000, 150000, NULL, 'Rhode Island');


INSERT INTO `event` (`id`, `name`, `date`, `description`, `user_id`, `hidden`, `start_time`, `end_time`, `public`, `longitude`, `latitude`, `role`, `reference`, `location`)
VALUES
	(1, 'Sprint 6 Meeting', '2016-04-13', 'Go over the SOW over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over over and over .', 31, 0, '12:00', '14:00', 1, 0, 0, 0, NULL, 'Benedictine University'),
	(2, 'Graduation', '2016-05-14', 'Join us in celebrating the class of 2016.', 31, 0, '09:00', '11:00', 1, 0, 0, 0, NULL, 'Benedictine University'),
	(3, 'Sprint Review Meeting', '2016-04-12', 'We are meeting to review our sprint 6 meeting.', 31, 0, '14:00', '15:00', 1, 0, 0, 0, NULL, 'Benedictine University'),
	(4, 'The Gettysburg Address', '2018-05-14', 'On June 1, 1865, Senator Charles Sumner referred to the most famous speech ever given by President Abraham Lincoln. In his eulogy on the slain president, he called the Gettysburg Address a "monumental act." He said Lincoln was mistaken that "the world will little note, nor long remember what we say here." Rather, the Bostonian remarked, "The world noted at once what he said, and will never cease to remember it. The battle itself was less important than the speech."', 31, 0, '10:00', '13:00', 1, 0, 0, 0, NULL, 'Gettysburg Pennsylvania'),
	(5, '4th of July', '2016-07-04', 'We celebrate American Independence Day on the Fourth of July every year. We think of July 4, 1776, as a day that represents the Declaration of Independence and the birth of the United States of America as an independent nation.', 31, 0, '01:00', '23:00', 1, 0, 0, 0, NULL, 'Earth'),
    (6, 'Founding of Benedictine', '2020-05-14', 'Join us in celebrating the finding of Benedictine University.', 31, 0, '09:00', '11:00', 1, 0, 0, 0, NULL, 'Benedictine University');
    

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
	(2, 2, 0),
	(3, 3, 0),
	(4, 4, 1),
	(5, 6, 1),
	(6, 1, 0),
	(7, 2, 0),
	(8, 6, 0),
	(9, 3, 0),
	(10, 4, 0),
	(11, 1, 0),
	(12, 2, 0),
	(13, 3, 0),
	(14, 4, 0),
	(15, 1, 0),
	(16, 2, 0),
	(17, 3, 0),
	(18, 4, 0),
	(19, 1, 0),
	(20, 2, 0),
	(21, 3, 0),
	(22, 4, 0),
	(23, 1, 0),
	(24, 2, 0),
	(25, 4, 0),
	(29, 1, 0),
	(30, 3, 0),
	(31, 1, 0),
	(32, 2, 0);

INSERT INTO `user_interest` (`user_id`, `interest_id`)
VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 1),
	(6, 1),
	(7, 1),
	(8, 1),
	(9, 1),
	(10, 1),
	(11, 1),
	(12, 1),
	(13, 1),
	(14, 1),
	(15, 1),
	(16, 1),
	(17, 1),
	(18, 1),
	(19, 1),
	(20, 1),
	(21, 1),
	(22, 1),
	(23, 1),
	(24, 1),
	(25, 1),
	(29, 1),
	(30, 1),
	(31, 1),
	(32, 1);
	

