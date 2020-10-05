CREATE TABLE messages_kafka
(
    id          SERIAL PRIMARY KEY,
    message     text,
    create_date timestamp without time zone DEFAULT now()
)


