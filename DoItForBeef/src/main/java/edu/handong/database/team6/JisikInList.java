package edu.handong.database.team6;

public class JisikInList {
    int userId;
    String category;
    int postId;
    String context;
    int likes;

    public JisikInList() {

    }

    public int getUserId() {
        return userId;
    }

    public String getCategory() {
        return category;
    }

    public int getPostId() {
        return postId;
    }

    public String getContext() {
        return context;
    }

    public int getLikes() {
        return likes;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setLikes(String reply) {
        this.likes = likes;
    }


}
