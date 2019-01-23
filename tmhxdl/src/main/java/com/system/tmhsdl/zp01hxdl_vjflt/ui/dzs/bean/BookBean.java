package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean;

import java.util.List;

public class BookBean {

    /**
     * total_page : 1
     * now_page : 1
     * list : [{"id":1,"image":"/uploads/181219/243271333299252309440146478340124219292190412439.jpg","title":"测试电子书"}]
     */

    private int total_page;
    private int now_page;
    private List<ListBean> list;

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getNow_page() {
        return now_page;
    }

    public void setNow_page(int now_page) {
        this.now_page = now_page;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * image : /uploads/181219/243271333299252309440146478340124219292190412439.jpg
         * title : 测试电子书
         */

        private int id;
        private String image;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
