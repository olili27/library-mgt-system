package com.example.library.mgt.system.services.impl;

import com.example.library.mgt.system.dtos.entries.TransactionEntryDto;
import com.example.library.mgt.system.dtos.responses.TransactionResponseDto;
import com.example.library.mgt.system.enums.BookStatus;
import com.example.library.mgt.system.enums.TransactionStatus;
import com.example.library.mgt.system.exceptions.BookNotAvailableException;
import com.example.library.mgt.system.exceptions.ResourceNotFoundException;
import com.example.library.mgt.system.models.Book;
import com.example.library.mgt.system.models.BookItem;
import com.example.library.mgt.system.models.Student;
import com.example.library.mgt.system.models.Transaction;
import com.example.library.mgt.system.repositories.BookItemRepository;
import com.example.library.mgt.system.repositories.BookRepository;
import com.example.library.mgt.system.repositories.StudentRepository;
import com.example.library.mgt.system.repositories.TransactionRepository;
import com.example.library.mgt.system.services.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final StudentRepository studentRepository;

    private final BookRepository bookRepository;

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionResponseDto bookACopy(TransactionEntryDto bookingDto) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setHappenedAt(LocalDate.now());

        if (!bookRepository.existsByTitle(bookingDto.getBookTitle())) {
            transaction.setStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);

            throw new ResourceNotFoundException("Book with title " + bookingDto.getBookTitle() + " does not exist");
        }

        if (!studentRepository.existsByEmail(bookingDto.getStudentEmail())) {
            transaction.setStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);

            throw new ResourceNotFoundException("No student with email " + bookingDto.getStudentEmail());
        }

        Book book = bookRepository.findByTitle(bookingDto.getBookTitle());

        if (book.getNumberOfCopies() == 0) {
            transaction.setStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);

            throw new BookNotAvailableException("No copies available");
        }

        Student student = studentRepository.findByEmail(bookingDto.getStudentEmail());

        BookItem bookItem = book.getBookItems().remove(0);
        bookItem.setStatus(BookStatus.BOOKED);
        bookItem.getStudents().add(student);
        bookItem.getTransactions().add(transaction);

        book.setNumberOfCopies(book.getBookItems().size());

        if (book.getNumberOfCopies() == 0) book.setStatus(BookStatus.OUT_OF_COPIES);

        transaction.setBookItem(bookItem);
        transaction.setStudent(student);
        transaction.setStatus(TransactionStatus.SUCCESS);

        student.getTransactions().add(transaction);
        student.getBookItems().add(bookItem);
        studentRepository.save(student);

        return TransactionResponseDto.builder()
                .transactionNumber(transaction.getTransactionNumber())
                .bookTitle(book.getTitle())
                .studentName(student.getName())
                .studentEmail(student.getEmail())
                .build();
    }
}
