create table bank (
    num NUMBER(20) not null,
    id VARCHAR2(20) not null,
    name VARCHAR2(20) not null,
    balance VARCHAR2(100) not null
);


create SEQUENCE bank_num_seq INCREMENT by 1 START WITH 1 MINVALUE 1 MAXVALUE 100;

select * from bank;

------------------------------------------------------------------------------------------

create table account(
    no number PRIMARY key,
    name VARCHAR2(20) not null,
    account_num VARCHAR2(100) not null,
    money number DEFAULT 0
);

insert into account VALUES(1, '홍길동', '1234-5678', '100000');
insert into account VALUES(2, '임꺽정', '5678-1234', '100000');
commit;
