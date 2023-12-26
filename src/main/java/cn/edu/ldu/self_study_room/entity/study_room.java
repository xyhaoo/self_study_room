package cn.edu.ldu.self_study_room.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class study_room {
    int room_id;
    String room_picture;
    String room_content;
}
