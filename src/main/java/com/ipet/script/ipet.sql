-- 建立資料庫 --
CREATE DATABASE IPETDB;
USE IPETDB;
-- drop schema IPETDB;

-- auto-generated definition
CREATE TABLE persistent_logins
(
    username  varchar(64) NOT NULL,
    series    varchar(64) NOT NULL
        PRIMARY KEY,
    token     varchar(64) NOT NULL,
    last_used timestamp   NOT NULL
);

-- 建立員工表格 --
CREATE TABLE IPETDB.STAFF (
                              STAFF_ID	  	int				Not Null primary key auto_increment,
                              STAFF_NAME 	varchar	(10)	Not Null,
                              STAFF_UID	    varchar	(10)	Not Null UNIQUE,
                              STAFF_BIRTH	date		Not Null,
                              STAFF_SEX	    char(1) Not Null,
                              STAFF_EMAIL		varchar	(50)	Not Null,
                              STAFF_PHONE		varchar	(10)	Not Null,
                              STAFF_TEL	    varchar	(10),
                              STAFF_ADD	    varchar	(100)	Not Null,
                              STAFF_AC	    varchar	(30)	Not Null UNIQUE,
                              STAFF_PW	    varchar	(30)	Not Null,
                              STAFF_STATUS	int		         default 0 Not Null,
                              STAFF_POSI	varchar	(10)	Not Null
);
-- drop table IPETDB.STAFF ;

insert into IPETDB.STAFF
(STAFF_NAME,STAFF_UID, STAFF_BIRTH, STAFF_SEX, STAFF_EMAIL,STAFF_PHONE,STAFF_ADD,STAFF_AC,STAFF_PW,STAFF_POSI)
values
    ('王曉明','A170952896','1981-11-17','男','xxxyyy@gmail.com',
     '0912345678','台北市天龍國','abc1117','0000','美容師'),
    ('王小美','A264653875','1997-01-07','女','aaabbb@gmail.com',
     '0923456789','桃園市中壢國','qazwsx0107','0000','美容師'),
    ('陳大壯','K167265918','1996-08-08','男','hihihi@gmail.com',
     '0934567891','苗栗國','abc0808','0000','美容助理'),
    ('張小菁','A282013860','1993-09-09','女','yoyoyo@gmail.com',
     '0945678912','台北市大安區','abc0909','0000','美容助理'),
    ('關小婷','F207913142','1997-06-06','女','goodproject@gmail.com',
     '0956789123','新北市林口區','abc0606','0000','美容助理'),
    ('蕭小豪','A114586283','1990-05-30','男','havemanymoney@gmail.com',
     '0967891230','台北市天龍國','abc0530','0000','美容助理'),
    ('吳小宏','A151865958','1975-01-01','男','wearegood@gmail.com',
     '0978912345','台北市天龍國','abc0101','0000','美容助理'),
    ('林曉華','A226491120','1975-01-01','女','momo@gmail.com',
     '0989123456','台北市天龍國','abc0102','0000','行政人員'),
    ('馬雲','A199029036','1975-01-01','男','china@gmail.com',
     '0990123456','台北市天龍國','abc0102ccc','0000','老闆');

-- 建立工作角色
CREATE TABLE IPETDB.JOB_ROLE(
                                ROLE_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                ROLE_NAME varchar(20) Not Null UNIQUE
);

INSERT INTO IPETDB.JOB_ROLE(ROLE_NAME)
VALUES ('負責人'), ('行政人事管理員'), ('美容管理員'), ('美容員工'), ('會員管理員');

-- 建立員工角色
CREATE TABLE IPETDB.STAFF_ROLE(
                                  STAFF_ID int NOT NULL,
                                  ROLE_ID int NOT NULL,
                                  CONSTRAINT pk_roleID_staffID PRIMARY KEY (ROLE_ID, STAFF_ID),
                                  CONSTRAINT fk_STAFF_ROLE_roleID FOREIGN KEY (ROLE_ID) REFERENCES IPETDB.JOB_ROLE(ROLE_ID),
                                  CONSTRAINT fk_STAFF_ROLE_staffID  FOREIGN KEY (STAFF_ID) REFERENCES IPETDB.STAFF(STAFF_ID)
);

INSERT INTO IPETDB.STAFF_ROLE(STAFF_ID, ROLE_ID)
VALUES (1,3),(1,4),
       (2,4),(2,5),
       (3,3),(3,4),
       (4,4),
       (5,4),
       (6,4),
       (7,4),(7,5),
       (8,2),
       (9,1);


-- 建立工作權限功能--
create table IPETDB.JOB_PERMISSION (
                                       PERMISSION_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                       PERMISSION_NAME VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO IPETDB.JOB_PERMISSION (PERMISSION_NAME)
VALUES ('消息編輯'),('消息檢視'),
       ('員工編輯'),('員工檢視'),
       ('美容班表編輯'),('美容班表檢視'),
       ('美容服務編輯'),('美容服務檢視'),
       ('美容優惠編輯'),('美容優惠檢視'),
       ('美容預約單編輯'),('美容預約單檢視'),
       ('會員編輯'),('會員檢視');
-- drop table IPETDB.STAFF_ADMIN_PER_FUNC;

-- 建立角色權限
CREATE TABLE IPETDB.STAFF_PERMISSION(
                                        ROLE_ID int NOT NULL,
                                        PERMISSION_ID int NOT NULL,
                                        CONSTRAINT pk_perID_roleID PRIMARY KEY (PERMISSION_ID, ROLE_ID),
                                        CONSTRAINT fk_STAFF_PERMISSION_perID FOREIGN KEY (PERMISSION_ID) REFERENCES IPETDB.JOB_PERMISSION(PERMISSION_ID),
                                        CONSTRAINT fk_STAFF_PERMISSION_roleID FOREIGN KEY (ROLE_ID) REFERENCES IPETDB.JOB_ROLE(ROLE_ID)
);

INSERT INTO IPETDB.STAFF_PERMISSION(ROLE_ID, PERMISSION_ID)
VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),
       (2,1),(2,2),(2,3),(2,4),
       (3,1),(3,2),(3,5),(3,6),(3,7),(3,8),(3,9),(3,10),(3,11),(3,12),
       (4,6),(4,8),(4,10),(4,12),
       (5,13),(5,14);





-- 最新消息 --
create table IPETDB.NEWS(
                            NEWS_ID	    int			 	Not Null primary key auto_increment,
                            NEWS_TITLE  varchar(100)	Not Null,
                            NEWS_TEXT	varchar	(1000)	Not Null,
                            NEWS_TIME	datetime default current_timestamp on update current_timestamp
);
--  drop table IPETDB.NEWS;

insert into IPETDB.NEWS(NEWS_TITLE, NEWS_TEXT)
values ('第一篇文章','安安大家你好!! rock and stone!');



-- 建立會員表格 --
create table IPETDB.MEMBER (
                               MEM_ID	    int				Not Null primary key auto_increment,
                               MEM_NAME	varchar	(10)	Not Null,
                               MEM_UID	    varchar	(10)	Not Null UNIQUE,
                               MEM_BIRTH	    date		Not Null,
                               MEM_SEX	    char(1) Not Null,
                               MEM_EMAIL	varchar	(50)	Not Null,
                               MEM_PHONE	varchar	(10)	Not Null,
                               MEM_TEL	    varchar	(10),
                               MEM_ADD	    varchar	(100)	Not Null,
                               MEM_AC	    varchar	(30)	Not Null UNIQUE,
                               MEM_PW	    varchar	(30)	Not Null,
                               MEM_STATUS	int		        default 0 Not Null
);
-- drop table IPETDB.MEMBER ;



-- 建立會員資料 --
insert into IPETDB.MEMBER
(MEM_NAME, MEM_UID, MEM_BIRTH, MEM_SEX, MEM_EMAIL, MEM_PHONE,
 MEM_ADD,MEM_AC,MEM_PW)
values
    ('連大戰','A123443212',STR_TO_DATE('1988-03-19','%Y-%m-%d'),'男','ggyy6969@gmail.com',
     '0923432125','台北市天龍國','ggyy6969','Password1'),
    ('陳大扁','A123344556',STR_TO_DATE('1999-06-09','%Y-%m-%d'),'男','7788ccgg@gmail.com',
     '0912334455','桃園市中壢國','7788ccgg','Password2'),
    ('馬小九','A126786688',STR_TO_DATE('1977-07-08','%Y-%m-%d'),'男','abc8877@gmail.com',
     '0912678668','苗栗國','abc8877','Password'),
    ('蔡筱英','A226699778',STR_TO_DATE('1993-08-07','%Y-%m-%d'),'女','hahaha3838@gmail.com',
     '0922669977','台北市大安區','hahaha3838','Password3'),
    ('連小文','A125556969',STR_TO_DATE('1995-07-11','%Y-%m-%d'),'男','lak14swang@gmail.com',
     '0912555696','新北市林口區','lak14swang','Password4'),
    ('高小安','A123321123',STR_TO_DATE('1994-09-21','%Y-%m-%d'),'女','thank9527@gmail.com',
     '0912332112','台北市天龍國','thank9527','Password5'),
    ('高小瑜','A125566778',STR_TO_DATE('1979-10-18','%Y-%m-%d'),'女','toyzdog@gmail.com',
     '0912556677','台北市天龍國','toyzdog','Password6');

-- 建立寵物表格 --
create table IPETDB.PET (
                            PET_ID   	    int	(10)		Not Null primary key auto_increment,
                            MEM_ID  	    int	(10)	    Not Null,
                            PET_NAME	    varchar	(30)	Not Null,
                            PET_VAR_ID	    varchar (20),
                            PET_SIZE	    varchar (10)    Not Null,
                            PET_GEN	        varchar	(1)	    Not Null,
                            PET_BIRTH	    date,
                            PET_STATUS	    varchar	(1)     Not Null,
                            constraint FK_PET_MEMBER
                                foreign key(MEM_ID) references MEMBER(MEM_ID)
);
-- drop table IPETDB.PET ;

-- 建立寵物資料 --
insert into IPETDB.PET
(MEM_ID, PET_NAME, PET_VAR_ID, PET_SIZE, PET_GEN, PET_BIRTH,
 PET_STATUS)
values
    ('1','Micky','米格魯','中型犬','公','2017-01-07','1'),
    ('1','Minnie','吉娃娃','小型犬','母','2019-05-05','1'),
    ('2','Luka','哈士奇','大型犬','公','2015-07-07','1'),
    ('3','LuLu','貴賓狗','小型犬','母','2019-08-18','1'),
    ('4','King','科基','中型犬','公','2019-05-05','1'),
    ('5','Pinky','馬爾濟斯','小型犬','母','2018-06-05','1'),
    ('5','JoJo','黃金獵犬','大型犬','母','2021-03-08','1');


-- 建立 美容服務類別 表格 --
create table IPETDB.SALON_SERVICE_CATEGORY (
                                               SVC_CATEGORY_ID		int (10)		Not Null primary key auto_increment,
                                               SVC_CATEGORY_NAME	varchar (40)	Not Null,
                                               SVC_CATEGORY_IMG	mediumblob,
                                               SVC_CATEGORY_STATUS	int (1)			Not Null default 1
);
-- drop table IPETDB.SALON_SERVICE_CATEGORY;

-- 建立 美容服務類別 資料 --
insert into IPETDB.SALON_SERVICE_CATEGORY
(SVC_CATEGORY_NAME)
values
    ('基礎美容'),
    ('造型美容'),
    ('頂級SPA');

-- 建立 美容服務寵物類別 表格 --
create table IPETDB.SALON_SERVICE_PET_TYPE (
                                               TYPE_ID		int (10)		Not Null primary key auto_increment,
                                               TYPE_NAME	varchar (40)	Not Null unique,
                                               PET_SIZE	varchar (10)	Not Null
);
-- drop table IPETDB.SALON_SERVICE_PET_TYPE;


-- 建立 美容服務寵物類別 資料 --
insert into IPETDB.SALON_SERVICE_PET_TYPE
(TYPE_NAME, PET_SIZE)
values
    ('貴賓狗', '小型犬'),('馬爾濟斯', '小型犬'),('巴哥犬', '小型犬'),
    ('科基', '中型犬'),('米格魯', '中型犬'),
    ('黃金獵犬', '大型犬'),('哈士奇', '大型犬')
;

-- 建立 美容服務 表格 --
create table IPETDB.SALON_SERVICE (
                                      SVC_ID			int (10)		Not Null primary key auto_increment,
                                      SVC_NAME		varchar (40)	Not Null,
                                      SVC_CONTENT		varchar (500),
                                      SVC_CATEGORY_ID	int (10)		Not Null,
                                      TYPE_ID			int (10)		Not Null,
                                      SVC_PRICE		int (10)		unsigned Not Null,
                                      SVC_STATUS		int (1)			Not Null default 1,
                                      constraint	FK_SALON_SERVICE_TO_CATEGORY foreign key (SVC_CATEGORY_ID)	references SALON_SERVICE_CATEGORY(SVC_CATEGORY_ID),
                                      constraint	FK_SALON_SERVICE_TO_PET_TYPE foreign key (TYPE_ID)			references SALON_SERVICE_PET_TYPE(TYPE_ID)
);
-- drop table IPETDB.SALON_SERVICE;

-- 建立 美容服務 資料 --
insert into IPETDB.SALON_SERVICE
(SVC_NAME, SVC_CONTENT, SVC_CATEGORY_ID, TYPE_ID, SVC_PRICE)
values
    -- id: 1-7
    ('經典洗澡護理', '基礎洗澡+簡單護理，含：剪指甲、清耳朵、小修頭型、小修足緣、剃腳底毛、剃肛門毛擠肛門腺', 1, 1, 1000),
    ('經典洗澡護理', '基礎洗澡+簡單護理，含：剪指甲、清耳朵、小修頭型、小修足緣、剃腳底毛、剃肛門毛擠肛門腺', 1, 2, 1200),
    ('經典洗澡護理', '基礎洗澡+簡單護理，含：剪指甲、清耳朵、小修頭型、小修足緣、剃腳底毛、剃肛門毛擠肛門腺', 1, 3, 1600),
    ('經典洗澡護理', '基礎洗澡+簡單護理，含：剪指甲、清耳朵、小修頭型、小修足緣、剃腳底毛、剃肛門毛擠肛門腺', 1, 4, 1900),
    ('經典洗澡護理', '基礎洗澡+簡單護理，含：剪指甲、清耳朵、小修頭型、小修足緣、剃腳底毛、剃肛門毛擠肛門腺', 1, 5, 2400),
    ('經典洗澡護理', '基礎洗澡+簡單護理，含：剪指甲、清耳朵、小修頭型、小修足緣、剃腳底毛、剃肛門毛擠肛門腺', 1, 6, 2800),
    ('經典洗澡護理', '基礎洗澡+簡單護理，含：剪指甲、清耳朵、小修頭型、小修足緣、剃腳底毛、剃肛門毛擠肛門腺', 1, 7, 1000),

    -- id: 8-14
    ('全身造型剪剃', '手剪頭型/電剪剃身體，含快速洗澡服務', 2, 1, 2000),
    ('全身造型剪剃', '手剪頭型/電剪剃身體，含快速洗澡服務', 2, 2, 2000),
    ('全身造型剪剃', '手剪頭型/電剪剃身體，含快速洗澡服務', 2, 3, 2200),
    ('全身造型剪剃', '手剪頭型/電剪剃身體，含快速洗澡服務', 2, 4, 2200),
    ('全身造型剪剃', '手剪頭型/電剪剃身體，含快速洗澡服務', 2, 5, 2600),
    ('全身造型剪剃', '手剪頭型/電剪剃身體，含快速洗澡服務', 2, 6, 2800),
    ('全身造型剪剃', '手剪頭型/電剪剃身體，含快速洗澡服務', 2, 7, 2700),

    -- id: 15-21
    ('膠原蛋白酵素SPA', '洗澡護理+膠原蛋白酵素SPA 油脂平衡，去角質保濕', 3, 1, 1200),
    ('膠原蛋白酵素SPA', '洗澡護理+膠原蛋白酵素SPA 油脂平衡，去角質保濕', 3, 2, 1400),
    ('膠原蛋白酵素SPA', '洗澡護理+膠原蛋白酵素SPA 油脂平衡，去角質保濕', 3, 3, 1800),
    ('膠原蛋白酵素SPA', '洗澡護理+膠原蛋白酵素SPA 油脂平衡，去角質保濕', 3, 4, 1800),
    ('膠原蛋白酵素SPA', '洗澡護理+膠原蛋白酵素SPA 油脂平衡，去角質保濕', 3, 5, 1800),
    ('膠原蛋白酵素SPA', '洗澡護理+膠原蛋白酵素SPA 油脂平衡，去角質保濕', 3, 6, 2000),
    ('膠原蛋白酵素SPA', '洗澡護理+膠原蛋白酵素SPA 油脂平衡，去角質保濕', 3, 7, 3200),

    -- id: 22-28
    ('法國玫瑰活膚泥SPA', '洗澡護理+法國玫瑰活膚泥SPA 緩敏去濕，皮毛保護', 3, 1, 1500),
    ('法國玫瑰活膚泥SPA', '洗澡護理+法國玫瑰活膚泥SPA 緩敏去濕，皮毛保護', 3, 2, 1600),
    ('法國玫瑰活膚泥SPA', '洗澡護理+法國玫瑰活膚泥SPA 緩敏去濕，皮毛保護', 3, 3, 1500),
    ('法國玫瑰活膚泥SPA', '洗澡護理+法國玫瑰活膚泥SPA 緩敏去濕，皮毛保護', 3, 4, 2200),
    ('法國玫瑰活膚泥SPA', '洗澡護理+法國玫瑰活膚泥SPA 緩敏去濕，皮毛保護', 3, 5, 2100),
    ('法國玫瑰活膚泥SPA', '洗澡護理+法國玫瑰活膚泥SPA 緩敏去濕，皮毛保護', 3, 6, 2600),
    ('法國玫瑰活膚泥SPA', '洗澡護理+法國玫瑰活膚泥SPA 緩敏去濕，皮毛保護', 3, 7, 2700);

-- 建立 美容優惠方案 表格 --
create table IPETDB.SALON_SALE (
                                   SALE_ID			int (10)		Not Null primary key auto_increment,
                                   SALE_NAME		varchar (40)	Not Null,
                                   SALE_CONTENT	varchar (500),
                                   START_TIME		datetime		Not Null,
                                   END_TIME		datetime		Not Null
);
-- drop table IPETDB.SALON_SALE;

-- 建立 美容優惠方案 資料 --
insert into IPETDB.SALON_SALE
(SALE_NAME, SALE_CONTENT, START_TIME, END_TIME)
values
    ('歡慶新年，頂級SPA全面95折', '即日起至1/31日預約頂級SPA享95折優惠', '2022-12-25 00:00:00', '2023-01-31 23:59:59'),
    ('歡慶開幕s，多項服務享150元折扣', '即日起至3/31日預約經典洗澡護理、全身造型剪剃折扣150元', '2022-12-25 00:00:00', '2023-03-31 23:59:59'),
    ('3/23小型犬享323元折扣', '歡慶3/23國際小狗日，當日上線預約小型犬全服務享323元折扣', '2022-03-23 00:00:00', '2023-03-23 23:59:59');

-- 建立 美容優惠服務細項 表格 --
create table IPETDB.SALON_SALE_DETAIL (
                                          SALE_ID		int (10)	Not Null,
                                          SVC_ID		int (10)	Not Null,
                                          SALE_PRICE	int (10)	unsigned Not Null,
                                          primary key	(SALE_ID, SVC_ID),
                                          constraint	FK_SALON_SALE_DETAIL_SALE_ID	foreign key (SALE_ID)	references SALON_SALE(SALE_ID),
                                          constraint	FK_SALON_SALE_DETAIL_SVC_ID	foreign key (SVC_ID)	references SALON_SERVICE(SVC_ID)
);
-- drop table IPETDB.SALON_SALE_DETAIL;

-- 建立 美容優惠服務細項 資料 --
insert into IPETDB.SALON_SALE_DETAIL
(SALE_ID, SVC_ID, SALE_PRICE)
values
    -- 頂級SPA優惠
    (1, 15, 1140),
    (1, 16, 1330),
    (1, 17, 1710),
    (1, 18, 1710),
    (1, 19, 1710),
    (1, 20, 1900),
    (1, 21, 3040),
    (1, 22, 1425),
    (1, 23, 1520),
    (1, 24, 1425),
    (1, 25, 2090),
    (1, 26, 1995),
    (1, 27, 2470),
    (1, 28, 2565),

    -- 經典洗澡護理優惠、全身造型剪剃
    (2, 8, 1850),
    (2, 9, 1850),
    (2, 10, 2050),
    (2, 11, 2050),
    (2, 12, 2450),
    (2, 13, 2650),
    (2, 14, 2550),

    -- 3/23小型犬享323元折扣
    (3, 1, 677),
    (3, 2, 877),
    (3, 3, 1277),
    (3, 8, 1677),
    (3, 9, 1677),
    (3, 10, 1877),
    (3, 15, 877),
    (3, 16, 1077),
    (3, 17, 1477),
    (3, 22, 1177),
    (3, 23, 1277),
    (3, 24, 1177);


-- 育菁的部分 --
-- 建立 美容員工班表 表格 --
CREATE TABLE IF NOT EXISTS IPETDB.SALON_SCHEDULE(
                                                    SCH_ID		INT (10)  NOT NULL  AUTO_INCREMENT PRIMARY KEY ,
                                                    SCH_DATE    DATE NOT NULL,
                                                    SCH_PERIOD  VARCHAR(10) NOT NULL,
                                                    GROOMER_ID  INT (10)  NOT NULL,
                                                    ASST_ID_1   INT (10)  NOT NULL,
                                                    ASST_ID_2   INT (10)  NOT NULL,
                                                    EMPLOYEE_NOTE  VARCHAR(500),
                                                    CONSTRAINT fk_SALON_SCHEDULE_GROOMER_ID FOREIGN KEY (GROOMER_ID) REFERENCES IPETDB.STAFF(STAFF_ID),
                                                    CONSTRAINT fk_SALON_SCHEDULE_ASST_ID_1 FOREIGN KEY (ASST_ID_1) REFERENCES IPETDB.STAFF(STAFF_ID),
                                                    CONSTRAINT fk_SALON_SCHEDULE_ASST_ID_2 FOREIGN KEY (ASST_ID_2) REFERENCES IPETDB.STAFF(STAFF_ID)
);
-- drop table IPETDB.SALON_SCHEDULE;

-- 建立 美容員工班表 資料 --
INSERT INTO  IPETDB.SALON_SCHEDULE (SCH_DATE, SCH_PERIOD, GROOMER_ID, ASST_ID_1, ASST_ID_2, EMPLOYEE_NOTE)
VALUES ('2022-12-26', '上午', 1, 3, 4, '王曉明負責開門，記得早點到!'),
       ('2022-12-26', '晚上', 1, 3, 4, '陳大壯負責關門'),
       ('2022-12-26', '晚上', 2, 6, 7, ''),

       ('2022-12-27', '上午', 2, 5, 7, '吳小宏負責開門，王小美要先處理垃圾'),
       ('2022-12-27', '上午', 1, 3, 6, ''),
       ('2022-12-27', '下午', 2, 5, 7, ''),
       ('2022-12-27', '下午', 1, 3, 6, ''),
       ('2022-12-27', '晚上', 2, 5, 7, ''),
       ('2022-12-27', '晚上', 1, 3, 4, '王曉明負責關門'),

       ('2022-12-29', '上午', 1, 3, 5, '陳大壯負責開門'),
       ('2022-12-29', '上午', 2, 4, 7, '蕭小豪請假，張小菁代班'),
       ('2022-12-29', '下午', 2, 4, 7, '蕭小豪請假，張小菁代班'),
       ('2022-12-29', '下午', 1, 5, 6, '關小婷負責關門'),

       ('2023-01-02', '上午', 1, 4, 7, '王曉明負責開門'),
       ('2022-01-02', '上午', 2, 3, 5, ''),
       ('2023-01-02', '下午', 1, 4, 7, ''),
       ('2022-01-02', '下午', 2, 3, 5, ''),
       ('2023-01-02', '晚上', 1, 4, 7, ''),
       ('2022-01-02', '晚上', 2, 3, 5, '王小美負責關門'),

       ('2023-01-03', '上午', 1, 4, 7, '王曉明負責開門'),
       ('2022-01-03', '上午', 2, 3, 5, ''),
       ('2023-01-03', '下午', 1, 4, 7, ''),
       ('2022-01-03', '下午', 2, 3, 5, ''),
       ('2023-01-03', '晚上', 1, 4, 7, ''),
       ('2022-01-03', '晚上', 2, 3, 5, '王小美負責關門'),

       ('2023-01-04', '上午', 1, 6, 5, '王曉明負責開門'),
       ('2022-01-04', '上午', 2, 4, 7, ''),
       ('2023-01-04', '下午', 1, 3, 5, ''),
       ('2022-01-04', '下午', 2, 4, 7, '蕭小豪請假，張小菁代班'),
       ('2023-01-04', '晚上', 1, 6, 3, ''),

       ('2023-01-05', '上午', 1, 4, 7, '王曉明負責開門'),
       ('2022-01-05', '上午', 2, 3, 5, ''),
       ('2023-01-05', '下午', 1, 4, 7, ''),
       ('2022-01-05', '下午', 2, 5, 6, '王小美負責關門'),


       ('2023-01-09', '上午', 1, 6, 5, '王曉明負責開門'),
       ('2022-01-09', '上午', 2, 4, 7, ''),
       ('2023-01-09', '下午', 1, 3, 5, ''),
       ('2022-01-09', '下午', 2, 4, 7, '蕭小豪請假，張小菁代班'),
       ('2023-01-09', '晚上', 1, 6, 3, ''),
       ('2022-01-09', '晚上', 2, 4, 7, '張小菁負責關門'),

       ('2023-02-21', '上午', 1, 6, 5, '王曉明負責開門'),
       ('2022-02-21', '上午', 2, 4, 7, ''),
       ('2023-02-21', '下午', 1, 3, 5, ''),
       ('2023-02-21', '晚上', 1, 3, 5, ''),

       ('2022-03-01', '上午', 2, 4, 7, ''),
       ('2023-03-01', '下午', 1, 3, 5, ''),
       ('2022-03-01', '下午', 2, 4, 7, '蕭小豪請假，張小菁代班'),
       ('2022-03-01', '晚上', 2, 4, 7, '蕭小豪請假，張小菁代班')
;



-- 建立 美容預約單 表格 --
CREATE TABLE IF NOT EXISTS IPETDB.SALON_APPOINTMENT (
                                                        APM_ID		INT (10)  NOT NULL  AUTO_INCREMENT PRIMARY KEY ,
                                                        APM_STATUS	INT (1)	 NOT NULL,
                                                        MEM_ID INT(10) NOT NULL,
                                                        PET_ID INT(10) NOT NULL,
                                                        SCH_ID INT(10) NOT NULL,
                                                        APM_BUILD_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                        CUSTOMER_NOTE VARCHAR(400),
                                                        TOTAL_PRICE INT(10) Not Null,
                                                        CONSTRAINT fk_SALON_APPOINTMENT_MEM_ID FOREIGN KEY (MEM_ID) REFERENCES IPETDB.MEMBER(MEM_ID),
                                                        CONSTRAINT fk_SALON_APPOINTMENT_PET_ID FOREIGN KEY (PET_ID) REFERENCES IPETDB.PET(PET_ID),
                                                        CONSTRAINT fk_SALON_APPOINTMENT_SCH_ID FOREIGN KEY (SCH_ID) REFERENCES IPETDB.SALON_SCHEDULE(SCH_ID)


);
-- drop table IPETDB.SALON_APPOINTMENT;

-- 建立 美容預約單 資料 --
INSERT INTO IPETDB.SALON_APPOINTMENT (APM_STATUS, MEM_ID, PET_ID, SCH_ID, APM_BUILD_TIME, CUSTOMER_NOTE, TOTAL_PRICE)
VALUES
    -- 2022-12-26
    ('1', 1, 1, 1, '2022-11-20 14:12:10', 'Micky 比較過動，需要用零食吸引注意力', 2250),
    ('2', 2, 3, 2, '2022-11-11 16:23:47', 'Luka怕生', 2650),
    ('1', 5, 6, 3, '2022-12-13 08:50:09', 'Pinky會咬人喔!', 1330),
    -- 2022-12-27
    ('1', 5, 7, 4, '2022-12-13 07:12:56', 'JoJo KoNoDioDa', 1900),
    ('1', 3, 4, 5, '2022-11-29 19:56:24', 'LuLu!', 850),
    ('3', 5, 6, 8, '2022-12-20 13:25:41', '', 2565),

    ('1', 1, 1, 9, '2022-12-01 02:14:05', '', 2250),
    -- 2022-12-29
    ('1', 2, 3, 10, '2022-12-12 09:08:04', '', 850),
    ('2', 5, 6, 11, '2022-12-26 12:04:03', '', 1330),
    ('2', 5, 7, 12, '2022-12-09 15:16:32', '', 1900),
    -- 2023-01-03
    ('1', 3, 4, 20, '2022-12-28 11:14:12', '', 850),
    -- 2023-02-20--
    ('0', 5, 6, 41, '2023-01-13 17:12:56', 'JoJo KoNoDioDa', 2565),

    ('0', 3, 4, 43, '2023-01-29 19:01:24', '', 1850),
    -- 2023-03-01--
    ('0', 5, 6, 45, '2023-02-14 12:22:41', '', 1850),
    ('0', 4, 5, 48, '2023-02-20 12:14:05', 'King!', 4000),
    ('0', 3, 4, 44, '2023-02-10 19:01:24', '', 1850)
;





-- 建立 美容預約單明細 表格 --
CREATE TABLE IF NOT EXISTS IPETDB.SALON_APPOINTMENT_DETAIL(
                                                              APM_ID		INT (10)  NOT NULL,
                                                              SVC_ID        INT (10)  NOT NULL,
                                                              SALE_ID       INT (10),
                                                              SVC_PRICE     INT (10)  UNSIGNED NOT NULL,
                                                              SALE_PRICE    INT (10)  UNSIGNED NOT NULL,
                                                              CONSTRAINT pk_SALON_APPOINTMENT_DETAIL_APM_ID_SVC_ID PRIMARY KEY (APM_ID, SVC_ID),
                                                              CONSTRAINT fk_SALON_APPOINTMENT_DETAIL_SALE_ID FOREIGN KEY (SALE_ID) REFERENCES IPETDB.SALON_SALE(SALE_ID),
                                                              CONSTRAINT fk_SALON_APPOINTMENT_DETAIL_APM_ID FOREIGN KEY (APM_ID) REFERENCES IPETDB.SALON_APPOINTMENT(APM_ID),
                                                              CONSTRAINT fk_SALON_APPOINTMENT_DETAIL_SVC_ID FOREIGN KEY (SVC_ID) REFERENCES IPETDB.SALON_SERVICE(SVC_ID)
);
-- drop table IPETDB.SALON_APPOINTMENT_DETAIL;


-- 建立 美容預約單明細 資料 --
INSERT INTO IPETDB.SALON_APPOINTMENT_DETAIL(APM_ID, SVC_ID, SALE_ID, SVC_PRICE, SALE_PRICE)
VALUES (1, 5, 2, 2400, 2250),
       (2, 7, 2, 1000, 850),
       (3, 16, 1, 1400, 1330),
       (4, 20, 2, 2000, 1900),
       (5, 1, 2, 1000, 850),
       (6, 15, 1, 1200, 1140),
       (6, 22, 1, 1500, 1425),

       (7, 5, 2, 2400, 2250),
       (8, 7, 2, 1000, 850),
       (9, 16, 1, 1400, 1330),
       (10, 20, 2, 2000, 1900),
       (11, 1, 2, 1000, 850),
       (12, 15, 1, 1200, 1140),
       (12, 22, 1, 1500, 1425),


       (13, 8, 2, 2000,  1850),
       (14, 9, 2, 2000,  1850),
       (15, 18, null, 1800,  1800),
       (15, 25, null, 2200,  2200),
       (16, 8, 2, 2800,  1850);
