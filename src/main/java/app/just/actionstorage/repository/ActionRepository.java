package app.just.actionstorage.repository;

import app.just.actionstorage.entity.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<ActionEntity, String> {
}
