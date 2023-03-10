package app.just.actionstorage.entity;

import app.just.common.dto.SourceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "source")
public class SourceEntity {
    @Id
    @Enumerated(EnumType.STRING)
    private SourceType type;
    private boolean active;
}
