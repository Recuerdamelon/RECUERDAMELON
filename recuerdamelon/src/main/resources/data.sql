INSERT INTO Calendar (ID,NAME, TASK_DATE) VALUES(1,'calendario1', '2022-07-07 09:48:00.0');
INSERT INTO Calendar (ID,NAME, TASK_DATE) VALUES(2,'calendario2', '2022-07-07 09:48:00.0');
INSERT INTO Calendar (ID,NAME, TASK_DATE) VALUES(3,'calendario3', '2022-07-07 09:48:00.0');
INSERT INTO Calendar (ID,NAME, TASK_DATE) VALUES(4,'calendario4', '2022-07-07 09:48:00.0');

INSERT INTO Notification (ID,notification_Time,notified) VALUES(1,2,false);
INSERT INTO Notification (ID,notification_Time,notified) VALUES(3,4,false);
INSERT INTO Notification (ID,notification_Time,notified) VALUES(5,6,true);
INSERT INTO Notification (ID,notification_Time,notified) VALUES(7,8,false);

INSERT INTO Task_Type (ID,NAME) VALUES(1,'taskType1');
INSERT INTO Task_Type (ID,NAME) VALUES(2,'taskType2');
INSERT INTO Task_Type (ID,NAME) VALUES(3,'taskType3');
INSERT INTO Task_Type (ID,NAME) VALUES(4,'taskType4');

INSERT INTO Task (ID, TITLE, START_DATE, END_DATE, DESCRIPTION, LOCATION_URL, DELETE)
VALUES(1,'task1','2007-12-03','2007-12-11','description1','url1',false);
INSERT INTO Task (ID, TITLE, START_DATE, END_DATE, DESCRIPTION, LOCATION_URL, DELETE)
VALUES(2,'task2','2007-12-03','2007-12-11','description2','url2',false);
INSERT INTO Task (ID, TITLE, START_DATE, END_DATE, DESCRIPTION, LOCATION_URL, DELETE)
VALUES(3,'task3','2007-12-03','2007-12-09','description3','url3',false);
INSERT INTO Task (ID, TITLE, START_DATE, END_DATE, DESCRIPTION, LOCATION_URL, DELETE)
VALUES(4,'task4','2007-12-03','2007-12-09','description4','url4',false);

INSERT INTO USER (ID,USER_NAME,NAME, SURNAME, EMAIL, PASSWORD, ACTIVE)
VALUES(1,'user1','name1','surname1','email1','$2a$04$5sT3dri6bOOG2b9P1LETEujUeYMR46G/OVybuBjxBAohlEtDsxmi2',true);
INSERT INTO USER (ID,USER_NAME,NAME, SURNAME, EMAIL, PASSWORD, ACTIVE)
VALUES(2,'user2','name2','surname2','email2','$2a$04$5sT3dri6bOOG2b9P1LETEujUeYMR46G/OVybuBjxBAohlEtDsxmi2',true);
INSERT INTO USER (ID,USER_NAME,NAME, SURNAME, EMAIL, PASSWORD, ACTIVE)
VALUES(3,'user3','name3','surname3','email3','$2a$04$5sT3dri6bOOG2b9P1LETEujUeYMR46G/OVybuBjxBAohlEtDsxmi2',true);
INSERT INTO USER (ID,USER_NAME,NAME, SURNAME, EMAIL, PASSWORD, ACTIVE)
VALUES(4,'admin','name4','surname4','email4','$2a$04$5sT3dri6bOOG2b9P1LETEujUeYMR46G/OVybuBjxBAohlEtDsxmi2',true);

INSERT INTO User_Role (ID, NAME) VALUES(1,'PLAIN_USER');
INSERT INTO User_Role (ID, NAME) VALUES(2,'ADMIN');

INSERT INTO `USER_HAS_ROLE` (`USER_ID`, `ROLE_ID`)
VALUES
    (1,1),
    (2,1),
    (3,1),
    (4,1),
    (4,2);
