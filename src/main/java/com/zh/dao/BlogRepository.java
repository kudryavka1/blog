package com.zh.dao;

import com.zh.pojo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog>{

}
