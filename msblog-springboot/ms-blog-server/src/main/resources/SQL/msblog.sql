/*
 Navicat Premium Data Transfer

 Source Server         : 8.130.33.86
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 8.130.33.86:3306
 Source Schema         : msblog

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 04/03/2024 17:49:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action` (
  `id` int(10) unsigned NOT NULL COMMENT '主键',
  `action_code` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '行为编号',
  `action_name` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '行为名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_action_name` (`action_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='行为表';

-- ----------------------------
-- Records of action
-- ----------------------------
BEGIN;
INSERT INTO `action` VALUES (1, 'INSERT', '添加');
INSERT INTO `action` VALUES (2, 'DELETE', '删除');
INSERT INTO `action` VALUES (3, 'UPDATE', '修改');
INSERT INTO `action` VALUES (4, 'SELECT', '查询');
INSERT INTO `action` VALUES (5, 'APPROVAL', '审批');
INSERT INTO `action` VALUES (6, 'EXPORT', '导出');
INSERT INTO `action` VALUES (7, 'BACKUP', '备份');
INSERT INTO `action` VALUES (8, 'ARCHIVE', '归档');
COMMIT;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `name` varchar(32) NOT NULL COMMENT '真实姓名',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `email` varchar(25) DEFAULT NULL COMMENT '用户邮箱',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别，1男0女',
  `id_number` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `image` varchar(120) DEFAULT NULL COMMENT '头像',
  `role` json NOT NULL COMMENT '角色',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门编号',
  `root` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是超级管理员',
  `status` int(2) DEFAULT '1' COMMENT '账号状态, 1正常 0锁定',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `update_user` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`) USING BTREE,
  UNIQUE KEY `idx_phone` (`phone`) USING BTREE,
  UNIQUE KEY `idx_email` (`email`) USING BTREE,
  UNIQUE KEY `id_number` (`id_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES (10, '木萨·塔布提', 'qarabala1', 'qarabala@126.com', 'e10adc3949ba59abbe56e057f20f883e', '13279455923', '1', '65432219930920191X', 'https://profile-avatar.csdnimg.cn/baec59bf11724c80a6b92a119b10d403_weixin_42519696.jpg!1', '[0]', NULL, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `admin` VALUES (16, '夏尔万·努尔沙哈提', '小夏', 'xiaoxia@126.com', 'e10adc3949ba59abbe56e057f20f883e', '15001156718', '0', '654026199510051385', 'https://profile-avatar.csdnimg.cn/baec59bf11724c80a6b92a119b10d403_weixin_42519696.jpg!1', '[5]', NULL, 0, 1, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for articles
-- ----------------------------
DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博文ID',
  `title` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '博文标题',
  `category_id` bigint(20) NOT NULL COMMENT '博文分类id',
  `tags_id` bigint(20) NOT NULL COMMENT '博文标签id',
  `image` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片路径',
  `summary` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '摘要',
  `content` longtext CHARACTER SET utf8 NOT NULL COMMENT '博文内容',
  `views` bigint(20) DEFAULT NULL COMMENT '浏览量',
  `status` int(2) DEFAULT '1' COMMENT '博文状态 0:禁用，1:启用',
  `comment_count` bigint(20) DEFAULT NULL COMMENT '评论总数',
  `favorite_count` bigint(20) DEFAULT NULL COMMENT '收藏总数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `update_user` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_title` (`title`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='博文表';

-- ----------------------------
-- Records of articles
-- ----------------------------
BEGIN;
INSERT INTO `articles` VALUES (6, '国资云、液冷服务器、数据中心', 19, 13, NULL, NULL, '国资云、液冷服务器、数据中心、华为算力等相关板块也大幅上涨，中公高科、日海智能、中科金财等涨停。ETF涨幅前20位的基金绝大部分与数字经济相关，其中云计算50ETF、大数据ETF、云50ETF等均涨超3%，位居前列。', 232, 1, 1, NULL, '2024-02-22 17:21:03', '2024-02-22 17:21:03', 10, 10);
INSERT INTO `articles` VALUES (7, '国资云', 19, 13, 'https://profile-avatar.csdnimg.cn/baec59bf11724c80a6b92a119b10d403_weixin_42519696.jpg!1', '国资云、液冷服务器22国资云、液冷服务器22国资云、液冷服务器22', '国资云、液冷服务器22', 232, 1, 1, NULL, '2024-02-22 18:21:05', '2024-02-22 22:38:48', 10, 10);
INSERT INTO `articles` VALUES (9, '数据中心', 19, 13, 'https://profile-avatar.csdnimg.cn/baec59bf11724c80a6b92a119b10d403_weixin_42519696.jpg!1', '国资云、液冷服务器22国资云、液冷服务器22国资云、液冷服务器22', '国资云、液冷服务器22', NULL, 1, NULL, NULL, '2024-02-22 21:16:05', '2024-02-22 21:16:05', 10, 10);
INSERT INTO `articles` VALUES (10, '73 天河区天河路', 19, 13, '/home/Administrator/Pictures/img_817967.jpg', 'After comparing data, the window shows the number of records that will be inserted, updated or deleted in the target. To clear or reload various internal caches, flush tables, or acquire locks, control-click your connection in the Navigation pane and select Flush and choose the flush                ', 'Navicat Cloud could not connect and access your databases. By which it means, it could only store your connection settings, queries, model files, and virtual group; your database passwords and data (e.g. tables, views, etc) will not be stored to Navicat Cloud. The Information Pane shows the detailed object information, project activities, the DDL of database objects, object dependencies, membership of users/roles and preview. If the Show objects under schema in navigation pane option is checked at the Preferences window, all database objects are also displayed in the pane. To open a query using an external editor, control-click it and select Open with External Editor. You can set the file path of an external editor in Preferences. The reason why a great man is great is that he resolves to be a great man. In the middle of winter I at last discovered that there was in me an invincible summer. If it scares you, it might be a good thing to try. Navicat Monitor can be installed on any local computer or virtual machine and does not require any software installation on the servers being monitored. Navicat Data Modeler is a powerful and cost-effective database design tool which helps you build high-quality conceptual, logical and physical data models. The reason why a great man is great is that he resolves to be a great man. It wasn’t raining when Noah built the ark. Creativity is intelligence having fun. Navicat provides a wide range advanced features, such as compelling code editing capabilities, smart code-completion, SQL formatting, and more. In the middle of winter I at last discovered that there was in me an invincible summer. Remember that failure is an event, not a person. I will greet this day with love in my heart. Monitored servers include MySQL, MariaDB and SQL Server, and compatible with cloud databases like Amazon RDS, Amazon Aurora, Oracle Cloud, Google Cloud and Microsoft Azure. It provides strong authentication and secure encrypted communications between two hosts, known as SSH Port Forwarding (Tunneling), over an insecure network. HTTP Tunneling is a method for connecting to a server that uses the same protocol (http://) and the same port (port 80) as a web server does. Secure Sockets Layer(SSL) is a protocol for transmitting private documents via the Internet. A query is used to extract data from the database in a readable format according to the user\'s request. Success consists of going from failure to failure without loss of enthusiasm. It collects process metrics such as CPU load, RAM usage, and a variety of other resources over SSH/SNMP. After comparing data, the window shows the number of records that will be inserted, updated or deleted in the target.', 463, 1, 736, 237, '2007-09-09 10:25:35', '2006-05-12 04:37:20', 10, 10);
INSERT INTO `articles` VALUES (11, '101 成华区玉双路6号', 19, 13, '/Users/Administrator/Pictures/img_786881.png', 'What you get by achieving your goals is not as important as what you become by achieving your goals. If the Show objects under schema in navigation pane option is checked at the Preferences window, all database objects are also displayed in the pane.', 'HTTP Tunneling is a method for connecting to a server that uses the same protocol (http://) and the same port (port 80) as a web server does. Anyone who has never made a mistake has never tried anything new. Always keep your eyes open. Keep watching. Because whatever you see can inspire you. All journeys have secret destinations of which the traveler is unaware. To clear or reload various internal caches, flush tables, or acquire locks, control-click your connection in the Navigation pane and select Flush and choose the flush option. You must have the reload privilege to use this feature. SQL Editor allows you to create and edit SQL text, prepare and execute selected queries. Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines and different platforms. It provides strong authentication and secure encrypted communications between two hosts, known as SSH Port Forwarding (Tunneling), over an insecure network. Anyone who has never made a mistake has never tried anything new. If opportunity doesn’t knock, build a door. Anyone who has never made a mistake has never tried anything new. The Navigation pane employs tree structure which allows you to take action upon the database and their objects through their pop-up menus quickly and easily. Sometimes you win, sometimes you learn. Navicat allows you to transfer data from one database and/or schema to another with detailed analytical process. To clear or reload various internal caches, flush tables, or acquire locks, control-click your connection in the Navigation pane and select Flush and choose the flush option. You must have the reload privilege to use this feature. Typically, it is employed as an encrypted version of Telnet. To get a secure connection, the first thing you need to do is to install OpenSSL Library and download Database Source. Navicat 15 has added support for the system-wide dark mode. Champions keep playing until they get it right. You cannot save people, you can just love them. Creativity is intelligence having fun. Instead of wondering when your next vacation is, maybe you should set up a life you don’t need to escape from. If your Internet Service Provider (ISP) does not provide direct access to its server, Secure Tunneling Protocol (SSH) / HTTP is another solution. Success consists of going from failure to failure without loss of enthusiasm. Navicat is a multi-connections Database Administration tool allowing you to connect to MySQL, Oracle, PostgreSQL, SQLite, SQL Server, MariaDB and/or MongoDB databases, making database administration to multiple kinds of database so easy. Navicat Data Modeler is a powerful and cost-effective database design tool which helps you build high-quality conceptual, logical and physical data models. Export Wizard allows you to export data from tables, collections, views, or query results to any available formats. If the plan doesn’t work, change the plan, but never the goal. Navicat provides a wide range advanced features, such as compelling code editing capabilities, smart code-completion, SQL formatting, and more. The Navigation pane employs tree structure which allows you to take action upon the database and their objects through their pop-up menus quickly and easily. Genius is an infinite capacity for taking pains.', 913, 0, 371, 130, '2005-12-12 10:45:50', '2023-12-08 07:11:45', 10, 10);
INSERT INTO `articles` VALUES (12, '428 东城区东单王府井东街', 19, 13, '/home/Administrator/Pictures/img_058260.jpg', 'In a Telnet session, all communications, including username and password, are transmitted in plain-text, allowing anyone to listen-in on your session and steal passwords and other information.', 'I may not have gone where I intended to go, but I think I have ended up where I needed to be. If it scares you, it might be a good thing to try. The repository database can be an existing MySQL, MariaDB, PostgreSQL, SQL Server, or Amazon RDS instance. It wasn’t raining when Noah built the ark. The Navigation pane employs tree structure which allows you to take action upon the database and their objects through their pop-up menus quickly and easily. I will greet this day with love in my heart. I destroy my enemies when I make them my friends. Anyone who has ever made anything of importance was disciplined. Navicat is a multi-connections Database Administration tool allowing you to connect to MySQL, Oracle, PostgreSQL, SQLite, SQL Server, MariaDB and/or MongoDB databases, making database administration to multiple kinds of database so easy. Flexible settings enable you to set up a custom key for comparison and synchronization. Navicat Monitor requires a repository to store alerts and metrics for historical analysis. It is used while your ISPs do not allow direct connections, but allows establishing HTTP connections. All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts for collaboration. The first step is as good as half over. I will greet this day with love in my heart. Actually it is just in an idea when feel oneself can achieve and cannot achieve. Navicat Data Modeler enables you to build high-quality conceptual, logical and physical data models for a wide variety of audiences. In the Objects tab, you can use the List List, Detail Detail and ER Diagram ER Diagram buttons to change the object view. The reason why a great man is great is that he resolves to be a great man. I destroy my enemies when I make them my friends. You can select any connections, objects or projects, and then select the corresponding buttons on the Information Pane. The past has no power over the present moment. Instead of wondering when your next vacation is, maybe you should set up a life you don’t need to escape from. To successfully establish a new connection to local/remote server - no matter via SSL or SSH, set the database login information in the General tab. Navicat Monitor requires a repository to store alerts and metrics for historical analysis. You can select any connections, objects or projects, and then select the corresponding buttons on the Information Pane. The past has no power over the present moment. SSH serves to prevent such vulnerabilities and allows you to access a remote server\'s shell without compromising security. Anyone who has never made a mistake has never tried anything new. Always keep your eyes open. Keep watching. Because whatever you see can inspire you. Secure SHell (SSH) is a program to log in into another computer over a network, execute commands on a remote server, and move files from one machine to another. Navicat provides powerful tools for working with queries: Query Editor for editing the query text directly, and Query Builder, Find Builder or Aggregate Builder for building queries visually. It is used while your ISPs do not allow direct connections, but allows establishing HTTP connections. Navicat Monitor can be installed on any local computer or virtual machine and does not require any software installation on the servers being monitored. The On Startup feature allows you to control what tabs appear when you launch Navicat. The Main Window consists of several toolbars and panes for you to work on connections, database objects and advanced tools. Typically, it is employed as an encrypted version of Telnet. All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts for collaboration. The repository database can be an existing MySQL, MariaDB, PostgreSQL, SQL Server, or Amazon RDS instance. Navicat provides powerful tools for working with queries: Query Editor for editing the query text directly, and Query Builder, Find Builder or Aggregate Builder for building queries visually. Import Wizard allows you to import data to tables/collections from CSV, TXT, XML, DBF and more. Navicat authorizes you to make connection to remote servers running on different platforms (i.e. Windows, macOS, Linux and UNIX), and supports PAM and GSSAPI authentication. The On Startup feature allows you to control what tabs appear when you launch Navicat. The reason why a great man is great is that he resolves to be a great man. To connect to a database or schema, simply double-click it in the pane. You cannot save people, you can just love them. Instead of wondering when your next vacation is, maybe you should set up a life you don’t need to escape from. After logged in the Navicat Cloud feature, the Navigation pane will be divided into Navicat Cloud and My Connections sections. Anyone who has never made a mistake has never tried anything new. Instead of wondering when your next vacation is, maybe you should set up a life you don’t need to escape from. Navicat allows you to transfer data from one database and/or schema to another with detailed analytical process. In other words, Navicat provides the ability for data in different databases and/or schemas to be kept up-to-date so that each repository contains the same information. The reason why a great man is great is that he resolves to be a great man. Sometimes you win, sometimes you learn. It collects process metrics such as CPU load, RAM usage, and a variety of other resources over SSH/SNMP. SSH serves to prevent such vulnerabilities and allows you to access a remote server\'s shell without compromising security. To start working with your server in Navicat, you should first establish a connection or several connections using the Connection window. If opportunity doesn’t knock, build a door. To get a secure connection, the first thing you need to do is to install OpenSSL Library and download Database Source. Navicat authorizes you to make connection to remote servers running on different platforms (i.e. Windows, macOS, Linux and UNIX), and supports PAM and GSSAPI authentication. The reason why a great man is great is that he resolves to be a great man. The Synchronize to Database function will give you a full picture of all database differences. SQL Editor allows you to create and edit SQL text, prepare and execute selected queries. Navicat Data Modeler enables you to build high-quality conceptual, logical and physical data models for a wide variety of audiences. If it scares you, it might be a good thing to try. Navicat Monitor can be installed on any local computer or virtual machine and does not require any software installation on the servers being monitored. Navicat 15 has added support for the system-wide dark mode. In a Telnet session, all communications, including username and password, are transmitted in plain-text, allowing anyone to listen-in on your session and steal passwords and other information. If it scares you, it might be a good thing to try. Navicat is a multi-connections Database Administration tool allowing you to connect to MySQL, Oracle, PostgreSQL, SQLite, SQL Server, MariaDB and/or MongoDB databases, making database administration to multiple kinds of database so easy. To open a query using an external editor, control-click it and select Open with External Editor. You can set the file path of an external editor in Preferences. Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines and different platforms. Success consists of going from failure to failure without loss of enthusiasm. The first step is as good as half over. Typically, it is employed as an encrypted version of Telnet. Navicat Cloud could not connect and access your databases. By which it means, it could only store your connection settings, queries, model files, and virtual group; your database passwords and data (e.g. tables, views, etc) will not be stored to Navicat Cloud.', 200, 0, 715, 856, '2012-03-31 00:28:15', '2001-04-08 05:13:56', 10, 10);
INSERT INTO `articles` VALUES (13, '326 房山区岳琉路', 19, 13, '/Users/Administrator/Pictures/img_423126.png', 'I will greet this day with love in my heart. To successfully establish a new connection to local/remote server - no matter via SSL or SSH, set the database login information in the General tab. HTTP Tunneling is a method for connecting to a server that uses the same protocol (http://)               ', 'The reason why a great man is great is that he resolves to be a great man. I destroy my enemies when I make them my friends. Such sessions are also susceptible to session hijacking, where a malicious user takes over your session once you have authenticated. Always keep your eyes open. Keep watching. Because whatever you see can inspire you. If the plan doesn’t work, change the plan, but never the goal. All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts for collaboration. Navicat provides a wide range advanced features, such as compelling code editing capabilities, smart code-completion, SQL formatting, and more.', 179, 1, 990, 87, '2023-04-27 11:46:26', '2012-05-17 09:49:39', 10, 10);
INSERT INTO `articles` VALUES (14, '664 海珠区江南西路', 19, 13, '/home/Administrator/Pictures/img_311245.png', 'Navicat provides a wide range advanced features, such as compelling code editing capabilities, smart code-completion, SQL formatting, and more. Navicat Cloud could not connect and access your databases. By which it means, it could only store your connection settings, queries, model                  ', 'A man is not old until regrets take the place of dreams. It provides strong authentication and secure encrypted communications between two hosts, known as SSH Port Forwarding (Tunneling), over an insecure network. The first step is as good as half over. You will succeed because most people are lazy. Monitored servers include MySQL, MariaDB and SQL Server, and compatible with cloud databases like Amazon RDS, Amazon Aurora, Oracle Cloud, Google Cloud and Microsoft Azure. Optimism is the one quality more associated with success and happiness than any other. Always keep your eyes open. Keep watching. Because whatever you see can inspire you. Navicat Monitor requires a repository to store alerts and metrics for historical analysis. You cannot save people, you can just love them. Difficult circumstances serve as a textbook of life for people. Navicat Cloud could not connect and access your databases. By which it means, it could only store your connection settings, queries, model files, and virtual group; your database passwords and data (e.g. tables, views, etc) will not be stored to Navicat Cloud. I destroy my enemies when I make them my friends. Instead of wondering when your next vacation is, maybe you should set up a life you don’t need to escape from. The Synchronize to Database function will give you a full picture of all database differences. Navicat 15 has added support for the system-wide dark mode. Success consists of going from failure to failure without loss of enthusiasm. SQL Editor allows you to create and edit SQL text, prepare and execute selected queries. Navicat provides a wide range advanced features, such as compelling code editing capabilities, smart code-completion, SQL formatting, and more. Optimism is the one quality more associated with success and happiness than any other. It provides strong authentication and secure encrypted communications between two hosts, known as SSH Port Forwarding (Tunneling), over an insecure network. Navicat provides powerful tools for working with queries: Query Editor for editing the query text directly, and Query Builder, Find Builder or Aggregate Builder for building queries visually. Navicat provides powerful tools for working with queries: Query Editor for editing the query text directly, and Query Builder, Find Builder or Aggregate Builder for building queries visually. It is used while your ISPs do not allow direct connections, but allows establishing HTTP connections. Difficult circumstances serve as a textbook of life for people. The Navigation pane employs tree structure which allows you to take action upon the database and their objects through their pop-up menus quickly and easily. After comparing data, the window shows the number of records that will be inserted, updated or deleted in the target. Remember that failure is an event, not a person. To successfully establish a new connection to local/remote server - no matter via SSL, SSH or HTTP, set the database login information in the General tab. After comparing data, the window shows the number of records that will be inserted, updated or deleted in the target. Remember that failure is an event, not a person. HTTP Tunneling is a method for connecting to a server that uses the same protocol (http://) and the same port (port 80) as a web server does. Creativity is intelligence having fun. Navicat provides a wide range advanced features, such as compelling code editing capabilities, smart code-completion, SQL formatting, and more. Navicat Data Modeler is a powerful and cost-effective database design tool which helps you build high-quality conceptual, logical and physical data models. There is no way to happiness. Happiness is the way. In a Telnet session, all communications, including username and password, are transmitted in plain-text, allowing anyone to listen-in on your session and steal passwords and other information. Navicat authorizes you to make connection to remote servers running on different platforms (i.e. Windows, macOS, Linux and UNIX), and supports PAM and GSSAPI authentication. If the plan doesn’t work, change the plan, but never the goal. Remember that failure is an event, not a person. What you get by achieving your goals is not as important as what you become by achieving your goals. You cannot save people, you can just love them. Navicat Monitor can be installed on any local computer or virtual machine and does not require any software installation on the servers being monitored. Navicat authorizes you to make connection to remote servers running on different platforms (i.e. Windows, macOS, Linux and UNIX), and supports PAM and GSSAPI authentication. A man is not old until regrets take the place of dreams. Navicat Monitor can be installed on any local computer or virtual machine and does not require any software installation on the servers being monitored. If the plan doesn’t work, change the plan, but never the goal. You will succeed because most people are lazy. Navicat Monitor requires a repository to store alerts and metrics for historical analysis. It provides strong authentication and secure encrypted communications between two hosts, known as SSH Port Forwarding (Tunneling), over an insecure network. Navicat Data Modeler is a powerful and cost-effective database design tool which helps you build high-quality conceptual, logical and physical data models. Navicat is a multi-connections Database Administration tool allowing you to connect to MySQL, Oracle, PostgreSQL, SQLite, SQL Server, MariaDB and/or MongoDB databases, making database administration to multiple kinds of database so easy. Success consists of going from failure to failure without loss of enthusiasm. I will greet this day with love in my heart. To successfully establish a new connection to local/remote server - no matter via SSL or SSH, set the database login information in the General tab. All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts for collaboration. HTTP Tunneling is a method for connecting to a server that uses the same protocol (http://) and the same port (port 80) as a web server does. Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines and different platforms. It can also manage cloud databases such as Amazon Redshift, Amazon RDS, Alibaba Cloud. Features in Navicat are sophisticated enough to provide professional developers for all their specific needs, yet easy to learn for users who are new to database server. You cannot save people, you can just love them. The past has no power over the present moment. SQL Editor allows you to create and edit SQL text, prepare and execute selected queries. You cannot save people, you can just love them. All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts for collaboration. The reason why a great man is great is that he resolves to be a great man. To connect to a database or schema, simply double-click it in the pane. A man’s best friends are his ten fingers. The Main Window consists of several toolbars and panes for you to work on connections, database objects and advanced tools. The Synchronize to Database function will give you a full picture of all database differences. A comfort zone is a beautiful place, but nothing ever grows there. After comparing data, the window shows the number of records that will be inserted, updated or deleted in the target. It can also manage cloud databases such as Amazon Redshift, Amazon RDS, Alibaba Cloud. Features in Navicat are sophisticated enough to provide professional developers for all their specific needs, yet easy to learn for users who are new to database server. Remember that failure is an event, not a person. In a Telnet session, all communications, including username and password, are transmitted in plain-text, allowing anyone to listen-in on your session and steal passwords and other information. Navicat provides a wide range advanced features, such as compelling code editing capabilities, smart code-completion, SQL formatting, and more. Anyone who has ever made anything of importance was disciplined. Optimism is the one quality more associated with success and happiness than any other. In the middle of winter I at last discovered that there was in me an invincible summer. If opportunity doesn’t knock, build a door. Typically, it is employed as an encrypted version of Telnet. Navicat Data Modeler enables you to build high-quality conceptual, logical and physical data models for a wide variety of audiences. If opportunity doesn’t knock, build a door. Anyone who has ever made anything of importance was disciplined. All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts for collaboration. In the Objects tab, you can use the List List, Detail Detail and ER Diagram ER Diagram buttons to change the object view. In the Objects tab, you can use the List List, Detail Detail and ER Diagram ER Diagram buttons to change the object view. To open a query using an external editor, control-click it and select Open with External Editor. You can set the file path of an external editor in Preferences.', 82, 1, 71, 527, '2018-11-19 16:09:54', '2000-10-30 22:39:42', 10, 10);
INSERT INTO `articles` VALUES (15, '308 龙岗区布吉镇西环路', 19, 13, '/Users/Administrator/Pictures/img_735379.png', 'What you get by achieving your goals is not as important as what you become by achieving your goals. To successfully establish a new connection to local/remote server - no matter via SSL or SSH, set the database login information in the General tab.', 'A comfort zone is a beautiful place, but nothing ever grows there. The reason why a great man is great is that he resolves to be a great man. Anyone who has ever made anything of importance was disciplined. Navicat is a multi-connections Database Administration tool allowing you to connect to MySQL, Oracle, PostgreSQL, SQLite, SQL Server, MariaDB and/or MongoDB databases, making database administration to multiple kinds of database so easy. Anyone who has ever made anything of importance was disciplined. Navicat Monitor is a safe, simple and agentless remote server monitoring tool that is packed with powerful features to make your monitoring effective as possible. Navicat Cloud could not connect and access your databases. By which it means, it could only store your connection settings, queries, model files, and virtual group; your database passwords and data (e.g. tables, views, etc) will not be stored to Navicat Cloud. To clear or reload various internal caches, flush tables, or acquire locks, control-click your connection in the Navigation pane and select Flush and choose the flush option. You must have the reload privilege to use this feature. A man’s best friends are his ten fingers. The first step is as good as half over. If the plan doesn’t work, change the plan, but never the goal. Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines and different platforms. Navicat Monitor is a safe, simple and agentless remote server monitoring tool that is packed with powerful features to make your monitoring effective as possible. Optimism is the one quality more associated with success and happiness than any other. The Navigation pane employs tree structure which allows you to take action upon the database and their objects through their pop-up menus quickly and easily. After comparing data, the window shows the number of records that will be inserted, updated or deleted in the target. To successfully establish a new connection to local/remote server - no matter via SSL or SSH, set the database login information in the General tab. You can select any connections, objects or projects, and then select the corresponding buttons on the Information Pane. You must be the change you wish to see in the world. Navicat Cloud could not connect and access your databases. By which it means, it could only store your connection settings, queries, model files, and virtual group; your database passwords and data (e.g. tables, views, etc) will not be stored to Navicat Cloud. I may not have gone where I intended to go, but I think I have ended up where I needed to be. You cannot save people, you can just love them. After comparing data, the window shows the number of records that will be inserted, updated or deleted in the target. To get a secure connection, the first thing you need to do is to install OpenSSL Library and download Database Source. A query is used to extract data from the database in a readable format according to the user\'s request. A query is used to extract data from the database in a readable format according to the user\'s request. The Navigation pane employs tree structure which allows you to take action upon the database and their objects through their pop-up menus quickly and easily. I destroy my enemies when I make them my friends. With its well-designed Graphical User Interface(GUI), Navicat lets you quickly and easily create, organize, access and share information in a secure and easy way. I may not have gone where I intended to go, but I think I have ended up where I needed to be. HTTP Tunneling is a method for connecting to a server that uses the same protocol (http://) and the same port (port 80) as a web server does. Secure Sockets Layer(SSL) is a protocol for transmitting private documents via the Internet. HTTP Tunneling is a method for connecting to a server that uses the same protocol (http://) and the same port (port 80) as a web server does. Secure SHell (SSH) is a program to log in into another computer over a network, execute commands on a remote server, and move files from one machine to another. In the middle of winter I at last discovered that there was in me an invincible summer. Typically, it is employed as an encrypted version of Telnet. To open a query using an external editor, control-click it and select Open with External Editor. You can set the file path of an external editor in Preferences. The Information Pane shows the detailed object information, project activities, the DDL of database objects, object dependencies, membership of users/roles and preview. Secure Sockets Layer(SSL) is a protocol for transmitting private documents via the Internet. You must be the change you wish to see in the world. A man’s best friends are his ten fingers. All journeys have secret destinations of which the traveler is unaware. Sometimes you win, sometimes you learn. A man’s best friends are his ten fingers. Genius is an infinite capacity for taking pains. All journeys have secret destinations of which the traveler is unaware. Import Wizard allows you to import data to tables/collections from CSV, TXT, XML, DBF and more. The past has no power over the present moment. Navicat Data Modeler enables you to build high-quality conceptual, logical and physical data models for a wide variety of audiences. Instead of wondering when your next vacation is, maybe you should set up a life you don’t need to escape from. If opportunity doesn’t knock, build a door. To clear or reload various internal caches, flush tables, or acquire locks, control-click your connection in the Navigation pane and select Flush and choose the flush option. You must have the reload privilege to use this feature. A comfort zone is a beautiful place, but nothing ever grows there. In the Objects tab, you can use the List List, Detail Detail and ER Diagram ER Diagram buttons to change the object view. In the middle of winter I at last discovered that there was in me an invincible summer. With its well-designed Graphical User Interface(GUI), Navicat lets you quickly and easily create, organize, access and share information in a secure and easy way. To start working with your server in Navicat, you should first establish a connection or several connections using the Connection window.', 742, 1, 526, 619, '2010-09-18 01:23:27', '2015-09-09 13:52:25', 10, 10);
INSERT INTO `articles` VALUES (16, '916 东城区东单王府井东街', 19, 13, '/home/Administrator/Pictures/img_773354.png', 'How we spend our days is, of course, how we spend our lives. Genius is an infinite capacity for taking pains. In the Objects tab, you can use the List List, Detail Detail and ER Diagram ER Diagram buttons to change the object view.', 'To open a query using an external editor, control-click it and select Open with External Editor. You can set the file path of an external editor in Preferences. To clear or reload various internal caches, flush tables, or acquire locks, control-click your connection in the Navigation pane and select Flush and choose the flush option. You must have the reload privilege to use this feature. It can also manage cloud databases such as Amazon Redshift, Amazon RDS, Alibaba Cloud. Features in Navicat are sophisticated enough to provide professional developers for all their specific needs, yet easy to learn for users who are new to database server. It can also manage cloud databases such as Amazon Redshift, Amazon RDS, Alibaba Cloud. Features in Navicat are sophisticated enough to provide professional developers for all their specific needs, yet easy to learn for users who are new to database server. Difficult circumstances serve as a textbook of life for people. A comfort zone is a beautiful place, but nothing ever grows there. The Information Pane shows the detailed object information, project activities, the DDL of database objects, object dependencies, membership of users/roles and preview. SQL Editor allows you to create and edit SQL text, prepare and execute selected queries. SSH serves to prevent such vulnerabilities and allows you to access a remote server\'s shell without compromising security. HTTP Tunneling is a method for connecting to a server that uses the same protocol (http://) and the same port (port 80) as a web server does. The repository database can be an existing MySQL, MariaDB, PostgreSQL, SQL Server, or Amazon RDS instance. The Main Window consists of several toolbars and panes for you to work on connections, database objects and advanced tools. In a Telnet session, all communications, including username and password, are transmitted in plain-text, allowing anyone to listen-in on your session and steal passwords and other information. The first step is as good as half over. Navicat Monitor is a safe, simple and agentless remote server monitoring tool that is packed with powerful features to make your monitoring effective as possible. To successfully establish a new connection to local/remote server - no matter via SSL, SSH or HTTP, set the database login information in the General tab. To open a query using an external editor, control-click it and select Open with External Editor. You can set the file path of an external editor in Preferences. To connect to a database or schema, simply double-click it in the pane. Export Wizard allows you to export data from tables, collections, views, or query results to any available formats. Creativity is intelligence having fun. I may not have gone where I intended to go, but I think I have ended up where I needed to be. Sometimes you win, sometimes you learn. Always keep your eyes open. Keep watching. Because whatever you see can inspire you. Difficult circumstances serve as a textbook of life for people. If the plan doesn’t work, change the plan, but never the goal. Monitored servers include MySQL, MariaDB and SQL Server, and compatible with cloud databases like Amazon RDS, Amazon Aurora, Oracle Cloud, Google Cloud and Microsoft Azure. Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines and different platforms. Navicat 15 has added support for the system-wide dark mode. A query is used to extract data from the database in a readable format according to the user\'s request. HTTP Tunneling is a method for connecting to a server that uses the same protocol (http://) and the same port (port 80) as a web server does. Such sessions are also susceptible to session hijacking, where a malicious user takes over your session once you have authenticated. I will greet this day with love in my heart. If your Internet Service Provider (ISP) does not provide direct access to its server, Secure Tunneling Protocol (SSH) / HTTP is another solution. The Navigation pane employs tree structure which allows you to take action upon the database and their objects through their pop-up menus quickly and easily. The first step is as good as half over. If the Show objects under schema in navigation pane option is checked at the Preferences window, all database objects are also displayed in the pane. Navicat Cloud could not connect and access your databases. By which it means, it could only store your connection settings, queries, model files, and virtual group; your database passwords and data (e.g. tables, views, etc) will not be stored to Navicat Cloud. Such sessions are also susceptible to session hijacking, where a malicious user takes over your session once you have authenticated. Import Wizard allows you to import data to tables/collections from CSV, TXT, XML, DBF and more. The first step is as good as half over. Always keep your eyes open. Keep watching. Because whatever you see can inspire you. Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines and different platforms. All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts for collaboration. You cannot save people, you can just love them. The Main Window consists of several toolbars and panes for you to work on connections, database objects and advanced tools. Navicat provides powerful tools for working with queries: Query Editor for editing the query text directly, and Query Builder, Find Builder or Aggregate Builder for building queries visually. Actually it is just in an idea when feel oneself can achieve and cannot achieve. Sometimes you win, sometimes you learn. With its well-designed Graphical User Interface(GUI), Navicat lets you quickly and easily create, organize, access and share information in a secure and easy way. A comfort zone is a beautiful place, but nothing ever grows there. Navicat Monitor is a safe, simple and agentless remote server monitoring tool that is packed with powerful features to make your monitoring effective as possible. To clear or reload various internal caches, flush tables, or acquire locks, control-click your connection in the Navigation pane and select Flush and choose the flush option. You must have the reload privilege to use this feature. To open a query using an external editor, control-click it and select Open with External Editor. You can set the file path of an external editor in Preferences. Always keep your eyes open. Keep watching. Because whatever you see can inspire you. The Synchronize to Database function will give you a full picture of all database differences. It collects process metrics such as CPU load, RAM usage, and a variety of other resources over SSH/SNMP. Typically, it is employed as an encrypted version of Telnet. Navicat is a multi-connections Database Administration tool allowing you to connect to MySQL, Oracle, PostgreSQL, SQLite, SQL Server, MariaDB and/or MongoDB databases, making database administration to multiple kinds of database so easy.', 884, 0, 907, 371, '2020-10-31 16:59:33', '2005-08-25 03:52:02', 10, 10);
INSERT INTO `articles` VALUES (17, '436 福田区景田东一街', 19, 13, '/Users/Administrator/Pictures/img_563337.jpg', 'To successfully establish a new connection to local/remote server - no matter via SSL, SSH or HTTP, set the database login information in the General tab.', 'It is used while your ISPs do not allow direct connections, but allows establishing HTTP connections. All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts for collaboration.', 712, 0, 520, 987, '2024-01-08 22:35:31', '2004-10-12 00:25:43', 10, 10);
INSERT INTO `articles` VALUES (18, '187 徐汇区虹桥路', 19, 13, '/home/Administrator/Pictures/img_034469.png', 'After logged in the Navicat Cloud feature, the Navigation pane will be divided into Navicat Cloud and My Connections sections.', 'All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts for collaboration. Navicat Cloud could not connect and access your databases. By which it means, it could only store your connection settings, queries, model files, and virtual group; your database passwords and data (e.g. tables, views, etc) will not be stored to Navicat Cloud. If your Internet Service Provider (ISP) does not provide direct access to its server, Secure Tunneling Protocol (SSH) / HTTP is another solution. What you get by achieving your goals is not as important as what you become by achieving your goals. HTTP Tunneling is a method for connecting to a server that uses the same protocol (http://) and the same port (port 80) as a web server does. Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines and different platforms. To successfully establish a new connection to local/remote server - no matter via SSL or SSH, set the database login information in the General tab. Anyone who has never made a mistake has never tried anything new. The repository database can be an existing MySQL, MariaDB, PostgreSQL, SQL Server, or Amazon RDS instance. The Synchronize to Database function will give you a full picture of all database differences. I may not have gone where I intended to go, but I think I have ended up where I needed to be. It can also manage cloud databases such as Amazon Redshift, Amazon RDS, Alibaba Cloud. Features in Navicat are sophisticated enough to provide professional developers for all their specific needs, yet easy to learn for users who are new to database server. Monitored servers include MySQL, MariaDB and SQL Server, and compatible with cloud databases like Amazon RDS, Amazon Aurora, Oracle Cloud, Google Cloud and Microsoft Azure. A man is not old until regrets take the place of dreams. Such sessions are also susceptible to session hijacking, where a malicious user takes over your session once you have authenticated. Genius is an infinite capacity for taking pains. The repository database can be an existing MySQL, MariaDB, PostgreSQL, SQL Server, or Amazon RDS instance. The Synchronize to Database function will give you a full picture of all database differences. If the Show objects under schema in navigation pane option is checked at the Preferences window, all database objects are also displayed in the pane. Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines and different platforms. Typically, it is employed as an encrypted version of Telnet. A query is used to extract data from the database in a readable format according to the user\'s request. Navicat Monitor can be installed on any local computer or virtual machine and does not require any software installation on the servers being monitored. The past has no power over the present moment. Secure Sockets Layer(SSL) is a protocol for transmitting private documents via the Internet. Navicat provides powerful tools for working with queries: Query Editor for editing the query text directly, and Query Builder, Find Builder or Aggregate Builder for building queries visually. All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts for collaboration. It provides strong authentication and secure encrypted communications between two hosts, known as SSH Port Forwarding (Tunneling), over an insecure network. It is used while your ISPs do not allow direct connections, but allows establishing HTTP connections. Monitored servers include MySQL, MariaDB and SQL Server, and compatible with cloud databases like Amazon RDS, Amazon Aurora, Oracle Cloud, Google Cloud and Microsoft Azure. SQL Editor allows you to create and edit SQL text, prepare and execute selected queries. If your Internet Service Provider (ISP) does not provide direct access to its server, Secure Tunneling Protocol (SSH) / HTTP is another solution. To successfully establish a new connection to local/remote server - no matter via SSL, SSH or HTTP, set the database login information in the General tab. In the Objects tab, you can use the List List, Detail Detail and ER Diagram ER Diagram buttons to change the object view. Sometimes you win, sometimes you learn. The first step is as good as half over. To start working with your server in Navicat, you should first establish a connection or several connections using the Connection window. All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts for collaboration. What you get by achieving your goals is not as important as what you become by achieving your goals. Success consists of going from failure to failure without loss of enthusiasm. The repository database can be an existing MySQL, MariaDB, PostgreSQL, SQL Server, or Amazon RDS instance.', 263, 0, 264, 66, '2009-07-27 08:38:42', '2018-01-19 22:12:32', 10, 10);
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `status` int(2) DEFAULT '1' COMMENT '分类状态 0:禁用，1:启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='分类表';

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (11, '酒水饮料', 10, 1, '2022-06-09 22:09:18', '2022-06-09 22:09:18', 1, 1);
INSERT INTO `category` VALUES (12, '传统主食', 9, 1, '2022-06-09 22:09:32', '2022-06-09 22:18:53', 1, 1);
INSERT INTO `category` VALUES (13, '人气套餐', 12, 1, '2022-06-09 22:11:38', '2022-06-10 11:04:40', 1, 1);
INSERT INTO `category` VALUES (15, '商务套餐', 13, 1, '2022-06-09 22:14:10', '2022-06-10 11:04:48', 1, 1);
INSERT INTO `category` VALUES (16, '蜀味烤鱼', 4, 1, '2022-06-09 22:15:37', '2022-08-31 14:27:25', 1, 1);
INSERT INTO `category` VALUES (17, '蜀味牛蛙', 5, 0, '2022-06-09 22:16:14', '2024-02-22 13:13:14', 1, 10);
INSERT INTO `category` VALUES (18, '特色蒸菜', 6, 0, '2022-06-09 22:17:42', '2024-02-22 13:12:14', 1, 10);
INSERT INTO `category` VALUES (19, '新鲜时蔬', 7, 1, '2022-06-09 22:18:12', '2022-06-09 22:18:28', 1, 1);
INSERT INTO `category` VALUES (20, '汤类', 0, 1, '2022-06-09 22:22:29', '2024-03-03 01:59:30', 1, 10);
INSERT INTO `category` VALUES (24, '电脑知识', 1, 1, '2024-02-22 17:17:51', '2024-02-22 17:17:51', 10, 10);
COMMIT;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `article_id` bigint(20) NOT NULL COMMENT '评论博文ID',
  `like_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `content` text CHARACTER SET utf8 NOT NULL COMMENT '评论内容',
  `parent_id` bigint(20) NOT NULL COMMENT '父评论ID',
  `status` int(2) DEFAULT '1' COMMENT '博文状态 0:禁用，1:启用',
  `create_time` datetime DEFAULT NULL COMMENT '评论日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '发表用户ID',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `article_id` (`article_id`) USING BTREE,
  KEY `parent_comment_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='博文评论表';

-- ----------------------------
-- Records of comments
-- ----------------------------
BEGIN;
INSERT INTO `comments` VALUES (1, 7, 0, '写的真棒!', 0, 1, '2024-02-28 12:31:38', '2024-02-28 12:31:38', 10, 10);
INSERT INTO `comments` VALUES (4, 9, 0, '不错不错!', 2, 1, '2024-02-28 14:14:09', '2024-02-28 14:14:09', 10, 10);
INSERT INTO `comments` VALUES (5, 9, 0, '不错不错!', 2, 1, '2024-02-28 14:14:11', '2024-02-28 14:14:11', 10, 10);
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父级菜单',
  `order_num` int(11) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(255) NOT NULL COMMENT '路径',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '权限标识',
  `has_hidden` tinyint(4) DEFAULT NULL COMMENT '是否隐藏（0否，1是）',
  `redirect` varchar(255) DEFAULT NULL COMMENT '重定向',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '1' COMMENT '菜单状态（0正常 1停用）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `index_title` (`title`) USING BTREE COMMENT '标题唯一'
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='菜单集合';

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES (5, '权限管理', 63, 1, 'permission', 'permission', 'permission', 'icon_menu_qxgl', 'M', 'permission', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (6, '博文管理', 63, 2, 'artile', 'artile', 'artile', 'icon_menu_qxgl', 'M', 'artile', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (7, '设置管理', 63, 3, 'setting', 'setting', 'setting/index', 'icon_menu_qxgl', 'C', 'setting:query', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (8, '用户管理', 5, 1, 'user', 'user', 'user/index', 'icon_menu_qxgl', 'C', 'user:list', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (9, '角色管理', 5, 1, 'role', 'role', 'role/index', 'icon_menu_qxgl', 'C', 'role:list', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (10, '菜单管理', 5, 1, 'menu', 'menu', 'menu/index', 'icon_menu_qxgl', 'C', 'menu:list', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (11, '分类管理', 6, 1, 'artile/category', 'category', 'artile/category/index', 'icon_menu_qxgl', 'C', 'artilce:category:list', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (12, '标签管理', 6, 1, 'artile/tags', 'tags', 'artile/tags/index', 'icon_menu_qxgl', 'C', 'artilce:tags:list', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (13, '文章管理', 6, 1, 'artile/list', 'list', 'artile/list/index', 'icon_menu_qxgl', 'C', 'artilce:list', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (14, '评论管理', 6, 1, 'artile/comments', 'comments', 'artile/comments/index', 'icon_menu_qxgl', 'C', 'artilce:comments:list', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (15, '用户查询', 8, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:user:query', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (16, '用户新增', 8, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:user:add', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (17, '用户修改', 8, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:user:update', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (18, '用户删除', 8, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:user:delete', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (19, '用户导出', 8, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:user:export', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (20, '用户导入', 8, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:user:import', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (21, '角色查询', 9, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:role:query', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (22, '角色新增', 9, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:role:add', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (23, '角色修改', 9, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:role:update', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (24, '角色删除', 9, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:role:delete', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (25, '角色导出', 9, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:role:export', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (26, '角色导入', 9, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:role:import', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (27, '菜单查询', 10, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:role:query', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (28, '菜单新增', 10, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:role:add', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (29, '菜单修改', 10, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:role:update', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (30, '菜单删除', 10, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:role:delete', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (31, '菜单导出', 10, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:role:export', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (32, '菜单导入', 10, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:role:import', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (33, '分类查询', 11, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:category:query', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (34, '分类新增', 11, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:category:add', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (35, '分类修改', 11, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:category:update', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (36, '分类删除', 11, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:category:delete', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (37, '分类导出', 11, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:category:export', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (38, '分类导入', 11, 1, '', '', '', 'icon_menu_qxgl', 'F', 'permission:category:import', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (45, '标签查询', 12, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:tags:query', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (46, '标签新增', 12, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:tags:add', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (47, '标签修改', 12, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:tags:update', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (48, '标签删除', 12, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:tags:delete', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (49, '标签导出', 12, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:tags:export', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (50, '标签导入', 12, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:tags:import', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (51, '文章查询', 13, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:article:query', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (52, '文章新增', 13, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:article:add', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (53, '文章修改', 13, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:article:update', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (54, '文章删除', 13, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:article:delete', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (55, '文章导出', 13, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:article:export', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (56, '文章导入', 13, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:article:import', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (57, '评论查询', 14, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:comments:query', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (58, '评论新增', 14, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:comments:add', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (59, '评论修改', 14, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:comments:update', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (60, '评论删除', 14, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:comments:delete', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (61, '评论导出', 14, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:comments:export', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (62, '评论导入', 14, 2, '', '', '', 'icon_menu_qxgl', 'F', 'permission:comments:import', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
INSERT INTO `menu` VALUES (63, '系统管理', 0, 1, 'system', 'system', 'system', 'icon_menu_qxgl', 'M', 'system', 1, NULL, '2021-03-31 03:12:12', '2021-03-31 03:12:12', 10, 10, '1');
COMMIT;

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `id` int(10) unsigned NOT NULL COMMENT '主键',
  `module_code` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '模块编号',
  `module_name` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '模块名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_module_id` (`module_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='模块资源表';

-- ----------------------------
-- Records of module
-- ----------------------------
BEGIN;
INSERT INTO `module` VALUES (1, 'ROLE', '角色管理');
INSERT INTO `module` VALUES (2, 'USER', '用户管理');
INSERT INTO `module` VALUES (3, 'DEPT', '部门管理');
INSERT INTO `module` VALUES (4, 'GOODS', '体检套餐管理');
INSERT INTO `module` VALUES (5, 'RULE', '促销规则管理');
INSERT INTO `module` VALUES (6, 'CUSTOMER', '客户档案管理');
INSERT INTO `module` VALUES (7, 'ORDER', '订单管理');
INSERT INTO `module` VALUES (8, 'CUSTOMER_IM', '客服模块');
INSERT INTO `module` VALUES (9, 'APPOINTMENT', '体检预约管理');
INSERT INTO `module` VALUES (10, 'CUSTOMER_CHECKIN', '体检签到');
INSERT INTO `module` VALUES (11, 'APPOINTMENT_RESTRICTION', '体检预约设置');
INSERT INTO `module` VALUES (12, 'CHECKUP', '医生检查');
INSERT INTO `module` VALUES (13, 'CHECKUP_REPORT', '体检报告管理');
INSERT INTO `module` VALUES (14, 'FLOW_REGULATION', '人员限流管理');
COMMIT;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(10) unsigned NOT NULL COMMENT '主键',
  `permission_name` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '权限',
  `module_id` int(10) unsigned NOT NULL COMMENT '模块ID',
  `action_id` int(10) unsigned NOT NULL COMMENT '行为ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_permission` (`permission_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES (0, 'ROOT', 0, 0);
INSERT INTO `permission` VALUES (1, 'ROLE:INSERT', 1, 1);
INSERT INTO `permission` VALUES (2, 'ROLE:DELETE', 1, 2);
INSERT INTO `permission` VALUES (3, 'ROLE:UPDATE', 1, 3);
INSERT INTO `permission` VALUES (4, 'ROLE:SELECT', 1, 4);
INSERT INTO `permission` VALUES (5, 'USER:INSERT', 2, 1);
INSERT INTO `permission` VALUES (6, 'USER:DELETE', 2, 2);
INSERT INTO `permission` VALUES (7, 'USER:UPDATE', 2, 3);
INSERT INTO `permission` VALUES (8, 'USER:SELECT', 2, 4);
INSERT INTO `permission` VALUES (9, 'DEPT:INSERT', 3, 1);
INSERT INTO `permission` VALUES (10, 'DEPT:DELETE', 3, 2);
INSERT INTO `permission` VALUES (11, 'DEPT:UPDATE', 3, 3);
INSERT INTO `permission` VALUES (12, 'DEPT:SELECT', 3, 4);
INSERT INTO `permission` VALUES (13, 'GOODS:INSERT', 4, 1);
INSERT INTO `permission` VALUES (14, 'GOODS:DELETE', 4, 2);
INSERT INTO `permission` VALUES (15, 'GOODS:UPDATE', 4, 3);
INSERT INTO `permission` VALUES (16, 'GOODS:SELECT', 4, 4);
INSERT INTO `permission` VALUES (17, 'RULE:INSERT', 5, 1);
INSERT INTO `permission` VALUES (18, 'RULE:DELETE', 5, 2);
INSERT INTO `permission` VALUES (19, 'RULE:UPDATE', 5, 3);
INSERT INTO `permission` VALUES (20, 'RULE:SELECT', 5, 4);
INSERT INTO `permission` VALUES (21, 'CUSTOMER:INSERT', 6, 1);
INSERT INTO `permission` VALUES (22, 'CUSTOMER:DELETE', 6, 2);
INSERT INTO `permission` VALUES (23, 'CUSTOMER:UPDATE', 6, 3);
INSERT INTO `permission` VALUES (24, 'CUSTOMER:SELECT', 6, 4);
INSERT INTO `permission` VALUES (25, 'ORDER:INSERT', 7, 1);
INSERT INTO `permission` VALUES (26, 'ORDER:DELETE', 7, 2);
INSERT INTO `permission` VALUES (27, 'ORDER:UPDATE', 7, 3);
INSERT INTO `permission` VALUES (28, 'ORDER:SELECT', 7, 4);
INSERT INTO `permission` VALUES (29, 'CUSTOMER_IM:INSERT', 8, 1);
INSERT INTO `permission` VALUES (30, 'CUSTOMER_IM:DELETE', 8, 2);
INSERT INTO `permission` VALUES (31, 'CUSTOMER_IM:UPDATE', 8, 3);
INSERT INTO `permission` VALUES (32, 'CUSTOMER_IM:SELECT', 8, 4);
INSERT INTO `permission` VALUES (33, 'APPOINTMENT:INSERT', 9, 1);
INSERT INTO `permission` VALUES (34, 'APPOINTMENT:DELETE', 9, 2);
INSERT INTO `permission` VALUES (35, 'APPOINTMENT:UPDATE', 9, 3);
INSERT INTO `permission` VALUES (36, 'APPOINTMENT:SELECT', 9, 4);
INSERT INTO `permission` VALUES (37, 'CUSTOMER_CHECKIN:INSERT', 10, 1);
INSERT INTO `permission` VALUES (38, 'CUSTOMER_CHECKIN:DELETE', 10, 2);
INSERT INTO `permission` VALUES (39, 'CUSTOMER_CHECKIN:UPDATE', 10, 3);
INSERT INTO `permission` VALUES (40, 'CUSTOMER_CHECKIN:SELECT', 10, 4);
INSERT INTO `permission` VALUES (41, 'APPOINTMENT_RESTRICTION:INSERT', 11, 1);
INSERT INTO `permission` VALUES (42, 'APPOINTMENT_RESTRICTION:DELETE', 11, 2);
INSERT INTO `permission` VALUES (43, 'APPOINTMENT_RESTRICTION:UPDATE', 11, 3);
INSERT INTO `permission` VALUES (44, 'APPOINTMENT_RESTRICTION:SELECT', 11, 4);
INSERT INTO `permission` VALUES (45, 'CHECKUP:INSERT', 12, 1);
INSERT INTO `permission` VALUES (46, 'CHECKUP:DELETE', 12, 2);
INSERT INTO `permission` VALUES (47, 'CHECKUP:UPDATE', 12, 3);
INSERT INTO `permission` VALUES (48, 'CHECKUP:SELECT', 12, 4);
INSERT INTO `permission` VALUES (49, 'CHECKUP_REPORT:INSERT', 13, 1);
INSERT INTO `permission` VALUES (50, 'CHECKUP_REPORT:DELETE', 13, 2);
INSERT INTO `permission` VALUES (51, 'CHECKUP_REPORT:UPDATE', 13, 3);
INSERT INTO `permission` VALUES (52, 'CHECKUP_REPORT:SELECT', 13, 4);
INSERT INTO `permission` VALUES (53, 'FLOW_REGULATION:INSERT', 14, 1);
INSERT INTO `permission` VALUES (54, 'FLOW_REGULATION:DELETE', 14, 2);
INSERT INTO `permission` VALUES (55, 'FLOW_REGULATION:UPDATE', 14, 3);
INSERT INTO `permission` VALUES (56, 'FLOW_REGULATION:SELECT', 14, 4);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '角色名称',
  `role_en` varchar(200) NOT NULL COMMENT '角色英文名称',
  `permissions` json NOT NULL COMMENT '权限集合',
  `menus` json NOT NULL COMMENT '菜单集合',
  `desc` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `default_permissions` json DEFAULT NULL COMMENT '系统角色内置权限',
  `systemic` tinyint(1) DEFAULT '0' COMMENT '是否为系统内置角色',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `update_user` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_role_name` (`role_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (0, '超级管理员', 'super_admin', '[0]', '[5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63]', '超级管理员用户不能删除和修改', '[0]', 1, NULL, NULL, NULL, NULL);
INSERT INTO `role` VALUES (1, '普通管理员', 'admin', '[1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29]', 'null', NULL, '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 27]', 1, NULL, NULL, NULL, NULL);
INSERT INTO `role` VALUES (2, '内容管理员', 'article_admin', '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]', 'null', NULL, '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 17, 27]', 1, NULL, NULL, NULL, NULL);
INSERT INTO `role` VALUES (3, '运管者', 'yunying', '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]', 'null', NULL, '[1, 2, 3, 4, 5, 6, 7, 8]', 1, NULL, NULL, NULL, NULL);
INSERT INTO `role` VALUES (4, '计划部', 'jihua', '[1, 2, 3, 4, 5, 6, 7, 8, 27, 28, 29, 30, 31, 32]', 'null', NULL, '[1, 2, 3, 4, 5, 6, 7, 8, 28, 27]', 1, NULL, NULL, NULL, NULL);
INSERT INTO `role` VALUES (5, '财务', 'caiwu', '[1, 2, 3, 4, 5, 6, 7, 8, 13, 14, 17]', '[5, 8, 15, 16, 17, 18, 19, 20, 63]', NULL, '[1, 2, 3, 4, 5, 6, 7, 8, 28, 36, 17]', 1, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blog_name` varchar(45) NOT NULL COMMENT '博客名称',
  `author` varchar(32) NOT NULL COMMENT '作者名',
  `logo` varchar(150) NOT NULL COMMENT '博客 LOGO',
  `avatar` varchar(150) NOT NULL COMMENT '作者头像',
  `introduction` varchar(200) DEFAULT NULL COMMENT '介绍语',
  `github_home_page` varchar(100) DEFAULT NULL COMMENT 'GitHub 主页访问地址',
  `gitee_home_page` varchar(100) DEFAULT NULL COMMENT 'Gitee  主页访问地址',
  `bilibili_home_page` varchar(100) DEFAULT NULL COMMENT 'B站 主页访问地址',
  `doc_library` varchar(100) DEFAULT NULL COMMENT '文档库地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `update_user` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='博客设置表';

-- ----------------------------
-- Records of setting
-- ----------------------------
BEGIN;
INSERT INTO `setting` VALUES (1, '程序猿小萨', '木萨·塔布提', 'https://qarabala.gitee.io/vitepress/img/logo.png', 'https://qarabala.gitee.io/vitepress/img/logo.png', '你的时间有限，不要浪费于重复别人的生活。不要让别人的观点淹没了你内心的声音。——史蒂夫·乔布斯', 'https://github.com/programmer-xiaosa', 'https://gitee.com/qarabala', 'https://space.bilibili.com/1562860055/favlist?fid=1592147955&ftype=create', 'https://qarabala.gitee.io/blog/', '2024-02-28 14:56:09', '2024-02-28 15:22:48', 10, 10);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(11) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) COLLATE utf8_bin DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(11) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int(11) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) COLLATE utf8_bin DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) COLLATE utf8_bin DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2083 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 6, 'system', NULL, 1, 0, 'M', '0', '0', '', 'icon_menu_xtgl', 'admin', '2021-03-31 03:12:10', 'admin', '2021-04-15 06:10:52', '系统管理目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 2024, 1, 'user', 'system/user/index', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2021-03-31 03:12:10', 'admin', '2021-04-13 02:28:18', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 2024, 2, 'role', 'system/role/index', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2021-03-31 03:12:10', 'admin', '2021-04-13 02:28:31', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 2024, 3, 'menu', 'system/menu/index', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2021-03-31 03:12:10', 'admin', '2021-04-13 02:28:46', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 2024, 4, 'dept', 'system/dept/index', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2021-03-31 03:12:10', 'admin', '2021-04-13 02:29:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 2024, 5, 'post', 'system/post/index', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2021-03-31 03:12:10', 'admin', '2021-04-13 02:29:13', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 3, 'dict', 'system/dict/index', 1, 0, 'C', '0', '0', 'system:dict:list', 'icon_menu_zdgl', 'admin', '2021-03-31 03:12:10', 'admin', '2021-04-13 03:34:47', '字典管理菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', 1, 0, 'M', '1', '0', '', 'log', 'admin', '2021-03-31 03:12:10', 'admin', '2021-04-01 11:04:39', '日志管理菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 10, 'operlog', 'monitor/operlog/index', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2021-03-31 03:12:11', 'admin', '2021-04-13 02:41:56', '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 11, 'logininfor', 'monitor/logininfor/index', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2021-03-31 03:12:11', 'admin', '2021-04-13 02:42:04', '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2021-03-31 03:12:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2021-03-31 03:12:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2021-03-31 03:12:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2021-03-31 03:12:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2021-03-31 03:12:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2021-03-31 03:12:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2021-03-31 03:12:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2021-03-31 03:12:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2021-03-31 03:12:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2021-03-31 03:12:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2021-03-31 03:12:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2021-03-31 03:12:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2021-03-31 03:12:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2021-03-31 03:12:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2001, '商机管理', 0, 2, 'business', 'clues/business/index', 1, 0, 'C', '0', '0', 'business:business:list', 'icon_menu_sjgl', 'admin', '2021-03-31 09:39:34', 'admin', '2021-04-27 09:34:06', '');
INSERT INTO `sys_menu` VALUES (2002, '合同管理', 0, 3, 'contract', 'clues/contract/index', 1, 0, 'C', '0', '0', 'contract:contract:list', 'icon_menu_htgl', 'admin', '2021-03-31 09:40:27', 'admin', '2021-05-21 07:35:07', '');
INSERT INTO `sys_menu` VALUES (2003, '活动管理', 0, 4, 'activity', 'clues/activity/index', 1, 0, 'C', '0', '0', 'clues:activity:list', 'icon_menu_hdgl', 'admin', '2021-04-01 02:56:38', 'admin', '2021-04-27 03:21:08', '活动管理菜单');
INSERT INTO `sys_menu` VALUES (2004, '活动管理查询', 2003, 1, '#', '', 1, 0, 'F', '0', '0', 'clues:activity:query', '#', 'admin', '2021-04-01 02:56:38', 'admin', '2021-04-01 03:08:39', '');
INSERT INTO `sys_menu` VALUES (2005, '活动管理新增', 2003, 2, '#', '', 1, 0, 'F', '0', '0', 'clues:activity:add', '#', 'admin', '2021-04-01 02:56:38', 'admin', '2021-04-01 03:08:50', '');
INSERT INTO `sys_menu` VALUES (2006, '活动管理修改', 2003, 3, '#', '', 1, 0, 'F', '0', '0', 'clues:activity:edit', '#', 'admin', '2021-04-01 02:56:38', 'admin', '2021-04-01 03:09:04', '');
INSERT INTO `sys_menu` VALUES (2007, '活动管理删除', 2003, 4, '#', '', 1, 0, 'F', '0', '0', 'clues:activity:remove', '#', 'admin', '2021-04-01 02:56:38', 'admin', '2021-04-01 03:09:14', '');
INSERT INTO `sys_menu` VALUES (2008, '活动管理导出', 2003, 5, '#', '', 1, 0, 'F', '0', '0', 'clues:activity:export', '#', 'admin', '2021-04-01 02:56:38', 'admin', '2021-04-01 03:09:31', '');
INSERT INTO `sys_menu` VALUES (2009, '课程管理', 0, 4, 'course', 'clues/course/index', 1, 0, 'C', '0', '0', 'clues:course:list', 'icon_menu_kcgl', 'admin', '2021-04-01 10:14:16', 'admin', '2021-04-13 02:04:05', '课程管理菜单');
INSERT INTO `sys_menu` VALUES (2010, '课程管理查询', 2009, 1, '#', '', 1, 0, 'F', '0', '0', 'clues:course:query', '#', 'admin', '2021-04-01 10:14:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2011, '课程管理新增', 2009, 2, '#', '', 1, 0, 'F', '0', '0', 'clues:course:add', '#', 'admin', '2021-04-01 10:14:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2012, '课程管理修改', 2009, 3, '#', '', 1, 0, 'F', '0', '0', 'clues:course:edit', '#', 'admin', '2021-04-01 10:14:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2013, '课程管理删除', 2009, 4, '#', '', 1, 0, 'F', '0', '0', 'clues:course:remove', '#', 'admin', '2021-04-01 10:14:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2015, '线索管理', 0, 1, 'clue', 'clues/clue/index', 1, 0, 'C', '0', '0', 'clues:clue:list', 'icon_menu_xsgl', 'admin', '2021-04-02 09:02:31', 'admin', '2021-04-13 02:03:06', '线索管理菜单');
INSERT INTO `sys_menu` VALUES (2016, '线索管理查询', 2015, 1, '#', '', 1, 0, 'F', '0', '0', 'clues:clue:query', '#', 'admin', '2021-04-02 09:02:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2017, '线索管理新增', 2015, 2, '#', '', 1, 0, 'F', '0', '0', 'clues:clue:add', '#', 'admin', '2021-04-02 09:02:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2018, '线索管理跟进', 2015, 3, '#', '', 1, 0, 'F', '0', '0', 'clues:record:add', '#', 'admin', '2021-04-02 09:02:31', 'admin', '2021-05-19 09:28:18', '');
INSERT INTO `sys_menu` VALUES (2019, '线索管理删除', 2015, 4, '#', '', 1, 0, 'F', '0', '0', 'clues:clue:remove', '#', 'admin', '2021-04-02 09:02:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2022, '线索管理查看', 2015, 6, '', NULL, 1, 0, 'F', '0', '0', 'clues:clue:info', '#', 'admin', '2021-04-08 10:07:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2023, '线索管理分配', 2015, 7, '', NULL, 1, 0, 'F', '0', '0', 'clues:clue:assignment', '#', 'admin', '2021-04-08 10:08:49', 'admin', '2021-05-19 09:27:49', '');
INSERT INTO `sys_menu` VALUES (2024, '权限管理', 1, 2, 'permission', NULL, 1, 0, 'M', '0', '0', NULL, 'icon_menu_qxgl', 'admin', '2021-04-13 02:27:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2025, '线索配置', 1, 4, 'clew', 'system/clew/index', 1, 0, 'C', '0', '0', 'system:clew:list', 'icon_menu_xspz', 'admin', '2021-04-13 03:11:34', 'admin', '2021-04-13 03:32:24', '');
INSERT INTO `sys_menu` VALUES (2026, '商机配置', 1, 6, 'opportunity', 'system/opportunity/index', 1, 0, 'C', '0', '0', 'system:opportunity:list', 'icon_menu_sjpz', 'admin', '2021-04-13 03:34:06', 'admin', '2021-04-26 08:16:57', '');
INSERT INTO `sys_menu` VALUES (2027, '统计分析', 0, 5, 'countAnalysis', 'clues/countAnalysis/index', 1, 0, 'C', '0', '0', 'clues:countAnalysis:list', 'icon_menu_tjfx', 'admin', '2021-04-15 06:06:56', 'admin', '2021-11-05 08:56:10', '');
INSERT INTO `sys_menu` VALUES (2028, '商机管理查询', 2001, 1, '', NULL, 1, 0, 'F', '0', '0', 'business:business:query', '#', 'admin', '2021-04-26 05:40:08', 'admin', '2021-04-27 09:49:30', '');
INSERT INTO `sys_menu` VALUES (2029, '商机管理新增', 2001, 2, '', NULL, 1, 0, 'F', '0', '0', 'business:business:add', '#', 'admin', '2021-04-26 05:40:46', 'admin', '2021-04-27 09:49:37', '');
INSERT INTO `sys_menu` VALUES (2031, '商机管理查看', 2001, 4, '', NULL, 1, 0, 'F', '0', '0', 'business:business:info', '#', 'admin', '2021-04-26 05:42:03', 'admin', '2021-04-27 09:49:56', '');
INSERT INTO `sys_menu` VALUES (2032, '商机管理分配', 2001, 5, '', NULL, 1, 0, 'F', '0', '0', 'business:business:assignment', '#', 'admin', '2021-04-26 05:42:29', 'admin', '2021-05-19 09:11:29', '');
INSERT INTO `sys_menu` VALUES (2033, '活动管理通过', 2003, 6, '', NULL, 1, 0, 'F', '0', '0', 'clues:activity:pass', '#', 'admin', '2021-04-26 06:47:54', 'admin', '2021-05-31 10:25:41', '');
INSERT INTO `sys_menu` VALUES (2034, '通知中心', 1, 8, 'noticeCenter', 'system/noticeCenter/index', 1, 0, 'C', '0', '0', 'system:noticeCenter:list', 'icon_menu_tzzx', 'admin', '2021-04-26 07:04:59', 'admin', '2021-04-26 07:05:33', '');
INSERT INTO `sys_menu` VALUES (2035, '转派管理', 0, 5, 'transferManage', 'clues/transferManage/index', 1, 0, 'C', '0', '0', 'transfer:transfer:list', 'example', 'admin', '2021-04-26 10:09:13', 'admin', '2021-11-05 08:56:03', '');
INSERT INTO `sys_menu` VALUES (2042, '系统日志', 1, 9, 'systemLog', 'system/systemLog/index', 1, 0, 'C', '0', '0', 'system:systemLog:list', 'icon_menu_xtrz', 'admin', '2021-04-28 10:04:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2043, '商机跟进', 2001, 6, '', NULL, 1, 0, 'F', '0', '0', 'business:record:add', '#', 'admin', '2021-05-14 07:16:18', 'admin', '2021-05-14 07:18:29', '');
INSERT INTO `sys_menu` VALUES (2044, '踢回公海', 2001, 8, '', NULL, 1, 0, 'F', '0', '0', 'business:business:back', '#', 'admin', '2021-05-14 07:18:11', 'admin', '2021-05-14 07:18:44', '');
INSERT INTO `sys_menu` VALUES (2045, '查询公海池', 2001, 9, '', NULL, 1, 0, 'F', '0', '0', 'business:business:pool', '#', 'admin', '2021-05-14 07:26:41', 'admin', '2021-05-14 07:32:35', '');
INSERT INTO `sys_menu` VALUES (2046, '线索池', 2015, 8, '', NULL, 1, 0, 'F', '0', '0', 'clues:clue:pool', '#', 'admin', '2021-05-14 07:32:07', 'admin', '2021-05-14 07:33:00', '');
INSERT INTO `sys_menu` VALUES (2047, '线索跟进记录', 2015, 9, '', NULL, 1, 0, 'F', '0', '0', 'clues:record:list', '#', 'admin', '2021-05-17 02:47:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2048, '线索管理批量添加', 2015, 10, '', NULL, 1, 0, 'F', '0', '0', 'clues:clue:batchAdd', '#', 'admin', '2021-05-19 09:04:37', 'admin', '2021-05-19 09:05:27', '');
INSERT INTO `sys_menu` VALUES (2049, '商机捞取', 2001, 10, '', NULL, 1, 0, 'F', '0', '0', 'business:business:gain', '#', 'admin', '2021-05-19 09:13:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2051, '活动查看', 2001, 11, '', NULL, 1, 0, 'F', '0', '0', 'clues:activity:list', '#', 'admin', '2021-05-20 01:46:35', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2052, '合同查询', 2002, 1, '', NULL, 1, 0, 'F', '0', '0', 'contract:contract:query', '#', 'admin', '2021-05-21 07:35:53', 'admin', '2021-05-21 07:36:30', '');
INSERT INTO `sys_menu` VALUES (2053, '合同新增', 2002, 2, '', NULL, 1, 0, 'F', '0', '0', 'contract:contract:add', '#', 'admin', '2021-05-21 07:36:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2059, '线索捞取', 2015, 11, 'clues:clue:gain', NULL, 1, 0, 'F', '0', '0', 'clues:clue:gain', '#', 'admin', '2021-05-25 06:19:10', 'admin', '2021-05-25 06:19:27', '');
INSERT INTO `sys_menu` VALUES (2065, '活动管理驳回', 2003, 7, '', NULL, 1, 0, 'F', '0', '0', 'clues:activity:reject', '#', 'admin', '2021-05-31 10:26:18', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2066, '商机转合同', 2001, 14, '', NULL, 1, 0, 'F', '0', '0', 'contract:contract:change', '#', 'admin', '2021-06-01 09:21:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2067, '合同基本信息查看', 2002, 10, '', NULL, 1, 0, 'F', '0', '0', 'contract:contract:detail', '#', 'admin', '2021-06-02 07:44:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2068, '首页', 0, 0, 'index', 'indexHome', 1, 0, 'C', '0', '0', 'indexHome:list', 'icon_menu_home', 'admin', '2021-06-23 08:34:29', 'admin', '2021-07-01 07:11:01', '');
INSERT INTO `sys_menu` VALUES (2069, '首页基础数据查询', 2068, 1, '', NULL, 1, 0, 'F', '0', '0', 'indexHome:baseQuery', '#', 'admin', '2021-06-23 08:59:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2070, '首页今日简报', 2068, 2, '', NULL, 1, 0, 'F', '0', '0', 'indexHome:todayQuery', '#', 'admin', '2021-06-23 09:00:26', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2071, '首页待办', 2068, 3, '', NULL, 1, 0, 'F', '0', '0', 'indexHome:todoQuery', '#', 'admin', '2021-06-23 09:01:35', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2072, '首页漏斗图', 2068, 4, '', NULL, 1, 0, 'F', '0', '0', 'indexHome:funnelQuery', '#', 'admin', '2021-06-23 09:02:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2073, '首页销售龙虎榜', 2068, 5, '', NULL, 1, 0, 'F', '0', '0', 'indexHome:saleQuery', '#', 'admin', '2021-06-23 09:02:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2074, '首页商机龙虎榜', 2068, 6, '', NULL, 1, 0, 'F', '0', '0', 'indexHome:businessQuery', '#', 'admin', '2021-06-23 09:03:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2077, '线索转商机', 2015, 12, 'clues:clue:changeBusiness', NULL, 1, 0, 'F', '0', '0', 'clues:clue:changeBusiness', '#', 'admin', '2021-06-23 09:03:32', 'admin', '2021-06-23 09:03:32', '线索转商机');
INSERT INTO `sys_menu` VALUES (2078, '转派处理', 2035, 0, 'transfer:transfer:assignment', NULL, 1, 0, 'F', '0', '0', 'transfer:transfer:assignment', '#', 'admin', '2021-06-23 09:03:32', 'admin', '2021-06-23 09:03:32', '转派管理');
INSERT INTO `sys_menu` VALUES (2079, '商机跟进记录列表查询', 2001, 6, 'business:record:list', NULL, 1, 0, 'F', '0', '0', 'business:record:list', '#', 'admin', '2021-06-23 09:02:48', '', NULL, '商机跟进记录');
INSERT INTO `sys_menu` VALUES (2080, '合同详情预览', 2002, 6, 'contract:contract:info', NULL, 1, 0, 'F', '0', '0', 'contract:contract:info', '#', 'admin', '2021-06-23 09:02:48', 'admin', '2021-06-23 09:02:48', '合同详情');
INSERT INTO `sys_menu` VALUES (2081, '退回公海', 2001, 11, 'business:business:back', NULL, 1, 0, 'F', '0', '0', 'business:business:back', '#', 'admin', '2021-06-23 09:02:48', 'admin', '2021-06-23 09:02:48', '退回公海');
INSERT INTO `sys_menu` VALUES (2082, '伪线索', 2015, 8, 'clues:clue:false', NULL, 1, 0, 'F', '0', '0', 'clues:clue:false', '#', 'admin', '2021-06-23 09:02:48', 'admin', '2021-06-23 09:02:48', '伪线索');
COMMIT;

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标签名称',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `status` int(2) DEFAULT '1' COMMENT '标签状态 0:禁用，1:启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='标签表';

-- ----------------------------
-- Records of tags
-- ----------------------------
BEGIN;
INSERT INTO `tags` VALUES (11, '酒水饮料', 10, 1, '2022-06-09 22:09:18', '2022-06-09 22:09:18', 1, 1);
INSERT INTO `tags` VALUES (12, '传统主食', 9, 1, '2022-06-09 22:09:32', '2022-06-09 22:18:53', 1, 1);
INSERT INTO `tags` VALUES (13, '人气套餐', 12, 1, '2022-06-09 22:11:38', '2022-06-10 11:04:40', 1, 1);
INSERT INTO `tags` VALUES (15, '商务套餐', 13, 1, '2022-06-09 22:14:10', '2022-06-10 11:04:48', 1, 1);
INSERT INTO `tags` VALUES (16, '蜀味烤鱼', 4, 1, '2022-06-09 22:15:37', '2022-08-31 14:27:25', 1, 1);
INSERT INTO `tags` VALUES (17, '蜀味牛蛙', 5, 1, '2022-06-09 22:16:14', '2024-02-22 13:13:14', 1, 10);
INSERT INTO `tags` VALUES (18, '特色蒸菜', 6, 1, '2022-06-09 22:17:42', '2024-02-22 13:12:14', 1, 10);
INSERT INTO `tags` VALUES (19, '新鲜时蔬', 7, 1, '2022-06-09 22:18:12', '2022-06-09 22:18:28', 1, 1);
INSERT INTO `tags` VALUES (20, '水煮鱼', 8, 1, '2022-06-09 22:22:29', '2024-02-22 11:03:02', 1, 10);
INSERT INTO `tags` VALUES (25, 'He Ziyi', 796, 1, '2006-11-11 21:53:42', '2004-03-15 11:53:01', 852, 239);
INSERT INTO `tags` VALUES (26, 'Kondo Ayato', 746, 1, '2014-05-08 09:09:51', '2001-02-15 00:37:02', 198, 313);
INSERT INTO `tags` VALUES (27, 'Hung Suk Yee', 747, 1, '2023-10-24 10:30:47', '2009-12-29 20:59:08', 484, 495);
INSERT INTO `tags` VALUES (28, 'Sato Minato', 410, 1, '2000-08-19 20:29:03', '2000-03-01 14:18:17', 463, 559);
INSERT INTO `tags` VALUES (29, 'Randall Foster', 934, 1, '2008-03-10 16:39:09', '2021-05-16 22:52:08', 890, 324);
INSERT INTO `tags` VALUES (30, 'Ishida Kasumi', 774, 1, '2017-03-13 07:22:04', '2012-05-09 16:31:04', 921, 697);
INSERT INTO `tags` VALUES (31, 'Xiao Lu', 182, 1, '2014-12-22 01:18:34', '2020-07-27 21:43:36', 873, 2);
INSERT INTO `tags` VALUES (32, 'Tam On Na', 661, 1, '2008-08-24 12:54:55', '2003-05-08 06:48:10', 190, 907);
INSERT INTO `tags` VALUES (33, 'Long Zitao', 831, 1, '2002-11-03 11:28:55', '2018-09-21 18:29:18', 262, 686);
INSERT INTO `tags` VALUES (34, 'Wu Xiuying', 963, 1, '2008-12-21 17:14:13', '2020-03-02 10:40:31', 565, 233);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '微信用户唯一标识',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

SET FOREIGN_KEY_CHECKS = 1;
