package com.app.neos.domain.neos;


import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.neos.NeosPower;
import com.app.neos.entity.user.User;
import com.app.neos.type.point.NeosPointContent;
import com.app.neos.type.point.NeosPowerContent;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor

public class NeosPowerDTO {
    private Long neosPowerId;
    private Integer neosPowerAbility;
    private NeosPowerContent neosPowerContent;
    private UserDTO user;
    private Long userId;

    private LocalDateTime createdDate;

    public NeosPower toEntity(){
        return NeosPower.builder()
                .neosPowerAbility(neosPowerAbility)
                .neosPowerContent(neosPowerContent)
                .build();
    }

    @QueryProjection
    public NeosPowerDTO(Long neosPowerId, Integer neosPowerAbility, NeosPowerContent neosPowerContent, UserDTO user) {
        this.neosPowerId = neosPowerId;
        this.neosPowerAbility = neosPowerAbility;
        this.neosPowerContent = neosPowerContent;
        this.user = user;
    }

    @QueryProjection
    public NeosPowerDTO(Long neosPowerId, Integer neosPowerAbility, NeosPowerContent neosPowerContent, Long userId, LocalDateTime createdDate) {
        this.neosPowerId = neosPowerId;
        this.neosPowerAbility = neosPowerAbility;
        this.neosPowerContent = neosPowerContent;
        this.userId = userId;
        this.createdDate = createdDate;
    }

}
