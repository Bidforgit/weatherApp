CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       login VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX idx_users_login ON Users (login);


CREATE TABLE locations (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           user_id BIGINT NOT NULL,
                           latitude DECIMAL(10, 8) NOT NULL,
                           longitude DECIMAL(11, 8) NOT NULL,
                           CONSTRAINT fk_locations_user FOREIGN KEY (user_id) REFERENCES Users(ID)

);

-- Add an index on UserId for faster lookups
CREATE INDEX idx_locations_userid ON Locations (user_id);

-- Optional: Add a composite index on (UserId, Name)
CREATE INDEX idx_locations_userid_name ON Locations (user_id, name);


CREATE TABLE sessions (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          expires_at TIMESTAMP NOT NULL,
                          CONSTRAINT fk_sessions_user FOREIGN KEY (user_id) REFERENCES Users(ID)

);

-- Add an index on UserId for faster lookups
CREATE INDEX idx_sessions_userid ON Sessions (user_id);

-- Add an index on ExpiresAt for querying active sessions
CREATE INDEX idx_sessions_expiresat ON Sessions (expires_at);

