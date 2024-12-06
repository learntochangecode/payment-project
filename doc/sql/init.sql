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
    `role_id`       VARCHAR(32) NOT NULL COMMENT '角色ID',
    `permission_id` VARCHAR(64) NOT NULL COMMENT '权限ID'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色权限关联表';

-- 系统操作日志表
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
    `sys_log_id`    int(11)      NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id`       bigint(20)            DEFAULT NULL COMMENT '系统用户ID',
    `user_name`     varchar(32)           DEFAULT NULL COMMENT '用户姓名',
    `user_ip`       varchar(128) NOT NULL DEFAULT '' COMMENT '用户IP',
    `sys_type`      varchar(8)   NOT NULL COMMENT '所属系统： MGR-运营平台, MCH-商户中心',
    `method_name`   varchar(128) NOT NULL DEFAULT '' COMMENT '方法名',
    `method_remark` varchar(128) NOT NULL DEFAULT '' COMMENT '方法描述',
    `req_url`       varchar(256) NOT NULL DEFAULT '' COMMENT '请求地址',
    `opt_req_param` TEXT                  DEFAULT NULL COMMENT '操作请求参数',
    `opt_res_info`  TEXT                  DEFAULT NULL COMMENT '操作响应结果',
    `created_at`    TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    PRIMARY KEY (`sys_log_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '系统操作日志表';

-- 系统操作日志表
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`    bigint(11)      NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_name`     varchar(32)           DEFAULT NULL COMMENT '用户姓名',
    `created_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `created_by`    VARCHAR(20) NOT NULL COMMENT '创建人',
    `updated_by`    VARCHAR(20) NOT NULL COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '系统操作日志表';
