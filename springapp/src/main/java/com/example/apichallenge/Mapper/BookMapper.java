package com.example.apichallenge.Mapper;


import com.example.apichallenge.DTO.BookDTO;
import com.example.apichallenge.Entity.Book;

public class BookMapper {

    public static Book toEntity(BookDTO dto) {
       Book b = new Book(dto.getIsbn(),dto.getTitle(),dto.getAuthor(),dto.getPublicationYear());
       return b;
    }

    public static BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO(book.getIsbn(),book.getTitle(),book.getAuthor(),book.getPublicationYear());
        return dto;
    }
}
