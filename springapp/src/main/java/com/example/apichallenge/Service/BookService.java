package com.example.apichallenge.Service;


import com.example.apichallenge.DTO.BookDTO;
import com.example.apichallenge.Mapper.BookMapper;
import com.example.apichallenge.Entity.Book;
import com.example.apichallenge.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepo repo;


    public BookDTO addBook(BookDTO bookDTO){
         Book book = BookMapper.toEntity(bookDTO);
          Book book1=repo.save(book);
         return BookMapper.toDTO(book1);
    }

    public List<BookDTO> getAllBooks(){
        List<Book>list=repo.findAll();
        if(list.isEmpty()){return null;}
        List<BookDTO>dtolist=new ArrayList<>();
        for(Book b:list){
            dtolist.add(BookMapper.toDTO(b));
        }
        return dtolist;
    }

    public BookDTO getBookById(Long isbn){
        Book b=repo.findById(isbn).orElse(null);
        if(b==null) return null;
        return BookMapper.toDTO(b);
    }

    public BookDTO updateBookByIsbn(BookDTO dto){
        Book b=repo.findById(dto.getIsbn()).orElse(null);
        if(b==null) return null;
        Book book=BookMapper.toEntity(dto);
        book=repo.save(book);
        return BookMapper.toDTO(book);
    }

    public String deleteBookByIsbn(Long isbn){
        Book b=repo.findById(isbn).orElse(null);
        if(b==null) return null;
        repo.delete(b);
        return "Book deleted";
    }




}
