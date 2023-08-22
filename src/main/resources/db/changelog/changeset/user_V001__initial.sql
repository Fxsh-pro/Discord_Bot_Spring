CREATE TABLE received_messages
(
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    user_id BIGINT NOT NULL,
    received_at TIMESTAMPTZ DEFAULT current_timestamp
);