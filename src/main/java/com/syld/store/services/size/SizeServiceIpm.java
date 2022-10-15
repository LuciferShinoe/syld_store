package com.syld.store.services.size;

import com.syld.store.dto.SizeDto;
import com.syld.store.entities.Size;
import com.syld.store.repositories.SizeRepository;
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
public class SizeServiceIpm implements SizeService {

    private final SizeRepository sizeRepository;
    final ModelMapper mapper = new ModelMapper();

    @Override
    public List<SizeDto> getAll() {
        try {
            return sizeRepository.findAll().stream().map(size -> mapper.map(size, SizeDto.class)).toList();
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public SizeDto getById(String id) {
        try {
            Optional<Size> size = sizeRepository.findById(id);
            if (size.isPresent()) {
                return mapper.map(size.get(), SizeDto.class);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    @Override
    public SizeDto getByName(String name) {
        try {
            return mapper.map(sizeRepository.getByName(name),SizeDto.class);
        }catch (Exception e){
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public void save(SizeDto entity) throws Exception {
        try {
            Size size = mapper.map(entity, Size.class);
            size.setSize_name(size.getSize_name().toUpperCase());
            size.setId(UUID.randomUUID().toString());
            sizeRepository.save(size);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public void update(SizeDto entity) throws Exception {
        try {
            Optional<Size> size = sizeRepository.findById(entity.getId());
            if (size.isPresent()) {
                Size size_ = size.get();
                BeanUtils.copyProperties(entity, size_);
                size_.setSize_name(size_.getSize_name().toUpperCase());
                sizeRepository.save(size_);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public void remove(String Id) throws Exception {
        try {
            Optional<Size> size = sizeRepository.findById(Id);
            if (size.isPresent()) {
                sizeRepository.deleteById(Id);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
