INSERT INTO user VALUES (1, 'rsjung@nablecomm.com','rsjung','qwer12','user');
INSERT INTO user VALUES (2, 'yoyo@nablecomm.com','yoyo','qwer12','user');
INSERT INTO user VALUES (4, 'jane@nablecomm.com','jane','qwer12','user');
--
--

INSERT INTO board(idx, email, title, upload_date, updated_date, read_count, image_path, content, user_idx) VALUES (2,'jane@nablecomm.com','test2','2016-10-25 08:02:12.501',NULL,3,NULL,'test2',4);
INSERT INTO board(idx, email, title, upload_date, updated_date, read_count, image_path, content, user_idx) VALUES (3,'jane@nablecomm.com','test3','2016-10-25 08:02:12.501',NULL,3,NULL,'test3',4);
INSERT INTO board(idx, email, title, upload_date, updated_date, read_count, image_path, content, user_idx) VALUES (4,'jane@nablecomm.com','test4','2016-10-25 08:02:12.501',NULL,3,NULL,'test4',4);
INSERT INTO board(idx, email, title, upload_date, updated_date, read_count, image_path, content, user_idx) VALUES (5,'jane@nablecomm.com','test5','2016-10-25 08:02:12.501',NULL,3,NULL,'test5',4);
INSERT INTO board(idx, email, title, upload_date, updated_date, read_count, image_path, content, user_idx) VALUES (6,'jane@nablecomm.com','test6','2016-10-25 08:02:12.501',NULL,3,NULL,'test6',4);
INSERT INTO board(idx, email, title, upload_date, updated_date, read_count, image_path, content, user_idx) VALUES (7,'jane@nablecomm.com','test7','2016-10-25 08:02:12.501',NULL,3,NULL,'test7',4);
INSERT INTO board(idx, email, title, upload_date, updated_date, read_count, image_path, content, user_idx) VALUES (8,'jane@nablecomm.com','test8','2016-10-25 08:02:12.501',NULL,3,NULL,'test8',4);
INSERT INTO board(idx, email, title, upload_date, updated_date, read_count, image_path, content, user_idx) VALUES (9,'jane@nablecomm.com','test9','2016-10-25 08:02:12.501',NULL,3,NULL,'test9',4);
INSERT INTO board(idx, email, title, upload_date, updated_date, read_count, image_path, content, user_idx) VALUES (10,'jane@nablecomm.com','test10','2016-10-25 08:02:12.501',NULL,3,NULL,'test10',4);

INSERT INTO `comment` VALUES (1,'test1','jane@nablecomm.com',0,'2016-10-25 08:02:12.501',10);
INSERT INTO `comment` VALUES (2,'2','jane@nablecomm.com',0,'2016-10-25 08:02:12.501',10);
INSERT INTO `comment` VALUES (3,'3','jane@nablecomm.com',0,'2016-10-25 08:02:12.501',10);

INSERT INTO `comment` VALUES (4,'test1','jane@nablecomm.com',0,'2016-10-25 08:02:12.501',9);
INSERT INTO `comment` VALUES (5,'2','jane@nablecomm.com',0,'2016-10-25 08:02:12.501',9);
INSERT INTO `comment` VALUES (6,'3','jane@nablecomm.com',0,'2016-10-25 08:02:12.501',9);

INSERT INTO `comment` VALUES (7,'test1','jane@nablecomm.com',0,'2016-10-25 08:02:12.501',8);
INSERT INTO `comment` VALUES (8,'2','jane@nablecomm.com',0,'2016-10-25 08:02:12.501',8);
INSERT INTO `comment` VALUES (9,'3','jane@nablecomm.com',0,'2016-10-25 08:02:12.501',8);