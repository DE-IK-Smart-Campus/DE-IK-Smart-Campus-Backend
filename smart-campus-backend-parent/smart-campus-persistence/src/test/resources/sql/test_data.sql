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

insert into consulting_date (id, date, START_DATE, END_DATE)
values (3, 'Monday 10-12',PARSEDATETIME('10-04-2017 08:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'),PARSEDATETIME('10-04-2017 10:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'));

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (1, 1);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (1, 2);

insert into instructor_consulting_dates (instructor_id,consulting_date_id)
values (2, 3);

insert into user_consulting_date(id,user_id,consulting_date_id,reason,duration)
values (1,4,1,'Mert teszt - 1','10 perc');

insert into user_consulting_date(id,user_id,consulting_date_id,reason,duration)
values (2,5,1,'Mert teszt - 2','5 perc');

insert into user_consulting_date(id,user_id,consulting_date_id,reason,duration)
values (3,6,1,'Mert teszt - 3','17 perc');

insert into user_consulting_date(id,user_id,consulting_date_id,reason,duration)
values (4,4,3,'Mert mennék TDK-t beszélni.','30 perc');

-- subjects

insert into subject_details(subject_name,subject_type, start_period, end_period) values ('AI', 'LABORATORY', DATE '2017-02-01', DATE '2017-05-31');

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Mesterséges intelligencia alapjai', 'LABORATORY', DATE '2000-02-01', DATE '2000-05-31');

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Mesterséges intelligencia alapjai', 'LECTURE', DATE '2000-02-01', DATE '2000-05-31');

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Hálózati architektúrák és protokollok', 'LECTURE', DATE '2000-02-01', DATE '2000-05-31');

insert into subject_details(subject_name, subject_type, start_period, end_period) values ('Az internet eszközei és szolgáltatásai', 'LECTURE', DATE '2000-02-01', DATE '2000-05-31');

--subject event
insert into subject_event(id,room_location,subject_details_name, subject_details_type, subject_details_start_period, subject_details_end_period) values (1,'IK-F01','Mesterséges intelligencia alapjai', 'LECTURE', DATE '2000-02-01', DATE '2000-05-31');

insert into subject_event(id,room_location,subject_details_name, subject_details_type, subject_details_start_period, subject_details_end_period) values (2,'IK-123','Mesterséges intelligencia alapjai', 'LABORATORY', DATE '2000-02-01', DATE '2000-05-31');

insert into subject_event(id,room_location,subject_details_name, subject_details_type, subject_details_start_period, subject_details_end_period) values (3,'IK-202','Mesterséges intelligencia alapjai', 'LABORATORY', DATE '2000-02-01', DATE '2000-05-31');

---------------------------------------------------------------------------------

--instructor to subject
insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (1, 'AI', 'LABORATORY',DATE '2017-02-01', DATE '2017-05-31');

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (2, 'Hálózati architektúrák és protokollok', 'LECTURE', DATE '2000-02-01', DATE '2000-05-31');

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (3, 'Hálózati architektúrák és protokollok', 'LECTURE', DATE '2000-02-01', DATE'2000-05-31');

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (4, 'Hálózati architektúrák és protokollok', 'LECTURE', DATE '2000-02-01', DATE'2000-05-31');

insert into instructor_subject_details(instructor_id,subject_name, subject_type, start_period, end_period)
values (5, 'Az internet eszközei és szolgáltatásai', 'LECTURE', DATE '2000-02-01', DATE'2000-05-31');
--------------------------------------------------------

-- User actual subjects
insert into user_subject_event_relation(user_id,subject_event_id)
values (2, 1);

insert into user_subject_event_relation(user_id,subject_event_id)
values (2, 2);

insert into user_subject_event_relation(user_id,subject_event_id)
values (2, 3);

-- User custom events
insert into custom_event(id,guid,event_name, event_description, event_place, event_start,event_end,event_repeat,reminder)
values (1,'GUID','Test event','Test event description', 'Test place',DATE '2000-02-01', DATE'2000-05-31','Repeat every day', 'Reminder');

insert into custom_event(id,guid,event_name, event_description, event_place, event_start,event_end,event_repeat,reminder)
values (2,'GUID-TO-DELETE','Test event','Test event description', 'Test place',DATE '2000-02-01', DATE'2000-05-31','Repeat every day', 'Reminder');

insert into user_custom_events(user_id,custom_event_id)
values (1,1);

insert into user_single_chat(user_entity_id,single_chat_list)
values(1,'1234@asd.com');

insert into user_single_chat(user_entity_id,single_chat_list)
values(1,'1234@asd2323.com');

insert into user_muc_chat(user_entity_id,muc_chat_list)
values(1,'1234234234234234234@asd.com');

insert into user_muc_chat(user_entity_id,muc_chat_list)
values(1,'1234234234@asd2323.com');

insert into user_muc_chat(user_entity_id,muc_chat_list)
values(1,'13434234234234@asd2323.com');
