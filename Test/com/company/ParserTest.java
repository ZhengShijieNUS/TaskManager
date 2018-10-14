package com.company;

import com.company.ProcessManageObject.TaskManagerException;
import com.company.ProcessMethodClass.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @Test
    void getCommandWord() {
        assertEquals("remove", Parser.getCommandWord("remove 4"));
        assertEquals("todo", Parser.getCommandWord("todo watch a movie"));
    }

    @Test
    void getIndex() throws Exception {
        assertEquals(4, Parser.getIndex("remove 4"));
        assertEquals(3, Parser.getIndex("done 3"));

    }

    @Test
    void getIndex_exception() {
        Throwable exception = assertThrows(Exception.class, () -> {
            Parser.getIndex("done   ");
        });
        assertEquals("The content format is wrong.", exception.getMessage());
        exception = assertThrows((Exception.class), () -> {
            Parser.getIndex("done af32 gr");
        });
        assertEquals("The content format is wrong.", exception.getMessage());
    }

    @Test
    void getContent() throws Exception {
        assertEquals("play game", Parser.getContent("modify 3 with play game"));
    }

    @Test
    void getContent_exception() {
        Throwable exception = assertThrows(TaskManagerException.class, () -> {
            Parser.getContent("modify 3 play game");
        });
        assertEquals("Command is without keyword with", exception.getMessage());
    }
}