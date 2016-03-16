INSERT INTO `user` (`id`, `bnumber`, `email`, `personal_email`, `password`, `salt`, `first_name`, `last_name`, `role`, `graduation_year`, `occupation`, `suffix`, `biography`, `experience`, `hidden`, `active`, `created`, `last_active`, `last_modified`, `social_media`)
VALUES
	(1, 0, 'example@ben.edu', NULL, 'as', 'word', 'donald', 'Detloff', 2, 0, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(2, NULL, 'example2@ben.edu', NULL, 'pass', 'word', 'Donald', 'Kirk', 2, NULL,  NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(3, 2160102, 'alumni@ben.edu', 'alumni@yahoo.com', 'a13d4b8b59ff5768129bc50083ac6ecc16078be5bdf0d462577399d6a10b2a73a084bc1cb4930e72', 'null', 'Bob', 'Doe', 2, 2014, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(4, 2160102, 'blah2@ben.edu', 'blah2@yahoo.com', 'code', 'null', 'Joe', 'Chang', 2, 2004, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(5, 2160102, 'blah3@ben.edu', 'blah3@yahoo.com', 'code', 'null', 'Matt', 'Wang', 2, 2006, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(6, 2160102, 'blah4@ben.edu', 'blah4@yahoo.com', 'code', 'null', 'Miguel', 'Magoo', 2, 2009, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(7, 2160102, 'blah5@ben.edu', 'blah5@yahoo.com', 'code', 'null', 'Peter', 'Hebda', 2, 2011, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(8, 2160102, 'blah6@ben.edu', 'blah6@yahoo.com', 'code', 'null', 'John', 'Duh', 2, 2012, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(9, 2160102, 'blah7@ben.edu', 'blah7@yahoo.com', 'code', 'null', 'Frank', 'Tank', 2, 2013, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(10, 2160102, 'blah8@ben.edu', 'blah8@yahoo.com', 'code', 'null', 'Don', 'Juan', 2, 1969, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(11, 2160102, 'blah9@ben.edu', 'blah9@yahoo.com', 'code', 'null', 'Andrew', 'Gregory', 2, 1935,  NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(14, 2160102, 'blah10@ben.edu', 'blah10@yahoo.com', 'code', 'null', 'Andy', 'Boy', 2, 2014, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(15, 2160102, 'blah11@ben.edu', 'blah11@yahoo.com', 'code', 'null', 'Hines', 'Ward', 2, 2004, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(16, 2160102, 'blah12@ben.edu', 'blah12@yahoo.com', 'code', 'null', 'Brett', 'Favre', 2, 2006, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(17, 2160102, 'blah13@ben.edu', 'blah13@yahoo.com', 'code', 'null', 'John', 'Madden', 2, 2009, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(18, 2160102, 'blah14@ben.edu', 'blah14@yahoo.com', 'code', 'null', 'Michael', 'Jordan', 2, 2011, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(19, 2160102, 'blah15@ben.edu', 'blah15@yahoo.com', 'code', 'null', 'Walter', 'Payton', 2, 2012, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(20, 2160102, 'blah16@ben.edu', 'blah16@yahoo.com', 'code', 'null', 'Sammy', 'Sosa', 2, 2013, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(22, 2160102, 'blah18@ben.edu', 'blah18@yahoo.com', 'code', 'null', 'Harry', 'Potter', 2, 1935, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(23, 2160102, 'blah19@ben.edu', 'blah19@yahoo.com', 'code', 'null', 'Lisa', 'Hotwagner', 2, 2002, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(24, 2160102, 'blah20@ben.edu', 'blah20@yahoo.com', 'code', 'null', 'Kate', 'Upton', 2, 1943, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(25, 2160102, 'blah21@ben.edu', 'blah21@yahoo.com', 'code', 'null', 'Michael', 'Jackson', 2, 2001, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(26, 2160102, 'blah22@ben.edu', 'blah22@yahoo.com', 'code', 'null', 'Brian', 'Mcknight', 2, 2012, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(27, 2160102, 'blah23@ben.edu', 'blah23@yahoo.com', 'code', 'null', 'Mariah', 'Carey', 2, 2008, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(28, 2160102, 'blah24@ben.edu', 'blah24@yahoo.com', 'code', 'null', 'Barrack', 'Obama', 2, 1976, NULL,  NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(29, 0, 'b1239876@ben.edu', 'spiderman@gmail.com', 'spider', 'spider', 'Peter', 'Parker', 2, 1977, 'Hero', NULL,  NULL, NULL, 1, 1, NULL, NULL, NULL, NULL),
	(31, 0, 'faculty@ben.edu', 'faculty@gmail.com', '0af3010c51f07c3e0d7e18720ed9fdd8088852ae4d5608f002b92d211d2100fd566bac48693aceb5', 'hulk', 'The', 'Hulk', 3, 2005, 'Hero', NULL, 'I can see all the students in the database.', 'I destroy things.', 1, 1, NULL, NULL, NULL, NULL);




INSERT INTO `event` (`id`, `name`, `date`, `description`, `user_id`, `hidden`)
VALUES
	(1, 'job fair', NULL, NULL, 1, 0),
	(2, 'Sprint Review', '2016-02-26', 'go over the sprint that we did.', 31, 0),
	(3, 'Sprint Review', '2016-02-26', 'Going over the sprint.', 31, 0),
	(4, 'Example 2', '2016-03-01', 'This is a test.', 31, 0),
	(5, 'Example 5', '2016-03-01', 'This is a test again.', 31, 0),
	(6, 'Example 3', '2016-03-01', 'This is a test and again.', 31, 0),
	(7, 'Test', '2016-03-01', 'This is a Test', 31, 0),
	(8, 'Review Sprint 3', '2016-03-02', 'Go over this at 3pm', 31, 0);


INSERT INTO `event_interest` (`interest_id`, `event_id`)
VALUES
	(1, 1),
	(2, 1);



INSERT INTO `interest` (`id`, `name`, `hidden`)
VALUES
	(1, 'coding', 0),
	(2, 'clerical', 0),
	(3, 'management', 0);


INSERT INTO `job_interest` (`interest_id`, `job_id`)
VALUES
	(1, 1);


INSERT INTO `major` (`id`, `name`, `concentration`, `major_id`)
VALUES
	(1, 'business', 0, NULL),
	(2, 'math', 0, NULL),
	(3, 'science', 0, NULL),
	(4, 'health', 0, NULL),
	(5, 'religion', 0, NULL),
	(6, 'algorithm design', 1, 2),
	(7, 'sports marketing', 1, 1),
	(8, 'discrete garbage', 1, 2);
	
	
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

