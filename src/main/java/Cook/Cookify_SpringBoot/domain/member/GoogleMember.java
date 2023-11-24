package Cook.Cookify_SpringBoot.domain.member;


import Cook.Cookify_SpringBoot.domain.comment.Comment;
import Cook.Cookify_SpringBoot.domain.recipe.Recipe;
import Cook.Cookify_SpringBoot.global.Entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
public class GoogleMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Recipe> recipes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();




    //== 연관관계 메서드 ==//
    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }


    @Builder
    public GoogleMember(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public GoogleMember update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }



    public String getRoleKey(){
        return this.role.getKey();
    }

}
