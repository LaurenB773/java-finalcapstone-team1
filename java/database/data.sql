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
COMMIT TRANSACTION;