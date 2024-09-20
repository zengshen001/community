package com.nowcoder.community.entity;

import lombok.Data;


public class Page {
    //当前页码
    private int current = 1;

    //显示上线
    private  int limit = 10;
    //数据总数（计算总页数）
    private int rows;
    //查询路径（复用分页链接）
    private String path;

    /*
    获取当前页起始行
     */
    public int getOffset(){
        return (current - 1) * limit;
    }

    /**
     * 获取总页数
     * @return 总页数
     */
    public int getTotal(){
        if(rows % limit != 0){
            return rows / limit;
        }else {
            return rows / limit + 1;
        }
    }

    /***
     *
     * @return 获取角标起始页
     */
    public int getFrom(){
        int from = current - 2;
        return from < 1 ? 1 : from;
    }

    /**
     *
     * @return 获取终止页数
     */
    public int getTo(){
        int to = current + 2;
        int total = getTotal();
        return to < total ? to : total;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setCurrent(int current) {
        if(current > 1){
        this.current = current;
        }
    }

    public void setLimit(int limit) {
        if(limit >= 1 && limit <= 100){

        this.limit = limit;
        }
    }

    public void setRows(int rows) {
        if(rows >= 0){

        this.rows = rows;
        }
    }



    public int getCurrent() {
        return current;
    }

    public int getLimit() {
        return limit;
    }

    public int getRows() {
        return rows;
    }
}
