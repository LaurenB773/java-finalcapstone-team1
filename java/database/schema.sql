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
  role VARCHAR(50) NOT NULL
);
CREATE TABLE user_profiles (
  user_profile_id SERIAL PRIMARY KEY,
  user_id INT REFERENCES users (user_id),
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  email VARCHAR(200),
  profile_picture VARCHAR(500) default '',
  goal VARCHAR(50)
);
CREATE TABLE equipments (
  equipment_id SERIAL PRIMARY KEY,
  equipment_name varchar(100),
  used_time_minutes INT
);
CREATE TABLE exercises (
  exercise_id SERIAL PRIMARY KEY,
  equipment_id INT references equipments(equipment_id),
  exercise_name varchar(100) NOT NULL,
  exercise_duration_minutes INT,
  reps INT NOT NULL,
  weight NUMERIC(5, 2) NOT NULL
);
CREATE TABLE workouts (
  workout_id SERIAL PRIMARY KEY,
  start_time TIMESTAMP NOT NULL,
  end_time TIMESTAMP,
  user_profile_id INT REFERENCES user_profiles (user_profile_id)
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