package top.shahow.deliverymailservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.shahow.deliverymailservice.domain.log.vo.LogVO;

/**
 * @author shahow
 * @date 2022-05-10
 */
@Repository
public interface LogRepository extends CrudRepository<LogVO, Integer> {

}
