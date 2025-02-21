-- Таблица с пользователями
-- Удаление таблицы "comment"
DROP TABLE IF EXISTS comment;

-- Удаление таблицы "post_preview"
DROP TABLE IF EXISTS post_preview;

-- Удаление таблицы "post"
DROP TABLE IF EXISTS post;

-- Создание таблицы "post"
CREATE TABLE IF NOT EXISTS post (
                                    id INTEGER AUTO_INCREMENT PRIMARY KEY,
                                    title VARCHAR(50) NOT NULL,
                                    image_url VARCHAR(255) NOT NULL,
                                    content VARCHAR(255) NOT NULL,
                                    tag VARCHAR(50),
                                    like_count BIGINT NOT NULL,
                                    created TIMESTAMP WITH TIME ZONE NOT NULL,
                                    updated TIMESTAMP WITH TIME ZONE NOT NULL
);

-- Создание таблицы "comment"
CREATE TABLE IF NOT EXISTS comment (
                                       id INTEGER AUTO_INCREMENT PRIMARY KEY,
                                       post_id BIGINT NOT NULL,
                                       content VARCHAR(255) NOT NULL,
                                       created TIMESTAMP WITH TIME ZONE NOT NULL,
                                       updated TIMESTAMP WITH TIME ZONE NOT NULL,
                                       FOREIGN KEY (post_id) REFERENCES post(id)
);

-- Создание таблицы "post_preview"
CREATE TABLE IF NOT EXISTS post_preview (
                                            post_id BIGINT PRIMARY KEY,
                                            description VARCHAR(50) NOT NULL,
                                            created TIMESTAMP WITH TIME ZONE NOT NULL,
                                            updated TIMESTAMP WITH TIME ZONE NOT NULL,
                                            FOREIGN KEY (post_id) REFERENCES post(id)
);
