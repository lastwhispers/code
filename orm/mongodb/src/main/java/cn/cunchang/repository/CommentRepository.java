package cn.cunchang.repository;

import cn.cunchang.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment,String > {

    Page<Comment> findByParentid(String parentid, Pageable pageable);

}
