package com.example.library.mgt.system.dtos.entries;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorEntryDto {

    String name;

    String email;

    Integer age;
}
