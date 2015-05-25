package io.spooncode.blog.sample.support;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by woniper on 15. 5. 22..
 */
@Data
public class UserDto {

    @NotNull private String username;
    @NotNull private String firstName;
    @NotNull private String lastName;

    @Data
    public static class Request extends UserDto {
        @NotNull
        private String password;
    }

    @Data
    public static class Response extends UserDto {
        private Date joinDate;
    }
}
