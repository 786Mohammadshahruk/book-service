DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS
(
    BOOK_ID                   INTEGER PRIMARY KEY,
    ISBN                      VARCHAR(100) UNIQUE,
    BOOK_NAME                 VARCHAR(100),
    DESCRIPTION               VARCHAR(100),
    AUTHOR                    VARCHAR(100),
    PUBLICATION_YEAR          INTEGER,
    SMALL_IMAGE_URL           VARCHAR(500),
    LARGE_IMAGE_URL           VARCHAR(500),
    PRICE                     FLOAT,
    NUMBER_OF_AVAILABLE_BOOKS INTEGER,
    RATING                    FLOAT

)
