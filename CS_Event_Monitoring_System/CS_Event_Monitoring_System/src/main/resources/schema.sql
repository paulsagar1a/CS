drop table Event if exists;
create table Event (
	id varchar(25) not null primary key,
	duration int not null,
	event_type varchar(25) not null,
	host varchar(25) not null,
	alert boolean null
);
