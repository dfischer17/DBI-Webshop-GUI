package at.htlgkr.dbi.webshop.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
}
