package com.orphan.api.children.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChildrenDto {
    private Integer id;

    private String fullName;

    private Boolean gender;

    private Boolean status;

    private Date dateOfBirth;

    private Date dateReceived;

//    private String introducerName;
//
//    private Date dateLeaved;

    private String image;
}
