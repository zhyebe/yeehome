
create database exam;

use exam;

create table student(
	student_id int(10) primary key auto_increment,
	username varchar(20),
	realname varchar(30),
	password varchar(30)
);
create table teacher(
	teacher_id int(10) primary key auto_increment,
	username varchar(20),
	realname varchar(30),
	password varchar(30)
);
create table management(
	id int(10) primary key auto_increment,
	username varchar(20),
	password varchar(30)
);
create table question(
	questionNo int(10) primary key auto_increment,
	question text,
	answer text,
	score int(5)
);
create table studentAnswers(
	questionNo int(10),
	answer text,
	student_id int(10),
	examNo date,
	exam_date date
);
create table totalScore(
	student_id int(10),
	exam_date date,
	score int(5),
	examNo date
);


insert into management(username,password)values('zhangyong','123456');
select * from management;
