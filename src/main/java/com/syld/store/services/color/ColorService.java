package com.syld.store.services.color;

import com.syld.store.dto.ColorDto;
import com.syld.store.interfaces.services.ICrudService;

import java.util.List;

public interface ColorService extends ICrudService<ColorDto,String> {
    List<ColorDto> getAll();

    ColorDto getName(String name);

    ColorDto getColorCode(String code_name);

}
