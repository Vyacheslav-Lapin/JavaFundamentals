package dao.interfaces;

import model.Instance;
import java.util.Optional;

public interface InstanceDao extends Dao {
    Optional<Instance> getInstanceById(int id);
}
