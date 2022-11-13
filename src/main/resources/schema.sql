create table chatting_account (
    id      BIGINT UNSIGNED AUTO_INCREMENT,
    name    VARCHAR(40) UNIQUE,
    PRIMARY KEY (id)
);

##----------------------------------------------------------------

insert into chatting_account(id, name) values (null, 'test1');
insert into chatting_account(id, name) values (null, 'test2');
insert into chatting_account(id, name) values (null, 'test3');