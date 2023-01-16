package ir.alikhah.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Slf4j()
@Entity
@Table(name="PRODUCT" , schema = "spring_sch")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @NotBlank(message = "image must be not empty" )
    @Size(min = 1 , max = 200 , message = "image must be 1 -200")
    @Column(name = "IMAGE" , length = 200 , nullable = false)
    private String image;

    @NotBlank(message = "name must be not empty" )
    @Size(min = 1 , max = 200 , message = "name must be 1 -200")
    @Column(name = "NAME" , length = 200 , nullable = false)
    private String name;

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


}
