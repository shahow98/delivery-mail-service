package top.shahow.deliverymailservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.shahow.deliverymailservice.domain.account.vo.AccountVO;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountVO, Integer> {
    boolean existsByEmail(String email);

    Optional<AccountVO> findByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);


}
