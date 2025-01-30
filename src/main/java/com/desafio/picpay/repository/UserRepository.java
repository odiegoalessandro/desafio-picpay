package com.desafio.picpay.repository;

import com.desafio.picpay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByDocument(String document);
  Optional<User> findByEmail(String email);

//  @Query("""
//        SELECT CASE
//            WHEN u.userType = 'MERCHANT'
//            THEN TRUE
//            ELSE FALSE
//        END
//        FROM User u
//        WHERE u.id = :id
//    """)
//  Boolean isMerchantByUserId(@Param("id") Long id);
}
