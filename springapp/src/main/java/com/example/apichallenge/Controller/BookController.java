package com.example.apichallenge.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apichallenge.ApiResponse.ApiResponse;
import com.example.apichallenge.DTO.BookDTO;
import com.example.apichallenge.Exception.ResourceNotFoundException;
import com.example.apichallenge.Service.BookService;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    BookService service;

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookdto) {
        if (service.getBookById(bookdto.getIsbn()) != null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiResponse<>("Book already exists", HttpStatus.NOT_ACCEPTABLE, null));
        }
        BookDTO dto = service.addBook(bookdto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Book added!", HttpStatus.CREATED, dto));
    }

    @GetMapping("/showAll")
    public ResponseEntity<?> showAllBooks() {
        List<BookDTO> list = service.getAllBooks();
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("Book not found");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse<>("Book list", HttpStatus.FOUND, list));
    }

    @GetMapping("/searchByIsbn/{isbn}")
    public ResponseEntity<?> searchBook(@PathVariable Long isbn) {
        BookDTO dto = service.getBookById(isbn);
        if (dto == null) {
            throw new ResourceNotFoundException("Book not found");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse<>("Book Found!", HttpStatus.FOUND, dto));

    }

    @PutMapping("/updateBookByIsbn")
    public ResponseEntity<?> updateBookByIsbn(@RequestBody BookDTO dto) {
        BookDTO dto1 = service.updateBookByIsbn(dto);
        if (dto1 == null) {
            throw new ResourceNotFoundException("Book not found");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse<>("Book updated", HttpStatus.FOUND, dto1));

    }

    @DeleteMapping("/deleteBookByIsbn/{isbn}")
    public ResponseEntity<?> deleteBookByIsbn(@PathVariable Long isbn) {
        String res = service.deleteBookByIsbn(isbn);
        if (res == null) {
            throw new ResourceNotFoundException("Book not found");
        }
        return ResponseEntity.status(HttpStatus.GONE).body(new ApiResponse<>("Book removed", HttpStatus.OK, null));

    }

}
