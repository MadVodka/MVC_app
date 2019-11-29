package ivan.vatlin.dao;

import ivan.vatlin.dto.OrderInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderInfo, Long> {
    List<OrderInfo> findByUser_UserName(String userName);
}
