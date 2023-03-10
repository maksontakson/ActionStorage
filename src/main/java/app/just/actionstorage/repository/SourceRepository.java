package app.just.actionstorage.repository;

import app.just.actionstorage.entity.SourceEntity;
import app.just.common.dto.SourceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepository extends JpaRepository<SourceEntity, SourceType> {
}
