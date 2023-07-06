package com.banking.Gesbank.repositories;

import com.banking.Gesbank.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    // select * from user where firstname = "ali"
    //List<User> findAllByFirstname(String firstname);

    // select * from User where firstname like '%ali%'
   // List<User> findAllByFirstnameContaining(String firstname);
    // select * from User where firstname ilike 'ali'
   // List<User> findAllByFirstnameContainingIgnoreCase(String firstname);
    //select * from user u inner join account a on u.id = o.id_user and a.iban = 'DE123545678'
   // List<User> findAllByAccountIban(String iban);
    // select * from user where firstname = '%ali%' and email = 'bouali@gmail.com'
    //User findByFirstnameContainingIgnoreCaseAndEmail(String firstname, String email);

    //@Query("from User where firstname =:fn")
    //List<User> searchByFirstname(@Param("fn") String firstname);

   // @Query("from User u inner firstname = '%:firstname%")
    //List<User> searchByFirstnameContaining(String firstname);

    //@Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    //List<User> searchByIban(String iban);

    //@Query(value = "select * from _user u inner join account a on u.id = a.id_user and a.iban = :iban", nativeQuery = true)
    //List<User> searchByIbanNative(String iban);
}
