package dao.interfaces;

import model.Gun;

import java.util.Collection;
import java.util.Optional;

public interface GunDao {
    Optional<Gun> getGunById(int id);

    Collection<Gun> getAll();
}
