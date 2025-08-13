package kr.ac.kopo.ctc.kopo33.board.repository;

import kr.ac.kopo.ctc.kopo33.board.domain.Post;
import kr.ac.kopo.ctc.kopo33.board.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DomainTest {
    @Autowired
    PostRepository postRepository ;

    @Autowired
    UserRepository userRepository;

//    @Test
//    public void before() {
//        for (int i = 1; i <= 5; i++) {
//            User user = new User();
//            user.setUsername("user" + i);
//            userRepository.save(user);
//
//            Post post = new Post();
//            post.setTitle("post" + i);
//            post.setBody(body);
//            postRepository.save(post);
//        }
//    }

}
