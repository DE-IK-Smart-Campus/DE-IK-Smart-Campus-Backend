-- User table insert
insert into user (id, username, password, role)
values (1, 'admin', '$2y$10$fg3EtHh11XUc4bSbAIntNuajBgZDsCAHsiiUL2J2q3IYJUTln8Idy', 'ADMIN');

insert into user (id, username, password, role)
values (2, 'adamkai', '$2y$10$P6Y9iCJ.zY6BtlZFV9m5ouiiyklHFDUc.UvT/UspdrhL.eJo.jnL2', 'USER');

insert into user (id, username, password, role)
values (3, 'nolbi', '$2y$10$eb8loJbSOyoaLSuvPp9vNeOX70n9fc4Rc0eks3c19qAivhL8zwnK.', 'USER');

insert into user (id, username, password, role)
values (4, 'holikai', '$2y$10$odHOFc/JS8bEVWVDuduK2.NT/ZE99P03RkvF07TDKMSRoqFCkE9Fe', 'USER');

insert into user (id, username, password, role)
values (5, 'filtikai', '$2y$10$UIkkPdXwzCe7dZLnXuihtODAgW4fLdJLbG4KFhrBxXIdVBa4SJvDm', 'USER');

insert into user (id, username, password, role)
values (6, 'palu', '$2y$10$rGw7Od2uWkIpCzkHr6ZUlOtTRrDH6ez552lJAq/JKyXeQUzgerOCm', 'USER');

insert into user (id, username, password, role)
values (7, 'butikai', '$2y$10$BmS0lp1oV25PsKlA5AyXaeSDUeOGBMlxvWIoLghH.l3Au4Tdn27we', 'USER');

insert into user (id, username, password, role)
values (8, 'gabai', '$2y$10$.D6nvZNahkSXVSRLHrtI5u0RERS6rdBqwMH/uja1Yi7DVfiHukzjm', 'USER');
----------------------------------------------------------------------------------------------------

-- instructors
insert into instructor (id, name)
values (1, 'instructor');

insert into instructor (id, name)
values (2, 'Dr. Gál Zoltán');

insert into instructor (id, name)
values (3, 'Dr. Szilágyi Szabolcs');

insert into instructor (id, name)
values (4, 'Vas Ádám');

insert into instructor (id, name)
values (5, 'Dr. Jeszenszky Péter');

-------------------------------

insert into consulting_date (id, date, START_DATE, END_DATE)
values (1, 'Friday 14-16',PARSEDATETIME('10-03-2017 14:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'),PARSEDATETIME('10-03-2017 16:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (2, 'Monday 08-10',PARSEDATETIME('06-03-2017 08:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'),PARSEDATETIME('06-03-2017 10:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'));

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (1, 1);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (1, 2);
-- subjects
insert into subject(id,name) values (1,'AI');

insert into subject(id,name) values (2,'Mesterséges intelligencia alapjai');

insert into subject(id,name) values (3,'Hálózati architektúrák és protokollok');

insert into subject(id,name) values (4,'Az internet eszközei és szolgáltatásai');

---------------------------------------------------------------------------------

--instructor to subject
insert into instructor_subjects(instructor_id,subject_id)
values (1, 1);

insert into instructor_subjects(instructor_id,subject_id)
values (2, 3);

insert into instructor_subjects(instructor_id,subject_id)
values (3, 3);

insert into instructor_subjects(instructor_id,subject_id)
values (4, 3);

insert into instructor_subjects(instructor_id,subject_id)
values (5, 4);
--------------------------------------------------------

-- User actual subjects
insert into user_actual_subjects(user_id,subject_id)
values (1, 1);

insert into user_actual_subjects(user_id,subject_id)
values (2, 2);

insert into user_actual_subjects(user_id,subject_id)
values (2, 3);

insert into user_actual_subjects(user_id,subject_id)
values (2, 4);