-- 在userdb中创建
CREATE TABLE `t_user`  (
  `user_id` bigint(0) PRIMARY KEY NOT NULL,
  `username` varchar(100) NOT NULL,
  `ustatus` varchar(50) NOT NULL,
  `uage` int(3)
);

CREATE TABLE `t_user_1`  (
  `user_id` bigint(0) PRIMARY KEY NOT NULL,
  `username` varchar(100) NOT NULL,
  `ustatus` varchar(50) NOT NULL,
  `uage` int(3)
);
CREATE TABLE `t_user_2`  (
  `user_id` bigint(0) PRIMARY KEY NOT NULL,
  `username` varchar(100) NOT NULL,
  `ustatus` varchar(50) NOT NULL,
  `uage` int(3)
);