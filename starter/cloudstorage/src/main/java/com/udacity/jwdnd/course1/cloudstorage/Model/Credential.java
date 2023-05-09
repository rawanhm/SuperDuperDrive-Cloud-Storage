package com.udacity.jwdnd.course1.cloudstorage.Model;

import lombok.*;

import javax.persistence.Entity;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
public class Credential {


    private Integer credentialid;
    private String url;
    private String username;
    private String key;
    private String password;
    private String decryptedpassword;
    private Integer userid;

}
