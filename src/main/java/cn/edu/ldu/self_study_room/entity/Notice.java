package cn.edu.ldu.self_study_room.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private String notice_id;
    private String notice_title;
    private String notice_content;
    private Timestamp notice_time;

}
