BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');


INSERT INTO user_profiles (first_name, last_name, email, goal, user_id)
VALUES ('first1', 'last1', 'user1@gmail.com', 'weight loss', 1),
       ('first2', 'last2', 'user2@gmail.com', 'strength', 2);


INSERT INTO equipments (equipment_name, used_time_minutes)
VALUES ('barbell', 30 ), ('dumbbell', 50  ) ;

INSERT INTO exercises (exercise_name, exercise_duration_minutes, sets, reps, weight_lbs)
VALUES ('bench press', 20 , 4, 12 , 60),
       ('squat', 18, 3, 10, 70),
       ('squat', 18, 10, 3 , 70);
INSERT INTO user_exercises (user_id, exercise_id)
VALUES (1, 1) , (2, 2) , (1, 3);

COMMIT TRANSACTION;
