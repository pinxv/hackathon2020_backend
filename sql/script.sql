create table if not exists AdminUser
(
    id       int auto_increment
        primary key,
    username varchar(32) not null,
    password varchar(32) null,
    constraint AdminUser_username_uindex
        unique (username)
);

create table if not exists Cargo
(
    id           int auto_increment
        primary key,
    serialNumber varchar(512) not null,
    description  varchar(32)  not null,
    creator      varchar(32)  not null,
    destination  varchar(128) null
);

create table if not exists CargoBatch
(
    id          int auto_increment
        primary key,
    batchNumber varchar(256)         not null,
    description varchar(32)          not null,
    sum         int                  not null,
    creator     varchar(32)          not null,
    destination varchar(64)          not null,
    isSafe      tinyint(1) default 1 null,
    place       varchar(64)          not null
);

create table if not exists ChangeCargoInfo
(
    id          int auto_increment
        primary key,
    place       varchar(128) not null,
    sum         int          not null,
    batchNumber varchar(256) not null,
    description varchar(32)  not null,
    destination varchar(128) null,
    creator     varchar(32)  not null,
    timestamp   timestamp    not null
);

create table if not exists HighRiskArea
(
    id        int auto_increment
        primary key,
    area      varchar(66) null comment '风险地区名称',
    latitude  float       null comment '纬度',
    longitude float       null comment '经度',
    riskLevel int         null comment '风险等级',
    adcode    int(10)     null comment 'area number'
);

create table if not exists News
(
    id          int auto_increment
        primary key,
    areaid      int          null,
    description varchar(512) null,
    url         varchar(512) null,
    title       varchar(256) null
);

create table if not exists RiskLevel
(
    id        int auto_increment
        primary key,
    province  varchar(60) not null,
    city      varchar(60) not null,
    block     varchar(60) not null,
    latitude  float       null comment '纬度',
    longitude float       null comment '经度',
    riskLevel int         null
);

create table if not exists UnsafeCargoBatch
(
    id                  int auto_increment
        primary key,
    batchNum            varchar(256) not null,
    highRiskAreaId      int          not null,
    unsafeChangeCargoId int          null,
    actualPlace         varchar(256) null,
    highRiskPlace       varchar(256) null
);


