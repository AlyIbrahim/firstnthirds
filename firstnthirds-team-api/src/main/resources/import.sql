INSERT INTO UserTeamRole (role) VALUES ("Lead");
INSERT INTO UserTeamRole (role) VALUES ("Member");

INSERT INTO TeamStatus (provisionStatus) VALUES ("Created");
INSERT INTO TeamStatus (provisionStatus) VALUES ("Disabled");
INSERT INTO TeamStatus (provisionStatus) VALUES ("Deleted");

INSERT INTO Team (NAME, CITY, STATE, COUNTRY, STATUS) VALUES ("Dallas", "Dallas", "Texas", "United States", 1);
INSERT INTO Team (NAME, CITY, STATE, COUNTRY, STATUS) VALUES ("Austin", "Austin", "Texas", "United States", 1);
INSERT INTO Team (NAME, CITY, STATE, COUNTRY, STATUS) VALUES ("Chicago", "Chicago", "Illinois", "United States", 3);

INSERT INTO UserTeam (USER, TEAM, ROLE) VALUES (1, 1, 1);
INSERT INTO UserTeam (USER, TEAM, ROLE) VALUES (2, 1, 2);
INSERT INTO UserTeam (USER, TEAM, ROLE) VALUES (1, 3, 2);