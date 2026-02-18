INSERT INTO patient (name, birth_date, email, gender, blood_group)
VALUES
    ('Suman', '2000-01-10', 'suman@example.com', 'Male', 'A_POSITIVE'),
    ('Priya Sharma', '1999-03-22', 'priya@example.com', 'Female', 'B_NEGATIVE'),
    ('Amit Verma', '1995-12-05', 'amitv@example.com', 'Male', 'O_POSITIVE'),
    ('Neha Gupta', '1998-08-14', 'neha.g@example.com', 'Female', 'AB_POSITIVE'),
    ('Rahul Mehta', '2001-02-11', 'rahulm@example.com', 'Male', 'A_NEGATIVE'),
    ('Kiran Patel', '1997-09-30', 'kiranp@example.com', 'Female', 'B_POSITIVE'),
    ('Rohit Singh', '1996-07-19', 'rohits@example.com', 'Male', 'O_NEGATIVE'),
    ('Ananya Roy', '1998-11-25', 'ananya@example.com', 'Female', 'AB_NEGATIVE'),
    ('Arjun Nair', '2000-05-02', 'arjun@example.com', 'Male', 'A_POSITIVE'),
    ('Meera Iyer', '1999-04-16', 'meera@example.com', 'Female', 'O_POSITIVE');

INSERT INTO doctor (name, specialization, email)
VALUES
    ('Dr. Rakesh Mehta', 'Cardiology', 'rakesh.mehta@example.com'),
    ('Dr. Sneha Kapoor', 'Dermatology', 'sneha.kapoor@example.com'),
    ('Dr. Arjun Nair', 'Orthopedics', 'arjun.nair@example.com');

INSERT INTO appointment (appointment_time, reason, doctor_id, patient_id)
VALUES
    ('2025-07-01 10:30:00', 'General Checkup', 1, 2),
    ('2025-07-02 11:00:00', 'Skin Rash', 2, 2),
    ('2025-07-03 09:45:00', 'Knee Pain', 3, 3),
    ('2025-07-04 14:00:00', 'Follow-up Visit', 1, 1),
    ('2025-07-05 16:15:00', 'Consultation', 1, 4),
    ('2025-07-06 08:30:00', 'Allergy Treatment', 2, 5);
