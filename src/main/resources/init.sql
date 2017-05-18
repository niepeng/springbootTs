
create database springbootts;
use springbootts;


CREATE TABLE `springbootts_user` (
`id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
`gmt_modified` datetime ,
`name` varchar(128),
`age` int  not null default 0 ,
`gmt_create` datetime ,
`deleted` tinyint  not null  default 0
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


