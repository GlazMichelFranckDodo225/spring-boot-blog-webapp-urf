package com.dgmf.repository;

import com.dgmf.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUrl(String url);
    /*
     * Returns a List of Posts based on JPQL Search Query Criteria
     * and Named Parameters (Use Class Names)
     */
    @Query("SELECT p FROM Post p WHERE " +
            " p.title LIKE CONCAT('%', :query, '%') OR " +
            " p.shortDescription LIKE CONCAT('%', :query, '%')")
    List<Post> searchPostsJPQLQuery(String query);
}
