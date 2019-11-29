package ivan.vatlin.repository;

import ivan.vatlin.dto.OrderInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderInfo, Long> {
    List<OrderInfo> findAll();

    List<OrderInfo> findByUserNameLike(String userName);

    List<OrderInfo> findByUser_UserName(String userName);
}
