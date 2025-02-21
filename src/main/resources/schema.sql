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

-- Вставка тестовых данных в таблицу "post"
INSERT INTO post (title, image_url, content, tag, like_count, created, updated) VALUES
                                                                                    ('Новая Tesla Model S', 'https://example.com/tesla_model_s.jpg', 'Обзор новой Tesla Model S с улучшенной батареей.', 'Электромобили', 150, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                    ('Обзор BMW M3 2025', 'https://example.com/bmw_m3_2025.jpg', 'Детальный обзор BMW M3 2025 года выпуска.', 'Спортивные автомобили', 200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                    ('Сравнение Audi A4 и Mercedes C-Class', 'https://example.com/audi_a4_vs_mercedes_c_class.jpg', 'Сравнительный анализ Audi A4 и Mercedes C-Class.', 'Седаны', 180, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Вставка тестовых данных в таблицу "comment"
INSERT INTO comment (post_id, content, created, updated) VALUES
                                                                     (1, 'Отличный обзор! Жду не дождусь, когда смогу протестировать новую Tesla.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                     (2, 'BMW снова на высоте с этой моделью.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                     (3, 'Полезная информация, спасибо!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Вставка тестовых данных в таблицу "post_preview"
INSERT INTO post_preview (post_id, description, created, updated) VALUES
                                                                      (1, 'Краткий обзор новой Tesla Model S.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                      (2, 'Основные характеристики BMW M3 2025.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                      (3, 'Сравнение Audi A4 и Mercedes C-Class: что выбрать?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
