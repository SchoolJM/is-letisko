create user if not exists `iis_project`@`localhost` identified by "iis_project";
create database if not exists `iis_project`;
use `iis_project`;


create table if not exists `user_details` (
	`email` varchar(254) default null,
    `phone_number` varchar(20) default null,
    `first_name` varchar(45) not null,
    `last_name` varchar(45) not null,
    `identification_number` varchar(32) not null,
    `city` varchar(45) not null,
    `street` varchar(45) not null,
    `house_number` varchar(5) not null,
    `zip` varchar(5) not null,
    `country` varchar(45) not null,
    
    primary key(`identification_number`)
   
    
);

create table if not exists `user` (
	`id` int(11) auto_increment,
	`username` varchar(254) not null,
	`password` varchar(80) not null,
	`enabled` tinyint default 0, 
    `user_details_id` varchar(10) not null,
    
	primary key(`id`),
    
    constraint `fk_user_details` foreign key(`user_details_id`) references `user_details`(`identification_number`)
);

create table if not exists `role` (
	`id` int(11) auto_increment,
    `name` varchar(50) not null,
    
    primary key(`id`)
);

create table if not exists `users_roles` (
	`role_id` int(11)  not null,
    `user_id` int(11) not null,
    
    PRIMARY KEY (`user_id`,`role_id`),
  
	KEY `fk_role` (`role_id`),
  
	CONSTRAINT `fk_user_id2` FOREIGN KEY (`user_id`) 
	REFERENCES `user` (`id`)
    ON DELETE CASCADE,
  
	CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) 
	REFERENCES `role` (`id`) 
	ON DELETE CASCADE
);

create table if not exists `activation_tokens`(
	`user_id` int(11) not null,
    `token` varchar(80) not null,
    
    PRIMARY KEY (`user_id`),
    
    CONSTRAINT `fk_user_id3` FOREIGN KEY (`user_id`) 
	REFERENCES `user` (`id`)
    ON DELETE CASCADE
);

create table if not exists `destinations` (
	`id` int(11) auto_increment,
    `airport` varchar(128) not null,
    `city` varchar(64),
    `country` varchar(64) not null,
    `latitude` double(23,20) not null,
    `longitude` double(23,20) not null,
    
    primary key(`id`)
);

create table if not exists `gates` (
	`gate_number` int(3) not null,
    `terminal` varchar(1) not null,
    
    primary key(`gate_number`)
);

create table if not exists `planes` (
	`serial_number` varchar(16) not null,
    `type` varchar(16) not null,
    `manufacturer` varchar(32) not null,
    `date_of_made` varchar(20) not null,
    `last_revision_date` varchar(20) default null,
    `number_of_seats_economic` int(4) default 0,
    `number_of_seats_business` int(4) default 0,
    `number_of_seats_first` int(4) default 0,
    `max_payload_in_tons` int(4) default 0,
    
    primary key(`serial_number`)
);

create table if not exists `flights` (
	`id` int(11) auto_increment,
    `takeoff_date` varchar(20) not null,
    `destination` int(11) not null,
    `gate` int(11) not null,
    `plane` varchar(16) not null,
    `price` double(6,2) not null,
    
    primary key(`id`),
    
    key `fk_destination`(`destination`), 
    constraint `fk_destination` foreign key(`destination`) references `destinations`(`id`),
    key `fk_gate`(`gate`), 
    constraint `fk_gate` foreign key(`gate`) references `gates`(`gate_number`),
    key `fk_plane_flights`(`plane`), 
    constraint `fk_plane_flights` foreign key(`plane`) references `planes`(`serial_number`)
);

create table if not exists `flight_tickets` (
	`id` int(11) auto_increment,
    `flight` int(11) not null,
    `class` varchar(16) not null,
    `user_identification_number` varchar(10) not null,
    
    primary key(`id`),
    
    key `fk_flight`(`flight`), 
    constraint `fk_flight` foreign key(`flight`) references `flights`(`id`) on delete cascade,
    key `fk_id_ticket`(`user_identification_number`), 
    constraint `fk_id_ticket` foreign key(`user_identification_number`) references `user_details`(`identification_number`) on delete cascade
);

SET FOREIGN_KEY_CHECKS = 1;
