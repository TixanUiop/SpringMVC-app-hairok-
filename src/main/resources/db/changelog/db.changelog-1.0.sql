--liquibase formatted sql

--changeset eloshonkov:1
CREATE TABLE IF NOT EXISTS clients (
    id BIGSERIAL PRIMARY KEY,
    name varchar(100) NOT NULL CHECK (length(name) > 1),
    surname varchar(100) NOT NULL CHECK (length(surname) > 1),
    patronymic varchar(100) NOT NULL CHECK (length(patronymic) > 1),
    birthday date NOT NULL CHECK (CURRENT_DATE <> birthday),
    phone varchar(50) NOT NULL UNIQUE,
    city varchar(50) NOT NULL,
    created_at TIMESTAMP,
    modified_at TIMESTAMP,
    created_by VARCHAR(100),
    modified_by VARCHAR(100),
    password VARCHAR(250) NOT NULL
);
--changeset eloshonkov:2
CREATE TABLE IF NOT EXISTS master_rating (
    id BIGSERIAL PRIMARY KEY,
    client_id BIGINT NOT NULL REFERENCES clients(id),
    target_master_id BIGINT NOT NULL REFERENCES clients(id),
    rating INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 10)
);


--changeset eloshonkov:3
CREATE TABLE IF NOT EXISTS master_profiles (
    id BIGSERIAL PRIMARY KEY,
    name varchar(100) NOT NULL CHECK (length(name) > 1),
    surname varchar(100) NOT NULL CHECK (length(surname) > 1),
    patronymic varchar(100) NOT NULL CHECK (length(patronymic) > 1),
    birthday date NOT NULL CHECK (CURRENT_DATE <> birthday),
    phone varchar(50) NOT NULL UNIQUE,
    city varchar(50) NOT NULL,
    created_at TIMESTAMP,
    modified_at TIMESTAMP,
    created_by VARCHAR(100),
    modified_by VARCHAR(100),
    rate BIGINT REFERENCES master_rating(id)
);
--changeset eloshonkov:4
CREATE TABLE IF NOT EXISTS master_account (
    id BIGSERIAL PRIMARY KEY,
    avatar varchar(123) NOT NULL,
    password varchar(123) NOT NULL,
    id_masters_info BIGINT references master_profiles(id),
    email varchar(123) NOT NULL CHECK ( LENGTH(EMAIL) > 6),
    created_at TIMESTAMP,
    modified_at TIMESTAMP,
    created_by VARCHAR(100),
    modified_by VARCHAR(100)
);


--changeset eloshonkov:5
CREATE TABLE IF NOT EXISTS masters_works (
      id BIGSERIAL PRIMARY KEY,
      image_master_works INTEGER,
      title varchar(100) CHECK (length(title) > 3) NOT NULL,
      description text,
      date timestamp,
      created_at TIMESTAMP,
      modified_at TIMESTAMP,
      created_by VARCHAR(100),
      modified_by VARCHAR(100)
);
--changeset eloshonkov:6
CREATE TABLE IF NOT EXISTS image_master_works (
    id SERIAL PRIMARY KEY,
    masters_works_id BIGINT NOT NULL REFERENCES masters_works(id) ON DELETE CASCADE,
    url TEXT NOT NULL
);
--changeset eloshonkov:7
CREATE TABLE IF NOT EXISTS haircuts_orders (
     id BIGSERIAL PRIMARY KEY,
     client_id BIGINT NOT NULL REFERENCES clients(id),
     master_profiles_id BIGINT NOT NULL REFERENCES master_profiles(id),
     slot_id bigint references available_slots(id),
     created_at TIMESTAMP,
     modified_at TIMESTAMP,
     created_by VARCHAR(100),
     modified_by VARCHAR(100),
     status varchar(30) not null default 'активно'
);

--changeset eloshonkov:8
CREATE TABLE IF NOT EXISTS available_slots (
    id BIGSERIAL PRIMARY KEY,
    master_id  BIGINT NOT NULL REFERENCES master_profiles(id),
    slot_start TIME NOT NULL,
    slot_end TIME NOT NULL,
    id_master_schedule bigint references master_schedule(id)
);

--changeset eloshonkov:9
CREATE TABLE IF NOT EXISTS master_schedule (
    id BIGSERIAL PRIMARY KEY,
    master_id  BIGINT NOT NULL REFERENCES master_profiles(id),
    work_date DATE NOT NULL,
    time_start TIME NOT NULL,
    time_end TIME NOT NULL,
    is_day_off boolean,
    comment text,
    place_of_work text
);