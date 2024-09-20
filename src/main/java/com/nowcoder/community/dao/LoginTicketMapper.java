package com.nowcoder.community.dao;

import com.nowcoder.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

@Mapper
@Deprecated
public interface LoginTicketMapper {

//    @Insert({
//            "insert into login_ticket(user_id,ticket,status,expired) ",
//            "values(#{userId},#{ticket},#{status},#{expired})"
//    })
//    @Options(useGeneratedKeys = true,keyProperty = "id")
//    int insertTicket(LoginTicket ticket);
//
//    @Select({
//            "select * ",
//            "from login_ticket where ticket = #{ticket}"
//    })
//    LoginTicket selectByTicket(String ticket);
//    @Update({
//            "update login_ticket set status=#{status} where ticket = #{ticket}"
//    })
//    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);

}
