create table if not exists user_centers.user
(
    id           bigint auto_increment comment 'ID 编号'
    primary key,
    username     varchar(255)  default '0'               not null,
    avatarUrl    varchar(1024) default '0'               not null,
    userAccount  varchar(255)                            null,
    gender       tinyint       default 0                 not null,
    userPassword varchar(512)                            not null,
    phone        varchar(128)  default '0'               not null,
    email        varchar(512)  default '0'               not null,
    userStatus   int           default 0                 not null,
    userRole     int           default 0                 not null comment '用户角色 0 - 普通用户 1 - 管理员',
    createTime   datetime      default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime      default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint       default 0                 not null
    )
    comment '用户表';

