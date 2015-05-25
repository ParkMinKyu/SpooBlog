package io.spooncode.blog.sample.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by woniper on 15. 5. 22..
 */
@Entity
@Data
public class User extends AbstractPersistable<Integer> {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate = new Date();

}
