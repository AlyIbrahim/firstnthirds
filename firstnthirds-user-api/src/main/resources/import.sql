INSERT INTO UserEventRole (ROLE) VALUES ("Co-ordinator");
INSERT INTO UserEventRole (ROLE) VALUES ("Presenter");
INSERT INTO UserEventRole (ROLE) VALUES ("Photographer");
INSERT INTO UserEventRole (ROLE) VALUES ("Participant");

INSERT INTO UserEventStatus (STATUS) VALUES ("Registered");
INSERT INTO UserEventStatus (STATUS) VALUES ("Withdrew");
INSERT INTO UserEventStatus (STATUS) VALUES ("Attended");
INSERT INTO UserEventStatus (STATUS) VALUES ("No-Show");

INSERT INTO User (FIRSTNAME, LASTNAME, EMAIL, CITY, STATE, COUNTRY,TEAMID) VALUES ("Aly", "Ibrahim", "aly@redhat.com", "Plano", "Texas", "United States", 1);
INSERT INTO User (FIRSTNAME, LASTNAME, EMAIL, CITY, STATE, COUNTRY,TEAMID) VALUES ("Hana", "Ibrahim", "hana@redhat.com", "Plano", "Texas", "United States", 1);
INSERT INTO User (FIRSTNAME, LASTNAME, EMAIL, CITY, STATE, COUNTRY,TEAMID) VALUES ("Bond", "Ibrahim", "bond@aliction.com", "Plano", "Texas", "United States", 1);

INSERT INTO Admin (USER) VALUES (1);

INSERT INTO UserEvent(USER, EVENT, ROLE, STATUS) VALUES (1, 1, 1, 1);
INSERT INTO UserEvent(USER, EVENT, ROLE, STATUS) VALUES (2, 1, 4, 3);
INSERT INTO UserEvent(USER, EVENT, ROLE, STATUS) VALUES (3, 1, 4, 3);
INSERT INTO UserEvent(USER, EVENT, ROLE, STATUS) VALUES (1, 2, 1, 4);