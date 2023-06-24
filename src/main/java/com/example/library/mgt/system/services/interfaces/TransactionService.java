package com.example.library.mgt.system.services.interfaces;

import com.example.library.mgt.system.dtos.entries.TransactionEntryDto;
import com.example.library.mgt.system.dtos.responses.TransactionResponseDto;

public interface TransactionService {
    TransactionResponseDto bookACopy(TransactionEntryDto bookingDto) throws Exception;
}
