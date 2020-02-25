-- A SQL file to load the database with some dummy-data
delete from users_locations;
delete from users;
delete from locations;

insert into users (id, username) values (9980, "Bob");
insert into users (id, username) values (9981, "Tom");
insert into users (id, username) values (9982, "Joe");
insert into users (id, username) values (9983, "Suzy");
insert into users (id, username) values (9984, "Sally");

insert into locations (id, zip) values (9980, "40514");
insert into locations (id, zip) values (9981, "40515");
insert into locations (id, zip) values (9982, "40516");
insert into locations (id, zip) values (9983, "40517");
insert into locations (id, zip) values (9984, "40518");

insert into users_locations (user_id, locations_id) values (9980, 9980);
insert into users_locations (user_id, locations_id) values (9981, 9981);
insert into users_locations (user_id, locations_id) values (9982, 9982);
insert into users_locations (user_id, locations_id) values (9983, 9983);
insert into users_locations (user_id, locations_id) values (9984, 9984);

-- Give Bob a few extra locations
insert into users_locations (user_id, locations_id) values (9980, 9981);
insert into users_locations (user_id, locations_id) values (9980, 9982);
insert into users_locations (user_id, locations_id) values (9980, 9983);