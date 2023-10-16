package projectHotel.entity;

import javax.persistence.*;

@Entity (name = "user")
public class UserEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private int id;
    @Column (name = "user_name")
    private String username;
    @Column (name = "email")
    private String email;
    @Column (name = "password")
    private String password;
    @Column (name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn (name = "id_role")
    private RoleEntity roleEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }
}
