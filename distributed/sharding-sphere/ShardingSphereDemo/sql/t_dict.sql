-- 在三个库中创建
CREATE TABLE `t_dict`  (
  `dict_id` bigint(0) PRIMARY KEY NOT NULL,
  `ustatus` varchar(100) NOT NULL,
  `uvalue` varchar(100) NOT NULL
);
-- 在userdb中创建
CREATE TABLE `t_dict_1`  (
  `dict_id` bigint(0) PRIMARY KEY NOT NULL,
  `ustatus` varchar(100) NOT NULL,
  `uvalue` varchar(100) NOT NULL
);
CREATE TABLE `t_dict_2`  (
  `dict_id` bigint(0) PRIMARY KEY NOT NULL,
  `ustatus` varchar(100) NOT NULL,
  `uvalue` varchar(100) NOT NULL
);