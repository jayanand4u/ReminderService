create schema rem;
create table rem.REMINDER (
	REMINDER_ID INTEGER NOT NULL,
	REMINDER_NAME VARCHAR(100) NOT NULL,
	REMINDER_DESCRIPTION VARCHAR(200) NOT NULL,
	REMINDER_STATUS  VARCHAR(50) NOT NULL,
	DUE_DATE DATE NOT NULL,
	CREATED_TIME		TIMESTAMP NOT NULL,
	UPDATED_TIME		TIMESTAMP NOT NULL,
	PRIMARY KEY (REMINDER_ID)
);

CREATE SEQUENCE rem.REMINDER_SEQ START WITH 1 INCREMENT BY 1


