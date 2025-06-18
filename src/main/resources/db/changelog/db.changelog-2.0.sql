--liquibase formatted sql

--changeset eloshonkov:1
ALTER TABLE master_account ALTER COLUMN avatar DROP NOT NULL;

--changeset eloshonkov:2
ALTER TABLE master_account DROP CONSTRAINT master_account_id_masters_info_fkey;
ALTER TABLE master_account
ADD CONSTRAINT master_account_id_masters_info_fkey
FOREIGN KEY (id_masters_info)
REFERENCES master_profiles(id)
ON DELETE CASCADE;