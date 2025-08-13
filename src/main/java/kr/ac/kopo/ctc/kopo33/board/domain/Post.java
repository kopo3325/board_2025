package kr.ac.kopo.ctc.kopo33.board.domain;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    private  String title;

    private  String post_body;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id")
    private Board board;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String post_body) {
        this.post_body = post_body;
    }
}
