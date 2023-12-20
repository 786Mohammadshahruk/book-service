drop table IF EXISTS USERS;
create TABLE USERS
(
    USER_ID                   INTEGER PRIMARY KEY,
    NAME                 VARCHAR(100),
    USER_NAME                 VARCHAR(100),
    MOBILE_NUMBER                 VARCHAR(100),
    EMAIL                      VARCHAR(100),
    PASSWORD                    VARCHAR(100)
)
