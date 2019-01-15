use my_practise;

create table student(
	id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(20),
    age int,
    score double,
    teacher int
);

create table teacher(
	id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(20),
    age int,
    teach varchar(255)
);

insert into student values(null, '张三', 21, 97.5, 2);
insert into student values(null, '李四', 23, 99.5, 1);
insert into student values(null, '王五', 20, 90.0, 1);
insert into student values(null, '赵六', 21, 97.5, 1);
insert into student values(null, '田七', 19, 94.5, 2);

insert into teacher values(null, '陈老师', 44, '数学');
insert into teacher values(null, '白老师', 56, '语文');

