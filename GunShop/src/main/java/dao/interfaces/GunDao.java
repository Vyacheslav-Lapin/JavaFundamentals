package dao.interfaces;

import com.epam.courses.jf.jdbc.common.Dao;
import model.Gun;

import java.util.Collection;
import java.util.Optional;

public interface GunDao extends Dao {
    Optional<Gun> getGunById(int id);

    Collection<Gun> getAll();
}
