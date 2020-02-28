-- A SQL file to load the database with some dummy-data
delete from users_locations;
delete from users;
delete from locations;

insert into users (id, username) values (9980, "Nicholas");
insert into users (id, username) values (9981, "Brian");
insert into users (id, username) values (9982, "Keith");
insert into users (id, username) values (9983, "Suzy");
insert into users (id, username) values (9984, "Sally");

insert into locations (id, zip) values (9980, "40514");
insert into locations (id, zip) values (9981, "41017");
insert into locations (id, zip) values (9982, "29925");
insert into locations (id, zip) values (9983, "90210");
insert into locations (id, zip) values (9984, "10001");

insert into users_locations (user_id, locations_id) values (9980, 9980);
insert into users_locations (user_id, locations_id) values (9981, 9981);
insert into users_locations (user_id, locations_id) values (9982, 9982);
insert into users_locations (user_id, locations_id) values (9983, 9983);
insert into users_locations (user_id, locations_id) values (9984, 9984);

-- Give some users a few extra locations
insert into users_locations (user_id, locations_id) values (9980, 9982);
insert into users_locations (user_id, locations_id) values (9980, 9983);
insert into users_locations (user_id, locations_id) values (9980, 9984);

insert into users_locations (user_id, locations_id) values (9981, 9980);
insert into users_locations (user_id, locations_id) values (9981, 9982);
insert into users_locations (user_id, locations_id) values (9981, 9983);
insert into users_locations (user_id, locations_id) values (9981, 9984);

insert into users_locations (user_id, locations_id) values (9982, 9980);
insert into users_locations (user_id, locations_id) values (9982, 9983);
insert into users_locations (user_id, locations_id) values (9982, 9984);