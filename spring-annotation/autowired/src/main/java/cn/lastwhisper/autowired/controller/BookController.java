package cn.lastwhisper.autowired.controller;

import cn.lastwhisper.autowired.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
}
