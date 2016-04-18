package dao;

import model.Gun;

import java.util.Optional;

public interface GunDao {

    Optional<Gun> getGunById(int id);
}
