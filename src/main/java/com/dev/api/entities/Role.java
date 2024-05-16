package com.dev.api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    private String name;

    public enum Values {
        ADMIN(1L),
        BASIC(2L);
        long roleId;
        Values(long roleId) {
            this.roleId = roleId;
        }
        public long getRoleId() {
            return roleId;
        }
    }
}
