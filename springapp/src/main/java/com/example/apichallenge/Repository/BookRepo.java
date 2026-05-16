package com.example.apichallenge.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.apichallenge.Entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {

}
