BEGIN TRANSACTION;
INSERT INTO users (username, password_hash, role)
VALUES (
    'user',
    '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC',
    'ROLE_USER'
  );
INSERT INTO users (username, password_hash, role)
VALUES (
    'admin',
    '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC',
    'ROLE_ADMIN'
  );
INSERT INTO user_profiles (
    user_id,
    first_name,
    last_name,
    email,
    profile_picture,
    goal
  )
VALUES (
    1,
    'first',
    'last',
    'email',
    '""',
    'Not Sure'
  );

INSERT INTO equipments (equipment_id, equipment_name, used_time_minutes)
VALUES (1, 'Barbell', 0);

INSERT INTO equipments (equipment_id, equipment_name, used_time_minutes)
VALUES (2, 'Bench', 0);

INSERT INTO equipments (equipment_id, equipment_name, used_time_minutes)
VALUES (3, 'Dumbbell', 0);

INSERT INTO exercises (exercise_id, exercise_name, exercise_duration_minutes, sets, reps, weight_lbs)
VALUES (1, 'Bench Press', 30, 3, 10, 135);

INSERT INTO exercises (exercise_id, exercise_name, exercise_duration_minutes, sets, reps, weight_lbs)
VALUES (2, 'Squat', 30, 3, 10, 135);

INSERT INTO exercises (exercise_id, exercise_name, exercise_duration_minutes, sets, reps, weight_lbs)
VALUES (3, 'Deadlift', 30, 3, 10, 135);

INSERT INTO exercises (exercise_id, exercise_name, exercise_duration_minutes, sets, reps, weight_lbs)
VALUES (4, 'Bicep Curl', 30, 3, 10, 135);

INSERT INTO exercise_equipments (exercise_id, equipment_id)
VALUES (1, 1);

INSERT INTO exercise_equipments (exercise_id, equipment_id)
VALUES (1, 2);

INSERT INTO exercise_equipments (exercise_id, equipment_id)
VALUES (2, 1);

INSERT INTO exercise_equipments (exercise_id, equipment_id)
VALUES (3, 1);

INSERT INTO exercise_equipments (exercise_id, equipment_id)
VALUES (4, 2);

INSERT INTO exercise_equipments (exercise_id, equipment_id)
VALUES (4, 3);

INSERT INTO workouts (workout_id, user_id, start_time, end_time)
VALUES (1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO workout_exercises (workout_id, exercise_id)
VALUES (1, 1);

INSERT INTO schedules (title, instructor, description, duration_minutes) VALUES ('Beginner Class With Gill', 'Gill', 'Gill will stretch you out and show you some beginner lifts. Class is open to all', 90);
INSERT INTO schedules (title, instructor, description, duration_minutes) VALUES ('Cardio With Max', 'Max', 'Max chases you through the gym', 30);



COMMIT TRANSACTION;