drop table sec_spends
drop table sec_users
drop table sec_menus

create table sec_menus(
		men_id int  not null IDENTITY PRIMARY KEY,
		men_menuname varchar(64)  not null,
		men_url varchar(64)  not null,
		men_parentmenu int  null,
		men_created datetime  not null default getdate(),
		men_updated datetime  not null default getdate()
		foreign key (men_parentmenu) references sec_menus(men_id)
	)
create table sec_users(
		usr_id int  not null IDENTITY PRIMARY KEY,
		usr_username varchar(64)  not null,
		usr_password varchar(64)  not null,
		usr_email varchar(64)  not null,
		usr_created datetime  not null default getdate(),
		usr_updated datetime  not null default getdate()
	)
create table sec_spends(
		spe_id int  not null IDENTITY PRIMARY KEY,
		spe_subject varchar(64)  not null,
		spe_price decimal(10,2)  null,
		spe_createdby int  not null,
		spe_updatedby int  not null,
		spe_created datetime  not null default getdate(),
		spe_updated datetime  not null default getdate()
		foreign key (spe_createdby) references sec_users(usr_id),
		foreign key (spe_updatedby) references sec_users(usr_id)
	)
