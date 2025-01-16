CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL
);

INSERT INTO users (name, email) VALUES
                                    ('John Doe', 'john.doe@example.com'),
                                    ('Jane Smith', 'jane.smith@example.com'),
                                    ('Alice Johnson', 'alice.johnson@example.com');

CREATE TABLE locations (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           user_id BIGINT NOT NULL,
                           latitude DECIMAL(10, 8) NOT NULL,
                           longitude DECIMAL(11, 8) NOT NULL
);
CREATE TABLE sessions (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          expires_at TIMESTAMP NOT NULL
);
