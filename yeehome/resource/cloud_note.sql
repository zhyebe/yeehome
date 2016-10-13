create table cn_activity_status(
	cn_activity_status_id varchar(100) primary key,
	cn_activity_id varchar(100),
	cn_activity_status_code varchar(100),
	cn_activity_status_name varchar(100)
);
create table cn_activity(
	cn_activity_id varchar(100) primary key,
	cn_activity_title text,
	cn_activity_body text,
	cn_activity_end_time bigint
);
create table cn_note_activity(
	cn_note_activity_id varchar(100) primary key,
	cn_activity_id varchar(100),
	cn_note_id varchar(100),
	cn_note_activity_up int(100),
	cn_note_activity_down int(100),
	cn_note_activity_title text,
	cn_note_activity_body text
);
create table cn_note_status(
	cn_note_status_id varchar(100) primary key,
	cn_note_status_code varchar(100),
	cn_note_status_name varchar(100)
);
create table cn_note_type(
	cn_note_type_id varchar(100) primary key,
	cn_note_type_code varchar(100),
	cn_note_type_name varchar(100),
	cn_note_type_desc varchar(100)
);
create table cn_note(
	cn_note_id varchar(100) primary key,
	cn_notebook_id varchar(100),
	cn_user_id varchar(100),
	cn_note_status_id varchar(100),
	cn_note_type_id varchar(100),
	cn_note_title text,
	cn_note_body text,
	cn_note_create_time bigint,
	cn_note_last_modify_time bigint
);
create table cn_notebook(
	cn_notebook_id varchar(100) primary key,
	cn_user_id varchar(100),
	cn_notebook_type_id varchar(100),
	cn_notebook_name varchar(100),
	cn_notebook_desc varchar(100),
	cn_notebook_createtime varchar(100)
);

create table cn_notebook_type(
	cn_notebook_type_id varchar(100) primary key,
	cn_notebook_type_code varchar(100),
	cn_notebook_type_name varchar(100),
	cn_notebook_type_desc varchar(100)
);
create table cn_share(
	cn_share_id varchar(100) primary key,
	cn_share_title text,
	cn_share_body text,
	cn_note_id varchar(100)
);
create table cn_user(
	cn_user_id varchar(100) primary key,
	cn_user_name varchar(100),
	cn_user_password varchar(100),
	cn_user_token varchar(100),
	cn_user_desc varchar(100),
	cn_user_nickname varchar(100)
);