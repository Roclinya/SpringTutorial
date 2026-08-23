
CREATE TABLE IF NOT EXISTS shedlock(
	name VARCHAR(64) NOT NULL,
	lock_until TIMESTAMP(3) NULL,
	locked_at TIMESTAMP(3) NULL,
	locked_by VARCHAR(255) NULL,
	PRIMARY KEY (name)
);

--member
INSERT INTO `member` (`eMail`, `usrName`,`usrPwd`) VALUES ('1@gmail.com', '川村元氣','pwd1');
INSERT INTO `member` (`eMail`, `usrName`,`usrPwd`) VALUES ('2@gmail.com', '安伯托‧艾可','pwd2');
INSERT INTO `member` (`eMail`, `usrName`,`usrPwd`) VALUES ('3@gmail.com', '崔恩‧葛瑞芬','pwd3');

--student
INSERT INTO student (`id`, `name`) VALUES (1, 'Alice');
INSERT INTO student (`id`, `name`) VALUES (2, 'Bob');
INSERT INTO student (`id`, `name`) VALUES (3, 'Charlie');
--course
INSERT INTO course (id, title) VALUES (101, 'Mathematics');
INSERT INTO course (id, title) VALUES (102, 'Physics');
INSERT INTO course (id, title) VALUES (103, 'Chemistry');
INSERT INTO course (id, title) VALUES (104, 'Literature');
-- student_course 聯結表
-- Alice 選了 Mathematics 和 Chemistry
INSERT INTO student_course (student_id, course_id) VALUES (1, 101);
INSERT INTO student_course (student_id, course_id) VALUES (1, 103);

-- Bob 選了 Physics 和 Literature
INSERT INTO student_course (student_id, course_id) VALUES (2, 102);
INSERT INTO student_course (student_id, course_id) VALUES (2, 104);

-- Charlie 選了所有課程
INSERT INTO student_course (student_id, course_id) VALUES (3, 101);
INSERT INTO student_course (student_id, course_id) VALUES (3, 102);
INSERT INTO student_course (student_id, course_id) VALUES (3, 103);
INSERT INTO student_course (student_id, course_id) VALUES (3, 104);