create table city (cid integer, name varchar(100) ,PRIMARY KEY(cid));
		insert into city (cid, name) values (1, 'SANJOSE');
		insert into city (cid, name) values (2, 'AUSTIN');
		insert into city (cid, name) values (3, 'NEWYORK');
create table slot (sid integer, starttime integer,endtime integer,PRIMARY KEY(sid));
		insert into slot (sid, starttime,endtime) values (1,8,9);
        insert into slot (sid, starttime,endtime) values (2,9,10);
		insert into slot (sid, starttime,endtime) values (3,10,11);
        insert into slot (sid, starttime,endtime) values (4,11,12);
        insert into slot (sid, starttime,endtime) values (5,12,13);
        insert into slot (sid, starttime,endtime) values (6,13,14);
        insert into slot (sid, starttime,endtime) values (7,14,15);
        insert into slot (sid, starttime,endtime) values (8,15,16);
        insert into slot (sid, starttime,endtime) values (9,16,17);
        insert into slot (sid, starttime,endtime) values (10,17,18);
create table reservation(rid integer GENERATED ALWAYS AS IDENTITY,sid integer,cid integer,email varchar(100),
PRIMARY KEY(rid),
CONSTRAINT unq_sid_cid UNIQUE(sid,cid),
CONSTRAINT fk_city FOREIGN KEY(cid) REFERENCES city(cid),
CONSTRAINT fk_slot FOREIGN KEY(sid) REFERENCES slot(sid));

