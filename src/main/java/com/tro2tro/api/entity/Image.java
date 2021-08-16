package com.tro2tro.api.entity;

// import lombok.Data;
import com.tro2tro.api.upload.services.FilesStorageServiceImpl;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "active")
    private boolean active;

    @OneToOne()
    private Post post;


}
