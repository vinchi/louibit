package kr.nexparan.louibit.model;

import javax.persistence.Id;

public class User_Role {
    @Id //Primary Key
    private Long userId;

    @Id //Primary Key
    private Long roleId;
}
