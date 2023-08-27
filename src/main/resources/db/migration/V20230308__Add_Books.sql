CREATE TABLE books
(
    id         BIGSERIAL PRIMARY KEY,
    version    INTEGER                  NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE          DEFAULT NULL,
    isbn       TEXT                     NOT NULL UNIQUE,
    title      TEXT                     NOT NULL
);

-- Creating a partial unique index to allow multiple NULL values in (isbn, deleted_at)
CREATE UNIQUE INDEX idx_books_isbn_deleted_at_unique_nulls ON books (isbn) WHERE deleted_at IS NULL;

CREATE TABLE authors_books
(
    author_id UUID NOT NULL REFERENCES authors(id),
    book_id   BIGINT NOT NULL REFERENCES books(id),
    PRIMARY KEY (author_id, book_id)
);
