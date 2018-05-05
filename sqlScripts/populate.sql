/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  cekef
 * Created: Apr 20, 2018
 */

insert into ROADTRIPUSER values (1,'United States','test@mail.com','test',0,0,'password','user','testuser');
insert into ROADTRIPUSER values (2,'United Kingdom','foo@bar.com','foo',0,0,'password','bar','foobar');
insert into ROADTRIPUSER values (3,'United States','admin@mail.com','admin',1,1,'admin','istrator','admin');
insert into ROADTRIPUSER values (4,'a','a','a',1,1,'a','a','a');

insert into LOGGEDINTIMESTAMPS (user_id,moment) values(1,'2018-04-15 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(1,'2018-04-16 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(1,'2018-04-17 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(1,'2018-04-20 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(1,'2018-04-20 16:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(1,'2018-04-21 15:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(1,'2018-04-21 16:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(1,'2018-04-21 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(1,'2018-04-20 18:00:00');

insert into LOGGEDINTIMESTAMPS (user_id,moment) values(2,'2018-04-15 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(2,'2018-04-16 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(2,'2018-04-17 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(2,'2018-04-20 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(2,'2018-04-20 16:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(2,'2018-04-21 15:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(2,'2018-04-21 16:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(2,'2018-04-21 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(2,'2018-04-20 18:00:00');

insert into LOGGEDINTIMESTAMPS (user_id,moment) values(4,'2018-04-15 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(4,'2018-04-16 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(4,'2018-04-17 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(4,'2018-04-20 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(4,'2018-04-20 16:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(4,'2018-04-21 15:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(4,'2018-04-21 16:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(4,'2018-04-21 17:00:00');
insert into LOGGEDINTIMESTAMPS (user_id,moment) values(4,'2018-04-20 18:00:00');

INSERT INTO COUNTRY VALUES (1,'US','usa2Low','United States');

insert into ACHIEVEMENT values (1,'some stupid achievement','inserted manually',5,'insert into achieved values (:userid,0);');

insert into ACHIEVED values (1,1);

insert into TITLE values (1,'some stupid title','person',0);
insert into TITLE values (2,'some stupid title','VIP',1);

insert into CHECKPOINT values(1,'Beautiful town in Illinois.','Chicago',1,1,1);
insert into CHECKPOINT values(2,'The middle of route 66.','Midpoint Cafe',1,1,1);
insert into CHECKPOINT values(3,'The enormous canyon in the middle of Amerca.','Grand Canyon',1,1,1);

insert into ROAD values(1,'Follow the classic Route 66.','Route 66',1,3);

insert into ISON values(1,1);
insert into ISON values(2,1);
insert into ISON values(3,1);

-- insert into PHOTO values (1,'City','https://www.rondreis.nl/media/inline/bezienswaardigheden/old/201512/1450278001.jpg',1,1);
-- insert into PHOTO values (2,'The middle','https://www.rondreis.nl/media/inline/bezienswaardigheden/old/201512/1450278574.jpg',2,1);
-- insert into PHOTO values (3,'The cafe','https://www.rondreis.nl/media/inline/bezienswaardigheden/old/201512/1450278591.jpg',2,2);
-- insert into PHOTO values (4,'Sunset at the Canyon','https://www.rondreis.nl/media/inline/bezienswaardigheden/old/201512/1450278990.jpg',3,2);

