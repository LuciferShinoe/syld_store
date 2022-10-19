package com.syld.store.services.color;

import com.syld.store.dto.ColorDto;
import com.syld.store.entities.Color;
import com.syld.store.repositories.ColorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ColorServiceIpm implements ColorService {

    private final ColorRepository colorRepository;
    final ModelMapper modelMapper = new ModelMapper();

    @Override
    public void save(ColorDto entity) throws Exception {
        try{
            Color color = this.modelMapper.map(entity, Color.class);
            color.setId(UUID.randomUUID().toString());
            colorRepository.save(color);

        }catch (Exception e){
                log.info(e.getMessage());
                throw e;
        }
    }

    @Override
    public void update(ColorDto entity) throws Exception {

        try{
            Color color = colorRepository.findById(entity.getId()).orElse(null);
            if(color != null){
                BeanUtils.copyProperties(entity, color);
            colorRepository.save(color);
            }else {
                throw new Exception("no color found");
            }
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }

    @Override
    public void remove(String Id) throws Exception {

        try{
            Color color = colorRepository.findById(Id).orElse(null);
            if( color != null) {
                colorRepository.delete(color);
            }else {
                throw new Exception("No color found");
            }
        }catch (Exception e){
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<ColorDto> getAll() {
        List<Color> colors = colorRepository.findAll();
        // tra ve tu repository
        return colors.stream().map(color -> modelMapper.map(color,ColorDto.class)).toList();
    }
    // goi gia tri name color va check voi color ng dung nhap kiem tra da ton tai chua
    @Override
    public ColorDto getName(String name) {
        Color color = colorRepository.findByName(name);
        if(color != null){
            return modelMapper.map(color, ColorDto.class);
        }
        return null;
    }
// goi gia tri code color de check code color da ton tai chua
    @Override
    public ColorDto getColorCode(String code) {
        Color color = colorRepository.findByColorCode(code);
        if(color != null){
            return  modelMapper.map(color, ColorDto.class);
        }
        return null;
    }

    @Override
    public ColorDto getListColor() {
        return null;
    }

}
