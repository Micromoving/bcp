
/* Drop Indexes */

DROP INDEX sys_area_parent_id;
DROP INDEX sys_area_parent_ids;
DROP INDEX sys_area_del_flag;
DROP INDEX sys_dict_value;
DROP INDEX sys_dict_label;
DROP INDEX sys_dict_del_flag;
DROP INDEX sys_log_create_by;
DROP INDEX sys_log_request_uri;
DROP INDEX sys_log_type;
DROP INDEX sys_log_create_date;
DROP INDEX sys_mdict_parent_id;
DROP INDEX sys_mdict_parent_ids;
DROP INDEX sys_mdict_del_flag;
DROP INDEX sys_menu_parent_id;
DROP INDEX sys_menu_parent_ids;
DROP INDEX sys_menu_del_flag;
DROP INDEX sys_office_parent_id;
DROP INDEX sys_office_parent_ids;
DROP INDEX sys_office_del_flag;
DROP INDEX sys_office_type;
DROP INDEX sys_role_del_flag;
DROP INDEX sys_role_enname;
DROP INDEX sys_user_office_id;
DROP INDEX sys_user_login_name;
DROP INDEX sys_user_company_id;
DROP INDEX sys_user_update_date;
DROP INDEX sys_user_del_flag;



/* Drop Tables */

DROP TABLE sys_user_role CASCADE CONSTRAINTS;
DROP TABLE sys_user CASCADE CONSTRAINTS;
DROP TABLE sys_role_office CASCADE CONSTRAINTS;
DROP TABLE sys_office CASCADE CONSTRAINTS;
DROP TABLE sys_area CASCADE CONSTRAINTS;
DROP TABLE sys_dict CASCADE CONSTRAINTS;
DROP TABLE sys_log CASCADE CONSTRAINTS;
DROP TABLE sys_mdict CASCADE CONSTRAINTS;
DROP TABLE sys_role_menu CASCADE CONSTRAINTS;
DROP TABLE sys_menu CASCADE CONSTRAINTS;
DROP TABLE sys_role CASCADE CONSTRAINTS;
DROP TABLE SYS_USER_TEACHER PURGE;
DROP TABLE SYS_WORK_EXPERIENCE PURGE;
DROP TABLE SYS_EDU PURGE;
DROP TABLE SYS_AWARD PURGE;
DROP TABLE SYS_SCHOOL_TITLE PURGE;
DROP TABLE SYS_PRACTICE_EXPERINCE PURGE;
DROP TABLE SYS_TRAIN_EXPERIENCE PURGE;
DROP TABLE SYS_CERTIFICATE PURGE;
DROP TABLE SYS_ATTACHMENT PURGE;





/* Create Tables */

-- 区域表
CREATE TABLE sys_area
(
	id varchar2(64) NOT NULL,
	parent_id varchar2(64) NOT NULL,
	parent_ids varchar2(2000) NOT NULL,
	name nvarchar2(100) NOT NULL,
	sort number(10,0) NOT NULL,
	code varchar2(100),
	type char(1),
	create_by varchar2(64) NOT NULL,
	create_date timestamp NOT NULL,
	update_by varchar2(64) NOT NULL,
	update_date timestamp NOT NULL,
	remarks nvarchar2(255),
	del_flag char(1) DEFAULT '0' NOT NULL,
	PRIMARY KEY (id)
);


-- 字典表
CREATE TABLE sys_dict
(
	id varchar2(64) NOT NULL,
	value varchar2(100) NOT NULL,
	label varchar2(100) NOT NULL,
	type varchar2(100) NOT NULL,
	description nvarchar2(100) NOT NULL,
	sort number(10,0) NOT NULL,
	parent_id varchar2(64) DEFAULT '0',
	create_by varchar2(64) NOT NULL,
	create_date timestamp NOT NULL,
	update_by varchar2(64) NOT NULL,
	update_date timestamp NOT NULL,
	remarks nvarchar2(255),
	del_flag char(1) DEFAULT '0' NOT NULL,
	PRIMARY KEY (id)
);


-- 日志表
CREATE TABLE sys_log
(
	id varchar2(64) NOT NULL,
	type char(1) DEFAULT '1',
	title nvarchar2(500),
	create_by varchar2(64),
	create_date timestamp,
	remote_addr varchar2(255),
	user_agent varchar2(255),
	request_uri varchar2(255),
	method varchar2(5),
	params clob,
	exception clob,
	PRIMARY KEY (id)
);


-- 多级字典表
CREATE TABLE sys_mdict
(
	id varchar2(64) NOT NULL,
	parent_id varchar2(64) NOT NULL,
	parent_ids varchar2(2000) NOT NULL,
	name nvarchar2(100) NOT NULL,
	sort number(10,0) NOT NULL,
	description nvarchar2(100),
	create_by varchar2(64) NOT NULL,
	create_date timestamp NOT NULL,
	update_by varchar2(64) NOT NULL,
	update_date timestamp NOT NULL,
	remarks nvarchar2(255),
	del_flag char(1) DEFAULT '0' NOT NULL,
	PRIMARY KEY (id)
);


-- 菜单表
CREATE TABLE sys_menu
(
	id varchar2(64) NOT NULL,
	parent_id varchar2(64) NOT NULL,
	parent_ids varchar2(2000) NOT NULL,
	name nvarchar2(100) NOT NULL,
	sort number(10,0) NOT NULL,
	href varchar2(2000),
	target varchar2(20),
	icon varchar2(100),
	is_show char(1) NOT NULL,
	permission varchar2(200),
	create_by varchar2(64) NOT NULL,
	create_date timestamp NOT NULL,
	update_by varchar2(64) NOT NULL,
	update_date timestamp NOT NULL,
	remarks nvarchar2(255),
	del_flag char(1) DEFAULT '0' NOT NULL,
	PRIMARY KEY (id)
);


-- 机构表
CREATE TABLE sys_office
(
	id varchar2(64) NOT NULL,
	parent_id varchar2(64) NOT NULL,
	parent_ids varchar2(2000) NOT NULL,
	name nvarchar2(100) NOT NULL,
	sort number(10,0) NOT NULL,
	area_id varchar2(64) NOT NULL,
	code varchar2(100),
	type char(1) NOT NULL,
	grade char(1) NOT NULL,
	address nvarchar2(255),
	zip_code varchar2(100),
	master nvarchar2(100),
	phone nvarchar2(200),
	fax nvarchar2(200),
	email nvarchar2(200),
	USEABLE varchar2(64),
	PRIMARY_PERSON varchar2(64),
	DEPUTY_PERSON varchar2(64),
	create_by varchar2(64) NOT NULL,
	create_date timestamp NOT NULL,
	update_by varchar2(64) NOT NULL,
	update_date timestamp NOT NULL,
	remarks nvarchar2(255),
	del_flag char(1) DEFAULT '0' NOT NULL,
	PRIMARY KEY (id)
);


-- 角色表
CREATE TABLE sys_role
(
	id varchar2(64) NOT NULL,
	office_id varchar2(64),
	name nvarchar2(100) NOT NULL,
	enname varchar2(255),
	role_type varchar2(255),
	data_scope char(1),
	is_sys varchar2(64),
	useable varchar2(64),
	create_by varchar2(64) NOT NULL,
	create_date timestamp NOT NULL,
	update_by varchar2(64) NOT NULL,
	update_date timestamp NOT NULL,
	remarks nvarchar2(255),
	del_flag char(1) DEFAULT '0' NOT NULL,
	PRIMARY KEY (id)
);


-- 角色-菜单
CREATE TABLE sys_role_menu
(
	role_id varchar2(64) NOT NULL,
	menu_id varchar2(64) NOT NULL,
	PRIMARY KEY (role_id, menu_id)
);


-- 角色-机构
CREATE TABLE sys_role_office
(
	role_id varchar2(64) NOT NULL,
	office_id varchar2(64) NOT NULL,
	PRIMARY KEY (role_id, office_id)
);


-- 用户表
CREATE TABLE sys_user
(
	ID VARCHAR2(64 BYTE) NOT NULL ,
	COMPANY_ID VARCHAR2(64 BYTE) NOT NULL ,
	OFFICE_ID VARCHAR2(64 BYTE) NOT NULL ,
	LOGIN_NAME VARCHAR2(100 BYTE) NOT NULL ,
	PASSWORD VARCHAR2(100 BYTE) NOT NULL ,
	NAME VARCHAR2(100 BYTE) NOT NULL ,
	USER_TYPE CHAR(1 BYTE) NULL ,
	LOGIN_IP VARCHAR2(100 BYTE) NULL ,
	LOGIN_DATE DATE NULL ,
	LOGIN_FLAG VARCHAR2(64 BYTE) NULL ,
	CREATE_BY VARCHAR2(64 BYTE) NULL ,
	CREATE_DATE DATE NULL ,
	UPDATE_BY VARCHAR2(64 BYTE) NULL ,
	UPDATE_DATE DATE NULL ,
	REMARKS VARCHAR2(255 BYTE) NULL ,
	DEL_FLAG CHAR(1 BYTE) NULL ,
	PHOTO VARCHAR2(50 BYTE) NULL ,
	EMAIL VARCHAR2(100 BYTE) NULL 
	CONSTRAINT PK_USER_ID PRIMARY KEY(ID)
);

--1.教师用户表--
CREATE TABLE SYS_USER_TEACHER(
ID VARCHAR2(64),
USER_ID VARCHAR2(64) NOT NULL,
NUM VARCHAR2(100）NOT NULL,
NAME VARCHAR2(100)NOT NULL,
SEX CHAR(1),
BIRTH_DATE DATE,
SPECIALTY VARCHAR2(100),
EDU_BACKGROUND CHAR(1),
ACADEMIC_DEGREE CHAR(1),
GRADUATE_SCHOOL VARCHAR2(100),
RESEARCH_AREA VARCHAR2(100),
PROFESSIONAL_TITLE CHAR(2),
PROFESSIONAL_TYPE CHAR(2),
PROFESSIONAL_LEVEL CHAR(2),
JOB_TITLE_DATE DATE,
IN_WORK_DATE DATE,
IN_SCHOOL_DATE DATE,
DPRO_TITLED CHAR(1),
POLITICS_STATUS CHAR(1),
PAPERS_TYPE CHAR(1),
PAPERS_NUMBER VARCHAR2(18),
QQ_ID VARCHAR2(15),
POSTCODE VARCHAR2(6),
WECHAT_ID VARCHAR2(100),
HOME_ADD VARCHAR2(100),
MARITAL_STATUS CHAR(1),
EMAIL VARCHAR2(100),
PHONE VARCHAR2(50),
MOBLIE VARCHAR2(11),
PHOTO VARCHAR2(50),
RESUME CLOB,
HOMEPAGE VARCHAR2(100), 
CONSTRAINT PK_TEACHER_ID PRIMARY KEY(ID),
CONSTRAINT FK_USER_ID_1 FOREIGN KEY(USER_ID) REFERENCES SYS_USER(ID)
);

--2.工作经验表--
CREATE TABLE SYS_WORK_EXPERIENCE(
ID VARCHAR2(64),
USER_ID VARCHAR2(64)NOT NULL,
START_DATE DATE NOT NULL,
END_DATE DATE NOT NULL,
COMPANY_NAME VARCHAR2(100)NOT NULL,
COMPANY_NATURE CHAR(1),
COMPANY_DEPARTMENT VARCHAR2(100),
POSITION VARCHAR2(100),
REMARKS VARCHAR2(100),
WORK_SORT CHAR(1), 
CONSTRAINT PK_WEXPERENCE_ID PRIMARY KEY (ID),
CONSTRAINT FK_USER_ID_2 FOREIGN KEY (USER_ID) REFERENCES SYS_USER(ID)
);

--3.教育经历表--
CREATE TABLE SYS_EDU(
ID VARCHAR2(64),
USER_ID VARCHAR2(64)NOT NULL,
START_DATE DATE NOT NULL,
END_DATE DATE NOT NULL,
SCHOOL_NAME VARCHAR2(100)NOT NULL,
SPECIALTY VARCHAR2(100)NOT NULL,
EDU_BACKGROUND CHAR(1)NOT NULL,
IS_FULLTIME CHAR(1),
REMARKS VARCHAR2(1000),
IS_OVERSEAS CHAR(1),
CONSTRAINT PK_EDU_ID PRIMARY KEY(ID),
CONSTRAINT FK_USER_ID_3 FOREIGN KEY(USER_ID) REFERENCES SYS_USER(ID)
);

--4.奖项表--
CREATE TABLE SYS_AWARD(
ID VARCHAR2(64),
USER_ID VARCHAR2(64)NOT NULL,
GAIN_DATE DATE NOT NULL,
AWARDS_NAME VARCHAR2(100)NOT NULL,
LEVEL_ONE CHAR(1),
LEVEL_TWO VARCHAR2(100), 
CONSTRAINT PK_AWARD_ID PRIMARY KEY(ID),
CONSTRAINT FK_USER_ID_4 FOREIGN KEY(USER_ID) REFERENCES SYS_USER(ID)
);

--5.校内职务表--
CREATE TABLE SYS_SCHOOL_TITLE(
ID VARCHAR2(64),
USER_ID VARCHAR2(64)NOT NULL,
START_DATE DATE NOT NULL,
END_DATE DATE NOT NULL,
JOB_NAME VARCHAR2(100) NOT NULL,
REMARKS VARCHAR2(1000),
CONSTRAINT PK_TITLE_ID PRIMARY KEY(ID),
CONSTRAINT FK_USER_ID_5 FOREIGN KEY(USER_ID) REFERENCES SYS_USER(ID)
);

--6.实践经验表--
CREATE TABLE SYS_PRACTICE_EXPERINCE(									
ID VARCHAR2(64),
USER_ID VARCHAR2(64)NOT NULL,
START_DATE DATE NOT NULL,
END_DATE DATE NOT NULL,
PRACTICE_NAME VARCHAR2(200)NOT NULL,
REMARKS VARCHAR2(1000),
CONSTRAINT PK_PEXPERINCE_ID PRIMARY KEY(ID),
CONSTRAINT FK_USER_ID_6 FOREIGN KEY(USER_ID) REFERENCES SYS_USER(ID)
);

--7.培训经历表--
CREATE TABLE SYS_TRAIN_EXPERIENCE(
ID VARCHAR2(64),
USER_ID VARCHAR2(64)NOT NULL,
START_DATE DATE NOT NULL,
END_DATE DATE NOT NULL,
TRAINING_AGENCY VARCHAR2(200)NOT NULL,
PLACE VARCHAR2(200)NOT NULL,
COURSE VARCHAR2(100)NOT NULL,
CERTIFICATE VARCHAR2(100)NOT NULL,
DETAIL_DESCRIPTION VARCHAR2(1000), 
CONSTRAINT PK_TEXPERENCE_ID PRIMARY KEY(ID),
CONSTRAINT FK_USER_ID_7 FOREIGN KEY(USER_ID) REFERENCES SYS_USER(ID)
);

--8.证书表--
CREATE TABLE SYS_CERTIFICATE(
ID VARCHAR2(64),
USER_ID VARCHAR2(64)NOT NULL,
GAIN_DATE DATE NOT NULL,
CERTIFICATE_NAME VARCHAR2(100)NOT NULL,
GRADE VARCHAR2(100),
CONSTRAINT PK_CERTIFICATE_ID PRIMARY KEY(ID),
CONSTRAINT FK_USER_ID_8 FOREIGN KEY(USER_ID) REFERENCES SYS_USER(ID)
);

--9.附件表--
CREATE TABLE SYS_ATTACHMENT(
ID VARCHAR2(64),
USER_ID VARCHAR2(64)NOT NULL,
ATTACHMENT_NAME VARCHAR2(100)NOT NULL,
PAPER VARCHAR2(100)NOT NULL,
REMARKS VARCHAR2(1000),
CONSTRAINT PK_ATTACHMENT_ID PRIMARY KEY(ID),
CONSTRAINT FK_USER_ID_9 FOREIGN KEY(USER_ID) REFERENCES SYS_USER(ID)
);

-- 用户-角色
CREATE TABLE sys_user_role
(
	user_id varchar2(64) NOT NULL,
	role_id varchar2(64) NOT NULL,
	PRIMARY KEY (user_id, role_id)
);



/* Create Indexes */

CREATE INDEX sys_area_parent_id ON sys_area (parent_id);
CREATE INDEX sys_area_parent_ids ON sys_area (parent_ids);
CREATE INDEX sys_area_del_flag ON sys_area (del_flag);
CREATE INDEX sys_dict_value ON sys_dict (value);
CREATE INDEX sys_dict_label ON sys_dict (label);
CREATE INDEX sys_dict_del_flag ON sys_dict (del_flag);
CREATE INDEX sys_log_create_by ON sys_log (create_by);
CREATE INDEX sys_log_request_uri ON sys_log (request_uri);
CREATE INDEX sys_log_type ON sys_log (type);
CREATE INDEX sys_log_create_date ON sys_log (create_date);
CREATE INDEX sys_mdict_parent_id ON sys_mdict (parent_id);
CREATE INDEX sys_mdict_parent_ids ON sys_mdict (parent_ids);
CREATE INDEX sys_mdict_del_flag ON sys_mdict (del_flag);
CREATE INDEX sys_menu_parent_id ON sys_menu (parent_id);
CREATE INDEX sys_menu_parent_ids ON sys_menu (parent_ids);
CREATE INDEX sys_menu_del_flag ON sys_menu (del_flag);
CREATE INDEX sys_office_parent_id ON sys_office (parent_id);
CREATE INDEX sys_office_parent_ids ON sys_office (parent_ids);
CREATE INDEX sys_office_del_flag ON sys_office (del_flag);
CREATE INDEX sys_office_type ON sys_office (type);
CREATE INDEX sys_role_del_flag ON sys_role (del_flag);
CREATE INDEX sys_role_enname ON sys_role (enname);
CREATE INDEX sys_user_office_id ON sys_user (office_id);
CREATE INDEX sys_user_login_name ON sys_user (login_name);
CREATE INDEX sys_user_company_id ON sys_user (company_id);
CREATE INDEX sys_user_update_date ON sys_user (update_date);
CREATE INDEX sys_user_del_flag ON sys_user (del_flag);



