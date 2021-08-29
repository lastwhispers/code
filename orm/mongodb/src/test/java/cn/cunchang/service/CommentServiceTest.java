package cn.cunchang.service;

import cn.cunchang.domain.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cunchang
 * @date 2021/4/5 1:43 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;


    @Test
    public void testFindCommentList() {
        List<Comment> commentList = commentService.findCommentList();
        System.out.println(commentList);
    }

    @Test
    public void testFindCommentById() {
        Comment commentById = commentService.findCommentById("606aa5b81cc0086e2eedd0d5");
        System.out.println(commentById);
    }

    @Test
    public void testSaveComment(){
        Comment comment=new Comment();
        // 不设置 id，MongoDB 会帮我们生成一个 ObjectId
        comment.setArticleid("100000");
        comment.setContent("测试添加的数据的评论");
        comment.setCreatedatetime(LocalDateTime.now());
        comment.setUserid("1004");
        comment.setNickname("tomcat");
        comment.setState("1");
        comment.setLikenum(0);
        comment.setReplynum(0);
        comment.setParentid("606aa5b81cc0086e2eedd0d5");
        commentService.saveComment(comment);
    }

    @Test
    public void testFindCommentListByParentid() {
        Page<Comment> page = commentService.findCommentListByParentid("606aa5b81cc0086e2eedd0d5", 1, 2);
        System.out.println(page.getTotalElements());
        System.out.println(page.getContent());
    }

    @Test
    public void testUpdateCommentLikenum() {
        commentService.updateCommentLikenum("606aa6ece3f00c7ab1909598");
    }

}