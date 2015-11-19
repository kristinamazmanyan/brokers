--roles

INSERT INTO role (id, name, description, created_by, created_at, changed_by, changed_at) VALUES
  (1, 'SUPER_ADMIN', 'superadmin', 1, '2015-09-01 05:00:00', 1, '2015-09-01 05:00:00'),
  (2, 'ADMIN', 'admin', 1, '2015-09-01 05:00:00', 1, '2015-09-01 05:00:00'),
  (3, 'USER', 'user', 1, '2015-09-01 05:00:00', 1, '2015-09-01 05:00:00');

-- permissions

INSERT INTO permission (id, name, description) VALUES
  (1, 'login', 'Permission to login system'),
  (2, 'system_requirements.view', 'Permission to view system requirements'),
  (3, 'users.view_users', 'Permission to view users'),
  (4, 'users.manage_user', 'Permission to manage user'),
  (5, 'event.view_events', 'Permission to view events'),
  (6, 'exam.view_exams', 'Permission to view exams'),
  (7, 'exam.manage_exams', 'Permission to manage exams'),
  (8, 'exam.view_committee_members', 'Permission to view committee members'),
  (9, 'exam.manage_committee_members', 'Permission to manage committee members'),
  (10, 'exam.view_exam_members', 'Permission to view exam members'),
  (11, 'exam.manage_exam_members', 'Permission to manage exam members'),
  (12, 'exam.print_result', 'Permission to print result'),
  (13, 'exam.manage_exam_questions', 'Permission to manage exam questions'),
  (14, 'exam.view_type1', 'Permission to view exam type1'),
  (15, 'exam.view_type2', 'Permission to view exam type2'),
  (16, 'exam.view_type3', 'Permission to view exam type3'),
  (17, 'exam.view_search', 'Permission to view search');

INSERT INTO role_permissions (role_id, permission_id) VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (1, 4),
  (1, 5),
  (2, 1),
  (3, 1);


--users
---- superpadmin - superadmin
---- admin -admin
---- user -user

INSERT INTO "user" (id, login, password, full_name, email, phone, last_login, deleted, created_by, created_at, changed_by, changed_at) VALUES
  (1, 'superadmin', '17c452f6cfd1ab53d8745e84681eb49', 'superadmin', '', '', '2015-09-01 05:00:00', false, 1, '2015-09-01 05:00:00', 1, '2015-09-01 05:00:00'),
  (2, 'admin', '21232f297a57a5a743894ae4a801fc3', 'admin', NULL, NULL, NULL, false, 1, '2015-09-01 05:00:00', 1, '2015-09-01 05:00:00'),
  (3, 'user', 'ee11cbb19052e4b7aac0ca6c23ee', 'user', NULL, NULL, NULL, false, 1, '2015-09-01 05:00:00', 1, '2015-09-01 05:00:00');

INSERT INTO user_roles (user_id, role_id) VALUES
  (1, 1),
  (2, 2),
  (3, 3);

INSERT INTO rf_exam_type (id, name) VALUES
	(1, 'Ներքին աուդիտորի որակավորում'),
	(2, 'Գնումների համակարգողի որակավորում'),
	(3, 'Ներքին աուդիտորի վերապատրաստում');

	INSERT INTO rf_exam_time (id, time, q_count) VALUES
	(1, 120, 50),
	(2, 75, 50),
	(3, 60, 30);

INSERT INTO rf_exam_condition (id, name) VALUES
	(0, 'Ընթացիկ'),
	(1, 'Արխիվացված');

INSERT INTO rf_examinator_position (id, name) VALUES
(0, 'Անդամ'),
(1, 'Նախագահ');









