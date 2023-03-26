package xyz.thewind.user.database.bean;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_hyper_user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long uid;

    @Column(name = "user_name")
    private String userName;

    private String token;

    private String email;

    @Column(name = "open_id")
    private String openId;

    private String phone;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "header_url")
    private String headerUrl;

    private int level; // 0: 未激活  1、普通用户 2、高级用户  50、管理员  100、拥有者 -1、禁止发言

    @Column(name = "is_vip")
    private boolean isVip = false;

    @Column(name = "vip_start_time")
    private long vipStartTime = -1;

    @Column(name = "vip_end_time")
    private long vipEndTime = -1;

    @Column(name = "register_time")
    private Long registerTime;

}
