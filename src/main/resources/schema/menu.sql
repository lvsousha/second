
create table sec_menus(
		men_id int is not null IDENTITY PRIMARY KEY,
		men_menuname varchar(64) is not null,
		men_parentmenu int is null,
		men_created datetime is not null,
		men_updated datetime is not null
		foreign key (men_parentmenu) references sec_menus(men_id)
	)
