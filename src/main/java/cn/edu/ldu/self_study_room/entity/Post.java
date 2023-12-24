package cn.edu.ldu.self_study_room.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String post_id;
    private String user_id;
    private String post_title;
    private String post_content;
    private Date post_time;
}
