package com.blogapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="title",length=45)
    private String title;
    @Column(name="description",length = 200)
    private  String description;
    @Column(name = "content",length = 200)
    private String content;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments= new ArrayList<>();
}
