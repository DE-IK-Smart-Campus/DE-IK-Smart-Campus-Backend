-- User table insert
insert into user (id,fullname, username, password, role)
values (1,'Admin', 'admin', 'admin', 'ADMIN');

insert into user (id,fullname, username, password, role)
values (2,'Dr. Adamkó Attila', 'adamkai', 'admin', 'USER');

insert into user (id,fullname, username, password, role)
values (3,'Kovács-Ferenc Norbert', 'nolbi', 'admin', 'USER');

insert into user (id,fullname, username, password, role)
values (4,'Nánti', 'holi60', 'admin', 'USER');

insert into user (id,fullname, username, password, role)
values (5,'Erdei Krisztián', 'filtikai', 'admin', 'USER');

insert into user (id,fullname, username, password, role)
values (6,'Bak Balázs', 'palu', 'admin', 'USER');

insert into user (id,fullname, username, password, role)
values (7,'Fekete Attila', 'butikai', 'admin', 'USER');

insert into user (id,fullname, username, password, role)
values (8,'Gabikaiikakaik', 'gabai', 'admin', 'USER');

insert into user (id,fullname, username, password, role)
values (9,'Test User', 'testuser', 'admin', 'USER');

insert into user (id,fullname, username, password, role)
values (10,'Smart Campus', 'smartcampus', 'admin', 'USER');
----------------------------------------------------------------------------------------------------

-- instructors
insert into instructor (id, name)
values (1, 'Dr. Adamkó Attila');

insert into instructor (id, name)
values (2, 'Dr. Gál Zoltán');

insert into instructor (id, name)
values (3, 'Dr. Szilágyi Szabolcs');

insert into instructor (id, name)
values (4, 'Vas Ádám');

insert into instructor (id, name)
values (5, 'Dr. Jeszenszky Péter');

insert into instructor (id, name)
values (6, 'Dr. Kuki Attila');

insert into instructor (id, name)
values (7, 'Tomán Henrietta');

insert into instructor (id, name)
values (8, 'Herendi Tamás');

insert into instructor (id, name)
values (9, 'Dr. Horváth Géza');

insert into instructor (id, name)
values (10, 'Balla Tibor');

insert into instructor (id, name)
values (11, 'Dr. Kocsis Gergely');

insert into instructor (id, name)
values (12, 'Dr. Dömösi Pál');

insert into instructor (id, name)
values (13, 'Dr. Fazekas Gábor');

insert into instructor (id, name)
values (14, 'Dr. Fazekas István');

insert into instructor (id, name)
values (15, 'Rápolti Ida');

-------------------------------

insert into consulting_date (id, date, START_DATE, END_DATE)
values (1, 'Friday 14-16',STR_TO_DATE('2017/03/31 14:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/03/31 16:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (2, 'Monday 08-10',STR_TO_DATE('2017/04/3 8:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/04/3 10:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (3, 'Friday 14-16',STR_TO_DATE('2017/04/7 14:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/04/7 16:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (4, 'Monday 08-10',STR_TO_DATE('2017/04/10 8:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/04/10 10:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (5, 'Friday 14-16',STR_TO_DATE('2017/04/17 14:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/04/17 16:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (6, 'Monday 08-10',STR_TO_DATE('2017/04/24 8:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/04/24 10:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (7, 'Friday 14-16',STR_TO_DATE('2017/05/1 14:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/05/1 16:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (8, 'Wednesday 10-12',STR_TO_DATE('2017/04/5 10:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/4/5 12:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (9, 'Tuesday 9-10',STR_TO_DATE('2017/04/4 9:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/4/4 10:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (10, 'Wednesday 10-12',STR_TO_DATE('2017/04/12 10:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/4/12 12:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (11, 'Tuesday 9-10',STR_TO_DATE('2017/04/11 9:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/4/11 10:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (12, 'Wednesday 10-12',STR_TO_DATE('2017/04/19 10:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/4/19 12:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (13, 'Tuesday 9-10',STR_TO_DATE('2017/04/18 9:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/4/18 10:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (14, 'Wednesday 10-12',STR_TO_DATE('2017/04/26 10:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/4/26 12:00:00', '%Y/%c/%d %T'));

insert into consulting_date (id, date, START_DATE, END_DATE)
values (15, 'Tuesday 9-10',STR_TO_DATE('2017/04/25 9:00:00', '%Y/%c/%d %T'),STR_TO_DATE('2017/4/25 10:00:00', '%Y/%c/%d %T'));


insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (1, 1);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (2, 1);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (3, 1);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (4, 1);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (7, 1);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (8, 1);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (14, 1);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (1, 3);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (2, 3);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (3, 3);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (4, 3);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (7, 3);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (8, 3);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (14, 3);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (1, 5);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (2, 5);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (3, 5);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (4, 5);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (7, 5);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (8, 5);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (14, 5);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (1, 7);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (2, 7);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (3, 7);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (4, 7);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (7, 7);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (8, 7);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (14, 7);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (6, 8);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (6, 10);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (6, 12);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (6, 14);

insert into user_consulting_date(id,user_id,consulting_date_id,reason,duration)
values (1,4,1,'Mert teszt - 1','10 perc');

insert into user_consulting_date(id,user_id,consulting_date_id,reason,duration)
values (2,5,1,'Mert teszt - 2','5 perc');

insert into user_consulting_date(id,user_id,consulting_date_id,reason,duration)
values (3,6,1,'Mert teszt - 3','17 perc');

insert into user_consulting_date(id,user_id,consulting_date_id,reason,duration)
values (4,4,3,'Mert mennék TDK-t beszélni.','30 perc');


-- subjects

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Operációs rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Mesterséges intelligencia alapjai', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Hálózati architektúrák és protokollok', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Az internet eszközei és szolgáltatásai', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('A rendszerfejlesztés technológiája', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Neurális hálók', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Java webalkalmazások fejlesztése', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Hálózatmodellezés', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Grafikus rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Algoritmusok tervezése és elemzése', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

---------------------------------------------------------------------------------

--instructor to subject
insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (1,'Operációs rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (15,'Operációs rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (13,'Operációs rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (2, 'Hálózati architektúrák és protokollok', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (3, 'Hálózati architektúrák és protokollok', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (4, 'Hálózati architektúrák és protokollok', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (5, 'Az internet eszközei és szolgáltatásai', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (6, 'Hálózatmodellezés', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (10, 'Java webalkalmazások fejlesztése', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (10, 'A rendszerfejlesztés technológiája', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (14, 'Neurális hálók', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (8, 'Algoritmusok tervezése és elemzése', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (7, 'Grafikus rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

--------------------------------------------------------

-- User actual subjects
-- Testuser
insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (9,'Operációs rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (9,'Operációs rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (9,'Operációs rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (9, 'Algoritmusok tervezése és elemzése', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (9, 'Neurális hálók', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (9, 'A rendszerfejlesztés technológiája', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (9, 'Java webalkalmazások fejlesztése', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (9, 'Grafikus rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (9, 'Hálózati architektúrák és protokollok', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (9, 'Hálózatmodellezés', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

-- Holi60

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (4,'Operációs rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (4,'Operációs rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (4,'Operációs rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (4, 'Algoritmusok tervezése és elemzése', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (4, 'Neurális hálók', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (4, 'A rendszerfejlesztés technológiája', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (4, 'Java webalkalmazások fejlesztése', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (4, 'Grafikus rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (4, 'Hálózati architektúrák és protokollok', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (4, 'Hálózatmodellezés', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

-- Filtikai

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (5,'Operációs rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (5,'Operációs rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (5,'Operációs rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (5, 'Algoritmusok tervezése és elemzése', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (5, 'Neurális hálók', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (5, 'A rendszerfejlesztés technológiája', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (5, 'Java webalkalmazások fejlesztése', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (5, 'Grafikus rendszerek', 'LABORATORY', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (5, 'Hálózati architektúrák és protokollok', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

insert into user_subject_details_relation(user_id,subject_name, subject_type, start_period, end_period)
values (5, 'Hálózatmodellezés', 'LECTURE', STR_TO_DATE('2000-02-01', '%Y-%m-%d'), STR_TO_DATE('2000-05-31', '%Y-%m-%d'));

-- User custom events
insert into custom_event(id,guid,event_name, event_description, event_place, event_start,event_end,event_repeat,reminder)
values (1,'GUID1','Test event','Test event description', 'Test place',STR_TO_DATE('2017-04-01', '%Y-%m-%d'), STR_TO_DATE('2017-04-01', '%Y-%m-%d'),'Repeat every day', 'Reminder');

insert into custom_event(id,guid,event_name, event_description, event_place, event_start,event_end,event_repeat,reminder)
values (2,'GUID2','Test event 2','Test event description', 'Test place',STR_TO_DATE('2017-04-01', '%Y-%m-%d'), STR_TO_DATE('2017-04-01', '%Y-%m-%d'),'Repeat every day', 'Reminder');

insert into custom_event(id,guid,event_name, event_description, event_place, event_start,event_end,event_repeat,reminder)
values (3,'GUID3','Test event 3','Test event description', 'Test place',STR_TO_DATE('2017-04-01', '%Y-%m-%d'), STR_TO_DATE('2017-04-01', '%Y-%m-%d'),'Repeat every day', 'Reminder');

insert into user_custom_events(user_id,custom_event_id)
values (4,1);

insert into user_custom_events(user_id,custom_event_id)
values (4,2);

insert into user_custom_events(user_id,custom_event_id)
values (4,3);

insert into user_single_chat(user_entity_id,single_chat_list)
values(4,'1234@asd.com');

insert into user_single_chat(user_entity_id,single_chat_list)
values(4,'1234@asd2323.com');

insert into user_single_chat(user_entity_id,single_chat_list)
values(4,'13434234@asd2323.com');

insert into user_muc_chat(user_entity_id,muc_chat_list)
values(4,'1234234234234234234@asd.com');

insert into user_muc_chat(user_entity_id,muc_chat_list)
values(4,'1234234234@asd2323.com');

insert into user_muc_chat(user_entity_id,muc_chat_list)
values(4,'13434234234234@asd2323.com');