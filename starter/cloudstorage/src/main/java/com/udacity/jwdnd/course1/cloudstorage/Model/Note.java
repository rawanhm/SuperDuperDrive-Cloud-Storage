package com.udacity.jwdnd.course1.cloudstorage.Model;

import lombok.*;

import javax.persistence.Entity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Note {

    private Integer noteid;
    private String notetitle;
    private String notedescription;
    private Integer userid;
}
