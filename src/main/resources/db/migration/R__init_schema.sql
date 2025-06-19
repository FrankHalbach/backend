create table app_user (
    id           raw(16) primary key,
    app_user_id  varchar2(255 char) not null unique,
    first_name   varchar2(255 char) not null,
    last_name    varchar2(255 char) not null,
    email        varchar2(255 char) not null unique,
    user_status  varchar2(64 char)  not null
);

create table user_group (
    id     raw(16) primary key,
    title  varchar2(255 char) not null
);

create table team (
    id     raw(16) primary key,
    title  varchar2(255 char) not null
);

create table team_member (
    team_id     raw(16) not null,
    user_id     raw(16) not null,
    access_type varchar2(64 char) not null,
    primary key (team_id, user_id),
    constraint fk_team_member_team foreign key (team_id) references team(id),
    constraint fk_team_member_user foreign key (user_id) references app_user(id)
);

create table user_group_members (
    user_group_id raw(16) not null,
    team_id       raw(16) not null,
    primary key (user_group_id, team_id),
    constraint fk_user_group_members_group foreign key (user_group_id) references user_group(id),
    constraint fk_user_group_members_team foreign key (team_id) references team(id)
);

create table project (
    id              raw(16) primary key,
    project_number  varchar2(255 char) not null unique,
    project_title   varchar2(255 char) not null,
    project_status  varchar2(64 char)  not null,
    user_group_id   raw(16)            not null,
    constraint fk_project_usergroup foreign key (user_group_id) references user_group(id)
);

create table plant (
    id           raw(16) primary key,
    code         varchar2(255 char) not null unique,
    name         varchar2(255 char) not null,
    created_at   timestamp with time zone not null,
    created_by   varchar2(255 char) not null,
    modified_at  timestamp with time zone,
    modified_by  varchar2(255 char)
);

create table audit_log (
    id            raw(16) primary key,
    user_id       raw(16) not null,
    request_type  varchar2(255 char) not null,
    request_body  clob,
    timestamp     timestamp with time zone not null,
    constraint fk_audit_log_user foreign key (user_id) references app_user(id)
);
