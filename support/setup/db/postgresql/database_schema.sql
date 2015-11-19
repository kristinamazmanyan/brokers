
CREATE DATABASE springjsfdemo;
CREATE USER springjsfdemo WITH password 'springjsfdemopass';
GRANT ALL privileges ON DATABASE springjsfdemo TO springjsfdemo;

-------------- permission -----------------
CREATE TABLE permission (
  id bigint NOT NULL,
  name character varying(70) NOT NULL,
  description text
);
ALTER TABLE public.permission OWNER TO springjsfdemo;

ALTER TABLE ONLY permission
ADD CONSTRAINT permission_pkey PRIMARY KEY (id);

CREATE INDEX permission_name_idx ON permission USING btree (name);

-------------- role -----------------
CREATE TABLE role (
	id bigint NOT NULL,
	name character varying(70) NOT NULL,
	description TEXT NULL,
	created_by bigint NOT NULL,
	created_at timestamp NOT NULL,
	changed_by bigint NOT NULL,
	changed_at timestamp NOT NULL
);
ALTER TABLE public.role OWNER TO springjsfdemo;
ALTER TABLE ONLY role
ADD CONSTRAINT role_pkey PRIMARY KEY (id);

CREATE INDEX role_name_idx ON role USING btree (name);

-------------- role_permissions -----------------
CREATE TABLE role_permissions (
	role_id bigint NOT NULL,
	permission_id bigint NOT NULL
);
ALTER TABLE public.role_permissions OWNER TO springjsfdemo;

ALTER TABLE ONLY role_permissions
ADD CONSTRAINT role_permissions_pkey PRIMARY KEY (role_id, permission_id);


ALTER TABLE ONLY role_permissions
ADD CONSTRAINT role_id_permission_id_uidx UNIQUE (role_id, permission_id);

CREATE INDEX role_permissions_permission_id_idx ON role_permissions USING btree (permission_id);

-------------- user -----------------
CREATE TABLE "user" (
	id bigint NOT NULL,
	login character varying(100) NOT NULL,
	password character varying(100) NOT NULL,
	full_name character varying(1000) NULL,
	email character varying(128) NULL DEFAULT NULL,
	phone character varying(128) NULL DEFAULT NULL,
	last_login timestamp NULL DEFAULT NULL,
	deleted boolean NOT NULL DEFAULT false,
	created_by bigint NOT NULL,
	created_at timestamp NOT NULL,
	changed_by bigint NOT NULL,
	changed_at timestamp NOT NULL
);
ALTER TABLE public.user OWNER TO springjsfdemo;

ALTER TABLE ONLY "user"
ADD CONSTRAINT user_pkey PRIMARY KEY (id);


ALTER TABLE ONLY "user"
ADD CONSTRAINT user_login_uidx UNIQUE (login);

CREATE INDEX user_deleted_idx ON "user" USING btree (deleted);

-------------- user_roles -----------------
CREATE TABLE user_roles (
	user_id bigint NOT NULL,
	role_id bigint NOT NULL
);
ALTER TABLE public.user_roles OWNER TO springjsfdemo;


ALTER TABLE ONLY user_roles
ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);


ALTER TABLE ONLY user_roles
ADD CONSTRAINT user_roles_uidx UNIQUE (user_id, role_id);

CREATE INDEX user_roles_role_id_idx ON user_roles USING btree (role_id);


-------------- event -----------------

CREATE TABLE event (
	id bigint NOT NULL,
	event_type bigint NOT NULL,
	message TEXT NULL,
	performed_by bigint,
	performed_on character varying(40),
	created_at timestamp NOT NULL
);
ALTER TABLE public.event OWNER TO springjsfdemo;

ALTER TABLE ONLY event
ADD CONSTRAINT event_pkey PRIMARY KEY (id);

CREATE INDEX event_event_type_idx ON event USING btree (event_type);
CREATE INDEX event_performed_by_idx ON event USING btree (performed_by);
CREATE INDEX event_created_at_idx ON event USING btree (created_at);

CREATE SEQUENCE event_sequence START 1;

-------------------rf_exam_type------------

CREATE TABLE rf_exam_type (
  id bigint NOT NULL,
  name character varying(100) NOT NULL
);

ALTER TABLE public.rf_exam_type OWNER TO springjsfdemo;

ALTER TABLE ONLY rf_exam_type
ADD CONSTRAINT exam_type_pkey PRIMARY KEY (id);

-------------------rf_exam_type------------

CREATE TABLE rf_exam_time (
  id bigint NOT NULL,
  time bigint NOT NULL,
  q_count bigint NOT NULL
);

ALTER TABLE public.rf_exam_time OWNER TO springjsfdemo;

ALTER TABLE ONLY rf_exam_time
ADD CONSTRAINT exam_time_pkey PRIMARY KEY (id);


-------------------rf_exam_condition------------

CREATE TABLE rf_exam_condition (
  id bigint NOT NULL,
  name character varying(100) NOT NULL
);

ALTER TABLE public.rf_exam_condition OWNER TO springjsfdemo;

ALTER TABLE ONLY rf_exam_condition
ADD CONSTRAINT exam_condition_pkey PRIMARY KEY (id);

-------------------exams------------

CREATE TABLE exams (
	id bigint NOT NULL,
	name character varying(400) NOT NULL,
	type bigint NOT NULL,
	start_date date NOT NULL,
	status bigint NOT NULL,
	archived bigint NOT NULL,
	usermod character varying(50) NOT NULL,
	datemod timestamp NOT NULL
);

ALTER TABLE public.exams OWNER TO springjsfdemo;

ALTER TABLE ONLY exams
ADD CONSTRAINT exams_pkey PRIMARY KEY (id);

CREATE SEQUENCE exam_sequence START 10;

-------------------committee_members------------
CREATE TABLE committee_members (
	id bigint NOT NULL,
	first_name character varying(100) NOT NULL,
	position_type bigint NOT NULL,
	exam_id bigint NOT NULL,
	last_name character varying(100) NOT NULL,
	usermod character varying(50) NOT NULL,
	datemod timestamp NOT NULL
);

ALTER TABLE public.committee_members OWNER TO springjsfdemo;

ALTER TABLE ONLY committee_members
ADD CONSTRAINT cm_pkey PRIMARY KEY (id);

CREATE SEQUENCE cm_sequence START 10;

-------------------rf_examinator_position-----------------

CREATE TABLE rf_examinator_position (
	id bigint NOT NULL,
	name character varying(50) NOT NULL
);

ALTER TABLE public.rf_examinator_position OWNER TO springjsfdemo;

ALTER TABLE ONLY rf_examinator_position
ADD CONSTRAINT examinator_pos_pkey PRIMARY KEY (id);

--------------------exam_members---------------------

CREATE TABLE exam_members (
	id bigint NOT NULL,
	first_name character varying(100) NOT NULL,
	last_name character varying(100) NOT NULL,
	surname character varying(100),
	quiz_end_time timestamp NULL DEFAULT NULL,
	psn character varying(14) NOT NULL,
	exam_id bigint NOT NULL,
	status bigint NOT NULL,
	session_id CHAR(9) NOT NULL,
	usermod character varying(50) NOT NULL,
	datemod timestamp NOT NULL
);

ALTER TABLE public.exam_members OWNER TO springjsfdemo;

ALTER TABLE ONLY exam_members
ADD CONSTRAINT exam_members_pkey PRIMARY KEY (id);

CREATE SEQUENCE exam_members_sequence START 10;

-------------------exam_questions---------------------------

CREATE TABLE exam_questions (
	id bigint NOT NULL,
	exam_id bigint NOT NULL,
	sector_id bigint NOT NULL,
	type bigint NOT NULL,
	question character varying(5000) NOT NULL,
	answer_a character varying(5000) NOT NULL,
	answer_b character varying(5000) NOT NULL,
	answer_c character varying(5000) NOT NULL,
	answer_d character varying(5000) NOT NULL,
	right_answer bigint NOT NULL,
	quest_index bigint NOT NULL
);
ALTER TABLE public.exam_questions OWNER TO springjsfdemo;

ALTER TABLE ONLY exam_questions
ADD CONSTRAINT exam_questions_pkey PRIMARY KEY (id);

CREATE SEQUENCE exam_questions_sequence START 1;

-------------------exam_sectors---------------------------

CREATE TABLE exam_sectors (
	id bigint NOT NULL,
	exam_id bigint NOT NULL,
	sector1 bigint NOT NULL,
	sector2 bigint NOT NULL,
	sector3 bigint NOT NULL,
	sector4 bigint NOT NULL,
	sector5 bigint NOT NULL
);
ALTER TABLE public.exam_sectors OWNER TO springjsfdemo;

ALTER TABLE ONLY exam_sectors
ADD CONSTRAINT exam_sectors_pkey PRIMARY KEY (id);

CREATE SEQUENCE exam_sectors_sequence START 1;

-------------------quiz_results---------------------------

CREATE TABLE quiz_results (
	id bigint NOT NULL,
	exam_id bigint NOT NULL,
	right_answer bigint NOT NULL,
	em_id bigint NOT NULL,
	question_id bigint NOT NULL,
	answer bigint NOT NULL
);

ALTER TABLE public.quiz_results OWNER TO springjsfdemo;

ALTER TABLE ONLY quiz_results
ADD CONSTRAINT quiz_results_pkey PRIMARY KEY (id);

CREATE SEQUENCE quiz_results_sequence START 1;


---------------quiz_final_result--------------------
 CREATE TABLE quiz_final_result (
	id bigint NOT NULL,
	exam_id bigint NOT NULL,
	em_id bigint NOT NULL,
	percent bigint NOT NULL
)
ALTER TABLE public.quiz_final_result OWNER TO springjsfdemo;

ALTER TABLE ONLY quiz_final_result
ADD CONSTRAINT quiz_final_result_pkey PRIMARY KEY (id);

CREATE SEQUENCE quiz_final_result_sequence START 1;

--------------- foreign keys


ALTER TABLE ONLY role_permissions
ALTER TABLE ONLY role_permissions
ADD CONSTRAINT role_permissions_role_id_fk FOREIGN KEY (role_id) REFERENCES role(id);

ALTER TABLE ONLY role_permissions
ADD CONSTRAINT role_permissions_permission_id_fk FOREIGN KEY (permission_id) REFERENCES permission(id);


ALTER TABLE ONLY user_roles
ADD CONSTRAINT user_roles_user_id_fk FOREIGN KEY (user_id) REFERENCES "user"(id);

ALTER TABLE ONLY user_roles
ADD CONSTRAINT user_roles_role_id_fk FOREIGN KEY (role_id) REFERENCES role(id);