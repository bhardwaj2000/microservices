package com.example.solaceToDB.SolaceToDB;

import com.mysql.cj.jdbc.Blob;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "airoplaneEventData" )
public class AiroplaneEventData {

    @Id
    @Column(name = "ID")
    private int id;

    @Lob
    private String headerProperties;
    @Lob
    private String rawJson;
    private String srcTimeStamp;
}
