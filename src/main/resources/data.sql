-- Drop existing tables if they exist
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Role;

-- Create the Role table
CREATE TABLE Role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create the Employee table
CREATE TABLE Employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    salary DOUBLE NOT NULL,
    manager_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (manager_id) REFERENCES Employee(id),
    FOREIGN KEY (role_id) REFERENCES Role(id)
);

-- Insert sample roles
INSERT INTO Role (id, name) VALUES (1, 'Employee');
INSERT INTO Role (id, name) VALUES (2, 'Manager');
INSERT INTO Role (id, name) VALUES (3, 'Sr. Manager');
INSERT INTO Role (id, name) VALUES (4, 'Director');
INSERT INTO Role (id, name) VALUES (5, 'Sr. Director');
INSERT INTO Role (id, name) VALUES (6, 'VP');
INSERT INTO Role (id, name) VALUES (7, 'Sr. VP');
INSERT INTO Role (id, name) VALUES (8, 'CTO');
INSERT INTO Role (id, name) VALUES (9,'CEO');

-- Insert sample employees
-- CEO
INSERT INTO Employee (id, name, salary, manager_id, role_id) VALUES (1, 'Alice', 300000, NULL, 9);

-- CTO
INSERT INTO Employee (id, name, salary, manager_id, role_id) VALUES (2, 'Bob', 250000, 1, 8);

-- VP
INSERT INTO Employee (id, name, salary, manager_id, role_id) VALUES (3, 'Charlie', 200000, 2, 6);

-- Sr. Director
INSERT INTO Employee (id, name, salary, manager_id, role_id) VALUES (4, 'David', 150000, 3, 5);

-- Director
INSERT INTO Employee (id, name, salary, manager_id, role_id) VALUES (5, 'Eve', 120000, 4, 4);

-- Sr. Manager
INSERT INTO Employee (id, name, salary, manager_id, role_id) VALUES (6, 'Frank', 100000, 5, 3);

-- Manager
INSERT INTO Employee (id, name, salary, manager_id, role_id) VALUES (7, 'Grace', 80000, 6, 2);

-- Employee
INSERT INTO Employee (id, name, salary, manager_id, role_id) VALUES (8, 'Heidi', 60000, 7, 1);
INSERT INTO Employee (id, name, salary, manager_id, role_id) VALUES (9, 'Ivan', 60000, 7, 1);