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

INSERT INTO exercises (exercise_id, equipment_id, exercise_name, sets, reps, weightLbs)
VALUES (1, 1, 'Bench Press', 4, 8, 30);

INSERT INTO workouts (workout_id, user_id, start_time, end_time)
VALUES (1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO workout_exercises (workout_id, exercise_id)
VALUES (1, 1);

INSERT INTO schedules (title, instructor, description, duration_minutes) VALUES ('Beginner Class With Gill', 'Gill', 'Gill will stretch you out and show you some beginner lifts. Class is open to all', 90);
INSERT INTO schedules (title, instructor, description, duration_minutes) VALUES ('Cardio With Max', 'Max', 'Max chases you through the gym', 30);
COMMIT TRANSACTION;