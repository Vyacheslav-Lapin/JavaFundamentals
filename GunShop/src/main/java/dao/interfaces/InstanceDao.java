package dao.interfaces;

import model.Instance;
import java.util.Optional;

public interface InstanceDao {
    Optional<Instance> getInstanceById(int id);
}
