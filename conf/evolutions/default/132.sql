# --- !Ups
ALTER TABLE MEMBER ADD COLUMN PLAN VARCHAR(30);
ALTER TABLE MEMBER ADD COLUMN YEARLY TINYINT(1) DEFAULT 1;

# --- !Downs
ALTER TABLE MEMBER DROP COLUMN PLAN;
ALTER TABLE MEMBER DROP COLUMN YEARLY;
