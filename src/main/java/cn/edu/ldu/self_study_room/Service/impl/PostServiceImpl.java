package cn.edu.ldu.self_study_room.service.impl;

import cn.edu.ldu.self_study_room.dao.CommentDao;
import cn.edu.ldu.self_study_room.dao.PostDao;
import cn.edu.ldu.self_study_room.entity.Comment;
import cn.edu.ldu.self_study_room.entity.Post;
import cn.edu.ldu.self_study_room.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;
    @Autowired
    private CommentDao commentDao;

    //查询所有记录，供用户或管理员点击论坛页面时展示所有帖子
    @Override
    public List<Post> findAll() throws Exception {
        return postDao.findAll();
    }

    //增加记录，供用户或管理员发布新帖子
    @Override
    public String insert(Post post){
        try {
            //只要插入成功，肯定返回1,否则抛出异常。不需要接收返回值再判断了。直接把dao层接口返回值设置成了void
            postDao.insert(post);
            return "发布成功～";
        }catch (Exception e){
            return "发布失败了，请再次尝试。如果此问题依然存在请联系开发者！";
        }
    }

    //删除记录，供用户或管理员删除帖子
    //删除帖子前，为防止外键冲突，应先删除这个帖子的所有回复
    @Override
    public String delete(String post_id) {
        try {
            List<Comment> comments = commentDao.findCommentByPostId(post_id);
            for (Comment comment: comments) {
                commentDao.delete(comment.getComment_id());
            }
            postDao.delete(post_id);
            return "删除成功～";
        }catch (Exception e){
            return "删除失败了，请再次尝试。如果此问题依然存在请联系开发者！";
        }
    }

    //修改记录，供用户或管理员修改帖子内容
    @Override
    public String update_content(String post_id, String post_title, String post_content) {
        try {
            postDao.update_content(post_id, post_title, post_content);
            return "修改成功～";
        }catch (Exception e){
            return "修改失败了，请再次尝试。如果此问题依然存在请联系开发者！";
        }
    }
    //修改记录，帖子有最佳评论时或最佳评论被删除时自动修改帖子状态
    @Override
    public String update_status(String post_id, String post_status) {
        try {
            postDao.update_status(post_id, post_status);
            return "修改帖子状态成功～";
        }catch (Exception e){
            return "修改帖子状态失败了，请再次尝试。如果此问题依然存在请联系开发者！";
        }
    }

    //根据帖子号查询，用于点击某个帖子链接跳转到详细界面时，利用该帖子号渲染内容
    @Override
    public Post searchByPostId(String post_id) {
        Post post;
        try {
            post = postDao.searchByPostId(post_id);
        }catch (Exception e){
            return null;
        }
        return post;
    }

    //模糊查询，供用户或管理员根据帖子标题查询帖子
    @Override
    public List<Post> fuzzySearch(String post_title) throws Exception {
        return postDao.fuzzySearch('%'+ post_title +'%');
    }
}
