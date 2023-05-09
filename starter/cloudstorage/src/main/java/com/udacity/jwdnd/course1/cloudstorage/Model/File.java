package com.udacity.jwdnd.course1.cloudstorage.Model;

import lombok.*;

import javax.persistence.Entity;

@Data

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class File {


    private Integer fileId;

    private String filename;
    private String contenttype;
    private Long filesize;
    private Integer userid;
    private byte[] filedata;
}
