package com.zh.service.impl;

import com.zh.dao.TypeRepository;
import com.zh.exception.NotFoundException;
import com.zh.pojo.Type;
import com.zh.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.findById(id).get();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public Type updateType(Long id, Type type) {
        Type type1 = typeRepository.findById(id).get();
        if(type1 == null){
            throw new NotFoundException("该Type的id不存在");
        }
        BeanUtils.copyProperties(type,type1);

        return typeRepository.save(type1);
    }

    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
