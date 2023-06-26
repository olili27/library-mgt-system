package com.example.library.mgt.system.transformers;

import com.example.library.mgt.system.models.Book;
import com.example.library.mgt.system.models.BookItem;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;

@UtilityClass
public class BookItemTransformer {
    public BookItem createBookItem(Book book){
        return BookItem.builder()
                .title(book.getTitle())
                .book(book)
                .status(book.getStatus())
                .students(new ArrayList<>())
                .transactions(new ArrayList<>())
                .build();
    }
}
