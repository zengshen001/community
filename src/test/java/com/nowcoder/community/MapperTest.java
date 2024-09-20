package com.nowcoder.community;

import com.nowcoder.community.dao.*;
import com.nowcoder.community.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private LoginTicketMapper ticketMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private MessageMapper messageMapper;

    @Test
    public void testSelect(){
        User user = mapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setUsername("tsy")
                .setEmail("2647735766@qq.com")
                .setSalt("12345")
                .setType(0)
                .setPassword("123456")
                .setType(0)
                .setStatus(1)
                .setHeaderUrl("http://www.nowcoder.com/101.png")
                .setCreateTime(new Date());
        int row = mapper.insertUser(user);
        System.out.println(row);
        System.out.println(user.getId());

    }
    @Test
    public void testUpdate(){
        mapper.updateStatus(150,0);
        mapper.updateHeader(150,"http://www.nowcoder.com/102.png");
        mapper.updatePassword(150,"2004726");
    }

    @Test
    public void testSelectPosts(){
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149,0,10);
        list.forEach(System.out::println);
        System.out.println(discussPostMapper.selectDiscussPostRows(149));

    }
//    @Test
//    public void testInsertTicket(){
//        LoginTicket loginTicket = new LoginTicket();
//        loginTicket.setUserId(101)
//                .setTicket("abc")
//                .setStatus(0)
//                .setExpired(new Date(System.currentTimeMillis() + 1000*60*10));
//        ticketMapper.insertTicket(loginTicket);
//    }
//    @Test
//    public void testSelectTicket(){
//        LoginTicket ticket = ticketMapper.selectByTicket("abc");
//        System.out.println(ticket);
//
//        ticketMapper.updateStatus("abc",1);
//        ticket=ticketMapper.selectByTicket("abc");
//        System.out.println(ticket);
//
//    }
    @Test
    public void testInsertDiscussPost(){
        DiscussPost discussPost = new DiscussPost();
        discussPost.setUserId(157)
                .setContent("从零开始的日记")
                .setCreateTime(new Date())
                .setStatus(0)
                .setType(0);
        discussPostMapper.insertDiscussPost(discussPost);
    }

    @Test
    public void testSelectComment(){
        List<Comment> comments = commentMapper.selectCommentsByEntity(1, 228, 0, 5);
        comments.forEach(System.out::println);
        System.out.println(commentMapper.selectCountByEntity(1, 228));
    }
    @Test
    public void testInsertComment(){
        Comment comment = new Comment();
        comment.setContent("谢谢你")
                .setEntityId(157)
                .setEntityType(1)
                .setStatus(1)
                .setStatus(0)
                .setCreateTime(new Date());
        System.out.println(commentMapper.insertComment(comment));

    }
    @Test
    public void testSelectMessage(){
//        List<Message> messages = messageMapper.selectConversations(111, 0, 5);
//        messages.forEach(System.out::println);
//        System.out.println(messageMapper.selectConversationCount(111));
//        List<Message> messages1 = messageMapper.selectLetters("111_112", 0, 5);
//        messages1.forEach(System.out::println);
//        System.out.println(messageMapper.selectLetterCount("111_112"));
//        System.out.println(messageMapper.selectLetterUnreadCount(131, null));
        Message message = new Message();
        message.setStatus(1)
                        .setFromId(111)
                                .setContent("nice")
                                        .setCreateTime(new Date())
                                                .setConversationId("111_112");
//        messageMapper.insertMessage(message);
        List<Integer> list = new ArrayList<>();
        list.add(355);
        messageMapper.updateStatus(list,2);
    }
}
