package com.jy.mission2.repository;

import com.jy.mission2.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserIdAndBoardId(Long userId, Long boardId);
    Optional<Like> deleteByUserIdAndBoardId(Long userId, Long boardId);
}
