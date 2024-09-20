package com.nowcoder.community.util;


public class RedisUtil {
    private static String SPLIT = ":";
    private static String PREFIX_ENTITY_LIKE = "like:entity";

    private static String   PREFIX_USER_LIKE = "like::user";

    private static String PREFIX_FOLLOWEE = "followee";

    private static String PREFIX_FOLLOWER = "follower";

    private static String PREFIX_KAPTCHA = "kaptcha";

    private static String PREFIX_TICKET = "ticket";

    private static String PREFIX_USER = "user";

    private static final String PREFIX_POST = "post";

    public static String getEntityLikeKey(int entityType,int entityId){
        return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    public static String getUserLikeKey(int userId){
        return PREFIX_USER_LIKE + SPLIT + userId;
    }

    public static String getFolloweeKey(int userId,int entityType){
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    public static String getFollowerKey(int entityType,int entityId){
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

    public static String getKaptchaKey(String owner){
        return PREFIX_KAPTCHA + SPLIT + owner;
    }

    public static String getTicketKey(String ticket){
        return PREFIX_TICKET + SPLIT + ticket;
    }

    public static String getUserKey(int userId){
        return PREFIX_USER + SPLIT + userId;
    }


}
