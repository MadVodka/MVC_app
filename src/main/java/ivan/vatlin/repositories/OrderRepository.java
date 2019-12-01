package ivan.vatlin.repositories;

import ivan.vatlin.dto.OrderInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderInfo, Long> {
    List<OrderInfo> findAll();

    List<OrderInfo> findByUserNameLike(String userName);

    List<OrderInfo> findByUser_UserName(String userName);
}
