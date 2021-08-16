package com.tro2tro.api.entity;

// import lombok.Data;
// import lombok.Getter;
// import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.awt.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "content")
    private String content;

    @Column(name = "active")
    private boolean active;

    @Column(name = "userlike")
    private int userlike;

    @Column(name = "userdislike")
    private int userdislike;

    @Column(name = "userlol")
    private int userlol;

    @Column(name = "usersurprise")
    private int usersurprise;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date created_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updated_at;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("posts")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("posts")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "likes", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> userslikes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dislikes", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> usersdislikes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "lols", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> userslols;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "surprises", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> userssurprises;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_id")
    private Image image;

    @Projection(name = "withCategory", types = Post.class)
    public interface WithCategory {
        Long getId();
        String getContent();
        boolean isActive();
        int getUserlike();
        int getUserdislike();
        int getUserlol();
        int getUsersurprise();
        Date getCreated_at();
        Date getUpdated_at();
        Category getCategory();
        User getUser();
        Image getImage();
    }


}
