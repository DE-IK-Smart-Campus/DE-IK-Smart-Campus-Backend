-- User table insert
insert into user (id, username, password, role)
values (1, 'admin', '$2y$10$fg3EtHh11XUc4bSbAIntNuajBgZDsCAHsiiUL2J2q3IYJUTln8Idy', 'ADMIN');

insert into user (id, username, password, role)
values (2, 'adamkai', '$2y$10$P6Y9iCJ.zY6BtlZFV9m5ouiiyklHFDUc.UvT/UspdrhL.eJo.jnL2', 'USER');

insert into user (id, username, password, role)
values (3, 'nolbi', '$2y$10$eb8loJbSOyoaLSuvPp9vNeOX70n9fc4Rc0eks3c19qAivhL8zwnK.', 'USER');

insert into user (id, username, password, role)
values (4, 'holi60', '$2y$10$odHOFc/JS8bEVWVDuduK2.NT/ZE99P03RkvF07TDKMSRoqFCkE9Fe', 'USER');

insert into user (id, username, password, role)
values (5, 'filtikai', '$2y$10$UIkkPdXwzCe7dZLnXuihtODAgW4fLdJLbG4KFhrBxXIdVBa4SJvDm', 'USER');

insert into user (id, username, password, role)
values (6, 'palu', '$2y$10$rGw7Od2uWkIpCzkHr6ZUlOtTRrDH6ez552lJAq/JKyXeQUzgerOCm', 'USER');

insert into user (id, username, password, role)
values (7, 'butikai', '$2y$10$BmS0lp1oV25PsKlA5AyXaeSDUeOGBMlxvWIoLghH.l3Au4Tdn27we', 'USER');

insert into user (id, username, password, role)
values (8, 'gabai', '$2y$10$.D6nvZNahkSXVSRLHrtI5u0RERS6rdBqwMH/uja1Yi7DVfiHukzjm', 'USER');

insert into user (id, username, password, role)
values (9, 'testuser', '$2y$10$.D6nvZNahkSXVSRLHrtI5u0RERS6rdBqwMH/uja1Yi7DVfiHukzjm', 'USER');

insert into user (id, username, password, role)
values (10, 'smartcampus', '$2y$10$.D6nvZNahkSXVSRLHrtI5u0RERS6rdBqwMH/uja1Yi7DVfiHukzjm', 'USER');
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

values (1, 'Friday 14-16',STR_TO_DATE('2017/03/17 14:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/03/17 16:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (2, 'Monday 08-10',STR_TO_DATE('2017/03/14 8:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/03/14 10:00:00', '%Y/%c/%d %T'));

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (1, 1);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (1, 2);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (2, 1);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (3, 1);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (4, 1);

-- subjects

insert into subject_details(subject_name,subject_type, start_period, end_period) values ('AI', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Mesterséges intelligencia alapjai', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Hálózati architektúrák és protokollok', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Az internet eszközei és szolgáltatásai', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

---------------------------------------------------------------------------------

--instructor to subject
insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (1, 'AI', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (2, 'Hálózati architektúrák és protokollok', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (3, 'Hálózati architektúrák és protokollok', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (4, 'Hálózati architektúrák és protokollok', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (5, 'Az internet eszközei és szolgáltatásai', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));
--------------------------------------------------------

-- User actual subjects
insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (1, 'AI', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (4, 'Mesterséges intelligencia alapjai', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (4, 'Hálózati architektúrák és protokollok', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (4, 'Az internet eszközei és szolgáltatásai', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));