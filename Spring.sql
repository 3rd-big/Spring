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



---------------------------------------------------------------------------------------------------
drop table board;
drop table member;

-- 회원

CREATE TABLE member (
        NUM number(20) not null,
        id VARCHAR(20), 
        pw VARCHAR(20), 
        name VARCHAR2(20),
        jumin1 VARCHAR2(20) ,
        jumin2 VARCHAR2(20) ,
        email VARCHAR2(100) ,
        zipcode VARCHAR2(20) ,
        addr VARCHAR2(100) ,
        JOB VARCHAR2(20) ,
        MAILING VARCHAR2(20)  ,
        INTEREST VARCHAR2(20)  ,
        MEMBER_LEVEL VARCHAR2(20)  ,
        REGISTER_DATE DATE 
        
);

create table zipcode(
    zipcode VARCHAR2(7),
    sido VARCHAR2(10),
    gugun VARCHAR2(20),
    dong VARCHAR2(30),
    ri VARCHAR2(15),
    bunji VARCHAR2(17)
);

CREATE SEQUENCE member_num_seq
INCREMENT BY 1 
START WITH 1 
MINVALUE 1 
                               
MAXVALUE 100
;




--게시판

CREATE TABLE board (
        BOARD_NUMBER number(20) not null,
        WRITER VARCHAR(20), 
        SUBJECT VARCHAR(20), 
        EMAIL VARCHAR2(20) ,
        CONTENT VARCHAR2(1000) ,
        PASSWORD VARCHAR2(20) ,
        WRITE_DATE DATE ,
        READ_COUNT number(20)  ,
        GROUP_NUMBER number(20)  ,
        SEQUENCE_NUMBER number(20)  ,
        SEQUENCE_LEVEL number(20),
        FILE_NAME VARCHAR2(100),
        PATH VARCHAR2(100),
        FILE_SIZE VARCHAR2(100)
        
);

CREATE SEQUENCE board_num_seq
INCREMENT BY 1 
START WITH 1 
MINVALUE 1 
                               
MAXVALUE 100
;


commit;



