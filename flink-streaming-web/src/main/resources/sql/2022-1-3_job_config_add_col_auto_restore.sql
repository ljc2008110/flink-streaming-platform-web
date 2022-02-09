-- author: Kevin.Lin
-- date: 2022-1-3 10:18:05

ALTER TABLE `flink_web`.`job_config`
ADD COLUMN `auto_restore` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '自动备份/恢复savepoint，1:开启 0: 关闭' AFTER `editor`;
