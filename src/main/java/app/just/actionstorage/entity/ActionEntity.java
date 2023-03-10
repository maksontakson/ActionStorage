package app.just.actionstorage.entity;

import app.just.common.dto.ActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "action")
public class ActionEntity {
    @Id
    private String id;
    private String username;
    private Instant date;
    @Enumerated(EnumType.STRING)
    private ActionType type;
    @ManyToOne(fetch = FetchType.EAGER)
    private SourceEntity source;
}
