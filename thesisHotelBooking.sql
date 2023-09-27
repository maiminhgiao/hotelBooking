use thesisHotelBooking;

create table review(
	id int,
	primary key (id),
	content text,
	rate varchar (50),
	name varchar(50),
	email varchar(50),
	idRoom int);
	
create table `size`(
	id int,
	primary key(id),
	square varchar(50));
	
create table`type`(
	id int,
	primary key(id),
	name varchar (50));
	
create table room(
	id int,
	primary key(id),
	price varchar(50),
	discount varchar(50),
	idSize int,
	idType int
	);

create table roomservice(
	idService int,
	idRoom int,
	primary key(idService, idRoom));

create table service(
	id int,
	primary key(id),
	name varchar(50));

create table image(
	id int,
	primary key (id),
	url varchar(50),
	name varchar(50),
	idRoom int);

create table cart(
	id int,
	primary key(id),
	checkIn datetime,
	checkOut datetime,
	adultNumber int,
	childNumber int,
	idUser int,
	isDelete bit,
	idRoom int);
	
create table reservation(
	id int,
	primary key (id),
	checkIn datetime,
	checkOut datetime,
	adultNumber varchar (50),
	idUser int,
	idStatus int,
	idRoom int);


create table status (
	id int,
	primary key(id),
	name varchar (50));

create table `user`(
	id int,
	primary key(id),
	userName varchar (50),
	email varchar (50),
	password varchar(50),
	phone varchar (50),
	idRole int);

create table blog (
	id int,
	primary key(id),
	title varchar(255),
	startDate datetime,
	images varchar(50),
	idUser int,
	content text);
	
create table blogtag(
	idBlog int,
	idTag int,
	primary key (idBlog, idTag));
	
create table tag(
	id int,
	primary key(id),
	name varchar(50));
	
create table comment(
	id int,
	primary key(id),
	content text,
	`like` varchar(50),
	email varchar(50),
	name varchar(50),
	createDate datetime,
	idBlog int);

create table `role`(
	id int,
	primary key (id),
	name varchar (50));

alter table review add constraint fk_idRoom_review foreign key (idRoom) references room(id);
alter table room add constraint fk_idSize_size foreign key (idSize) references `size` (id);
alter table room add constraint fk_idType_room foreign key (idType) references `type` (id); 

alter table roomservice add constraint fk_idRoom_roomservice foreign key (idRoom) references room(id);
alter table roomservice add constraint fk_idService_roomservice foreign key (idService) references service(id);

alter table image add constraint fk_idRoom_image foreign key (idRoom) references room(id);

alter table cart add constraint fk_idRoom_cart foreign key(idRoom) references room(id);
alter table cart add constraint fk_idUser_cart foreign key (idUser) references `user`(id);

alter table reservation add constraint fk_idRoom_reservation foreign key (idRoom) references room(id);
alter table reservation add constraint fk_idStatus_reservation foreign key (idStatus) references status(id);
alter table reservation add constraint fk_idUser_reservation foreign key (idUser) references `user`(id);

alter table blog add constraint fk_idUser_blog foreign key (idUser) references `user` (id);
alter table blogtag add constraint fk_idBlog_blogtag foreign key(idBlog) references blog (id);
alter table blogtag add constraint fk_idTag_blogtag foreign key (idTag) references tag (id);

alter table comment add constraint fk_idBlog_comment foreign key(idBlog) references blog(id);

alter table `user` add constraint fk_idRole_user foreign key (idRole) references `role` (id);


















	

	
	
	