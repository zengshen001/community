package com.nowcoder.community.dao;


import com.nowcoder.community.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    //查询当前用户的会话列表，每个会话返回一条最新的私信
    List<Message> selectConversations(int userId,int offset,int limit);

    int selectConversationCount(int userId);
    //查询某个会话的信息列表
    List<Message> selectLetters(String conversationId,int offset,int limit);

    int selectLetterCount(String conversationId);

    int selectLetterUnreadCount(int userId,String conversationId);

    int insertMessage(Message message);

    int updateStatus(List<Integer> ids,int status);

    Message selectLatestNotice(int userId,String topic);

    int selectNoticeCount(int userId,String topic);

    int selectNoticeUnreadCount(int userId,String topic);
    // 查询某个主题所包含的通知列表
    List<Message> selectNotices(int userId, String topic, int offset, int limit);


}
