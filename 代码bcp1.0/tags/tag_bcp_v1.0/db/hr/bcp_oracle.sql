DROP TABLE hr_retire PURGE;
DROP TABLE hr_user_employee PURGE;
DROP TABLE hr_edu PURGE;
DROP TABLE hr_train_experience PURGE;
DROP TABLE hr_teacher_qualification PURGE;
DROP TABLE hr_personnel_agency PURGE;
DROP TABLE hr_pro_tech_position PURGE;
DROP TABLE hr_post PURGE;
DROP TABLE hr_quit PURGE;
DROP TABLE hr_recruiement PURGE;
DROP TABLE hr_insuance_rule PURGE;
DROP TABLE hr_degree_edu PURGE;
DROP TABLE hr_insured_situation PURGE;
DROP TABLE hr_insurance_record PURGE;
DROP TABLE hr_assessment PURGE;
DROP TABLE hr_retire_contact PURGE;
DROP TABLE hr_attendance PURGE;
DROP TABLE hr_audit_records PURGE;
DROP TABLE hr_insurance_transfer PURGE;
DROP TABLE hr_employ PURGE;
DROP TABLE hr_loan_records PURGE;
drop table HR_SAFE_QUESTION PURGE;

--1.离退休表--
CREATE TABLE hr_retire (
	ID varchar2(64),
	employee_id varchar2(64),
	gender char(1),
	nation char(2),
	native_place varchar2(255),
	birth_date date,
	retire_type char(1),
	retire_position char(2),
	retire_station char(2),
	treatment char(1),
	office_id varchar2(64),
	retire_date date,
	in_work_date date,
	politics_status char(2),
	joined_date date,
	edu_background char(1),
	DEGREE char(1),
	tech_position char(2),
	physical_condition varchar2(255),
	selfcare_ability char(1),
	is_living_along char(1),
	spouse_status varchar2(255),
	address varchar2(255),
	amply_place varchar2(255),
	dorm_area char(1),
	die_date date,
	del_flag char(1),
	CONSTRAINT PK_retire_ID PRIMARY KEY(ID)
);


--2.职员表--
CREATE TABLE hr_user_employee (
	ID VARCHAR2 (64),
	user_no VARCHAR2 (100),
	gender CHAR (1),
	birth_date DATE,
	nation CHAR (2),
	native_place VARCHAR2 (100),
	born_place VARCHAR2 (255),
	papers_type CHAR (1),
	papers_number VARCHAR2 (18),
	place_domicile VARCHAR2 (255),
	marital_status CHAR (1),
	politics_status CHAR (2),
	add_time DATE,
	in_work_date DATE,
	in_school_date DATE,
	engaging_date DATE,
	school_age_full NUMBER,
	school_age NUMBER,
	update_date DATE,
	job_type CHAR (1),
	school_job_type CHAR (1),
	self_identity char (1),
	staff_type CHAR (1),
	establishment_situation char(1),
	staffing_agent CHAR (1),
	mobile VARCHAR2 (11),
	phone VARCHAR2 (50),
	qq VARCHAR2 (15),
	family_address VARCHAR2 (100),
	postcode VARCHAR2 (6),
	personal_homepage CLOB,
	Personal_PROFILE VARCHAR2 (1000),
	learning_edge_rafters CHAR (1),
	incumbency_status CHAR (1),
	social_security_id VARCHAR2 (100),
	agent_id VARCHAR2 (100),
	agent_into_date DATE,
	del_flag char (1),
	is_only_child char(1),
	is_part_time char(1),
	CONSTRAINT PK_employee_ID PRIMARY KEY(ID)
);


--3.教育情况表--
CREATE TABLE hr_edu (
	ID VARCHAR2 (64),
	employee_id VARCHAR2 (64),
	start_date DATE,
	end_date DATE,
	edu_background CHAR (1),
	edu_background_date DATE,
	DEGREE CHAR (1),
	get_degree_date DATE,
	graduate_school VARCHAR2 (100),
	specialty VARCHAR2 (100),
	research_area VARCHAR2 (100),
	study_type CHAR (1),
	is_overseas CHAR (1),
	remarks VARCHAR2 (1000),
	del_flag char(1),
	CONSTRAINT PK_hedu_ID PRIMARY KEY(ID)
);


--4.培训经历表--
CREATE TABLE hr_train_experience (
	ID VARCHAR2 (64),
	employee_id VARCHAR2 (64),
	training_type CHAR (2),
	training_cycle CHAR (1),
	is_home CHAR (1),
	training_agency VARCHAR2 (100),
	training_place VARCHAR2 (100),
	training_course VARCHAR2 (100),
	training_content VARCHAR2 (512),
	start_date DATE,
	end_date DATE,
	training_mode CHAR (1),
	has_certificate CHAR (1),
	certificate_id VARCHAR2 (64),
	training_etc CLOB,
	training_funds NUMBER,
	funds_origin CHAR (1),
	leave_date DATE,
	return_date DATE,
	loan NUMBER,
	loan_date DATE,
	wipeout NUMBER,
	wipeout_date DATE,
	wipeout_detail CLOB,
	school_score NUMBER,
	network_score NUMBER,
	course_score NUMBER,
	del_flag CHAR (1),
	CONSTRAINT PK_hexperience_ID PRIMARY KEY(ID)
);


--5.教师资格认证表--
CREATE TABLE hr_teacher_qualification (
	ID VARCHAR2 (64),
	employee_id VARCHAR2 (64),
	qualifications_type CHAR (1),
	certificate_id VARCHAR2 (64),
	gain_date DATE,
	auditing_status CHAR (1),
	auditing_date DATE,
	comments VARCHAR2 (100),
	del_flag CHAR (1),
	certifying_body varchar2(100),
	application_unit varchar2(100),
	CONSTRAINT PK_qualification_ID PRIMARY KEY(ID)
);


--6.人事代理表--
CREATE TABLE hr_personnel_agency (
	ID VARCHAR2 (64),
	employee_id VARCHAR2 (64),
	archives_is_complete CHAR (1),
	missing_material VARCHAR2 (255),
	archives_list varchar2 (255),
	comments VARCHAR2 (1000),
	del_flag CHAR (1),
	CONSTRAINT PK_agency_ID PRIMARY KEY(ID)
);


--7.职称情况表--
CREATE TABLE hr_pro_tech_position (
	ID VARCHAR2 (64),
	employee_id VARCHAR2 (64),
	tech_position_type CHAR (1),
	tech_position_level CHAR (1),
	NAME CHAR (2),
	gain_data DATE,
	appoint_date DATE,
	update_date date,
	del_flag CHAR (1),
	CONSTRAINT PK_position_ID PRIMARY KEY(ID)
);


--8.岗位职数表--
CREATE TABLE hr_post (
	ID VARCHAR2 (64),
	office_id VARCHAR2 (64),
	post_level CHAR (1),
	post_name VARCHAR2 (64),
	comments VARCHAR2 (255),
	del_flag char(1),
	position_setting varchar2(100),
	post_number_setting number,
	recruit_num number,
	CONSTRAINT PK_post_ID PRIMARY KEY(ID)
);


--9.离职表--
CREATE TABLE hr_quit (
	ID VARCHAR2 (64),
	employee_id VARCHAR2 (64),
	application_date date,
	application varchar2(1000),
	quit_date DATE,
	quit_type CHAR (1),
	quit_where VARCHAR2 (64),
	break_contract VARCHAR2 (64),
	return_the_training_fee NUMBER ,
	social_insurance_backpay NUMBER ,
	other_fee NUMBER,
	salary_stop_date DATE,
	social_insurance_stop_date DATE,
	archives_transfer_type CHAR (1),
	archives_transfer_date DATE,
	del_flag CHAR (1),
	CONSTRAINT PK_quit_ID PRIMARY KEY(ID)
);

--10.岗位聘任管理表--
create table hr_recruiement(
id varchar2(64) not null,
employee varchar2(64) not null,
office char(1),
duties varchar2(512),
post_type char(1),
post_level char(1),
professional_level char(1),
duties_level char(1),
try_out char(1),
engage_situation char(1),
start_date date,
end_date date,
is_sign_contract char(1),
update_date date,
del_flag char(1),
CONSTRAINT PK_recruiement_ID PRIMARY KEY(ID)
);

--11.险种规则表--
create table hr_insuance_rule(
id varchar2(64) not null,
insurance_type char(1),
unit_proportion number,
people_proportion number,
del_flag char(1),
CONSTRAINT PK_rule_ID PRIMARY KEY(ID)
);

--12.学历教育表--
create table hr_degree_edu(
id	varchar2(64) not null,
employee_id	varchar2(64) not null,
edu_background	char(1),
degree	char(1),
edu_mode	char(1),
edu_type	char(1),
is_offjob	char(1),
sign_agreement	char(2),
age_limit	char(1),
school	varchar2(100),
major	varchar2(100),
research_area	varchar2(100),
start_date	date,
end_date	date,
is_confirm	char(1),	
tuition	number,
loan number,
loan_date	date,
wipeout	number,
wipeout_date	date,
has_salary	char(1),
recover_salary_date	date,	
is_delay	char(1),
get_certificate_date	date,
diploma_num	varchar2(64),
degree_num	varchar2(64),
comments	varchar2(1000),
del_flag char(1),
CONSTRAINT PK_rdegreeedu_ID PRIMARY KEY(ID)
);

--13.参保情况表--
create table hr_insured_situation(
id	varchar2(64) not null,
social_insurance_id	varchar2(64) not null,
insurance_type char(1),
insured_date date,
school_insured_date date,
insured_year char(2),
base number,
del_flag char(1),
CONSTRAINT PK_situation_ID PRIMARY KEY(ID)
);

--14.参保记录表--
create table hr_insurance_record(
id varchar2(64),
employee_id varchar2(64),
insured_type char(1),
unit_payment number,
people_payment number,
payment_date date,
del_flag char(1),
comments varchar2(1000),
CONSTRAINT PK_record_ID PRIMARY KEY(ID)
);

--15.考核表--
create table hr_assessment(
id	varchar2(64) not null,
employee_id	varchar2(64) not null,
assessment_year	char(4),
assessment_level	char(1),
no_checkrise	varchar2(255),
comments	varchar2(1000),
del_flag char(1),
CONSTRAINT PK_assessment_ID PRIMARY KEY(ID)
);


--16.离退休人员联系表--
create table hr_retire_contact(
id	varchar2(64) not null,
employee_id	varchar2(64) not null,
retire_name	varchar2(100),
relation	char(1),
retire_type	char(1),
retire_mode	varchar2(100),
del_flag char(1),
CONSTRAINT PK_contact_ID PRIMARY KEY(ID)
);


--17.考勤表--
create table hr_attendance(
id varchar2(64),
employee_id varchar2(64),
apply_date date,
start_date date,
end_date date,
leave_type char(1),
leave_reason varchar2(255),
leave_status varchar2(255),
report_date date,
del_flag char(1),
CONSTRAINT PK_attendance_ID PRIMARY KEY(ID)
);

--18.审核记录表--
create table hr_audit_records(
id varchar2(64) not null,
business_id varchar2(64),
record_id varchar2(64),
audit_opinion varchar2(64),
auditor varchar2(64),
audit_date date,
leave_status char(1),
del_flag char(1),
CONSTRAINT PK_records_ID PRIMARY KEY(ID)
);

--19.社保转移情况表--
create table hr_insurance_transfer(
id varchar2(64),
employee_id varchar2(64),
insurance_id varchar2(64),
start_date date,
end_date date,
original_insured_unit varchar2(100),
del_flag char(1),
CONSTRAINT PK_transfer_ID PRIMARY KEY(ID)
);

--20.招聘表--
create table hr_employ(
id varchar2(64),
user_id	varchar2(64),
declare_position_number	char(4),
post_type	char(1),
name	varchar2(64),
ID_card_number	varchar2(64),
gender	char(1),
birth_date	date,
place_domicile	varchar2(255),
politics_status	char(2),
nation	char(2),
marital_status	char(1),
highest_edu_background	char(1),
highest_degree	char(1),
highest_graduate_school	varchar2(255),
highest_major	varchar2(255),
highest_start_date	date,
highest_end_date	date,
highest_degree_start_date	date,
highest_degree_end_date	date,
first_edu_background	char(1),
first_degree	char(1),
first_graduate_school	varchar2(255),
first_major	varchar2(255),
first_start_date	date,
first_end_date	date,
first_degree_start_date	date,
first_degree_end_date	date,
is_work_experience	char(1),
working_unit	varchar2(255),
working_post	varchar2(255),
in_work_date	date,
qualifications	varchar2(255),
tech_position	varchar2(255),
personal_profile 	varchar2(2000),
family_status	varchar2(512),
comments	varchar2(1000),
auditing_status	char(1),
mobile	varchar2(20),						
correspondence_address	varchar2(64),						
email	varchar2(30),						
fixed_phone varchar2(20),	
apply_date date,
update_date date,
audit_date date,	
audit_comments varchar2(1000),				
CONSTRAINT PK_employ_ID PRIMARY KEY(ID)
);

--21.借款记录表--
create table hr_loan_records(
id varchar2(64) not null,
business_id varchar2(64),
record_id varchar2(64),
loan_type char(2),
employee_id varchar2(64),
loan_date date,
loan number,
comments varchar2(1000),
del_flag char(1),
CONSTRAINT PK_loan_ID PRIMARY KEY(ID)
);

--22.安全问题表--
CREATE TABLE HR_SAFE_QUESTION (
"ID" VARCHAR2(64 BYTE) not null ,
"FIRST_VERIFICATION_QUESTION" CHAR(2 BYTE) not null ,
"SECOND_VERIFICATION_QUESTION" CHAR(2 BYTE) not null ,
"THIRD_VERIFICATION_QUESTION" CHAR(2 BYTE) not null ,
"CUSTOM_QUESTION" VARCHAR2(255 BYTE) not null ,
"FIRST_ANSWER" VARCHAR2(64 BYTE) not null ,
"SECOND_ANSWER" VARCHAR2(64 BYTE) not null ,
"THIRD_ANSWER" VARCHAR2(64 BYTE) not null ,
"CUSTOM_ANSWER" VARCHAR2(255 BYTE) not null,
CONSTRAINT PK_safe_qusetion_ID PRIMARY KEY(ID) 
);
