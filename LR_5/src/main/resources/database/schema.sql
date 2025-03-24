-- Створення таблиці студентів
CREATE TABLE IF NOT EXISTS students (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    birth_date DATE NOT NULL,
    record_book_number VARCHAR(20) UNIQUE NOT NULL
    );

-- Додавання тестових даних
MERGE INTO students (last_name, first_name, middle_name, birth_date, record_book_number)
    KEY (record_book_number)
    VALUES
    ('Іваненко', 'Олег', 'Сергійович', '2001-02-15', 'RB1001'),
    ('Петров', 'Андрій', 'Миколайович', '2000-03-20', 'RB1002'),
    ('Сидоренко', 'Марія', 'Олександрівна', '2002-04-10', 'RB1003'),
    ('Коваленко', 'Оксана', 'Іванівна', '2001-05-25', 'RB1004'),
    ('Петрова', 'Марія', 'Вікторівна', '2002-05-16', 'RB1005'),
    ('Гончар', 'Віталій', 'Петрович', '2003-07-30', 'RB1006');
