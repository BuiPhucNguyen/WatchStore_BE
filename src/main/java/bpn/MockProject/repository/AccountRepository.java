package bpn.MockProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bpn.MockProject.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	@Query("SELECT a FROM Account a WHERE a.userName = :username and a.status = true")
	Account findByUsername(@Param("username") String username);

	@Query("SELECT a FROM Account a WHERE a.email = :email")
	Account findByEmail(@Param("email") String email);

	@Query("SELECT a FROM Account a WHERE a.id = :accountId")
	Account getAccountById(@Param("accountId") Integer accountId);
	
	@Query("SELECT a FROM Account a JOIN a.roles b WHERE b.name <> 'ADMIN'")
    Page<Account> findAccountsNotAdmin(Pageable pageable);
}
