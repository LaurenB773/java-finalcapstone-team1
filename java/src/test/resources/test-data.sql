BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');


INSERT INTO user_profiles (first_name, last_name, email, goal, user_id)
VALUES ('name1', 'last1', 'user1@gmail.com', 'weight loss', 1),
       ('name2', 'last2', 'user2@gmail.com', 'strength', 2);

COMMIT TRANSACTION;
