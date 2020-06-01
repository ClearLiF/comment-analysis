package com.cuit;

import static org.junit.Assert.assertTrue;

import com.cuit.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Autowired
    CommentService commentService;
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void testComment(){
       // commentService.partingWord();
        commentService.partingWord2();
    }
}
