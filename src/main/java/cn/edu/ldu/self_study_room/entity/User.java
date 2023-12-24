package cn.edu.ldu.self_study_room.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String user_id;
    private String password;
    private String user_name;
    private String phone_number;
    private String sex;
}
