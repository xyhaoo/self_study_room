package cn.edu.ldu.self_study_room.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int comment_id;
    private String post_id;
    private String user_id;
    private Timestamp comment_time;
    private String comment_content;
    private boolean is_best;
}
