package com.app.neos.repository.myPage;

import com.app.neos.entity.neos.NeosPower;
import com.app.neos.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MypageNeosPowerRepository extends JpaRepository<NeosPower, Long> {
}
