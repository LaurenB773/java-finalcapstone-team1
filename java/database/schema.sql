BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, user_profile, workout_data, exercise, equipment;

CREATE TABLE users (
   user_id SERIAL,
   username varchar(50) NOT NULL UNIQUE,
   password_hash varchar(200) NOT NULL,
   role varchar(50) NOT NULL,
   email varchar(200) NOT NULL,
   CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE user_profile (
     profile_id SERIAL PRIMARY KEY,
     user_id INT REFERENCES users(user_id),
     profile_picture varchar(500) NOT NULL,
);

create table user_goals (
  goal varchar(50) not null,
  user_id int references users (user_id)
);


CREATE TABLE exercise(
  exercise_id SERIAL PRIMARY KEY,
  exercise_name varchar(100) NOT NULL,
  exercise_duration_minutes int,
  reps int NOT NULL,
  weight NUMERIC(5,2) NOT NULL,
  equipment varchar(50) NOT NULL
);

CREATE TABLE workout_data(
   workout_data_id SERIAL PRIMARY KEY,
   start_time DATE NOT NULL,
   end_time DATE,
   profile_id int REFERENCES user_profile(profile_id),
   exercise_id int REFERENCES exercise(exercise_id)
);

CREATE TABLE equipment(
 equipment_id SERIAL PRIMARY KEY,
 equipment_name varchar(100),
 used_time_minutes int
);


COMMIT TRANSACTION;