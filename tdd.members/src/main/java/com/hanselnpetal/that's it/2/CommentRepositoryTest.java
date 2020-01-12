package com.one2one.one2one;

import com.one2one.one2one.entities.Comment;
import com.one2one.one2one.entities.Post;
import com.one2one.one2one.entities.PostPart;
import com.one2one.one2one.repositories.CommentRepository;
import com.one2one.one2one.repositories.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommentRepositoryTest {

    @Autowired
    CommentRepository repository;

    @Test
    public void insertTest() {
        String title = "This is a tutorial post.";
        Post post = new Post(title, new Date());

        String body = "This is a small comment.";
        String author = "Kevin Bruj";
        Comment comment = new Comment(author, body);
        comment.setPost(post);
        repository.save(comment);

        Comment dbComment = repository.getOne(comment.getCommentId());
        assertNotNull(dbComment);
        assertNotNull(dbComment.getPost());
        assertEquals(author, dbComment.getAuthor());
        assertEquals(body, dbComment.getBody());

    }

}