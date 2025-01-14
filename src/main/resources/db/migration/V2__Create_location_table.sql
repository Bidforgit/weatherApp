CREATE TABLE locations (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          user_id BIGINT NOT NULL,
                          latitude DECIMAL(10, 8) NOT NULL,
                          longitude DECIMAL(11, 8) NOT NULL
);
