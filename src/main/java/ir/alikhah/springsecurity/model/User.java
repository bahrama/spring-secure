package ir.alikhah.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Slf4j()
@Entity
@Table(name="USER" , schema = "spring_sch")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @NotBlank(message = "username must be not empty")
    @Size(min = 1 , max = 100 , message = "username must be 1 -100")
    @Column(name = "USERNAME" , length = 100 , unique = true , nullable = false , updatable = false)
    private String username;
    @NotBlank(message = "password must be not empty" )
    @Size(min = 1 , max = 200 , message = "password must be 1 -200")
    @Column(name = "PASSWORD" , length = 200 , nullable = false)
    private String password;
    @Transient
    private String confirmPassword;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateDate")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updateDate;

    @PrePersist
    protected void onCreateDate(){
        this.createDate = new Date();
        log.info( "created :" + this.toString());
    }
    @PreUpdate
    protected void onUpdateDate(){
        this.createDate = new Date();
        log.info( "updated :" + this.toString());
    }
    @PreRemove
    protected void onRemove(){
        log.info("removed :" + this.toString());
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
