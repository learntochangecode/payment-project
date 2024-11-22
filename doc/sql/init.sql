#####  表结构及初始化数据SQL  #####
-- 系统权限表
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统权限表';

-- 角色表
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统角色表';

-- 角色权限关联表
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    `role_id` VARCHAR(32) NOT NULL COMMENT '角色ID',
    `permission_id` VARCHAR(64) NOT NULL COMMENT '权限ID'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色权限关联表';