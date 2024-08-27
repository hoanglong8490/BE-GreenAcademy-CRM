-- liquibase formatted sql

-- changeset department:1
CREATE TABLE department (
    department_id SERIAL PRIMARY KEY,
    department_name VARCHAR(255) NOT NULL,
    "description" VARCHAR(255),
    "status" BIT,
    create_at TIMESTAMP,
    update_at TIMESTAMP
);

-- changeset position:1
CREATE TABLE "position" (
    position_id SERIAL PRIMARY KEY,
    position_name VARCHAR(50) NOT NULL,
    "status" SMALLINT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    department_id INTEGER NOT NULL,
    FOREIGN KEY (department_id) REFERENCES department(department_id)
);

-- changeset employee:1
CREATE TABLE employee (
    employee_id SERIAL PRIMARY KEY,
    employee_name VARCHAR(50),
    date_of_birth TIMESTAMP,
    gender SMALLINT,
    citizen_id VARCHAR(50),
    address VARCHAR(255),
    email VARCHAR(50),
    "password" VARCHAR(255),
    phone_number VARCHAR(50),
    image VARCHAR(255),
    "status" SMALLINT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    position_id INTEGER NOT NULL,
    department_id INTEGER NOT NULL,
    FOREIGN KEY (position_id) REFERENCES "position"(position_id),
    FOREIGN KEY (department_id) REFERENCES department(department_id)
);

-- changeset contract:1
CREATE TABLE contract (
    contract_id SERIAL PRIMARY KEY,
    contract_category VARCHAR(50),
    content_contract VARCHAR(255),
    salary FLOAT(4),
    date_start TIMESTAMP,
    date_end TIMESTAMP,
    "status" SMALLINT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    employee_id INTEGER NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

-- changeset insurance:1
CREATE TABLE insurance (
    insurance_id SERIAL PRIMARY KEY,
    insurance_number VARCHAR(50) NOT NULL,
    insurance_category VARCHAR(10) NOT NULL,
    provider_address VARCHAR(255) NOT NULL,
    provider_date TIMESTAMP,
    expiry_date TIMESTAMP,
    clinic VARCHAR(255),
    "status" BIT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    employee_id INTEGER NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

-- changeset qualification:1
CREATE TABLE qualification (
    qualification_id SERIAL PRIMARY KEY,
    qualification_name VARCHAR(50) NOT NULL,
    expiry_date TIMESTAMP,
    "status" SMALLINT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    employee_id INTEGER NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

-- changeset allowance:1
CREATE TABLE allowance (
    allowance_id SERIAL PRIMARY KEY,
    allowance_category VARCHAR(50) NOT NULL,
    allowance_salary FLOAT(4) NOT NULL,
    "status" SMALLINT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    position_id INTEGER NOT NULL,
    FOREIGN KEY (position_id) REFERENCES "position"(position_id)
);

-- changeset salary:1
CREATE TABLE salary (
    salary_id SERIAL PRIMARY KEY,
    contract_id INTEGER NOT NULL,
    overtime_id INTEGER NOT NULL,
    allowance_id INTEGER NOT NULL,
    salary_advance_id INTEGER NOT NULL,
    reward_discipline_id INTEGER NOT NULL,
    time_off_id INTEGER NOT NULL,
    salary FLOAT(4) NOT NULL,
    "status" SMALLINT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    employee_id INTEGER NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

-- changeset holiday:1
CREATE TABLE holiday (
    holiday_id SERIAL PRIMARY KEY,
    holiday_date TIMESTAMP NOT NULL,
    "status" SMALLINT,
    create_at TIMESTAMP,
    update_at TIMESTAMP
);

-- changeset time_tracking:1
CREATE TABLE time_tracking (
    time_tracking_id SERIAL PRIMARY KEY,
    time_tracking_date TIMESTAMP NOT NULL,
    salary_id INTEGER NOT NULL,
    holiday_id INTEGER NOT NULL,
    time_in TIME NOT NULL,
    "time_out" TIME NOT NULL,
    "status" SMALLINT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    employee_id INTEGER NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
    FOREIGN KEY (holiday_id) REFERENCES holiday(holiday_id)
);

-- changeset overtime:1
CREATE TABLE overtime (
    overtime_id SERIAL PRIMARY KEY,
    overtime_date TIMESTAMP NOT NULL,
    hours FLOAT(4),
    overtime_salary FLOAT(4),
    multiplier FLOAT(4),
    "status" SMALLINT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    employee_id INTEGER NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

-- changeset salary_advance:1
CREATE TABLE salary_advance (
    salary_advance_id SERIAL PRIMARY KEY,
    salary_advance_date TIMESTAMP NOT NULL,
    money FLOAT(4),
    "status" SMALLINT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    employee_id INTEGER NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

-- changeset time_off:1
CREATE TABLE time_off (
    holiday_id SERIAL PRIMARY KEY,
    date_start TIMESTAMP,
    date_end TIMESTAMP,
    day_number INTEGER,
    "status" SMALLINT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    employee_id INTEGER NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

-- changeset reward_discipline:1
CREATE TABLE reward_discipline (
    reward_discipline_id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    decision_date TIMESTAMP NOT NULL,
    money FLOAT(4),
    category VARCHAR(50),
    "status" SMALLINT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    salary_id INTEGER NOT NULL,
    FOREIGN KEY (salary_id) REFERENCES salary(salary_id)
);

-- changeset work_process:1
CREATE TABLE work_process (
    work_process_id SERIAL PRIMARY KEY,
    time_off INTEGER NOT NULL,
    work_date TIMESTAMP NOT NULL,
    "status" SMALLINT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    employee_id INTEGER NOT NULL,
    reward_discipline_id INTEGER NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
    FOREIGN KEY (reward_discipline_id) REFERENCES reward_discipline(reward_discipline_id)
);
