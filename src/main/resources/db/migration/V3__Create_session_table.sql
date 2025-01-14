CREATE TABLE sessions (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         expires_at TIMESTAMP NOT NULL
);
