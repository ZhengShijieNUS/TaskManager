package com.company.ProcessMethodClass;

import com.company.Tasks.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    @Test
    void convertBoolean() {
        assertEquals(true, Converter.convertBoolean("1"));
    }

    @Test
    void convertTaskToString() {
        todo Todo = new todo(true, "read book");
        assertEquals("T | 1 | read book", Converter.convertTaskToString(Todo));

        deadline Deadline = new deadline("read the book", "tonight");
        assertEquals("D | 0 | read the book | tonight", Converter.convertTaskToString(Deadline));
    }
}