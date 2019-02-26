package com.example.demo.dao;

import com.example.demo.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 用户仓库.
 *
 * @since 1.0.0 2017年3月2日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface UserRepository extends JpaRepository<UserInfo,String>{


	Page<UserInfo> findByNameLike(String name, Pageable pageable);

	
	UserInfo findByName(String username);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update UserInfo u set u.loginTime=?2 where u.id=?1")
	void updataById(Integer id, Date LoginTime);


}
