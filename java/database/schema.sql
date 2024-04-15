BEGIN TRANSACTION;

DROP TABLE IF EXISTS users,
user_profiles,
equipments,
exercises,
workouts,
workout_exercises,
schedules;

CREATE TABLE users (
  user_id SERIAL PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password_hash VARCHAR(200) NOT NULL,
  role VARCHAR(150) NOT NULL
);

CREATE TABLE user_profiles (
  user_profile_id SERIAL PRIMARY KEY,
  user_id INT REFERENCES users (user_id),
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) DEFAULT '',
  profile_picture TEXT DEFAULT '',
  goal VARCHAR(100)
);

CREATE TABLE equipments (
  equipment_id SERIAL PRIMARY KEY,
  equipment_name varchar(100) NOT NULL,
  used_time_minutes INT DEFAULT 0
);

CREATE TABLE exercises (
  exercise_id SERIAL PRIMARY KEY,
  user_id INT REFERENCES users (user_id),
  exercise_name varchar(100) NOT NULL,
  exercise_duration_minutes INT NOT NULL,
  sets INT NOT NULL,
  reps INT NOT NULL,
  weight_lbs NUMERIC(5, 2) NOT NULL
);

CREATE TABLE workouts (
  workout_id SERIAL PRIMARY KEY,
  start_time TIMESTAMP NOT NULL,
  end_time TIMESTAMP,
  user_id INT REFERENCES users (user_id)
);

CREATE TABLE workout_exercises(
  workout_id INT REFERENCES workouts (workout_id) NOT NULL,
  exercise_id INT REFERENCES exercises (exercise_id) NOT NULL
);

CREATE TABLE schedules (
  schedule_id SERIAL PRIMARY KEY,
  title VARCHAR(100) default 'title',
  instructor VARCHAR(50) default 'teacher' NOT NULL,
  description VARCHAR (200),
  class_time TIMESTAMP default CURRENT_TIMESTAMP,
  duration_minutes INT default 30
);

CREATE TABLE checkins (
  checkin_id SERIAL PRIMARY KEY,
  user_id INT REFERENCES users (user_id),
  checkin_time TIMESTAMP,
  checkout_time TIMESTAMP
);

COMMIT TRANSACTION;