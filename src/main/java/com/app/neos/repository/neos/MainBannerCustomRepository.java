package com.app.neos.repository.neos;

import com.app.neos.domain.banner.BannerDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface MainBannerCustomRepository {


//public Slice<BannerDTO> findAllBannerpage(Pageable pageable);

public List<BannerDTO> findAllBanner();
}
