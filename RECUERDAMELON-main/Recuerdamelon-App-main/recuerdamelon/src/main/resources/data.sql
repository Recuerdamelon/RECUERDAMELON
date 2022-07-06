INSERT INTO Calendar (ID,NAME) VALUES(1,'calendario1');
INSERT INTO Calendar (ID,NAME) VALUES(2,'calendario2');
INSERT INTO Calendar (ID,NAME) VALUES(3,'calendario3');
INSERT INTO Calendar (ID,NAME) VALUES(4,'calendario4');

INSERT INTO Notification (ID,notification_Time,notified) VALUES(1,2,false);
INSERT INTO Notification (ID,notification_Time,notified) VALUES(3,4,false);
INSERT INTO Notification (ID,notification_Time,notified) VALUES(5,6,true);
INSERT INTO Notification (ID,notification_Time,notified) VALUES(7,8,false);

INSERT INTO Task_Type (ID,NAME,IMAGE) VALUES(1,'taskType1','url1');
INSERT INTO Task_Type (ID,NAME,IMAGE) VALUES(2,'taskType2','url2');
INSERT INTO Task_Type (ID,NAME,IMAGE) VALUES(3,'taskType3','url3');
INSERT INTO Task_Type (ID,NAME,IMAGE) VALUES(4,'taskType4','url4');

INSERT INTO Task (ID, TITLE, START_DATE, END_DATE, DESCRIPTION, LOCATION_URL, DELETE)
VALUES(1,'task1','2007-12-03','2007-12-11','description1','url1',false);
INSERT INTO Task (ID, TITLE, START_DATE, END_DATE, DESCRIPTION, LOCATION_URL, DELETE)
VALUES(2,'task2','2007-12-03','2007-12-11','description2','url2',false);
INSERT INTO Task (ID, TITLE, START_DATE, END_DATE, DESCRIPTION, LOCATION_URL, DELETE)
VALUES(3,'task3','2007-12-03','2007-12-09','description3','url3',false);
INSERT INTO Task (ID, TITLE, START_DATE, END_DATE, DESCRIPTION, LOCATION_URL, DELETE)
VALUES(4,'task4','2007-12-03','2007-12-09','description4','url4',false);

INSERT INTO RM_User (ID,USERNAME,NAME, SURNAME, EMAIL, PASSWORD, ACTIVE, AVATAR)
VALUES(1,'user1','name1','surname1','email1','password1',true,'url1');
INSERT INTO RM_User (ID,USERNAME,NAME, SURNAME, EMAIL, PASSWORD, ACTIVE, AVATAR)
VALUES(2,'user2','name2','surname2','email2','password2',true,'url2');
INSERT INTO RM_User (ID,USERNAME,NAME, SURNAME, EMAIL, PASSWORD, ACTIVE, AVATAR)
VALUES(3,'user3','name3','surname3','email3','password3',true,'url3');
INSERT INTO RM_User (ID,USERNAME,NAME, SURNAME, EMAIL, PASSWORD, ACTIVE, AVATAR)
VALUES(4,'user4','name4','surname4','email4','password4',true,'url4');

INSERT INTO User_Role (ID, NAME) VALUES(1,'userRole1');
INSERT INTO User_Role (ID, NAME) VALUES(2,'userRole2');
INSERT INTO User_Role (ID, NAME) VALUES(3,'userRole3');
INSERT INTO User_Role (ID, NAME) VALUES(4,'userRole4');
