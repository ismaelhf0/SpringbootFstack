-- V2__add_unique_constraint.sql

-- Add a unique constraint to ensure that emails are unique
ALTER TABLE customer ADD CONSTRAINT uk_customer_email UNIQUE (email);
