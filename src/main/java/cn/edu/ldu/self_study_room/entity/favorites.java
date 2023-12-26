package cn.edu.ldu.self_study_room.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class favorites {
    String user_id;
    int seat_number;
    int room_id;
    String status;
}
