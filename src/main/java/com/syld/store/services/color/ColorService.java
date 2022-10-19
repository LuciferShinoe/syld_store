package com.syld.store.services.color;

import com.syld.store.dto.ColorDto;
import com.syld.store.interfaces.services.ICrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ColorService extends ICrudService<ColorDto,String> {
    List<ColorDto> getAll();

    ColorDto getName(String name);

    ColorDto getColorCode(String code_name);

    ColorDto getByNameNotSame(String name, String id);

    ColorDto getListColor();

    ColorDto getByColor_name(String toSlug);
}
