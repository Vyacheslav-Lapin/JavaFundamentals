package dao.interfaces;

import com.epam.courses.jf.jdbc.common.Dao;
import model.Instance;
import java.util.Optional;

public interface InstanceDao extends Dao {
    Optional<Instance> getInstanceById(int id);
}
